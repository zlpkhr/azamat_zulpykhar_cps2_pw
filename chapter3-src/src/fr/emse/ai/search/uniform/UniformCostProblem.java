package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.Problem;
import java.util.*;

public class UniformCostProblem implements Problem {
    private final Map<String, Map<String, Integer>> graph;

    public UniformCostProblem() {
        graph = new HashMap<>();
        
        // Initialize the graph with costs
        // a --2--> b --1--> d
        // |        |
        // 3        2
        // |        |
        // v        v
        // c --2--> e --1--> h
        
        Map<String, Integer> aEdges = new HashMap<>();
        aEdges.put("b", 2);
        aEdges.put("c", 3);
        graph.put("a", aEdges);

        Map<String, Integer> bEdges = new HashMap<>();
        bEdges.put("d", 1);
        bEdges.put("e", 2);
        graph.put("b", bEdges);

        Map<String, Integer> cEdges = new HashMap<>();
        cEdges.put("e", 2);
        graph.put("c", cEdges);

        Map<String, Integer> eEdges = new HashMap<>();
        eEdges.put("h", 1);
        graph.put("e", eEdges);

        // Empty maps for terminal nodes
        graph.put("d", new HashMap<>());
        graph.put("h", new HashMap<>());
    }

    @Override
    public Object getInitialState() {
        return "a";
    }

    @Override
    public boolean isGoal(Object state) {
        return "d".equals(state.toString());
    }

    @Override
    public List<Object> getActions(Object state) {
        Map<String, Integer> edges = graph.get(state.toString());
        return edges != null ? new ArrayList<>(edges.keySet()) : new ArrayList<>();
    }

    @Override
    public Object getNextState(Object state, Object action) {
        return action.toString();
    }

    @Override
    public double getStepCost(Object state, Object action, Object nextState) {
        Map<String, Integer> edges = graph.get(state.toString());
        return edges != null ? edges.getOrDefault(action.toString(), Integer.MAX_VALUE) : Integer.MAX_VALUE;
    }
} 