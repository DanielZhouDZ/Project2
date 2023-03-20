package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class HyponymsHandler extends NgordnetQueryHandler{
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
        Set<String> output = new HashSet<>();
        for (int i : nodes) {
            output.addAll(handleWords(i, words));
        }
        List<String> outputWords = new ArrayList<>(output);
        Collections.sort(outputWords);
        return outputWords.toString();
    }
    public List<String> handleWords(int index, List<String> words) {
        if (words.size() == 1 && wg.getWords(index).contains(words.get(0))) {
            return getAll(index);
        }
        List<String> newWords = new ArrayList<>();
        for (String w : words) {
            if (!wg.getWords(index).contains(w)) {
                newWords.add(w);
            }
        }
        if (newWords.size() == 0) {
            return getAll(index);
        }
        List<String> output = new ArrayList<>();
        for (int i : wg.getEdges(index)) {
            output.addAll(handleWords(i, newWords));
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