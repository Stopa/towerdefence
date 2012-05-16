package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import towerdefence.Configuration; 
import towerdefence.gameelements.Level;
import towerdefence.logic.LevelController;

//TODO - implements listener, vastavad meetodid.. 
public class GameWindow extends JFrame implements ActionListener {
    
    private LevelPanel levelPanel;     
    private JButton startCombatButton;    
    private boolean buildingPhaseActive;     
    private Level level; 
    private LevelController levelController;
    
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
        this.getContentPane().setBackground(Color.BLACK);
        
        
        this.buildingPhaseActive = false; //TODO? 
        
        startCombatButton = new JButton(Configuration.GAMEWINDOW_STARTCOMBATBUTTON_TEXT);
        startCombatButton.setBounds(
                Configuration.GAMEWINDOW_STARTCOMBATBUTTON_POSX,
                Configuration.GAMEWINDOW_STARTCOMBATBUTTON_POSY,
                Configuration.GAMEWINDOW_STARTCOMBATBUTTON_WIDTH,
                Configuration.GAMEWINDOW_STARTCOMBATBUTTON_HEIGHT);
        startCombatButton.setForeground(Color.RED);
        startCombatButton.setBackground(Color.BLACK);
        startCombatButton.setFocusable(false);
        startCombatButton.addActionListener(this);
        this.add(startCombatButton);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(level.getName());
        this.setVisible(true); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        levelController.startCombatPhase();
    }
    
    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;        
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
    
    
    public void setBuildingPhase(boolean active) {        
        this.buildingPhaseActive = active;
        this.startCombatButton.setEnabled(active);
        //kui enabletakse, siis... levelPanelile mingi signaal?
        //kõik mingid ehitusnupud jälle enableda vms? või selleks listeneri lihtsalt
        //mingi check sisse panna?
        
        
        //kui disabletakse siis ehitada enam ei saa jne
    }

}
