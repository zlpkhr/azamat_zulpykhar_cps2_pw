package fr.emse.ai.util;

public class MiniMaxAlgo {
    private static int expandedNodes = 0;  // Counter for explored nodes
    
    /**
     * Returns the minimax value for a given game tree
     * @param tree The game tree to evaluate
     * @return The minimax value of the tree
     */
    public static int minimax(SimpleTwoPlyGameTree tree) {
        expandedNodes = 0;  // Reset counter before starting
        return minimaxHelper(tree);
    }
    
    /**
     * Helper method that implements the minimax algorithm
     * @param tree The game tree to evaluate
     * @return The minimax value of the tree
     */
    private static int minimaxHelper(SimpleTwoPlyGameTree tree) {
        expandedNodes += 2;  // Count both MAX and MIN expansions for this state
        
        if (tree.isLeaf()) {
            return tree.getValue();
        }
        
        if (tree.isMax()) {
            int maxValue = Integer.MIN_VALUE;
            for (SimpleTwoPlyGameTree child : tree.getChildren()) {
                maxValue = Math.max(maxValue, minimaxHelper(child));
            }
            tree.setValue(maxValue);
            return maxValue;
        } else {
            int minValue = Integer.MAX_VALUE;
            for (SimpleTwoPlyGameTree child : tree.getChildren()) {
                minValue = Math.min(minValue, minimaxHelper(child));
            }
            tree.setValue(minValue);
            return minValue;
        }
    }
    
    /**
     * Returns the number of nodes expanded during the last search
     */
    public static int getExpandedNodes() {
        return expandedNodes;
    }
} 