package towerdefence.gameelements;

import java.util.*; 
import towerdefence.Configuration;

public class Wave {
    
    private ArrayList<Enemy> inactiveEnemyList; //kollid, kes veel pole väljakule tulnud
    private ArrayList<Enemy> enemyList; //kollid, kes on parasjagu platsil ja aktiivsed
    private final ArrayList<AmmoSprite> ammoSpriteList; //TODO!!!!!!!!!!!!!!!!    
        
    private final Level level; 
    private ArrayList<Effect> effectList;
    
    //0: infantry väljas, 1: infantry sees
    //2: cavalry väljas, 3: cavalry sees
    //4: knights väljas, 5: knights sees
    private int[] enemyNumbers; 
    
    public Wave(Level level, int infantry, int knights, int cavalry) {
        this.inactiveEnemyList = new ArrayList<Enemy>(); 
        this.enemyList = new ArrayList<Enemy>(); 
        enemyNumbers = new int[]{0,0,0,0,0,0};
        this.effectList = new ArrayList<Effect>();
        
        this.level = level;
        this.ammoSpriteList = new ArrayList<AmmoSprite>();         
        
        for (int i = 0; i < cavalry; i++) {
            inactiveEnemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_CAVALRY_TYPE));
            enemyNumbers[2]++;
        }
        for (int i = 0; i < infantry; i++) {
            inactiveEnemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_INFANTRY_TYPE));
            enemyNumbers[0]++;
        }
        for (int i = 0; i < knights; i++) {
            inactiveEnemyList.add(Enemy.getFactoryEnemy(Configuration.ENEMY_KNIGHT_TYPE));
            enemyNumbers[4]++;
        }
    }
    
    public void addAmmoSprite(AmmoSprite ammoSprite) {
        this.ammoSpriteList.add(ammoSprite);
    }

    public void addEffect(Effect effect) {
        this.effectList.add(effect);
    }
    
    public void removeEffect(Effect effect) {
        this.effectList.remove(effect);
    }    
    
    public ArrayList<Effect> getEffectList() {
        return this.effectList;
    }
    
    public int[] getEnemyNumbers() {
        return this.enemyNumbers;
    }
            
    //kas on vastaseid alles?
    public boolean hasInactiveEnemies() {
        return !this.inactiveEnemyList.isEmpty();
    }
    
    //lisab uue vastase levelile
    public Enemy addNewEnemy() {
        Enemy enemy = this.inactiveEnemyList.get(new Random().nextInt(inactiveEnemyList.size()));
        if (enemy.getType().equals(Configuration.ENEMY_INFANTRY_TYPE)) {
            enemyNumbers[0]--;
            enemyNumbers[1]++;
        }
        else if (enemy.getType().equals(Configuration.ENEMY_CAVALRY_TYPE)) {
            enemyNumbers[2]--;
            enemyNumbers[3]++;
        }
        else if (enemy.getType().equals(Configuration.ENEMY_KNIGHT_TYPE)) {
            enemyNumbers[4]--;        
            enemyNumbers[5]++;
        }
        this.inactiveEnemyList.remove(enemy);
        this.enemyList.add(enemy);
        Grid grid = level.getRandomStartingGrid();
        enemy.setGrid(grid);
        grid.addEnemy(enemy);        
        return enemy; 
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
        if (enemy.getType().equals(Configuration.ENEMY_INFANTRY_TYPE)) {
            enemyNumbers[1]--;            
        }
        else if (enemy.getType().equals(Configuration.ENEMY_CAVALRY_TYPE)) {
            enemyNumbers[3]--;
        }
        else if (enemy.getType().equals(Configuration.ENEMY_KNIGHT_TYPE)) {
            enemyNumbers[5]--;
        }
        enemy.getGrid().getEnemyList().remove(enemy);
        this.enemyList.remove(enemy);     
        enemy.die();
    }
}
