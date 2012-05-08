package towerdefence.gameelements;

import java.util.*; 

//FAAS 1 - TODO!!
//kust saab enemylisti? 

public class Wave {
    
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();  
    private final Level level;
    
    public Wave(Level level) {
        this.level = level;        
    }
    
    public Level getLevel() {
        return this.level;
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
