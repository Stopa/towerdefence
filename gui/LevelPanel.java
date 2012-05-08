package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import javax.swing.*;
import java.io.*; 
import javax.imageio.*; 

import towerdefence.Configuration; 

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
    
    
    private boolean drawingActive; 
    int i = 0;     
    
    private GameWindow gameWindow;         
    
    LevelPanel (GameWindow gameWindow) {
        this.gameWindow = gameWindow; 
        this.drawingActive = false;   

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
    

    
    //REPAINT KUTSUTAKSE VÄLJA GAMEWINDOWST VMS!!!!!
    
    //TODO - testimiseks!!! hiljem kustutatakse.. 
    public void draw() {

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
    }

    @Override
    public void paintComponent(Graphics g) {
 
        //puhastame terve väljaku.. - TODO - hiljem ära võtta? 
        g.setColor(Color.RED);
                
        g.fillRect(0, 
                   0, 
                   Configuration.LEVELPANEL_WIDTH,
                   Configuration.LEVELPANEL_HEIGHT); 
        
        
        g.drawImage(infantryImage, i*5, i*5, null);     
        
    }
}