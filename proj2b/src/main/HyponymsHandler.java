package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.Set;

public class HyponymsHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        InputGraph graph = new InputGraph("./data/wordnet/synsets.txt", "./data/wordnet/hyponyms.txt");
        Set<Integer> response = graph.handle(q);
        return response.toString();
        //return "Hello!";
    }
}
