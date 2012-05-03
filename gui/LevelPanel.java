package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import towerdefence.Configuration; 

public class LevelPanel extends JPanel {
    
    private boolean drawingActive; 
    int i = 0;     
    
    private GameWindow gameWindow; 
    
    LevelPanel (GameWindow gameWindow) {
        this.gameWindow = gameWindow; 
        this.drawingActive = false;          
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
        g.setColor(Color.GRAY);
        g.fillRect(0, 
                   0, 
                   Configuration.LEVELPANEL_WIDTH,
                   Configuration.LEVELPANEL_HEIGHT); 
        
        
        g.setColor(Color.RED); 
        g.fillRect(i * 5, i * 5, 10, 10); 
    }
}