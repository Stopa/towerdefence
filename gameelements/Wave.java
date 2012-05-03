package towerdefence.gameelements;

import java.util.*; 

public class Wave {
    
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();  
    
    public Wave() {
        
    }
    
    public ArrayList getEnemyList() {
        return this.enemyList; 
    }

}
