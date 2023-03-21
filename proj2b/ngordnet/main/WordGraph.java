package ngordnet.main;

import java.util.*;

public class WordGraph {
    private int size;
    private List<List<String>> words;
    private List<List<Integer>> adjacencyList;
    private Set<Integer> roots;
    public WordGraph() {
        words = new ArrayList<>();
        adjacencyList = new ArrayList<>();
        roots = new HashSet<>();
    }
    public void addNode(String[] words) {
        roots.add(size);
        this.words.add(new ArrayList<>(Arrays.asList(words)));
        adjacencyList.add(new ArrayList<>());
        size += 1;
    }
    public void addEdge(String[] edges) {
        int parent = Integer.parseInt(edges[0]);
        for (int i = 1; i < edges.length; i++) {
            adjacencyList.get(parent).add(Integer.parseInt(edges[i]));
            roots.remove(Integer.parseInt(edges[i]));
        }
    }
    public List<String> getWords(int index) {
        return words.get(index);
    }
    public List<List<String>> getWords() {
        return words;
    }
    public List<Integer> getEdges(int index) {
        return adjacencyList.get(index);
    }
    public List<List<Integer>> getEdges() {
        return adjacencyList;
    }
    public Set<Integer> getRoots() {
        return roots;
    }
    public int size() {
        return size;
    }
}
