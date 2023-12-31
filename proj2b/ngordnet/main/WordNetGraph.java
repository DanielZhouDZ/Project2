package ngordnet.main;

import edu.princeton.cs.algs4.In;

public class WordNetGraph {
    private WordGraph wg;
    public WordNetGraph(String synsets, String hyponyms) {
        this.wg = new WordGraph();
        In syns = new In(synsets);
        while (syns.hasNextLine()) {
            String line1 = syns.readLine();
            if (line1 != null) {
                String[] line = line1.split(",");
                wg.addNode(line[1].split(" "));
            }
        }
        In hypo = new In(hyponyms);
        while (hypo.hasNextLine()) {
            String line1 = hypo.readLine();
            if (line1 != null) {
                String[] line = line1.split(",");
                wg.addEdge(line);
            }
        }
    }

    public WordGraph getWordGraph() {
        return this.wg;
    }
}
