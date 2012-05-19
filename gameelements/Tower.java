package towerdefence.gameelements;

import towerdefence.Configuration; 
import java.util.ArrayList; 

//TODO - FAAS 2 - torni eemaldamine.. 

public class Tower {
    
    private int level; 
    private int turnsToFire; 
    private final TowerType towerType; 
    private Grid grid; 
    //TODO - FAAS 2 - panna mingisse sellisesse järjekorda, et kui enemyt otsitakse,
    //siis kõige kaugemal olev enemy võetakse eelisjärjekorras? 
    
    private ArrayList<Grid> gridsList; //hoiab kõiki liste, mis on gridis
    //cachetakse ära siis, kui torn ehitatakse. uuendatakse, kui torn upgradetakse.
    
    
    public Tower(TowerType towerType, Grid grid) {        
        this.grid = grid;
        this.level = 1; 
        this.turnsToFire = 0; 
        this.towerType = towerType;
        setGridsList(); 
    }
    
    
    public TowerType getTowerType() {
        return this.towerType;
    }
    
    public final void setGridsList() {        
        this.gridsList = new ArrayList<Grid>(); 
        addLinkedGrid(towerType.getRange(level), grid, gridsList);        
    }
    
    public static void addLinkedGrid(int range, Grid grid, ArrayList<Grid> gridsList) {
        if (range <= 0) return;
        else if (!gridsList.contains(grid)) {
            gridsList.add(grid);
        }
        int x = grid.getX();
        int y = grid.getY();
        
        int coords[] = {1,0,-1,0,0,1,0,-1}; 
        
        for (int j = 0; j < 4; j++) {
            int offx = coords[j * 2]; //0, 2, 4, 6
            int offy = coords[(j * 2) + 1]; //1, 3, 5, 7
        
            Grid newGrid = null;
            try {
            newGrid = grid.getLevel().getGridAt(x+offx, y+offy);
            }
            catch (ArrayIndexOutOfBoundsException aioobe) {
                //do nothing
            }
            if (newGrid != null) addLinkedGrid(range-1, newGrid, gridsList);
        }
    }
        
    
    public boolean canFire() {        
        return turnsToFire <= 0;
    }
    
    public Enemy getTargetEnemy() {
        
        for (Grid targetGrid : gridsList) {
            if (!targetGrid.getEnemyList().isEmpty()) {
                return targetGrid.getEnemyList().get(0);
            }
        }
        return null;         
    }
    
    public void fire() {
        if (!canFire()) return;
        Enemy target = getTargetEnemy();
        
        if (target == null) return; 

        if (this.towerType == TowerType.ArrowTower) {
            Effect effect = new Effect(
                    1, 
                    TowerType.ArrowTower.getAttack(level),
                    Effect.Target.SINGLE,
                    Effect.Type.DAMAGE);
            effect.setTargetEnemy(target);
            grid.getLevel().getCurrentWave().addEffect(effect);            
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
    
    public static Tower getFactoryTower(String type, Grid grid) {      
        Tower tower = null;
        if (type.equals(Configuration.TOWER_ARROWTOWER_TYPE)) {            
            tower = new Tower(TowerType.ArrowTower, grid);
        }
        else if (type.equals(Configuration.TOWER_CANNONTOWER_TYPE)) {
            tower = new Tower(TowerType.CannonTower, grid);
        }
        else throw new AssertionError(); 
        return tower; 
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

}
