package fr.emse.ai.util;

import java.util.ArrayList;

public class MiniMaxAlgoTest {
    public static void main(String[] args) {
        // Create the root node (MAX node)
        SimpleTwoPlyGameTree tree1 = new SimpleTwoPlyGameTree(Integer.MAX_VALUE, true);
        
        // Create the first subtree
        ArrayList<SimpleTwoPlyGameTree> sublist1 = new ArrayList<SimpleTwoPlyGameTree>();
        sublist1.add(new SimpleTwoPlyGameTree(3, true));
        sublist1.add(new SimpleTwoPlyGameTree(12, true));
        sublist1.add(new SimpleTwoPlyGameTree(8, true));
        SimpleTwoPlyGameTree subtree1 = new SimpleTwoPlyGameTree(Integer.MIN_VALUE, false, sublist1);
        
        // Create the second subtree
        ArrayList<SimpleTwoPlyGameTree> sublist2 = new ArrayList<SimpleTwoPlyGameTree>();
        sublist2.add(new SimpleTwoPlyGameTree(2, true));
        sublist2.add(new SimpleTwoPlyGameTree(4, true));
        sublist2.add(new SimpleTwoPlyGameTree(6, true));
        SimpleTwoPlyGameTree subtree2 = new SimpleTwoPlyGameTree(Integer.MIN_VALUE, false, sublist2);
        
        // Create the third subtree
        ArrayList<SimpleTwoPlyGameTree> sublist3 = new ArrayList<SimpleTwoPlyGameTree>();
        sublist3.add(new SimpleTwoPlyGameTree(14, true));
        sublist3.add(new SimpleTwoPlyGameTree(5, true));
        sublist3.add(new SimpleTwoPlyGameTree(2, true));
        SimpleTwoPlyGameTree subtree3 = new SimpleTwoPlyGameTree(Integer.MIN_VALUE, false, sublist3);
        
        // Add subtrees to the root
        tree1.addChild(subtree1);
        tree1.addChild(subtree2);
        tree1.addChild(subtree3);
        
        // Run minimax algorithm
        int minimaxValue = MiniMaxAlgo.minimax(tree1);
        System.out.println("Minimax value of the tree: " + minimaxValue);
        
        // Print the values at each level to verify correctness
        System.out.println("\nRoot node (MAX) value: " + tree1.getValue());
        System.out.println("\nMIN level values:");
        for (SimpleTwoPlyGameTree child : tree1.getChildren()) {
            System.out.println("Node value: " + child.getValue());
        }
    }
} 