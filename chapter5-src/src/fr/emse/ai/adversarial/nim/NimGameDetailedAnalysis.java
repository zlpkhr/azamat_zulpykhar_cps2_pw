package fr.emse.ai.adversarial.nim;

import fr.emse.ai.adversarial.AlphaBetaSearch;
import fr.emse.ai.adversarial.IterativeDeepeningAlphaBetaSearch;
import fr.emse.ai.adversarial.MinimaxSearch;
import java.util.List;

public class NimGameDetailedAnalysis {
    public static void main(String[] args) {
        // Test with larger stick counts
        System.out.println("\nTesting with Larger Stick Counts");
        System.out.println("===============================");
        testWithLargerStickCounts();
        
        // Detailed node expansion tracing
        System.out.println("\nDetailed Node Expansion Tracing");
        System.out.println("============================");
        traceNodeExpansions();
    }
    
    private static void testWithLargerStickCounts() {
        int[] stickCounts = {20, 30, 40, 50};
        
        for (int sticks : stickCounts) {
            System.out.println("\nTesting with " + sticks + " sticks:");
            NimGame game = new NimGame(sticks);
            List<Integer> state = game.getInitialState();
            
            // Test each algorithm
            long startTime = System.currentTimeMillis();
            MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
            Integer minimaxAction = minimaxSearch.makeDecision(state);
            long minimaxTime = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
            Integer alphaBetaAction = alphaBetaSearch.makeDecision(state);
            long alphaBetaTime = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> idSearch = 
                IterativeDeepeningAlphaBetaSearch.createFor(game, -1.0, 1.0, 3);
            Integer idAction = idSearch.makeDecision(state);
            long idTime = System.currentTimeMillis() - startTime;
            
            System.out.println("Minimax: Action=" + minimaxAction + ", Time=" + minimaxTime + "ms");
            System.out.println("Metrics: " + minimaxSearch.getMetrics());
            
            System.out.println("\nAlpha-Beta: Action=" + alphaBetaAction + ", Time=" + alphaBetaTime + "ms");
            System.out.println("Metrics: " + alphaBetaSearch.getMetrics());
            
            System.out.println("\nIterative Deepening: Action=" + idAction + ", Time=" + idTime + "ms");
            System.out.println("Metrics: " + idSearch.getMetrics());
        }
    }
    
    private static void traceNodeExpansions() {
        // Test with a small number of sticks to show detailed tracing
        NimGame game = new NimGame(5);
        List<Integer> state = game.getInitialState();
        
        System.out.println("Detailed tracing with 5 sticks:");
        
        // Run each algorithm and print detailed metrics
        MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
        AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
        IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> idSearch = 
            IterativeDeepeningAlphaBetaSearch.createFor(game, -1.0, 1.0, 3);
        
        System.out.println("\nMinimax Search Trace:");
        System.out.println("-------------------");
        minimaxSearch.makeDecision(state);
        System.out.println("Expanded nodes: " + minimaxSearch.getMetrics().get("expandedNodes"));
        System.out.println("Max depth: " + minimaxSearch.getMetrics().get("maxDepth"));
        
        System.out.println("\nAlpha-Beta Search Trace:");
        System.out.println("----------------------");
        alphaBetaSearch.makeDecision(state);
        System.out.println("Expanded nodes: " + alphaBetaSearch.getMetrics().get("expandedNodes"));
        System.out.println("Max depth: " + alphaBetaSearch.getMetrics().get("maxDepth"));
        
        System.out.println("\nIterative Deepening Alpha-Beta Search Trace:");
        System.out.println("---------------------------------------");
        idSearch.makeDecision(state);
        System.out.println("Expanded nodes: " + idSearch.getMetrics().get("expandedNodes"));
        System.out.println("Max depth: " + idSearch.getMetrics().get("maxDepth"));
    }
} 