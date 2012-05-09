package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import javax.swing.*;
import java.io.*; 
import javax.imageio.*; 
import java.util.EnumMap;

import towerdefence.Configuration; 
import towerdefence.gameelements.Grid.GridType;

public class LevelPanel extends JPanel {
    
    private Image pathImage;
    private Image grassImage;
    private Image forestImage;
    private Image villageImage;
    private Image castleImage;
    
    private Image arrowtowerImage;
    private Image cannontowerImage; 
    
    private Image infantryImage;
    private Image cavalryImage;
    private Image knightImage;
    
    private EnumMap<GridType,Image> gridImageMap; 
    
    
    private boolean drawingActive; 
    int i = 0;     
    
    private GameWindow gameWindow;         
    
    LevelPanel (GameWindow gameWindow) {
        this.gameWindow = gameWindow; 
        this.drawingActive = false;   
        
        initImages();
        initGridImageMap();


    }
    
    private void initImages() {
        try {
            pathImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\road.png"));
            grassImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\grass.png"));
            forestImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\forest.png"));
            villageImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\village.png"));
            castleImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\fort1.png"));
            
            arrowtowerImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\arrowtower.png"));
            cannontowerImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\cannontower.png"));
            
            infantryImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\infantry.png"));
            cavalryImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\cavalry.png"));
            knightImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\knight.png"));
        }
        catch (IOException ioe) {
           ioe.printStackTrace(); 
        }          
    }
    
    private void initGridImageMap() {
        gridImageMap = new EnumMap<GridType, Image>(GridType.class);
        
        gridImageMap.put(GridType.PATH, pathImage);
        gridImageMap.put(GridType.GRASS, grassImage);                
        gridImageMap.put(GridType.FOREST, forestImage);
        gridImageMap.put(GridType.VILLAGE, villageImage);
        gridImageMap.put(GridType.CASTLE, castleImage);                
    }
    

    
    //REPAINT KUTSUTAKSE VÄLJA GAMEWINDOWST VMS!!!!!
    
    //TODO - testimiseks!!! hiljem kustutatakse.. 
    public void draw() {

        /*
        drawingActive = true;
        
        while (drawingActive) {            
            repaint();         
            System.out.println(i); 
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException ie) {
                break; 
            }                   
           i++;
           if (i == 80) drawingActive = false; 
        } 
         * 
         */
    }

    @Override
    public void paintComponent(Graphics g) {
        
        int levelWidth = Configuration.LEVEL_WIDTH;
        int levelHeight = Configuration.LEVEL_HEIGHT;
        
        //joonistame gridide taustad..
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {   
                Image image = gridImageMap.get(
                        this.gameWindow.getLevel().getGridAt(i, i).getGridType());
                g.drawImage(image, i * 30, j * 30, null);
            }
        }        
    }
}