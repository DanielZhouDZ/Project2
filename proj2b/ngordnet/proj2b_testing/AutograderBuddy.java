package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.WordNetGraph;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        WordNetGraph wng = new WordNetGraph(synsetFile, hyponymFile);
        return new HyponymsHandler(wng);
    }
}