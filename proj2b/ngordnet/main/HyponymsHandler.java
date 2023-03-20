package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler{
    private WordNetGraph wng;
    private WordGraph wg;
    private List<Node> hyponymNodes;
    private List<String> hyponyms;
    public HyponymsHandler(WordNetGraph wng) {
        this.wng = wng;
        this.wg = wng.getWordGraph();
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String word = words.get(0);
        hyponyms = new ArrayList<>();
        List<Node> nodesWithWord = new ArrayList<>();
        hyponymNodes = new ArrayList<>();

        for (int i = 0; i < wg.size(); i++) {
            if (wg.getNode(i).getWords().contains(word)) {
                nodesWithWord.add(wg.getNode(i));
            }
        }

        for (Node node : nodesWithWord) {
            addNodes(node);
        }

        for (Node node : hyponymNodes) {
            for (String s : node.getWords()) {
                if (!hyponyms.contains(s)) {
                    hyponyms.add(s);
                }
            }
        }

        return hyponyms.toString();
    }

    public void addNodes(Node node) {
        hyponymNodes.add(node);
        if (node.getChildren().size() != 0) {
            for (Node n : node.getChildren()) {
                addNodes(n);
            }
        }
    }
}
