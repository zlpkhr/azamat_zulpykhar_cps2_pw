package fr.emse.ai.search.bottle;

import fr.emse.ai.search.core.Node;

public class BottleTest {
    public static void main(String[] args) {
        // Create a problem with 3 bottles: 8L, 5L, and 3L
        // Try to measure 4L
        int[] capacities = {8, 5, 3};
        int target = 4;
        
        BottleProblem problem = new BottleProblem(capacities, target);
        AStarSearch search = new AStarSearch();
        
        System.out.println("Solving water jug problem...");
        System.out.println("Capacities: 8L, 5L, 3L");
        System.out.println("Target: 4L");
        
        Node solution = search.solve(problem);
        
        if (solution == null) {
            System.out.println("No solution found!");
            return;
        }
        
        // Print the solution path
        System.out.println("\nSolution found!");
        System.out.println("Path:");
        printPath(solution);
    }
    
    private static void printPath(Node node) {
        if (node.getParent() != null) {
            printPath(node.getParent());
        }
        System.out.println(node.getState() + 
                         (node.getAction() != null ? " <- " + node.getAction() : ""));
    }
} 