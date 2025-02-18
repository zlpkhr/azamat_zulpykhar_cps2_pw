package fr.emse.ai.adversarial.nim;

import fr.emse.ai.adversarial.AlphaBetaSearch;
import fr.emse.ai.adversarial.IterativeDeepeningAlphaBetaSearch;
import fr.emse.ai.adversarial.MinimaxSearch;
import java.util.List;

public class NimGameExploration {
    public static void main(String[] args) {
        // Test with different initial stick counts to show algorithm behavior
        System.out.println("Testing with 5 sticks (Small Game Tree):");
        System.out.println("=======================================");
        runComparison(5);
        
        System.out.println("\nTesting with 10 sticks (Medium Game Tree):");
        System.out.println("========================================");
        runComparison(10);
        
        System.out.println("\nTesting with 15 sticks (Large Game Tree):");
        System.out.println("========================================");
        runComparison(15);
    }
    
    private static void runComparison(int sticks) {
        NimGame game = new NimGame(sticks);
        List<Integer> state = game.getInitialState();
        
        // Test Minimax
        System.out.println("\nMinimax Search:");
        System.out.println("--------------");
        MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
        long startTime = System.currentTimeMillis();
        Integer minimaxAction = minimaxSearch.makeDecision(state);
        long minimaxTime = System.currentTimeMillis() - startTime;
        System.out.println("Chosen action: Remove " + minimaxAction + " sticks");
        System.out.println("Metrics: " + minimaxSearch.getMetrics());
        System.out.println("Time taken: " + minimaxTime + "ms");
        
        // Test Alpha-Beta
        System.out.println("\nAlpha-Beta Search:");
        System.out.println("-----------------");
        AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
        startTime = System.currentTimeMillis();
        Integer alphaBetaAction = alphaBetaSearch.makeDecision(state);
        long alphaBetaTime = System.currentTimeMillis() - startTime;
        System.out.println("Chosen action: Remove " + alphaBetaAction + " sticks");
        System.out.println("Metrics: " + alphaBetaSearch.getMetrics());
        System.out.println("Time taken: " + alphaBetaTime + "ms");
        
        // Test Iterative Deepening Alpha-Beta
        System.out.println("\nIterative Deepening Alpha-Beta Search:");
        System.out.println("------------------------------------");
        IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> idSearch = 
            IterativeDeepeningAlphaBetaSearch.createFor(game, -1, 1, 1);
        startTime = System.currentTimeMillis();
        Integer idAction = idSearch.makeDecision(state);
        long idTime = System.currentTimeMillis() - startTime;
        System.out.println("Chosen action: Remove " + idAction + " sticks");
        System.out.println("Metrics: " + idSearch.getMetrics());
        System.out.println("Time taken: " + idTime + "ms");
        
        // Compare results
        System.out.println("\nComparison Summary:");
        System.out.println("-----------------");
        System.out.println("Initial sticks: " + sticks);
        System.out.println("Minimax decision: " + minimaxAction + " (took " + minimaxTime + "ms)");
        System.out.println("Alpha-Beta decision: " + alphaBetaAction + " (took " + alphaBetaTime + "ms)");
        System.out.println("Iterative Deepening decision: " + idAction + " (took " + idTime + "ms)");
    }
} 