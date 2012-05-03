package towerdefence.gameelements;


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
    
    public boolean isActive() {
        return this.active; 
    }                
    
    public void damage(int health) {
       this.currentHealth -= health;  
       if (this.currentHealth <= 0) this.die(); 
    }
    
    public void heal(int health) {
        this.currentHealth += health; 
    }
    
    public void die() {
        this.active = false; 
    }
    
}
