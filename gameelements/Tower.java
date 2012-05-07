package towerdefence.gameelements;

<<<<<<< HEAD
import towerdefence.Configuration; 
import java.util.ArrayList; 

//TODO - FAAS 1 - ära unusta torni ja enemyt hävitades eemaldada ka gridilt!!!

//TODO - kuidas määratakse see, kas Tower saab tulistada?

public class Tower {
    
    private int level; 
    private int turnsToFire; 
    private final TowerType towerType; 
    private Grid grid; 
    //TODO - FAAS 1 - ei ole list, vaid set?
    //TODO - FAAS 1 - panna mingisse sellisesse järjekorda, et kui enemyt otsitakse,
    //siis kõige kaugemal olev enemy võetakse eelisjärjekorras? 
    
    private ArrayList<Grid> gridsList; //hoiab kõiki liste, mis on gridis
    //cachetakse ära siis, kui torn ehitatakse. uuendatakse, kui torn upgradetakse.
    
    
    public Tower(TowerType towerType) {
        this.level = 1; 
        this.turnsToFire = 0; 
        this.towerType = towerType;
        this.gridsList = getGridsList(this); 
    }
    
    public static ArrayList<Grid> getGridsList(Tower tower) {
        //TODO - FAAS 1
        return null;
    }
    
    public boolean canFire() {
        return turnsToFire <= 0;
    }
    
    public Enemy getTargetEnemy() {
        
        for (Grid targetGrid : gridsList) {
            if (!targetGrid.getEnemyList().isEmpty()) {
                //TODO - mõelda, miks ma castima pean? 
                return (Enemy)targetGrid.getEnemyList().get(0);
            }
        }
        return null;         
    }
    
    public void fire() {
        if (!canFire()) return;
        Enemy target = getTargetEnemy();
        if (target == null) return; 
        
        if (this.towerType == TowerType.ArrowTower) {
            //TODO
        }
        
        else if (this.towerType == TowerType.CannonTower) {
            //TODO
        }
        
        else throw new AssertionError(); 
        
         
        //setib turns to fire kuhugi
        //efekt vaja tekitada
    }
    
    public Grid getGrid() {
        return this.grid;
    }
    
    public void setGrid(Grid grid) {
        this.grid = grid; 
    }
    
    
//TOWERTYPE ENUM    
public enum TowerType {
    
    ArrowTower(Configuration.TOWER_ARROWTOWER_BASEATTACK,
               Configuration.TOWER_ARROWTOWER_ATTACKLEVELCOEFFICIENT,
               Configuration.TOWER_ARROWTOWER_BASESPEED,
               Configuration.TOWER_ARROWTOWER_SPEEDLEVELCOEFFICIENT,
               Configuration.TOWER_ARROWTOWER_BASERANGE,
               Configuration.TOWER_ARROWTOWER_RANGELEVELCOEFFICIENT),
    
    CannonTower(Configuration.TOWER_CANNONTOWER_BASEATTACK,
                Configuration.TOWER_CANNONTOWER_ATTACKLEVELCOEFFICIENT,
                Configuration.TOWER_ARROWTOWER_BASESPEED,
                Configuration.TOWER_ARROWTOWER_SPEEDLEVELCOEFFICIENT,
                Configuration.TOWER_CANNONTOWER_BASERANGE,
                Configuration.TOWER_CANNONTOWER_RANGELEVELCOEFFICIENT);
    
    private int baseAttack;
    private int attackLevelCoefficient; 
    
    private int baseSpeed;
    private int speedLevelCoefficient;     
    
    private int baseRange;
    private int rangeLevelCoefficient;
    
    TowerType(int baseAttack,
              int attackLevelCoefficient,
              int baseSpeed,
              int speedLevelCoefficient, 
              int baseRange,
              int rangeLevelCoefficient) {
                  
        this.baseAttack = baseAttack; 
        this.attackLevelCoefficient = attackLevelCoefficient;
        this.baseSpeed = baseSpeed;
        this.speedLevelCoefficient = speedLevelCoefficient;                     
        this.baseRange = baseRange;
        this.rangeLevelCoefficient = rangeLevelCoefficient; 
    }   
    
    public int getSpeed(int level) {
        return (int)(this.baseSpeed + (level * speedLevelCoefficient)); 
    }
    
    public int getAttack(int level) {
        return (int)(this.baseAttack + (level * attackLevelCoefficient)); 
    }
    
    public int getRange(int level) {
        return (int)(this.baseRange + (level * rangeLevelCoefficient));
    }
}    
=======
public class Tower {
    
    private int level; 
    
    public Tower() {
        this.level = 1; 
    }
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86

}
