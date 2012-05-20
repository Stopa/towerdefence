package towerdefence.logic;

import towerdefence.Configuration;
import towerdefence.gameelements.*;
import towerdefence.gui.*; 

public class LevelController {
    
    private GameWindow gameWindow;
    private Level level; 
    private WaveController waveController; 
    private Thread waveThread;
    
    public LevelController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;       
        this.level = gameWindow.getLevel(); 
        this.waveController = null; 
        //this.startBuildingPhase();
        //this.startCombatPhase();
    }
                    
    //kutsutakse välja wavecontrolleri poolt iga wave lõpus
    public void startBuildingPhase() {
        
        //TODO - võta ära!!
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (level.getGridAt(i, j).getGridType() == Grid.GridType.BURNED_CASTLE) {
                    level.getGridAt(i, j).setGridType(Grid.GridType.CASTLE);
                    level.getGridAt(i, j).damage(-100);
                }
            }
        }
        
        waveThread = null;    
        waveController = null; //gc       
        level.addMoney(level.getTotalMoneyPerTurn());
        level.setNextWave();
        gameWindow.drawWaveLabel();
        gameWindow.drawWaveCurrentCavalryLabel();
        gameWindow.drawWaveCurrentInfantryLabel();
        gameWindow.drawWaveCurrentKnightsLabel();
        gameWindow.drawWaveRemainingCavalryLabel();
        gameWindow.drawWaveRemainingInfantryLabel();
        gameWindow.drawWaveRemainingKnightsLabel();
        gameWindow.drawMoneyLabel();
        gameWindow.setBuildingPhase(true);
    }
    
    
    //kutsutakse välja siis kui mängija "start" nuppu klikkab vms.. 
    //ehk kui parasjagu on building phase ja mängija otsustab et aitab.. 
    public void startCombatPhase() {        
        gameWindow.setBuildingPhase(false);
        waveController = new WaveController(level.getCurrentWave(), this);        
        waveThread = new Thread(waveController);
        waveThread.start();        
    }
    
    public GameWindow getGameWindow() {
        return this.gameWindow; 
    }
    
    public Level getLevel() {
        return this.level; 
    }

}
