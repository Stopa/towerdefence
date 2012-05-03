package towerdefence.gameelements;

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
    
    public Grid(int x, int y, GridType gridType) {
        this.x = x;
        this.y = y;
        this.gridType = gridType; 
        this.enemyList = new ArrayList<Enemy>();                 
    }
    
    
    public void setTower(Tower tower) {
        this.tower = tower;
    }
    
    public Tower getTower() {
        return this.tower; 
    }
    
    public int getX() {
        return this.x; 
    }
    
    public int getY() {
        return this.y; 
    }
    
    public ArrayList getEnemyList() {
        return this.enemyList;
    }
    
    //TODO - kas neid kahte ikka läheb vaja? 
    public void addEnemy(Enemy enemy) {
        this.enemyList.add(enemy);
    }
    
    public void removeEnemy(Enemy enemy) {
        this.enemyList.remove(enemy);
    }
    
    public void updateEnemyList(ArrayList levelEnemyList) {
        //TODO - kas läheb vaja?
        //pmst võiks olla meetod mis käib üle TERVE leveli enemy listi
        //ja uuendab konkreetse enemy listi vastavalt
    }
 
}
