package ngordnet.main;

import java.util.List;
import java.util.ArrayList;

public class WordGraph {
    private List<Node> nodes;
    public WordGraph(){
        nodes = new ArrayList<>();
    }
    public void addNode(String[] words) {
        nodes.add(new Node(words));
    }
    public Node getNode(int index){
        return nodes.get(index);
    }
}
