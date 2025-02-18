package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;
import java.util.*;

public class BestFirstSearch extends AbstractTreeSearch {
    private Set<String> visited = new HashSet<>();
    
    @Override
    public Collection<Node> initFrontier() {
        // Initialize frontier as a priority queue sorted by heuristic value
        return new PriorityQueue<>((n1, n2) -> {
            int h1 = ((HeuristicState) n1.getState()).getHValue();
            int h2 = ((HeuristicState) n2.getState()).getHValue();
            return Integer.compare(h1, h2);
        });
    }

    @Override
    public Node chooseLeafNode(Collection<Node> frontier, Problem problem) {
        PriorityQueue<Node> queue = (PriorityQueue<Node>) frontier;
        Node node = queue.poll();
        
        // Skip nodes we've already visited
        while (node != null && visited.contains(node.getState().toString())) {
            node = queue.poll();
        }
        
        if (node != null) {
            visited.add(node.getState().toString());
        }
        
        return node;
    }
} 