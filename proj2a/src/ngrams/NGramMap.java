package ngrams;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;
import edu.princeton.cs.algs4.In;
import java.util.Map;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    // TODO: Add any necessary static/instance variables.
    HashMap<String, TimeSeries> words = new HashMap<>();
    TimeSeries counts = new TimeSeries();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        In inWordsFile = new In(wordsFilename);
        In inCountsFile = new In(countsFilename);

        //Putting the words data in place
        String strR = null;
        TimeSeries ts = new TimeSeries();
        while(!inWordsFile.isEmpty()) {
            String str = inWordsFile.readString();
            int year = inWordsFile.readInt();
            double app = inWordsFile.readDouble();
            if(!str.equals(strR)) {
                ts = new TimeSeries();
                words.put(str, ts);
            }
            ts.put(year, app);
            strR = str;
            //last field is not needed throw it away
            inWordsFile.readInt();
        }

        //Putting the counts data in place
        while(!inCountsFile.isEmpty()) {
            String line = inCountsFile.readLine();
            String[] splitLine = line.split(",");
            int year = Integer.parseInt(splitLine[0]);
            double app = Double.parseDouble(splitLine[1]);
            counts.put(year, app);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = words.get(word);
        if (ts == null) return new TimeSeries();
        return new TimeSeries(ts, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts = words.get(word);
        if (ts == null) return new TimeSeries();
        return new TimeSeries(ts);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(counts);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries countHistory = countHistory(word, startYear, endYear);
        TimeSeries totalCountHistory = totalCountHistory();
        TimeSeries ts = new TimeSeries();
        for(Map.Entry<Integer, Double> entry : countHistory.entrySet()) {
            int entryKey = entry.getKey();
            ts.put(entryKey, entry.getValue() / totalCountHistory.get(entryKey));
        }
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries countHistory = countHistory(word);
        TimeSeries totalCountHistory = totalCountHistory();
        TimeSeries ts = new TimeSeries();
        for(Map.Entry<Integer, Double> entry : countHistory.entrySet()) {
            int entryKey = entry.getKey();
            ts.put(entryKey, entry.getValue() / totalCountHistory.get(entryKey));
        }
        return ts;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        for(String word : words) {
            ts = ts.plus(weightHistory(word, startYear, endYear));
        }
        return ts;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        for(String word : words) {
            ts = ts.plus(weightHistory(word));
        }
        return ts;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
