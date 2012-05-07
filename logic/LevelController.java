package towerdefence.logic;

import towerdefence.Configuration;
import towerdefence.gameelements.*;
import towerdefence.gui.*; 

public class LevelController {
    
    private GameWindow gameWindow;
    private Level level; 
    private WaveController waveController;
    
    public LevelController(GameWindow gameWindow, Level level) {
        
    }
    
    
    
    
    
    //kutsutakse välja wavecontrolleri poolt
    public void startBuildingPhase() {
        //TODO
        
        
        waveController = null; //gc
        
        gameWindow.setBuildingPhase(true);
    }
    
    
    //kutsutakse välja siis kui mängija "start" nuppu klikkab vms.. 
    public void startCombatPhase() {
        gameWindow.setBuildingPhase(false);
        waveController = new WaveController(level.getCurrentWave(), this); 
        waveController.start(); 
        
        //loo uus waveController..
        
        
        //TODO
    }
    
    public GameWindow getGameWindow() {
        return this.gameWindow; 
    }
    
    public Level getLevel() {
        return this.level; 
    }

}
