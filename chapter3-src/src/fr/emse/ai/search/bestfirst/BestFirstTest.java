package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.core.Node;

public class BestFirstTest {
    public static void main(String[] args) {
        System.out.println("Testing Best First Search...");
        
        // Create a problem with the following graph:
        // a(h=4) --2--> b(h=2) --1--> d(h=0)
        // |                |
        // 3                2
        // |                |
        // v                v
        // c(h=4) --2--> e(h=3) --1--> h(h=5)
        BestFirstProblem problem = new BestFirstProblem();
        BestFirstSearch search = new BestFirstSearch();
        
        Node solution = search.solve(problem);
        
        // Verify solution exists
        if (solution == null) {
            System.out.println("ERROR: Solution should not be null");
            return;
        }
        
        // Verify the solution state has h=0
        HeuristicState goalState = (HeuristicState) solution.getState();
        if (goalState.getHValue() != 0) {
            System.out.println("ERROR: Goal state should have h=0, but was: " + goalState.getHValue());
            return;
        }
        
        // Print the path
        System.out.println("Solution found!");
        System.out.println("Path:");
        Node current = solution;
        while (current != null) {
            System.out.println(current.getState());
            current = current.getParent();
        }
    }
} 