package towerdefence.gameelements;

import java.util.*; 
import towerdefence.Configuration;

public class Wave {
    
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private final ArrayList<AmmoSprite> ammoSpriteList; //TODO!!!!!!!!!!!!!!!!    
        
    private final Level level;
    
    public Wave(Level level, int infantry, int knights, int cavalry) {
        this.level = level;
        this.ammoSpriteList = new ArrayList<AmmoSprite>();         
        
        for (int i = 0; i < cavalry; i++) {
            enemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_CAVALRY_TYPE));
        }
        for (int i = 0; i < infantry; i++) {
            enemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_INFANTRY_TYPE));
        }
        for (int i = 0; i < knights; i++) {
            enemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_KNIGHT_TYPE));
        }
    }
    
    public ArrayList<AmmoSprite> getAmmoSpriteList() {
        return this.ammoSpriteList;
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
