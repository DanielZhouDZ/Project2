package ngordnet.main;

import java.util.List;
import java.util.ArrayList;

public class WordGraph {
    private int size;
    private List<Node> nodes;
    public WordGraph(){
        nodes = new ArrayList<>();
    }
    public void addNode(String[] words) {
        nodes.add(new Node(words));
        size++;
    }
    public Node getNode(int index){
        return nodes.get(index);
    }

    public int size() {
        return size;
    }
}
