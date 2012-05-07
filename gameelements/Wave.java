package towerdefence.gameelements;

import java.util.*; 

public class Wave {
    
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();  
    
    public Wave() {
<<<<<<< HEAD
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
=======
        
    }
    
    public ArrayList getEnemyList() {
        return this.enemyList; 
    }
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86

}
