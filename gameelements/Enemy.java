package towerdefence.gameelements;

import java.util.ArrayList;

public class Enemy {
    
    private final int maxHealth; //kolli maksimumelu, millega ta spawnib ja millest suuremaks minna ei saa
    private int currentHealth; //kolli praegune elu
    private final int speed;  //kolli kiirus (grids per turn) - default 1
    private boolean active; //määrab, kas koll on aktiivne või mitte (elus või mitte). 
    private Grid grid; //grid, millel koll asub
    private final ArrayList<Grid> movePath; //määrab kolli liikumisteekonna selle turni jooksul
    //TODO - FAAS 1 - mõelda, kas LinkedList poleks parem? 
    
    //TODO - FAAS 1 - mõtle, kas seda ikka läheb vaja..? 
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
    //määrab ära movePathi selle turni jaoks
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

    public boolean isActive() {
        return this.active; 
    }                
    
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

    public void heal(int health) {
        this.currentHealth += health; 
    }
    
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
    }
    
}
