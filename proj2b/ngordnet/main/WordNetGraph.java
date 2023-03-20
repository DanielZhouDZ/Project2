package ngordnet.main;

import java.util.Collection;
import edu.princeton.cs.algs4.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class WordNetGraph {
    private WordGraph wg;
    public WordNetGraph(String synsets, String hyponyms) {
        this.wg = new WordGraph();
        In syns = new In(synsets);
        while (syns.hasNextLine()) {
            String[] line = syns.readLine().split(",");
            if (line.length > 0) {
                wg.addNode(line[1].split(" "));
            }
        }
        In hypo = new In(hyponyms);
        while (hypo.hasNextLine()) {
            String[] line = syns.readLine().split(",");
            if (line.length > 0) {
                Node node = wg.getNode(Integer.parseInt(line[0]));
                for (int i = 1; i < line.length; i++) {
                    node.addChild(wg.getNode(Integer.parseInt(line[i])));
                }
            }
        }
    }
}
