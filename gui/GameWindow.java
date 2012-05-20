package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import towerdefence.Configuration; 
import towerdefence.gameelements.Level;
import towerdefence.gameelements.*; 
import towerdefence.logic.LevelController;

public final class GameWindow extends JFrame implements ActionListener {
    
    private LevelPanel levelPanel;     
    private JButton startCombatButton;    
    private boolean buildingPhaseActive;     
    private Level level; 
    private LevelController levelController;
    
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
    
    private JLabel moneyLabel;
    private JLabel moneyPerTurnLabel;
    
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
        
        arrowTowerButton = new JButton(
                Configuration.GAMEWINDOW_ARROWTOWERBUTTON_TEXT + 
                " (" +
                Configuration.TOWER_ARROWTOWER_COST + 
                ")");
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
        
        cannonTowerButton = new JButton(
                Configuration.GAMEWINDOW_CANNONTOWERBUTTON_TEXT + 
                " (" + 
                Configuration.TOWER_CANNONTOWER_COST + 
                ")");
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
        
        /*
    private JLabel waveRemainingInfantryLabel; //TODO - infantry järgmises laines
    private JLabel waveCurrentInfantryLabel; //TODO
    private JLabel waveRemainingCavalryLabel; //TODO - 
    private JLabel waveCurrentCavalryLabel; //TODO
    private JLabel waveRemainingKnightsLabel; //TODO 
    private JLabel waveCurrentKnightsLabel;         
         * 
         */
        
        waveLabel = new JLabel();
        waveLabel.setBounds(
                Configuration.GAMEWINDOW_WAVELABEL_POSX, 
                Configuration.GAMEWINDOW_WAVELABEL_POSY, 
                Configuration.GAMEWINDOW_WAVELABEL_WIDTH, 
                Configuration.GAMEWINDOW_WAVELABEL_HEIGHT);
        waveLabel.setForeground(Color.RED);
        waveLabel.setBackground(Color.BLACK);
        this.add(waveLabel);
        drawWaveLabel(); 
                
        waveRemainingInfantryLabel = new JLabel();
        waveRemainingInfantryLabel.setBounds(
                Configuration.GAMEWINDOW_REMAININGINFANTRYLABEL_POSX, 
                Configuration.GAMEWINDOW_REMAININGINFANTRYLABEL_POSY, 
                Configuration.GAMEWINDOW_REMAININGINFANTRYLABEL_WIDTH, 
                Configuration.GAMEWINDOW_REMAININGINFANTRYLABEL_HEIGHT);
        waveRemainingInfantryLabel.setForeground(Color.RED);
        waveRemainingInfantryLabel.setBackground(Color.BLACK);
        this.add(waveRemainingInfantryLabel);
        drawWaveRemainingInfantryLabel();     
        
        waveCurrentInfantryLabel = new JLabel();
        waveCurrentInfantryLabel.setBounds(
                Configuration.GAMEWINDOW_CURRENTINFANTRYLABEL_POSX, 
                Configuration.GAMEWINDOW_CURRENTINFANTRYLABEL_POSY, 
                Configuration.GAMEWINDOW_CURRENTINFANTRYLABEL_WIDTH, 
                Configuration.GAMEWINDOW_CURRENTINFANTRYLABEL_HEIGHT);
        waveCurrentInfantryLabel.setForeground(Color.RED);
        waveCurrentInfantryLabel.setBackground(Color.BLACK);
        this.add(waveCurrentInfantryLabel);
        drawWaveCurrentInfantryLabel();          
        
        
        waveRemainingCavalryLabel = new JLabel();
        waveRemainingCavalryLabel.setBounds(
                Configuration.GAMEWINDOW_REMAININGCAVALRYLABEL_POSX, 
                Configuration.GAMEWINDOW_REMAININGCAVALRYLABEL_POSY, 
                Configuration.GAMEWINDOW_REMAININGCAVALRYLABEL_WIDTH, 
                Configuration.GAMEWINDOW_REMAININGCAVALRYLABEL_HEIGHT);
        waveRemainingCavalryLabel.setForeground(Color.RED);
        waveRemainingCavalryLabel.setBackground(Color.BLACK);
        this.add(waveRemainingCavalryLabel);
        drawWaveRemainingCavalryLabel();           
        
        waveCurrentCavalryLabel = new JLabel();
        waveCurrentCavalryLabel.setBounds(
                Configuration.GAMEWINDOW_CURRENTCAVALRYLABEL_POSX, 
                Configuration.GAMEWINDOW_CURRENTCAVALRYLABEL_POSY, 
                Configuration.GAMEWINDOW_CURRENTCAVALRYLABEL_WIDTH, 
                Configuration.GAMEWINDOW_CURRENTCAVALRYLABEL_HEIGHT);
        waveCurrentCavalryLabel.setForeground(Color.RED);
        waveCurrentCavalryLabel.setBackground(Color.BLACK);
        this.add(waveCurrentCavalryLabel);
        drawWaveCurrentCavalryLabel();           
        
