package towerdefence.gameelements;

import towerdefence.Configuration; 

public enum TowerType {
    
    ArrowTower(Configuration.TOWER_ARROWTOWER_BASEATTACK,
               Configuration.TOWER_ARROWTOWER_ATTACKLEVELCOEFFICIENT,
               Configuration.TOWER_ARROWTOWER_BASESPEED,
               Configuration.TOWER_ARROWTOWER_SPEEDLEVELCOEFFICIENT),
    
    CannonTower(Configuration.TOWER_CANNONTOWER_BASEATTACK,
                Configuration.TOWER_CANNONTOWER_ATTACKLEVELCOEFFICIENT,
               Configuration.TOWER_ARROWTOWER_BASESPEED,
               Configuration.TOWER_ARROWTOWER_SPEEDLEVELCOEFFICIENT);
    
    TowerType(int baseAttack,
              int attackLevelCoefficient,
              int baseSpeed,
              int speedLevelCoefficient) {
                  
        this.baseAttack = baseAttack; 
        this.attackLevelCoefficient = attackLevelCoefficient;
        this.baseSpeed = baseSpeed;
        this.speedLevelCoefficient = speedLevelCoefficient;                     
    }

    
    private int baseAttack;
    private int attackLevelCoefficient; 
    
    private int baseSpeed;
    private int speedLevelCoefficient; 
    
    public int getSpeed(int level) {
        return this.baseSpeed + (level * speedLevelCoefficient); 
    }
    
    public int getAttack(int level) {
        return this.baseAttack + (level * attackLevelCoefficient); 
    }
}
