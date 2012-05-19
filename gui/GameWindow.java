package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import towerdefence.Configuration; 
import towerdefence.gameelements.Level;
import towerdefence.gameelements.*; 
import towerdefence.logic.LevelController;

public class GameWindow extends JFrame implements ActionListener {
    
    private LevelPanel levelPanel;     
    private JButton startCombatButton;    
    private boolean buildingPhaseActive;     
    private Level level; 
    private LevelController levelController;
    
    private JLabel goldLabel; //TODO - hoiab kulda..
    
    private JButton buildButton; //TODO - sellele vajutades ehitatakse torn
    
    private JButton arrowTowerButton; //TODO - sellele vajutades ehitatakse arrowTower
    private JButton cannonTowerButton; //TODO - sellel vajutades ehitatakse cannonTower
    private boolean arrowTowerSelected; //TODO - kas arrow tower on selectitud
    private boolean cannonTowerSelected; //TODO - kas cannon tower on selectitud 
    
    //TODO - joonista nuppudele borderid juba valmis!!! aga alguses painted = false!!
    //siis hiljem ei pea iga kord uut objekti välja kutsuma vaid paned lihtsalt
    //.setBorderPainted(true); 
    
    
    private JLabel waveLabel; //TODO - "next wave:" või current wave.. vms.. 
    private JLabel waveRemainingInfantryLabel; //TODO - infantry järgmises laines
    private JLabel waveCurrentInfantryLabel; //TODO
    private JLabel waveRemainingCavalryLabel; //TODO - 
    private JLabel waveCurrentCavalryLabel; //TODO
    private JLabel waveRemainingKnightsLabel; //TODO 
    private JLabel waveCurrentKnightsLabel; 
    
    public GameWindow(Level level) {                
        
        this.level = level; 
        
        this.arrowTowerSelected = false;
        this.cannonTowerSelected = false; 
        
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
        
        arrowTowerButton = new JButton(Configuration.GAMEWINDOW_ARROWTOWERBUTTON_TEXT);
        arrowTowerButton.setBounds(
                Configuration.GAMEWINDOW_ARROWTOWERBUTTON_POSX,
                Configuration.GAMEWINDOW_ARROWTOWERBUTTON_POSY,
                Configuration.GAMEWINDOW_ARROWTOWERBUTTON_WIDTH,
                Configuration.GAMEWINDOW_ARROWTOWERBUTTON_HEIGHT);
        arrowTowerButton.setForeground(Color.RED);
        arrowTowerButton.setBackground(Color.BLACK);
        arrowTowerButton.setFocusable(false);
        arrowTowerButton.addActionListener(this); //TODO - tugi
        arrowTowerButton.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));        
        this.add(arrowTowerButton);
        
        cannonTowerButton = new JButton(Configuration.GAMEWINDOW_CANNONTOWERBUTTON_TEXT);
        cannonTowerButton.setBounds(
                Configuration.GAMEWINDOW_CANNONTOWERBUTTON_POSX,
                Configuration.GAMEWINDOW_CANNONTOWERBUTTON_POSY,
                Configuration.GAMEWINDOW_CANNONTOWERBUTTON_WIDTH,
                Configuration.GAMEWINDOW_CANNONTOWERBUTTON_HEIGHT);
        cannonTowerButton.setForeground(Color.RED);
        cannonTowerButton.setBackground(Color.BLACK);
        cannonTowerButton.setFocusable(false);
        cannonTowerButton.addActionListener(this); //TODO - tugi
        cannonTowerButton.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));        
        this.add(cannonTowerButton);        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(level.getName());
        this.setVisible(true); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        if (source == startCombatButton)
            levelController.startCombatPhase();
        else if (source == arrowTowerButton) {
            arrowTowerSelected = !arrowTowerSelected;
            if (arrowTowerSelected) {
                cannonTowerSelected = false;
                drawCannonTowerButton();
            }
            drawArrowTowerButton();
        }
        else if (source == cannonTowerButton) {
            cannonTowerSelected = !cannonTowerSelected;
            if (cannonTowerSelected) {
                arrowTowerSelected = false;
                drawArrowTowerButton();
            }
            drawCannonTowerButton();
        }
    }
    
    public void respondToClick(int x, int y) {
        Grid grid = level.getGridAt(x, y);
        if (!grid.canBuild()) {
            return;
        }
        else {
            if (arrowTowerSelected) {
                level.addTower(Tower.getFactoryTower(
                        Configuration.TOWER_ARROWTOWER_TYPE, grid), grid);
                levelPanel.updateUI();
            }
            else if (cannonTowerSelected) {
                level.addTower(Tower.getFactoryTower(
                        Configuration.TOWER_CANNONTOWER_TYPE, grid), grid);                
                levelPanel.updateUI();
            }
            else return;
        }
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
    
    private void drawGoldLabel() {
        goldLabel.setText("Kulda: " + level.getMoney());
    }
    
    private void drawBuildButton() {
        buildButton.setEnabled(arrowTowerSelected || cannonTowerSelected);
    }
    
    private void drawArrowTowerButton() {
        if (arrowTowerSelected)
            arrowTowerButton.setBorder(BorderFactory.createLineBorder(Color.RED,5));       
        else
            arrowTowerButton.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    }
    
    private void drawCannonTowerButton() {
        if (cannonTowerSelected)
            cannonTowerButton.setBorder(BorderFactory.createLineBorder(Color.RED,5));       
        else
            cannonTowerButton.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
    }
    
    private void drawWaveLabel() {
        waveLabel.setText(Configuration.GAMEWINDOW_WAVELABEL_TEXT +
                level.getCurrentWaveNumber() + 
                "/" + 
                level.getTotalWaves()); 
    }
    
    private void drawWaveRemainingInfantryLabel() {
        waveRemainingInfantryLabel.setText(
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[1]));
    }
    
    private void drawWaveCurrentInfantryLabel() {
        waveCurrentInfantryLabel.setText(
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[0]));        
    }
    
    private void drawWaveRemainingCavalryLabel() {
        waveRemainingCavalryLabel.setText(
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[3]));        
    }
    
    private void drawWaveCurrentCavalryLabel() {
        waveCurrentCavalryLabel.setText(
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[2]));        
    }
    
    private void drawWaveRemainingKnightsLabel() {
        waveRemainingKnightsLabel.setText(
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[5]));        
    }
    
    private void drawWaveCurrentKnightsLabel() {
        waveCurrentKnightsLabel.setText(
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[4]));        
    }
    
    
    public void setBuildingPhase(boolean active) {        
        this.buildingPhaseActive = active;
        this.startCombatButton.setEnabled(active);
        this.arrowTowerButton.setEnabled(active);
        this.cannonTowerButton.setEnabled(active);
        
        if (!active) {
            this.arrowTowerSelected = false;
            this.cannonTowerSelected = false; 
            drawArrowTowerButton();
            drawCannonTowerButton();
        }
    }
    
    public boolean isBuildingPhase() {
        return this.buildingPhaseActive; 
    }

}
