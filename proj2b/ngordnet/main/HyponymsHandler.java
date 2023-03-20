package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;

public class HyponymsHandler extends NgordnetQueryHandler{
    private WordNetGraph wng;
    public HyponymsHandler(WordNetGraph wng) {
        this.wng = wng;
    }
    @Override
    public String handle(NgordnetQuery q) {
        return "Hello";
    }
}
