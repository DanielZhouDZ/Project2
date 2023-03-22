package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.WordNetGraph;
import ngordnet.ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        WordNetGraph wng = new WordNetGraph(synsetFile, hyponymFile);
        NGramMap ngm = new NGramMap(wordFile, countFile); //added
        return new HyponymsHandler(wng, ngm);
    }
}