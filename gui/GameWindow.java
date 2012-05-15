package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import towerdefence.Configuration; 
import towerdefence.gameelements.Level;

//TODO - implements listener, vastavad meetodid.. 
public class GameWindow extends JFrame {
    
    private LevelPanel levelPanel;     
    private JButton turnButton; 
    
    private boolean buildingPhaseActive; 
    
    private Level level; 
    
    public GameWindow(Level level) {                
        
        this.level = level; 
        
        this.setLayout(null);
        this.setSize(Configuration.GAMEWINDOW_WIDTH,
                     Configuration.GAMEWINDOW_HEIGHT);      
        
        levelPanel = new LevelPanel(this);    
        levelPanel.setBounds(
                Configuration.LEVELPANEL_POSX,
                Configuration.LEVELPANEL_POSY,
                Configuration.LEVELPANEL_WIDTH,
                Configuration.LEVELPANEL_HEIGHT);
        this.add(levelPanel);                         
        
        
        this.buildingPhaseActive = false; //TODO? 
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(level.getName());
        this.setVisible(true); 
    }
    
    public Level getLevel() {
        return this.level; 
    }
    
    
    //kutsutakse välja levelcontrolleri vms poolt;
    //joonistab uue levelpanel fraami (repaint kutsudes)
    //samuti uuendab kulda, mana, jne
    public void update() {
        levelPanel.draw();
    }
    
    
    public void setBuildingPhase(boolean enabled) {
        //kui enabletakse, siis... levelPanelile mingi signaal?
        //kõik mingid ehitusnupud jälle enableda vms? või selleks listeneri lihtsalt
        //mingi check sisse panna?
        
        
        //kui disabletakse siis ehitada enam ei saa jne
    }

}
