package main;

import java.util.*;
import java.util.Map.Entry;

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
                //valueList.addAll(hyponymsMap.get(Integer.parseInt(tokens[i])));
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
        if (hyponymsMap.containsKey(key)) {
            return hyponymsMap.get(key);
        }

        // Leaf
        if (!helperMap.containsKey(key)) {
            Set<Integer> leafSet = new HashSet<>();
            hyponymsMap.put(key, leafSet);
            return leafSet;
        }

        // Has children
        Set<Integer> result = new HashSet<>();
        for (Integer child : helperMap.get(key)) {
            result.addAll(computeGraph(child));
        }

        hyponymsMap.put(key, result);
        return result;
    }
}
