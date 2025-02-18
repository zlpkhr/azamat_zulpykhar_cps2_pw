package fr.emse.ai.search.bottle;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;
import java.util.*;

public class AStarSearch extends AbstractTreeSearch {
    private Set<String> visited = new HashSet<>();
    
    @Override
    public Collection<Node> initFrontier() {
        // Initialize frontier as a priority queue sorted by f(n) = g(n) + h(n)
        // where g(n) is the path cost and h(n) is the heuristic value
        return new PriorityQueue<>((n1, n2) -> {
            double f1 = n1.getPathCost() + ((Bottles) n1.getState()).getHValue();
            double f2 = n2.getPathCost() + ((Bottles) n2.getState()).getHValue();
            return Double.compare(f1, f2);
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