        waveRemainingKnightsLabel = new JLabel();
        waveRemainingKnightsLabel.setBounds(
                Configuration.GAMEWINDOW_REMAININGKNIGHTSLABEL_POSX, 
                Configuration.GAMEWINDOW_REMAININGKNIGHTSLABEL_POSY, 
                Configuration.GAMEWINDOW_REMAININGKNIGHTSLABEL_WIDTH, 
                Configuration.GAMEWINDOW_REMAININGKNIGHTSLABEL_HEIGHT);
        waveRemainingKnightsLabel.setForeground(Color.RED);
        waveRemainingKnightsLabel.setBackground(Color.BLACK);
        this.add(waveRemainingKnightsLabel);
        drawWaveRemainingKnightsLabel();
        
        waveCurrentKnightsLabel = new JLabel();
        waveCurrentKnightsLabel.setBounds(
                Configuration.GAMEWINDOW_CURRENTKNIGHTSLABEL_POSX, 
                Configuration.GAMEWINDOW_CURRENTKNIGHTSLABEL_POSY, 
                Configuration.GAMEWINDOW_CURRENTKNIGHTSLABEL_WIDTH, 
                Configuration.GAMEWINDOW_CURRENTKNIGHTSLABEL_HEIGHT);
        waveCurrentKnightsLabel.setForeground(Color.RED);
        waveCurrentKnightsLabel.setBackground(Color.BLACK);
        this.add(waveCurrentKnightsLabel);
        drawWaveCurrentKnightsLabel();
        
        moneyLabel = new JLabel();
        moneyLabel.setBounds(
                Configuration.GAMEWINDOW_MONEYLABEL_POSX, 
                Configuration.GAMEWINDOW_MONEYLABEL_POSY, 
                Configuration.GAMEWINDOW_MONEYLABEL_WIDTH, 
                Configuration.GAMEWINDOW_MONEYLABEL_HEIGHT);
        moneyLabel.setForeground(Color.YELLOW);
        moneyLabel.setBackground(Color.BLACK);
        this.add(moneyLabel);
        drawMoneyLabel();  
        
        moneyPerTurnLabel = new JLabel();
        moneyPerTurnLabel.setBounds(
                Configuration.GAMEWINDOW_MONEYPERWAVELABEL_POSX, 
                Configuration.GAMEWINDOW_MONEYPERWAVELABEL_POSY, 
                Configuration.GAMEWINDOW_MONEYPERWAVELABEL_WIDTH, 
                Configuration.GAMEWINDOW_MONEYPERWAVELABEL_HEIGHT);
        moneyPerTurnLabel.setForeground(Color.YELLOW);
        moneyPerTurnLabel.setBackground(Color.BLACK);
        this.add(moneyPerTurnLabel);
        drawMoneyPerWaveLabel();      
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Kalevite Kangeim Kaitsja!");
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
            }
            else if (cannonTowerSelected) {                
                level.addTower(Tower.getFactoryTower(
                        Configuration.TOWER_CANNONTOWER_TYPE, grid), grid);                              
            }
            else return;
            levelPanel.updateUI();
            drawMoneyLabel();
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
    
    public void drawWaveLabel() {
        waveLabel.setText(Configuration.GAMEWINDOW_WAVELABEL_TEXT +
                level.getCurrentWaveNumber() + 
                "/" + 
                level.getTotalWaves()); 
    }
    
    public void drawWaveRemainingInfantryLabel() {
        waveRemainingInfantryLabel.setText(
                Configuration.GAMEWINDOW_REMAININGINFANTRYLABEL_TEXT + 
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[0]));
    }
    
    public void drawWaveCurrentInfantryLabel() {
        waveCurrentInfantryLabel.setText(
                Configuration.GAMEWINDOW_CURRENTINFANTRYLABEL_TEXT + 
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[1]));        
    }
    
    public void drawWaveRemainingCavalryLabel() {
        waveRemainingCavalryLabel.setText(
                Configuration.GAMEWINDOW_REMAININGCAVALRYLABEL_TEXT + 
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[2]));        
    }
    
    public void drawWaveCurrentCavalryLabel() {
        waveCurrentCavalryLabel.setText(
                Configuration.GAMEWINDOW_CURRENTCAVALRYLABEL_TEXT + 
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[3]));        
    }
    
    public void drawWaveRemainingKnightsLabel() {
        waveRemainingKnightsLabel.setText(
                Configuration.GAMEWINDOW_REMAININGKNIGHTSLABEL_TEXT + 
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[4]));        
    }
    
    public void drawWaveCurrentKnightsLabel() {
        waveCurrentKnightsLabel.setText(
                Configuration.GAMEWINDOW_CURRENTKNIGHTSLABEL_TEXT + 
                Integer.toString(level.getCurrentWave().getEnemyNumbers()[5]));        
    }
    
    public void drawMoneyLabel() {
        moneyLabel.setText(
                Configuration.GAMEWINDOW_MONEYLABEL_TEXT + 
                level.getMoney());
    }
    
    public void drawMoneyPerWaveLabel() {
        moneyPerTurnLabel.setText(
                Configuration.GAMEWINDOW_MONEYPERWAVELABEL_TEXT + 
                level.getTotalMoneyPerTurn());
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
