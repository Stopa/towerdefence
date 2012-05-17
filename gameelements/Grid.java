package towerdefence.gameelements;

import java.util.ArrayList; 
import towerdefence.Configuration;

public class Grid {
    
    private Tower tower; //viide tornile, mis siin peal on
    private final ArrayList<Enemy> enemyList; //viide kõigile vastastele, kes siin peal on
    private final int x; //x-koordinaat
    private final int y; //y-koordinaat
    private GridType gridType; //selle gridi maastiku tüüp
    private final ArrayList<Grid> linkedGrids;
    private int currentHealth; //kasutatakse village/castle jaoks - praegune elu
    private final Level level; 

    
    public Grid(int x, int y, GridType gridType, Level level) {
        this.x = x;
        this.y = y;
        this.gridType = gridType; 
        this.enemyList = new ArrayList<Enemy>();                 
        this.linkedGrids = new ArrayList<Grid>(); 
        this.currentHealth = gridType.getMaxHealth();
        this.level = level; 
    }       
    

    public void setGridType(GridType type) {
        this.gridType = type;
    }    
    
    public int getHealth() {
        return this.currentHealth;
    }
    
    public void damage(int damage) {
        this.currentHealth -= damage; 
        if (this.currentHealth <= 0) this.burn();
    }
    
    public Level getLevel() {
        return this.level; 
    }
    
    //TODO - FAAS 1 - kutsutakse välja üks kord leveli loomisel
    //saadakse leveli failist
    /**
     * Määrab ära gridiga lingitud järgnevad gridid, lisades need listi.
     */
    public void setLinkedGrids(ArrayList<Grid> gridList) {
        for (Grid grid : gridList) {
        this.linkedGrids.add(grid);
        }
    }
        
    /**
     * Määrab ära torni, mis vastaval gridil asub.     
     * @param tower 
     */
    public void setTower(Tower tower) {
        if (this.tower != null) throw new AssertionError(); 
        this.tower = tower;
    }
    
    /**
     * Tagastab vastaval gridil asuva torni.
     * Tagastatakse null, kui gridil torni ei asu. 
     * @return 
     */

    public Tower getTower() {
        return this.tower; 
    }
    

    /**
     * Tagastab selle gridi x-koordinaadi leveli teljestikus. 
     */

    public int getX() {
        return this.x; 
    }

    /**
     * Tagastab selle gridi y-koordinaadi leveli teljestikus. 
     */

    public int getY() {
        return this.y; 
    }
    

    /**
     * Tagastab listi kollidest, kes selles gridis asuvad. 
     * @return 
     */

    public ArrayList<Enemy> getEnemyList() {
        return this.enemyList;
    }
    

    /**
     * Lisab kolli selle gridi järjendisse. 
     * @param enemy 
     */

    public void addEnemy(Enemy enemy) {
        this.enemyList.add(enemy);
    }
    

    /**
     * Eemaldab kolli selle gridi järjendist. 
     * @param enemy 
     */
    public void removeEnemy(Enemy enemy) {
        if (!this.enemyList.contains(enemy)) throw new AssertionError(); 
        this.enemyList.remove(enemy);
    }       
    
    public GridType getGridType() {
        return this.gridType;
    }
    
    public boolean isBurned() {
		return (this.gridType == GridType.BURNED_CASTLE ||
                        this.gridType == GridType.BURNED_VILLAGE);
	}
    
    public void burn() {
        if (this.gridType == GridType.CASTLE) {
            this.level.setTotalMoneyPerTurn(
                    this.level.getTotalMoneyPerTurn() - 
                    GridType.CASTLE.getMoneyPerTurn());
            this.gridType = GridType.BURNED_CASTLE;         
            this.level.setCastleBurned(true);
        }
        else if (this.gridType == GridType.VILLAGE) {
            this.level.setTotalMoneyPerTurn(
                    this.level.getTotalMoneyPerTurn() - 
                    GridType.VILLAGE.getMoneyPerTurn());
            this.gridType = GridType.BURNED_VILLAGE;            
        }
        //else throw new AssertionError(); //vale gridtype.. 
    }    
    
public enum GridType {
    
    PATH(0, 
         02, 
         0,
         true), //tee, mida mööda kollid käivad
    FOREST(0, 
           03, 
           0,
           false), //mets, kuhu torni paigutada ei saa
    GRASS(0, 
          01, 
          0,
          false), //muru - saab torni paigutada
    VILLAGE(Configuration.GRID_VILLAGE_MAXHEALTH,
            04, 
            Configuration.VILLAGE_MONEYPERWAVE,
            false), //küla - 
    BURNED_VILLAGE(0,
                  06,
                   0,
                   true),
    CASTLE(Configuration.GRID_CASTLE_MAXHEALTH,
           05, 
           Configuration.CASTLE_MONEYPERWAVE,
           false), //"loss" - kollide lõppeesmärk
    //TODO - võta true ära..
    BURNED_CASTLE(0,
                  07,
                  0,
                  true);

    private final int maxHealth; //seda tüüpi gridide max "elu", mida kollid hävitavad
    private int id; // ID - levelite laadimiseks etc
    private int moneyperturn;
    private boolean passable; 
        
    GridType(int maxHealth, int id, int moneyPerTurn, boolean passable) {
        this.maxHealth = maxHealth; 
        this.id = id;
        this.moneyperturn = moneyPerTurn;
        this.passable = passable;
    }
    
    public boolean isPassable() {
        return this.passable;
    }
    

	/**
     * Tagastab selle grid tüübi max elu. 
     * @return 
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public static GridType getById(int id) {
    	GridType result = null;
    	for(GridType gt:GridType.values()) {
    		if(gt.getId() == id) {
    			result = gt;
    		}
    	}
    	return result;
    }
    
    public int getMoneyPerTurn() {
        return this.moneyperturn;
    }

}    
 
}