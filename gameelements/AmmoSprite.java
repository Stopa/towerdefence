package towerdefence.gameelements;

//tornide poolt väljatulistatavad kuulid..

import java.util.ArrayList; 

import towerdefence.Configuration;

public class AmmoSprite {
    
    private final Grid startGrid;
    private final Grid endGrid; 
    private ArrayList<int[]> coordsList;
    private int currentCoordsIndex; 
    
    public AmmoSprite(Grid startGrid, Grid endGrid) {
        this.startGrid = startGrid;
        this.endGrid = endGrid; 
        setCoordsList(); 
        currentCoordsIndex = 0; 
    }            
    
    private void setCoordsList() {
        coordsList = new ArrayList<int[]>(); 
        
        int startx = startGrid.getX() * 30 + 15; //TODO - panna confi..
        int starty = startGrid.getY() * 30 + 15; //TODO - panna confi..
        
        int endx = endGrid.getX() * 30 + 15; //TODO - panna confi..
        int endy = endGrid.getY() * 30 + 15; //TODO..
        
        int distx = endx - startx; 
        int disty = endy - starty;
        
        int stepx = (int)distx / Configuration.MICROTURNS;
        int stepy = (int)disty / Configuration.MICROTURNS;
        
        //lisame kõik positsioonid, kuhu kuul teekonnal satub igal microturnil
        for (int i = 1; i <= Configuration.MICROTURNS; i++) {
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
