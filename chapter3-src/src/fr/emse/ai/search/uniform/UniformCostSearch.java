package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;
import java.util.*;

public class UniformCostSearch extends AbstractTreeSearch {
    
    @Override
    public Collection<Node> initFrontier() {
        // Initialize frontier as a priority queue sorted by path cost
        return new PriorityQueue<>((n1, n2) -> 
            Double.compare(n1.getPathCost(), n2.getPathCost()));
    }

    @Override
    public Node chooseLeafNode(Collection<Node> frontier, Problem problem) {
        // Cast frontier to PriorityQueue and return/remove minimum cost node
        PriorityQueue<Node> queue = (PriorityQueue<Node>) frontier;
        return queue.poll();
    }
} 