package towerdefence.gameelements;

import java.util.ArrayList;
import towerdefence.Configuration;
import java.util.Random;

public class Enemy {
    
    private final int maxHealth; //kolli maksimumelu, millega ta spawnib ja millest suuremaks minna ei saa
    private int currentHealth; //kolli praegune elu
    private final int speed;  //kolli kiirus (grids per turn) - default 1
    private boolean active; //määrab, kas koll on aktiivne või mitte (elus või mitte). 
    private final int damage; 
    private Grid grid; //grid, millel koll asub
    private final ArrayList<Grid> movePath; //määrab kolli liikumisteekonna selle turni jooksul
    private ArrayList<int[]> coordsList; //kooli koordinaadid SELLE turni jooksul
    private int currentCoordsIndex; 
    
    private final String type;
    private Grid previousGrid; 
    
    private boolean hittingBuilding;
    private Grid buildingGrid;
    
    public Enemy (int health, int speed, int damage, String type) {
        this.hittingBuilding = false; 
        this.maxHealth = health; 
        this.currentHealth = maxHealth; 
        this.speed = speed;
        this.damage = damage; 
        this.type = type; 
        this.active = true; 
        this.movePath = new ArrayList<Grid>();        
    }
    
    public void micromove() {
        if (this.hittingBuilding) return;
        this.currentCoordsIndex++; 
    }
    
    private void setCoordsList() {
        this.currentCoordsIndex = 0;                
        
        coordsList = new ArrayList<int[]>(); 
        
        for (int grids = 0; grids < movePath.size()-1; grids++) {
        
            int startx = movePath.get(grids).getX() * 30; //TODO - panna confi..
            int starty = movePath.get(grids).getY() * 30; //TODO - panna confi..
        
            int endx = movePath.get(grids+1).getX() * 30; //TODO - panna confi..
            int endy = movePath.get(grids+1).getY() * 30; //TODO - panna confi
        
            int distx = endx - startx; 
            int disty = endy - starty;
            
            int stepx = distx / (30 * this.speed / Configuration.MICROTURNS); //TODO - conf
            int stepy = disty / (30 * this.speed / Configuration.MICROTURNS); //TODO - conf
        
            //lisame kõik positsioonid, kuhu kuul teekonnal satub igal microturnil
            for (int i = 1; i <= Configuration.MICROTURNS / this.speed; i++) {
                coordsList.add(new int[]{startx + ((i+1) * stepx), 
                                         starty + ((i+1) * stepy)});
                
            }
        }
    }
    
    private boolean canMove() {
        int coords[] = {1,0,-1,0,0,1,0,-1}; 
        
        for (int j = 0; j < 4; j++) {
            int x = coords[j * 2]; //0, 2, 4, 6
            int y = coords[(j * 2) + 1]; //1, 3, 5, 7
            
            Grid adjacentGrid = null;
            try {
            adjacentGrid = grid.getLevel().getGridAt(
                        grid.getX() + x, 
                        grid.getY() + y);
            }
            catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
            }
            if (adjacentGrid == null || adjacentGrid == previousGrid) continue;
            if (adjacentGrid.getHealth() > 0) this.buildingGrid = adjacentGrid; 
                                  
            if (adjacentGrid.getGridType().isPassable()) {
                return true;
            } 
            }     
        return false;      
    }
    
    public void hitBuilding() {
        if (!this.hittingBuilding) return;
        buildingGrid.damage(damage);                        
    }
    
    //TODO - FAAS 1 - kutsutakse välja iga turni alguses
    //määrab ära movePathi selle turni jaoks
    public void calculateMovePath() {
        this.buildingGrid = null;
        
        if (!this.canMove()) {
            this.hittingBuilding = true;
            return;
        }
        this.hittingBuilding = false; 
        this.movePath.clear();
        this.movePath.add(this.grid); //TODO - võta ära kui probleeme tekitab..?         
            
        Grid tmpPreviousGrid = this.previousGrid;
        Grid sourceGrid = this.grid; 
        ArrayList<Grid> possibleGrids = new ArrayList<Grid>(); 
        
        Random rnd = new Random();
        
        int coords[] = {1,0,-1,0,0,1,0,-1};
        
        setGrids:
        for (int i = 0; i < this.speed; i++) {
            possibleGrids.clear(); 
            for (int j = 0; j < 4; j++) {
                int x = coords[j * 2]; //0, 2, 4, 6
                int y = coords[(j * 2) + 1]; //1, 3, 5, 7
                //TODO - pane confi..
                Grid adjacentGrid = null;
                try {
                adjacentGrid = sourceGrid.getLevel().getGridAt(
                        sourceGrid.getX() + x, 
                        sourceGrid.getY() + y);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    //do nothing
                }
                if (adjacentGrid == null) continue;
                 
                if (adjacentGrid != tmpPreviousGrid &&
                    adjacentGrid.getGridType().isPassable()) {
                    possibleGrids.add(adjacentGrid);
                }
            }

            if (possibleGrids.isEmpty()) {
                if (i == 0) { //linna ääres.. 
                    System.out.println(this.canMove());
                    throw new AssertionError(); 
                }
                else break setGrids; 
            }
            
            int randInt = rnd.nextInt(possibleGrids.size());
            Grid chosenGrid = possibleGrids.get(randInt);
            tmpPreviousGrid = sourceGrid; //praegune valitud on nüüd uus eelmine
            sourceGrid = chosenGrid; //nüüd on see "valitud ruut.."
            movePath.add(chosenGrid); //lisame movepathi
        }
        this.previousGrid = tmpPreviousGrid; 
        setCoordsList(); 
    }
    
    public void setNewGrid() {
        this.grid = movePath.get(movePath.size() - 1); 
    }
    
    /**
     * Tagastab kolli x ja y koordinaadi ekraanil. 
     * @return 
     */
    public int[] getCoords() {
        if (currentCoordsIndex >= coordsList.size())
            currentCoordsIndex = coordsList.size()-1; 
        return coordsList.get(currentCoordsIndex); 
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
    
    public int getDamage() {
        return this.damage;
    }
    
    public String getType() {
        return this.type; 
    }
        
    //tagastatakse enemy vastavalt sissesaadud stringile..
    public static Enemy getFactoryEnemy(String type) {
        if (type.equals(Configuration.ENEMY_CAVALRY_TYPE)) {
            return new Enemy(
                    Configuration.ENEMY_CAVALRY_HEALTH,
                    Configuration.ENEMY_CAVALRY_SPEED,
                    Configuration.ENEMY_CAVALRY_DAMAGE,
                    type);
        }
        else if (type.equals(Configuration.ENEMY_INFANTRY_TYPE)) {
            return new Enemy(
                    Configuration.ENEMY_INFANTRY_HEALTH,
                    Configuration.ENEMY_INFANTRY_SPEED,
                    Configuration.ENEMY_INFANTRY_DAMAGE,
                    type);                        
        }
        else if (type.equals(Configuration.ENEMY_KNIGHT_TYPE)) {
            return new Enemy(
                    Configuration.ENEMY_KNIGHT_HEALTH,
                    Configuration.ENEMY_KNIGHT_SPEED,
                    Configuration.ENEMY_KNIGHT_DAMAGE,
                    type);             
        }
        else throw new AssertionError(); //mingi vale string.. 
    }
    
}
