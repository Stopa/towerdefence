package towerdefence;

//package towerdefence;

import towerdefence.logic.LevelController;

public class TowerDefence {

    public static void main(String[] args) {
        // TODO code application logic here
        LevelController lc = new towerdefence.logic.LevelController(new towerdefence.gui
                .GameWindow(new towerdefence
                .gameelements.Level("src\\towerdefence\\testlevel.map"))); 
        lc.startCombatPhase();
    }
}
