package fr.emse.ai.util;

import java.util.ArrayList;

public class AlphaBetaAlgoTest {
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
        
        // Test MiniMax algorithm
        System.out.println("Testing MiniMax algorithm:");
        int minimaxValue = MiniMaxAlgo.minimax(tree1);
        System.out.println("Minimax value of the tree: " + minimaxValue);
        System.out.println("\nRoot node (MAX) value: " + tree1.getValue());
        System.out.println("\nMIN level values:");
        for (SimpleTwoPlyGameTree child : tree1.getChildren()) {
            System.out.println("Node value: " + child.getValue());
        }
        
        // Create a new identical tree for Alpha-Beta (since the previous one has been modified)
        SimpleTwoPlyGameTree tree2 = new SimpleTwoPlyGameTree(Integer.MAX_VALUE, true);
        
        // Recreate subtrees
        ArrayList<SimpleTwoPlyGameTree> sublist1_ab = new ArrayList<SimpleTwoPlyGameTree>();
        sublist1_ab.add(new SimpleTwoPlyGameTree(3, true));
        sublist1_ab.add(new SimpleTwoPlyGameTree(12, true));
        sublist1_ab.add(new SimpleTwoPlyGameTree(8, true));
        SimpleTwoPlyGameTree subtree1_ab = new SimpleTwoPlyGameTree(Integer.MIN_VALUE, false, sublist1_ab);
        
        ArrayList<SimpleTwoPlyGameTree> sublist2_ab = new ArrayList<SimpleTwoPlyGameTree>();
        sublist2_ab.add(new SimpleTwoPlyGameTree(2, true));
        sublist2_ab.add(new SimpleTwoPlyGameTree(4, true));
        sublist2_ab.add(new SimpleTwoPlyGameTree(6, true));
        SimpleTwoPlyGameTree subtree2_ab = new SimpleTwoPlyGameTree(Integer.MIN_VALUE, false, sublist2_ab);
        
        ArrayList<SimpleTwoPlyGameTree> sublist3_ab = new ArrayList<SimpleTwoPlyGameTree>();
        sublist3_ab.add(new SimpleTwoPlyGameTree(14, true));
        sublist3_ab.add(new SimpleTwoPlyGameTree(5, true));
        sublist3_ab.add(new SimpleTwoPlyGameTree(2, true));
        SimpleTwoPlyGameTree subtree3_ab = new SimpleTwoPlyGameTree(Integer.MIN_VALUE, false, sublist3_ab);
        
        tree2.addChild(subtree1_ab);
        tree2.addChild(subtree2_ab);
        tree2.addChild(subtree3_ab);
        
        // Test Alpha-Beta algorithm
        System.out.println("\n\nTesting Alpha-Beta algorithm:");
        AlphaBetaAlgo alphaBeta = new AlphaBetaAlgo();
        int alphabetaValue = alphaBeta.alphabeta(tree2);
        System.out.println("Alpha-Beta value of the tree: " + alphabetaValue);
        System.out.println("Number of nodes expanded: " + alphaBeta.getExpandedNodes());
        System.out.println("\nRoot node (MAX) value: " + tree2.getValue());
        System.out.println("\nMIN level values:");
        for (SimpleTwoPlyGameTree child : tree2.getChildren()) {
            System.out.println("Node value: " + child.getValue());
        }
    }
} 