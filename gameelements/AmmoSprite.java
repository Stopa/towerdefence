package towerdefence.gameelements;

import java.util.ArrayList; 
import towerdefence.Configuration;

//tornide poolt väljatulistatavad kuulid..
//esialgu eeldame, et iga "kuul" lendab täpselt ühe turni
//esimesel microturnil on kuul alguspunkti keskel, 
//viimasel microturnil on kuul lõpppunkti keskel
//vahepealsetel microturnidel liigub kuul vahepealsetesse koordinaatidesse,
//kusjuures kiirus ei muutu
public class AmmoSprite {
    
    private final Grid startGrid; //kust alustab lendamist
    private final Grid endGrid; //kus lõpetab lendamise
    //2d koordinaatide list, kus on vahepealsetel microturnidel - saadakse indeksi järgi
    private ArrayList<int[]> coordsList; 
    private int currentCoordsIndex; //coordsListi praegune indeks
    private final String type; //TODO - lahendada enumiga vms.. 
    
    public AmmoSprite(Grid startGrid, Grid endGrid, String type) {
        this.startGrid = startGrid;
        this.endGrid = endGrid; 
        setCoordsList(); 
        currentCoordsIndex = 0; 
        this.type = type; 
    }            
    
    public String getType() {
        return this.type; 
    }
    
    public void setCoordsList() {
        this.currentCoordsIndex = 0; 
        coordsList = new ArrayList<int[]>();         
        
        int startx = startGrid.getX() * 30 + 15; //TODO - panna confi..
        int starty = startGrid.getY() * 30 + 15; //TODO - panna confi..
        
        int endx = endGrid.getX() * 30 + 15; //TODO - panna confi..
        int endy = endGrid.getY() * 30 + 15; //TODO - panna confi
        
        int distx = endx - startx; 
        int disty = endy - starty;
        
        int stepx = (int)(distx / Configuration.MICROTURNS);
        int stepy = (int)(disty / Configuration.MICROTURNS);
        
        //lisame kõik positsioonid, kuhu kuul teekonnal satub igal microturnil
        for (int i = 0; i <= Configuration.MICROTURNS; i++) {
            coordsList.add(new int[]{startx + (i * stepx), 
                                     starty + (i * stepy)});
        }      
    }
    
    public void micromove() {
        this.currentCoordsIndex++; 
    }
    
    public int[] getCoords() {
        return coordsList.get(currentCoordsIndex); 
    }

}
