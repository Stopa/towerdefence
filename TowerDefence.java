package towerdefence;

//package towerdefence;

import towerdefence.logic.LevelController;
import towerdefence.gui.GameWindow;

public class TowerDefence {

    public static void main(String[] args) {
        // TODO code application logic here
        GameWindow gw = new GameWindow(new towerdefence
                .gameelements.Level("src\\towerdefence\\testlevel.map"));
        
        LevelController lc = new LevelController(gw);
        
        gw.setLevelController(lc);
        lc.startBuildingPhase();
    }
}
