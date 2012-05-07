package towerdefence.gameelements;

import java.awt.Color;

public enum GridType {
    
    DIRT(1, Color.GRAY), 
    WATER(-1, Color.BLUE), 
    FOREST(10, Color.GREEN), 
    ROCK(20, Color.BLACK);
    
    private final int moveWeight; //kui raske läbi liikuda..? 
    
    private final Color gridColor; //TODO - hiljem eemaldada..? 
        
    GridType(int moveWeight, Color gridColor) {
        this.moveWeight = moveWeight;
        this.gridColor = gridColor; 
    }

}
