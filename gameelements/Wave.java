package towerdefence.gameelements;

import java.util.*; 

public class Wave {
    
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();  
    
    public Wave() {
        //TODO? 
    }
    
    public ArrayList<Enemy> getEnemyList() {
        return this.enemyList; 
    }
    
    public void removeEnemy(Enemy enemy) {        
        enemy.getGrid().getEnemyList().remove(this);
        this.enemyList.remove(enemy);     
        enemy.die();
    }

}
