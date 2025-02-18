package fr.emse.ai.search.astar;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;
import fr.emse.ai.search.bestfirst.HeuristicState;
import java.util.*;

public class AStarSearch extends AbstractTreeSearch {
    private Map<String, Node> closed = new HashMap<>();  // Closed list with best known paths
    private Map<String, Node> open = new HashMap<>();    // Open list (frontier) with best known paths
    
    @Override
    public Collection<Node> initFrontier() {
        // Initialize frontier as a priority queue sorted by f(n) = g(n) + h(n)
        return new PriorityQueue<>((n1, n2) -> {
            double f1 = f(n1);
            double f2 = f(n2);
            return Double.compare(f1, f2);
        });
    }

    private double f(Node n) {
        // f(n) = g(n) + h(n)
        return n.getPathCost() + ((HeuristicState) n.getState()).getHValue();
    }

    @Override
    public Node chooseLeafNode(Collection<Node> frontier, Problem problem) {
        PriorityQueue<Node> queue = (PriorityQueue<Node>) frontier;
        Node node = queue.poll();
        
        if (node != null) {
            // Add the node to closed list
            closed.put(node.getState().toString(), node);
            // Remove from open list since we're expanding it
            open.remove(node.getState().toString());
        }
        
        return node;
    }

    @Override
    public Collection<Node> expand(Node node, Problem problem) {
        Collection<Node> successors = new ArrayList<>();
        Collection<Object> actions = problem.getActions(node.getState());
        
        for (Object action : actions) {
            Object nextState = problem.getNextState(node.getState(), action);
            Node successor = new Node(nextState, node, action, 
                                   problem.getStepCost(node.getState(), action, nextState));
            
            String stateStr = successor.getState().toString();
            
            // Check if this state is in closed list
            Node closedNode = closed.get(stateStr);
            if (closedNode != null) {
                // If new path is better, remove from closed and add to successors
                if (f(successor) < f(closedNode)) {
                    closed.remove(stateStr);
                    successors.add(successor);
                    open.put(stateStr, successor);
                }
                continue;
            }
            
            // Check if this state is already in open list
            Node openNode = open.get(stateStr);
            if (openNode != null) {
                // If new path is better, update open list and add to successors
                if (f(successor) < f(openNode)) {
                    // Note: The frontier will be updated in the solve method when adding successors
                    successors.add(successor);
                    open.put(stateStr, successor);
                }
                continue;
            }
            
            // New state, add to successors and open list
            successors.add(successor);
            open.put(stateStr, successor);
        }
        
        return successors;
    }
} 