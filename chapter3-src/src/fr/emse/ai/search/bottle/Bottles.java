package fr.emse.ai.search.bottle;

import java.util.Arrays;

public class Bottles {
    private final int[] capacities;  // Maximum capacity of each bottle
    private final int[] amounts;     // Current amount in each bottle
    private final int target;        // Target amount we want to measure
    
    public Bottles(int[] capacities, int[] amounts, int target) {
        this.capacities = capacities.clone();
        this.amounts = amounts.clone();
        this.target = target;
    }
    
    public int[] getAmounts() {
        return amounts.clone();
    }
    
    public int[] getCapacities() {
        return capacities.clone();
    }
    
    public int getTarget() {
        return target;
    }
    
    // Heuristic function: minimum number of pour operations needed
    // This is a simple heuristic that returns the difference between
    // the closest amount to target and the target
    public int getHValue() {
        int minDiff = Integer.MAX_VALUE;
        for (int amount : amounts) {
            minDiff = Math.min(minDiff, Math.abs(amount - target));
        }
        return minDiff;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bottles)) return false;
        Bottles other = (Bottles) obj;
        return Arrays.equals(amounts, other.amounts);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(amounts);
    }
    
    @Override
    public String toString() {
        return Arrays.toString(amounts);
    }
} 