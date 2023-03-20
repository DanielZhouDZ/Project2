package ngordnet.main;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Node {
    private Set<String> words;
    private List<Node> children;
    public Node(String[] words) {
        this.words = new HashSet<>();
        this.children = new ArrayList<>();
        Collections.addAll(this.words, words);
    }
    public void addChild(Node child) {
        children.add(child);
    }
    public List<Node> getChildren() {
        return children;
    }
    public Set<String> getWords() {
        return words;
    }
    public Boolean contains(String word) {
        return words.contains(word);
    }
}
