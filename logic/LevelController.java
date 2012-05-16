package towerdefence.logic;

import towerdefence.Configuration;
import towerdefence.gameelements.*;
import towerdefence.gui.*; 

public class LevelController {
    
    private GameWindow gameWindow;
    private Level level; 
    private WaveController waveController;    
    
    public LevelController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;       
        this.level = gameWindow.getLevel(); 
        this.waveController = null; 
        //this.startBuildingPhase();
        //this.startCombatPhase();
    }
                    
    //kutsutakse välja wavecontrolleri poolt iga wave lõpus
    public void startBuildingPhase() {
                
        waveController = null; //gc        
        gameWindow.setBuildingPhase(true);
    }
    
    
    //kutsutakse välja siis kui mängija "start" nuppu klikkab vms.. 
    //ehk kui parasjagu on building phase ja mängija otsustab et aitab.. 
    public void startCombatPhase() {
        gameWindow.setBuildingPhase(false);
        waveController = new WaveController(level.getCurrentWave(), this); 
        waveController.start();         
        
    }
    
    public GameWindow getGameWindow() {
        return this.gameWindow; 
    }
    
    public Level getLevel() {
        return this.level; 
    }

}
