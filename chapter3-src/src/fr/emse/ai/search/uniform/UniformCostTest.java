package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.Node;

public class UniformCostTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Uniform Cost Search...");
        
        // Create a problem with the following graph:
        // a --2--> b --1--> d
        // |        |
        // 3        2
        // |        |
        // v        v
        // c --2--> e --1--> h
        UniformCostProblem problem = new UniformCostProblem();
        UniformCostSearch search = new UniformCostSearch();
        
        Node solution = search.solve(problem);
        
        // Verify solution exists
        if (solution == null) {
            System.out.println("ERROR: Solution should not be null");
            return;
        }
        
        // Verify path cost
        if (solution.getPathCost() != 3) {
            System.out.println("ERROR: Path cost should be 3, but was: " + solution.getPathCost());
            return;
        }
        
        // Verify the path
        String path = "";
        Node current = solution;
        while (current != null) {
            path = current.getState().toString() + path;
            current = current.getParent();
        }
        
        if (!path.equals("abd")) {
            System.out.println("ERROR: Path should be 'abd', but was: " + path);
            return;
        }
        
        System.out.println("All tests passed!");
        System.out.println("Found path: " + path);
        System.out.println("Path cost: " + solution.getPathCost());
    }
} 