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
        List<Node> nodes = wg.getNodes();
        Set<String> output = new HashSet<>();
        for (Node n : nodes) {
            if (!n.visited) {
                output.addAll(addNodes(n, words));
            }
        }
        List<String> outputWords = new ArrayList<>(output);
        Collections.sort(outputWords);
        return outputWords.toString();
    }
    public Set<String> addNodes(Node n, List<String> words) {
        if (words.size() == 1 && n.contains(words.get(0))) {
            return addNodes(n);
        }
        List<String> newWords = new ArrayList<>(words);
        Set<String> output = new HashSet<>();
        for (String word : words) {
            if (n.contains(word)) {
                newWords.remove(word);
            }
        }
        for (Node node : n.getChildren()) {
            output.addAll(addNodes(node, newWords));
        }
        return output;
    }
    public Set<String> addNodes(Node node) {
        Set<String> output = new HashSet<>(node.getWords());
        for (Node n : node.getChildren()) {
            output.addAll(addNodes(n));
        }
        return output;
    }
}