package fr.emse.ai.search.bottle;

public class Action {
    private final ActionType type;
    private final int bottle;
    private final int targetBottle;  // only used for POUR
    
    public enum ActionType {
        FILL,   // Fill a bottle from tap
        EMPTY,  // Empty a bottle
        POUR    // Pour from one bottle to another
    }
    
    private Action(ActionType type, int bottle, int targetBottle) {
        this.type = type;
        this.bottle = bottle;
        this.targetBottle = targetBottle;
    }
    
    public ActionType getType() {
        return type;
    }
    
    public int getBottle() {
        return bottle;
    }
    
    public int getTargetBottle() {
        return targetBottle;
    }
    
    @Override
    public String toString() {
        switch (type) {
            case FILL: return "Fill bottle " + bottle;
            case EMPTY: return "Empty bottle " + bottle;
            case POUR: return "Pour " + bottle + " -> " + targetBottle;
            default: return "Unknown action";
        }
    }
    
    // Factory methods
    public static Action fill(int bottle) {
        return new Action(ActionType.FILL, bottle, -1);
    }
    
    public static Action empty(int bottle) {
        return new Action(ActionType.EMPTY, bottle, -1);
    }
    
    public static Action pour(int from, int to) {
        return new Action(ActionType.POUR, from, to);
    }
} 