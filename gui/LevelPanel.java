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
            pathImage = ImageIO.read(new File("src\\bitmapimages\\road.png"));
            grassImage = ImageIO.read(new File("src\\bitmapimages\\grass.png"));
            forestImage = ImageIO.read(new File("src\\bitmapimages\\forest.png"));
            villageImage = ImageIO.read(new File("src\\bitmapimages\\village.png"));
            castleImage = ImageIO.read(new File("src\\bitmapimages\\fort1.png"));
            
            arrowtowerImage = ImageIO.read(new File("src\\bitmapimages\\arrowtower.png"));
            cannontowerImage = ImageIO.read(new File("src\\bitmapimages\\cannontower.png"));
            
            infantryImage = ImageIO.read(new File("src\\bitmapimages\\infantry.png"));
            cavalryImage = ImageIO.read(new File("src\\bitmapimages\\cavalry.png"));
            knightImage = ImageIO.read(new File("src\\bitmapimages\\knight.png"));
        }
        catch (IOException ioe) {
           ioe.printStackTrace(); 
        }  
    }
    

    
    //REPAINT KUTSUTAKSE VÄLJA GAMEWINDOWST VMS!!!!!
    
    //TODO - testimiseks!!! hiljem kustutatakse.. 
    public void draw() {
        
        repaint(); 
        
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
 
        //puhastame terve väljaku.. - TODO - hiljem ära võtta? 
        g.setColor(Color.RED);
                
        g.fillRect(0, 
                   0, 
                   Configuration.LEVELPANEL_WIDTH,
                   Configuration.LEVELPANEL_HEIGHT); 
        
        
        g.drawImage(infantryImage, 100, 100, null);     
    }
}