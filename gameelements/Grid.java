package towerdefence.gameelements;

<<<<<<< HEAD
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
=======
//kas peaks ära olema lahendatud ka nii, et igal gridil on viide towerile ja listile enemytest
//kes siin peal on..? 


//TODO - ära unusta torni ja enemyt hävitades eemaldada ka gridilt!!!

import java.util.*; 

public class Grid {
    
    private Tower tower; 
    private final ArrayList<Enemy> enemyList; 
    private final int x;
    private final int y; 
    private GridType gridType; 
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    
    public Grid(int x, int y, GridType gridType) {
        this.x = x;
        this.y = y;
        this.gridType = gridType; 
        this.enemyList = new ArrayList<Enemy>();                 
<<<<<<< HEAD
        this.linkedGrids = new ArrayList<Grid>(); 
        this.currentHealth = gridType.getMaxHealth();
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
=======
    }
    
    
    public void setTower(Tower tower) {
        this.tower = tower;
    }
    
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public Tower getTower() {
        return this.tower; 
    }
    
<<<<<<< HEAD
    /**
     * Tagastab selle gridi x-koordinaadi leveli teljestikus. 
     */
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public int getX() {
        return this.x; 
    }
    
<<<<<<< HEAD
    /**
     * Tagastab selle gridi y-koordinaadi leveli teljestikus. 
     */
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public int getY() {
        return this.y; 
    }
    
<<<<<<< HEAD
    /**
     * Tagastab listi kollidest, kes selles gridis asuvad. 
     * @return 
     */
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public ArrayList getEnemyList() {
        return this.enemyList;
    }
    
<<<<<<< HEAD
    /**
     * Lisab kolli selle gridi järjendisse. 
     * @param enemy 
     */
=======
    //TODO - kas neid kahte ikka läheb vaja? 
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public void addEnemy(Enemy enemy) {
        this.enemyList.add(enemy);
    }
    
<<<<<<< HEAD
    /**
     * Eemaldab kolli selle gridi järjendist. 
     * @param enemy 
     */
    public void removeEnemy(Enemy enemy) {
        if (!this.enemyList.contains(enemy)) throw new AssertionError(); 
        this.enemyList.remove(enemy);
    }       
    
public enum GridType {
    
    PATH(Color.GRAY, 0), //tee, mida mööda kollid käivad
    FOREST(Color.BLACK, 0), //mets, kuhu torni paigutada ei saa
    GRASS(Color.GREEN, 0), //muru - saab torni paigutada
    VILLAGE(Color.BLUE, Configuration.GRID_VILLAGE_MAXHEALTH), //küla - 
    CASTLE(Color.RED, Configuration.GRID_CASTLE_MAXHEALTH); //"loss" - kollide lõppeesmärk
        
    private final Color gridColor; //TODO - hiljem eemaldada..? 
    private final int maxHealth; //seda tüüpi gridide max "elu", mida kollid hävitavad
        
    GridType(Color gridColor, int maxHealth) {
        this.gridColor = gridColor; 
        this.maxHealth = maxHealth; 
    }
    
    /**
     * Tagastab selle grid tüübi max elu. 
     * @return 
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

}    
=======
    public void removeEnemy(Enemy enemy) {
        this.enemyList.remove(enemy);
    }
    
    public void updateEnemyList(ArrayList levelEnemyList) {
        //TODO - kas läheb vaja?
        //pmst võiks olla meetod mis käib üle TERVE leveli enemy listi
        //ja uuendab konkreetse enemy listi vastavalt
    }
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
 
}
