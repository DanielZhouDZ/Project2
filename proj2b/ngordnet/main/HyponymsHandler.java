package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNetGraph wng;
    private WordGraph wg;
    public HyponymsHandler(WordNetGraph wng) {
        this.wng = wng;
        this.wg = wng.getWordGraph();
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        Set<Integer> nodes = wg.getRoots();
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
        return finalOutput.toString();
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
