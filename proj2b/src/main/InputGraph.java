package main;

import java.util.*;
import java.util.Map.Entry;

import browser.NgordnetQuery;
import browser.NgordnetQueryType;
import edu.princeton.cs.algs4.In;

public class InputGraph {
    private HashMap<Integer, List<String>> idToWordsMap = new HashMap<>();
    private HashMap<String, List<Integer>> wordToIdsMap = new HashMap<>();
    private HashMap<Integer, Set<Integer>> hyponymsMap = new HashMap<>();
    private HashMap<Integer, Set<Integer>> reverseHyponymsMap = new HashMap<>();
    private HashMap<Integer, Set<Integer>> helperMap = new HashMap<>();

    public InputGraph(String synsetsFilename, String hyponymsFilename) {
        In synsetsFile = new In(synsetsFilename);
        In hyponymsFile = new In(hyponymsFilename);

        //Handling synsets input file
        while(!synsetsFile.isEmpty()) {
            String line = synsetsFile.readLine();
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            List<String> wordTokens = new ArrayList<>(List.of(tokens[1].split(" ")));

            //Put the key -> value (id -> words) pairs in the map
            idToWordsMap.put(id, wordTokens);

            //Put the key -> value (word -> ids) pairs in the map
            for(String word : wordTokens) {
                wordToIdsMap
                        .computeIfAbsent(word, w -> new ArrayList<>())
                        .add(id);
            }
        }

        //Handling hyponyms input file

        //First pass add only immediate descendants directly connected to the nodes
        while(!hyponymsFile.isEmpty()) {
            String line = hyponymsFile.readLine();
            String[] tokens = line.split(",");
            int node = Integer.parseInt(tokens[0]);
            helperMap.computeIfAbsent(node, w -> new HashSet<>());
            Set<Integer> valueList = helperMap.get(node);
            for(int i = 1; i < tokens.length; i++) {
                valueList.add(Integer.parseInt(tokens[i]));
            }
        }

        //Second pass pre-compute all descendants
        for(Integer key : helperMap.keySet()) {
            hyponymsMap.put(key, computeGraph(key));
        }

        //Reverse Graph
        for(int oldKey : hyponymsMap.keySet()) {
            for(int child : hyponymsMap.get(oldKey)) {
                reverseHyponymsMap
                        .computeIfAbsent(child, w -> new HashSet<>())
                        .add(oldKey);
            }
        }

    }
    /**
    *Go through all entries from helperMap
    *If the key is in hyponymsMap return value and put empty Set in hyponymsMap
    *Else for each element in value repeat with it as key
     */
    private Set<Integer> computeGraph(Integer key) {
        // Already computed
        Set<Integer> result = new HashSet<>();
        result.add(key);
        if (hyponymsMap.containsKey(key)) {
            return hyponymsMap.get(key);
        }

        // Leaf
        if (!helperMap.containsKey(key)) {
            return result;
        }

        // Has children
        for (Integer child : helperMap.get(key)) {
            result.addAll(computeGraph(child));
        }

        hyponymsMap.put(key, result);
        return result;
    }

    /**
     * Handles the query and returns Set answer
     */
    public Set<Integer> handle(NgordnetQuery q) {
        List<String> words = q.words();
        if(words.isEmpty()) {
            return Set.of();
        }
        Set<Integer> responseSet;
        if(q.ngordnetQueryType() == NgordnetQueryType.HYPONYMS) {
            responseSet = hyponyms(words);
        } else {
            responseSet = hypernyms(words);
        }
        return responseSet;
    }

    /**
     * Returns a Set of all common hyponyms of the given words
     */
    private Set<Integer> hyponyms(List<String> words) {
        /*Set<Integer> ids = returnIds(words);
        Set<Integer> returnSet = hyponymsMap.get(ids.iterator().next());
        for(int id : ids) {
            Set<Integer> newSet = hyponymsMap.get(id);
            Set<Integer> helperSet = new HashSet<>();
            for(int element : returnSet) {
                if(newSet.contains(element)) {
                    helperSet.add(element);
                }
            }
            returnSet = helperSet;
            if(returnSet.isEmpty()) {
                return returnSet;
            }
        }
        return returnSet;*/
        Set<Integer> ids = returnIds(words);
        Iterator<Integer> iter = ids.iterator();
        if (!iter.hasNext()) return Set.of();

        Set<Integer> intersection = new HashSet<>(hyponymsMap.get(iter.next()));

        while (iter.hasNext()) {
            intersection.retainAll(hyponymsMap.get(iter.next()));
            if (intersection.isEmpty()) break;
        }

        return intersection;
    }

    /**
     * Returns a list of all common hypernyms of the given words
     */
    private Set<Integer> hypernyms(List<String> words) {
        Set<Integer> ids = returnIds(words);
        Set<Integer> returnSet = reverseHyponymsMap.get(ids.iterator().next());
        for(int id : ids) {
            Set<Integer> newSet = reverseHyponymsMap.get(id);
            Set<Integer> helperSet = new HashSet<>();
            for(int element : returnSet) {
                if(newSet.contains(element)) {
                    helperSet.add(element);
                }
            }
            returnSet = helperSet;
            if(returnSet.isEmpty()) {
                return returnSet;
            }
        }
        return returnSet;
    }

    /**
     * Returns a set of ids coresponding to the list of words given as input
     */
    private Set<Integer> returnIds(List<String> words) {
        Set<Integer> returnIds = new HashSet<>();
        for(String word : words) {
            returnIds.addAll(wordToIdsMap.get(word));
        }
        return returnIds;
    }


}
