package fr.emse.ai.search.bottle;

import fr.emse.ai.search.core.Problem;
import java.util.*;

public class BottleProblem implements Problem {
    private final int[] capacities;
    private final int target;
    
    public BottleProblem(int[] capacities, int target) {
        this.capacities = capacities.clone();
        this.target = target;
    }
    
    @Override
    public Object getInitialState() {
        // Start with all bottles empty
        return new Bottles(capacities, new int[capacities.length], target);
    }
    
    @Override
    public boolean isGoal(Object state) {
        Bottles bottles = (Bottles) state;
        // Check if any bottle contains exactly the target amount
        for (int amount : bottles.getAmounts()) {
            if (amount == target) return true;
        }
        return false;
    }
    
    @Override
    public List<Object> getActions(Object state) {
        List<Object> actions = new ArrayList<>();
        Bottles bottles = (Bottles) state;
        int[] amounts = bottles.getAmounts();
        
        // For each bottle
        for (int i = 0; i < capacities.length; i++) {
            // Can fill if not full
            if (amounts[i] < capacities[i]) {
                actions.add(Action.fill(i));
            }
            
            // Can empty if not empty
            if (amounts[i] > 0) {
                actions.add(Action.empty(i));
                
                // Can pour to other bottles if they're not full
                for (int j = 0; j < capacities.length; j++) {
                    if (i != j && amounts[j] < capacities[j]) {
                        actions.add(Action.pour(i, j));
                    }
                }
            }
        }
        
        return actions;
    }
    
    @Override
    public Object getNextState(Object state, Object action) {
        Bottles bottles = (Bottles) state;
        Action act = (Action) action;
        
        int[] oldAmounts = bottles.getAmounts();
        int[] newAmounts = oldAmounts.clone();
        
        switch (act.getType()) {
            case FILL:
                newAmounts[act.getBottle()] = capacities[act.getBottle()];
                break;
                
            case EMPTY:
                newAmounts[act.getBottle()] = 0;
                break;
                
            case POUR:
                int from = act.getBottle();
                int to = act.getTargetBottle();
                int available = oldAmounts[from];
                int space = capacities[to] - oldAmounts[to];
                int transfer = Math.min(available, space);
                newAmounts[from] -= transfer;
                newAmounts[to] += transfer;
                break;
        }
        
        return new Bottles(capacities, newAmounts, target);
    }
    
    @Override
    public double getStepCost(Object state, Object action, Object nextState) {
        return 1.0;  // Each action costs 1
    }
} 