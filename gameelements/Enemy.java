package towerdefence.gameelements;

<<<<<<< HEAD
import java.util.ArrayList;

public class Enemy {
    
    private final int maxHealth; //kolli maksimumelu, millega ta spawnib ja millest suuremaks minna ei saa
    private int currentHealth; //kolli praegune elu
    private final int speed;  //kolli kiirus (grids per turn) - default 1
    private boolean active; //määrab, kas koll on aktiivne või mitte (elus või mitte). 
    private Grid grid; //grid, millel koll asub
    private final ArrayList<Grid> movePath; //määrab kolli liikumisteekonna selle turni jooksul
    //TODO - FAAS 1 - mõelda, kas LinkedList poleks parem? 
    
    private int xcoord; //kolli xcoord joonistatud ekraanil
    private int ycoord; //kolli ycoord joonistatud ekraanil
    
    public Enemy (int health, int speed, int armor) {
        this.maxHealth = health; 
        this.currentHealth = maxHealth; 
        this.speed = speed;
        this.active = true; 
        this.movePath = new ArrayList<Grid>();
    }
    
    //TODO - FAAS 1 - kutsutakse välja iga turni alguses
    public void calculateMovePath() {
        
    }
    
    /**
     * Tagastab kolli x ja y koordinaadi ekraanil. 
     * @return 
     */
    public int[] getCoords() {
        return new int[] {xcoord, ycoord};
    }
    
    //TODO - FAAS 1
    /**
     * Tsenderdab kolli praeguse ruudu suhtes ehk resetib x ja y koordinaadi.
     * Muuhulgas kutsutakse välja iga turni algul. 
     */
    public void centerCoords() {
        
    }
    
    /**
     * Tagastab gridi, kus see koll hetkel on. 
     * @return 
     */
    public Grid getGrid() {
        return this.grid;
    }
    
    /**
     * Määrab gridi, kus koll hetkel on. 
     */
    public void setGrid(Grid grid) {
        this.grid = grid; 
    }
    
    /**
     * Tagastab kolli max elu. 
     * @return 
     */
    public int getMaxHealth() {
        return this.maxHealth; 
    }
    
    /**
     * Tagastab kolli praeguse elu. 
     * @return 
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    
    /**
     * Tagastab kolli kiiruse. 
     * @return 
     */
    public int getSpeed() {
        //TODO - FAAS 2 - kui slowitud, siis aeglusta.. 
        return this.speed; 
    }
    
    /**
     * Tagastab, kas koll on aktiivne. 
     * @return 
     */
=======

public class Enemy {
    
    private final int maxHealth; 
    private int currentHealth; 
    private final int speed; 
    private final int armor;   
    private boolean active; 
    
    public Enemy (int health, int speed, int armor) {
        this.maxHealth = health;
        this.currentHealth = maxHealth; 
        this.speed = speed;
        this.armor = armor; 
        this.active = true; 
    }
    
    public int getHealth() {
        return this.maxHealth; 
    }
    
    public int getSpeed() {
        return this.speed; 
    }
    
    public int getArmor() {
        return this.armor; 
    }
    
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public boolean isActive() {
        return this.active; 
    }                
    
<<<<<<< HEAD
    /**
     * Kahjustab kolli teatud summa võrra. 
     * @param health 
     */
    public void damage(int health) {
       this.currentHealth -= health;  
       //if (this.currentHealth <= 0) this.die(); 
    }
    
    //TODO - FAAS 2
    //Vaata siis ka et maxHealthis rohkem ei healiks.. 
    /**
     * Parandab kolli.
     * @param health 
     */
=======
    public void damage(int health) {
       this.currentHealth -= health;  
       if (this.currentHealth <= 0) this.die(); 
    }
    
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public void heal(int health) {
        this.currentHealth += health; 
    }
    
<<<<<<< HEAD
    //TODO - FAAS 2
    //Vaata, kas seda meetodit on üldse vaja? 
    /**
     * Teeb kõik sisemised asjad kolli endaga. 
     */
    public void die() {
        this.active = false; 
    }                        
        
    //TODO - FAAS 1
    //factory meetodid siia
    //tagastatakse enemy vastavalt sissesaadud stringile..
    public Enemy getFactoryEnemy(String type) {
        //TODO
        return null; 
=======
    public void die() {
        this.active = false; 
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    }
    
}
