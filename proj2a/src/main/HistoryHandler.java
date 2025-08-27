package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;
import java.util.List;

import java.util.ArrayList;

public class HistoryHandler extends NgordnetQueryHandler {
    NGramMap nMap;
    public HistoryHandler(NGramMap map) {
        nMap = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        int startYear = q.startYear();
        int endYear = q.endYear();
        for(String word : words) {
            lts.add(nMap.weightHistory(word, startYear, endYear));
            labels.add(word);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        return Plotter.encodeChartAsString(chart);
    }
}
