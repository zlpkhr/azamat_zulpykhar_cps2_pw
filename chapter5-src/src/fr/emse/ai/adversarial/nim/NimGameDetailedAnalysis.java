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
        
        // Detailed decision tracing
        System.out.println("\nDetailed Decision Making Process");
        System.out.println("==============================");
        traceDecisionMaking();
        
        // Test different depth limits
        System.out.println("\nTesting Different Depth Limits");
        System.out.println("============================");
        testDepthLimits();
        
        // Investigate constant node counts
        System.out.println("\nInvestigating Constant Node Counts");
        System.out.println("================================");
        investigateNodeCounts();
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
    
    private static void traceDecisionMaking() {
        // Test with a small number of sticks to show detailed decision process
        int sticks = 5;
        System.out.println("Tracing decision making with " + sticks + " sticks:");
        NimGame game = new NimGame(sticks);
        List<Integer> state = game.getInitialState();
        
        // Analyze each possible move at the root
        System.out.println("\nAnalyzing root moves:");
        for (Integer action : game.getActions(state)) {
            List<Integer> nextState = game.getResult(state, action);
            System.out.println("\nIf we remove " + action + " stick(s):");
            System.out.println("Remaining sticks: " + nextState.get(1));
            System.out.println("Next player: " + nextState.get(0));
            System.out.println("Position evaluation: " + game.getUtility(nextState, state.get(0)));
        }
        
        // Run algorithms with detailed metrics
        System.out.println("\nDetailed algorithm execution:");
        
        MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
        Integer minimaxAction = minimaxSearch.makeDecision(state);
        System.out.println("\nMinimax chose to remove " + minimaxAction + " sticks");
        System.out.println("Full metrics: " + minimaxSearch.getMetrics());
        
        AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
        Integer alphaBetaAction = alphaBetaSearch.makeDecision(state);
        System.out.println("\nAlpha-Beta chose to remove " + alphaBetaAction + " sticks");
        System.out.println("Full metrics: " + alphaBetaSearch.getMetrics());
        
        IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> idSearch = 
            IterativeDeepeningAlphaBetaSearch.createFor(game, -1.0, 1.0, 3);
        Integer idAction = idSearch.makeDecision(state);
        System.out.println("\nIterative Deepening chose to remove " + idAction + " sticks");
        System.out.println("Full metrics: " + idSearch.getMetrics());
    }
    
    private static void testDepthLimits() {
        int sticks = 15;
        NimGame game = new NimGame(sticks);
        List<Integer> state = game.getInitialState();
        
        System.out.println("Testing different depth limits with " + sticks + " sticks:");
        
        int[] depthLimits = {2, 4, 6, 8, 10, 12};
        for (int depth : depthLimits) {
            System.out.println("\nDepth limit: " + depth);
            IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> idSearch = 
                IterativeDeepeningAlphaBetaSearch.createFor(game, -1.0, 1.0, depth);
            
            long startTime = System.currentTimeMillis();
            Integer action = idSearch.makeDecision(state);
            long time = System.currentTimeMillis() - startTime;
            
            System.out.println("Chosen action: Remove " + action + " sticks");
            System.out.println("Time taken: " + time + "ms");
            System.out.println("Metrics: " + idSearch.getMetrics());
        }
    }
    
    private static void investigateNodeCounts() {
        // Test with very different stick counts to understand why node counts stay constant
        int[] stickCounts = {5, 25, 50, 100};
        
        for (int sticks : stickCounts) {
            System.out.println("\nAnalyzing game tree with " + sticks + " sticks:");
            NimGame game = new NimGame(sticks);
            List<Integer> state = game.getInitialState();
            
            // Count available actions at root
            List<Integer> actions = game.getActions(state);
            System.out.println("Available actions at root: " + actions);
            
            // Analyze first few levels of the tree
            System.out.println("\nFirst level analysis:");
            for (Integer action : actions) {
                List<Integer> nextState = game.getResult(state, action);
                System.out.println("After removing " + action + " sticks:");
                System.out.println("- Remaining sticks: " + nextState.get(1));
                System.out.println("- Next player: " + nextState.get(0));
                System.out.println("- Utility value: " + game.getUtility(nextState, state.get(0)));
                
                // Show next level of actions
                List<Integer> nextActions = game.getActions(nextState);
                System.out.println("- Next possible actions: " + nextActions);
            }
            
            // Run algorithms and compare node counts
            MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
            minimaxSearch.makeDecision(state);
            
            AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
            alphaBetaSearch.makeDecision(state);
            
            System.out.println("\nNode expansion comparison:");
            System.out.println("Minimax nodes: " + minimaxSearch.getMetrics().get("expandedNodes"));
            System.out.println("Alpha-Beta nodes: " + alphaBetaSearch.getMetrics().get("expandedNodes"));
        }
    }
} 