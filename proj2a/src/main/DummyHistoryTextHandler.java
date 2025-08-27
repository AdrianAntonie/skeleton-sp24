package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class DummyHistoryTextHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        /*
        String response = "You entered the following info into the browser:\n";
        response += "Words: " + q.words() + "\n";
        response += "Start Year: " + q.startYear() + "\n";
        response += "End Year: " + q.endYear() + "\n";*/

        String response = "";
        NGramMap nMap = new NGramMap("top_14377_words.csv", "total_counts.csv");
        for(String word : words) {
            response += word + ": {" + nMap.weightHistory(word, startYear, endYear).toString();
        }
        return response;
    }
}
