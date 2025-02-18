package fr.emse.ai.util;

public class MiniMaxAlgo {
    
    /**
     * Returns the minimax value for a given game tree
     * @param tree The game tree to evaluate
     * @return The minimax value of the tree
     */
    public static int minimax(SimpleTwoPlyGameTree tree) {
        if (tree.isLeaf()) {
            return tree.getValue();
        }
        
        if (tree.isMax()) {
            int maxValue = Integer.MIN_VALUE;
            for (SimpleTwoPlyGameTree child : tree.getChildren()) {
                maxValue = Math.max(maxValue, minimax(child));
            }
            tree.setValue(maxValue);
            return maxValue;
        } else {
            int minValue = Integer.MAX_VALUE;
            for (SimpleTwoPlyGameTree child : tree.getChildren()) {
                minValue = Math.min(minValue, minimax(child));
            }
            tree.setValue(minValue);
            return minValue;
        }
    }
} 