package towerdefence.gameelements;

import java.util.ArrayList; 
import java.awt.Color; 
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

    public ArrayList getEnemyList() {
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
    
public enum GridType {
    
    PATH(Color.GRAY, 0,02, 0), //tee, mida mööda kollid käivad
    FOREST(Color.BLACK, 0,03, 0), //mets, kuhu torni paigutada ei saa
    GRASS(Color.GREEN, 0,01, 0), //muru - saab torni paigutada
    VILLAGE(Color.BLUE, Configuration.GRID_VILLAGE_MAXHEALTH,04, Configuration.VILLAGE_MONEYPERWAVE), //küla - 
    CASTLE(Color.RED, Configuration.GRID_CASTLE_MAXHEALTH,05, Configuration.CASTLE_MONEYPERWAVE); //"loss" - kollide lõppeesmärk
        
    private final Color gridColor; //TODO - hiljem eemaldada..? 
    private final int maxHealth; //seda tüüpi gridide max "elu", mida kollid hävitavad
    private int id; // ID - levelite laadimiseks etc
    private int moneyperturn;
    private boolean burned;
        
    GridType(Color gridColor, int maxHealth, int id, int moneyPerTurn) {
        this.gridColor = gridColor; 
        this.maxHealth = maxHealth; 
        this.id = id;
        this.moneyperturn = moneyPerTurn;
        this.burned = false;
    }
    
    public boolean isBurned() {
		return this.burned;
	}
    
    public void burn() {
    	this.burned = true;
    	// TODO
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
    	if (!this.burned) {
    		return moneyperturn;
    	} else {
    		return 0;
    	}
    }

}    
 
}

//TODO - FAAS 1 - GRIDTYPE �?MBER TEHA:
//uus boolean meetod: isPassable()
//path tagastab alati true
//village/castle tagastavad true siis, kui nad on hävitatud (isBurned() == true) vms
//sama panna ka kollide liikumist arvutavatesse meetoditesse jne! praegu kontrollitakse pathi..