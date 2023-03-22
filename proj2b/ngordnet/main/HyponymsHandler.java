package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import org.antlr.v4.runtime.tree.Tree;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNetGraph wng;
    private WordGraph wg;
    private NGramMap ngm;

    public HyponymsHandler(WordNetGraph wng, NGramMap ngm) {
        this.wng = wng;
        this.wg = wng.getWordGraph();
        this.ngm = ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        Set<Integer> nodes = wg.getRoots();

        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        List<Set<String>> outputs = new ArrayList<>();
        Set<String> outputWords;
        for (String word : words) {
            outputWords = new HashSet<>();
            outputs.add(outputWords);
            for (int node : nodes) {
                outputWords.addAll(handleWords(node, word));
            }
        }
        Set<String> output = outputs.get(0);
        for (Set<String> sets : outputs) {
            output.retainAll(sets);
        }
        List<String> finalOutput = new ArrayList<>(output);
        Collections.sort(finalOutput);

        if (k != 0) {
            List<String> kOutput = new ArrayList<>();
            PriorityQueue<Pairing> pq = new PriorityQueue<Pairing>();

            for (String s : finalOutput) {
                double count = 0;
                TimeSeries wordHistory = ngm.countHistory(s, startYear, endYear);
                for (int i : wordHistory.keySet()) {
                    count = count + wordHistory.get(i);

                }
                if (count > 0) {
                    pq.add(new Pairing(s, count));
                }

            }
            for (int i = 0; i < k; i++) {
                kOutput.add(pq.poll().word);
            }

            Collections.sort(kOutput);
            return kOutput.toString();
        }

        return finalOutput.toString();
    }

    private class Pairing implements Comparable<Pairing> {
        private String word;
        private Double count;
        public Pairing(String word, double count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Pairing o) {
            if (this.count > o.count) {
                return -1;
            } else if (this.count < o.count) {
                return 1;
            }
            return 0;
        }
    }

    public List<String> handleWords(int index, String word) {
        if (wg.getWords(index).contains(word)) {
            return getAll(index);
        }
        List<String> output = new ArrayList<>();
        for (int i : wg.getEdges(index)) {
            output.addAll(handleWords(i, word));
        }
        return output;
    }
    public List<String> getAll(int index) {
        List<String> output = new ArrayList<>(wg.getWords(index));
        for (int i : wg.getEdges(index)) {
            output.addAll(getAll(i));
        }
        return output;
    }
}
