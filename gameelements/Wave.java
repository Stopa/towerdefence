package towerdefence.gameelements;

import java.util.*; 
import towerdefence.Configuration;

public class Wave {
    
    private ArrayList<Enemy> inactiveEnemyList; //kollid, kes veel pole väljakule tulnud
    private ArrayList<Enemy> enemyList; //kollid, kes on parasjagu platsil ja aktiivsed
    private final ArrayList<AmmoSprite> ammoSpriteList; //TODO!!!!!!!!!!!!!!!!    
        
    private final Level level;        
    
    public Wave(Level level, int infantry, int knights, int cavalry) {
        this.inactiveEnemyList = new ArrayList<Enemy>(); 
        this.enemyList = new ArrayList<Enemy>(); 
        
        this.level = level;
        this.ammoSpriteList = new ArrayList<AmmoSprite>();         
        
        for (int i = 0; i < cavalry; i++) {
            inactiveEnemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_CAVALRY_TYPE));
        }
        for (int i = 0; i < infantry; i++) {
            inactiveEnemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_INFANTRY_TYPE));
        }
        for (int i = 0; i < knights; i++) {
            inactiveEnemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_KNIGHT_TYPE));
        }
    }
    
    //kas on vastaseid alles?
    public boolean hasInactiveEnemies() {
        return !this.inactiveEnemyList.isEmpty();
    }
    
    //lisab uue vastase levelile
    public void addNewEnemy() {
        Enemy enemy = this.inactiveEnemyList.get(new Random().nextInt(inactiveEnemyList.size()));
        this.inactiveEnemyList.remove(enemy);
        this.enemyList.add(enemy);
        Grid grid = level.getRandomStartingGrid();
        enemy.setGrid(grid);
        grid.addEnemy(enemy);        
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
