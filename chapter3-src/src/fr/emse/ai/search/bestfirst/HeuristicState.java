package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.simple.SimpleState;
import java.util.HashMap;
import java.util.Map;

public class HeuristicState extends SimpleState {
    private static final Map<String, Integer> heuristics = new HashMap<>();
    
    static {
        // Initialize heuristic values for each node
        // These values represent estimated cost to goal
        heuristics.put("a", 4);  // Starting node
        heuristics.put("b", 2);  // Middle node
        heuristics.put("c", 4);  // Alternative path
        heuristics.put("d", 0);  // Goal node (h=0)
        heuristics.put("e", 3);  // Alternative path
        heuristics.put("h", 5);  // Dead end
    }
    
    public HeuristicState(String value) {
        super(value.toLowerCase());  // Store values in lowercase for consistency
    }
    
    public int getHValue() {
        return heuristics.getOrDefault(value.toLowerCase(), Integer.MAX_VALUE);
    }
    
    @Override
    public String toString() {
        return value + "(h=" + getHValue() + ")";
    }
} 