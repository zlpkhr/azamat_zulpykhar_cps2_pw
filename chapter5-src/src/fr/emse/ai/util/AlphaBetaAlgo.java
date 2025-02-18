package fr.emse.ai.util;

public class AlphaBetaAlgo {
    private int expandedNodes = 0;  // Counter for explored nodes
    
    /**
     * Returns the minimax value using alpha-beta pruning for a given game tree
     * @param tree The game tree to evaluate
     * @return The minimax value of the tree
     */
    public int alphabeta(SimpleTwoPlyGameTree tree) {
        expandedNodes = 0;
        return alphabeta(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * Helper method that implements alpha-beta pruning
     * @param tree The current node in the game tree
     * @param alpha The best value that the maximizer currently can guarantee
     * @param beta The best value that the minimizer currently can guarantee
     * @return The minimax value of the node
     */
    private int alphabeta(SimpleTwoPlyGameTree tree, int alpha, int beta) {
        expandedNodes += 2;  // Count both MAX and MIN expansions for this state
        
        if (tree.isLeaf()) {
            return tree.getValue();
        }
        
        if (tree.isMax()) {
            int value = Integer.MIN_VALUE;
            for (SimpleTwoPlyGameTree child : tree.getChildren()) {
                value = Math.max(value, alphabeta(child, alpha, beta));
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    break; // Beta cut-off
                }
            }
            tree.setValue(value);
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (SimpleTwoPlyGameTree child : tree.getChildren()) {
                value = Math.min(value, alphabeta(child, alpha, beta));
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    break; // Alpha cut-off
                }
            }
            tree.setValue(value);
            return value;
        }
    }
    
    /**
     * Returns the number of nodes expanded during the last search
     */
    public int getExpandedNodes() {
        return expandedNodes;
    }
} 