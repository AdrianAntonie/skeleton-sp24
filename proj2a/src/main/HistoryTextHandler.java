package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap nMap;
    public HistoryTextHandler(NGramMap map) {
        nMap = map;
    }
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        for(String word : words) {
            response += word + ": " + nMap.weightHistory(word, startYear, endYear).toString();
            response += "\n";
        }
        return response;
    }
}
