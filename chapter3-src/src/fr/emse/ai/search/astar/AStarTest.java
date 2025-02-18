package fr.emse.ai.search.astar;

import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.bestfirst.BestFirstProblem;

public class AStarTest {
    public static void main(String[] args) {
        System.out.println("Testing A* Search...");
        
        // Create a problem with the following graph:
        // a(h=4) --2--> b(h=2) --1--> d(h=0)
        // |                |
        // 3                2
        // |                |
        // v                v
        // c(h=4) --2--> e(h=3) --1--> h(h=5)
        BestFirstProblem problem = new BestFirstProblem();
        AStarSearch search = new AStarSearch();
        
        Node solution = search.solve(problem);
        
        // Verify solution exists
        if (solution == null) {
            System.out.println("ERROR: Solution should not be null");
            return;
        }
        
        // Print the path and costs
        System.out.println("\nSolution found!");
        System.out.println("Path from start to goal:");
        printPath(solution);
    }
    
    private static void printPath(Node node) {
        if (node.getParent() != null) {
            printPath(node.getParent());
        }
        System.out.println(node.getState() + 
                         " (g=" + node.getPathCost() + ")" +
                         (node.getAction() != null ? " <- " + node.getAction() : ""));
    }
} 