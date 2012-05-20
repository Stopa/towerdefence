package towerdefence.logic;

import towerdefence.gameelements.*;
import java.util.ArrayList; 
import towerdefence.Configuration;

public class WaveController implements Runnable {
    
    private Wave wave; 
    private LevelController levelController; 
    private boolean waveOver;
    private boolean gameOver; 
    
    public WaveController(Wave wave, LevelController levelController) {
        
        this.wave = wave;
        this.levelController = levelController;        
        
        waveOver = false;                         
    }
    

    
    @Override
    public void run() {
        this.waveOver = false;
        levelController.getLevel().setCastleBurned(false);
        
        
        while(!waveOver) {                  
            if (isGameOver()) {
                System.out.println("KAOTASID!");
                return; 
            }
            turn();         
            this.setWaveOver();
        } 
            
        if (wave.getLevel().getCurrentWaveNumber() < 
            wave.getLevel().getTotalWaves())
            levelController.startBuildingPhase();    
        else
            System.out.println("SINU VÕIT!");

    }
    
    private void turn() {       
        
        
        //TODO - turni lõpus peab kollile määrama ka uue positsiooni reaalselt.. 

        //TODO - FAAS 1 - arvuta raha
        
        //TODO - FAAS 2/3 - arvuta mana jne
        
               
        //lisame uusi kolle platsile.. 
        if (wave.hasInactiveEnemies()) {
            Enemy enemy = wave.addNewEnemy();            
            if (enemy.getType().equals(Configuration.ENEMY_INFANTRY_TYPE)) {
                levelController.getGameWindow().drawWaveCurrentInfantryLabel();
                levelController.getGameWindow().drawWaveRemainingInfantryLabel();
            }
            else if (enemy.getType().equals(Configuration.ENEMY_CAVALRY_TYPE)) {
                levelController.getGameWindow().drawWaveCurrentCavalryLabel();
                levelController.getGameWindow().drawWaveRemainingCavalryLabel();
            }
            else if (enemy.getType().equals(Configuration.ENEMY_KNIGHT_TYPE)) {
                levelController.getGameWindow().drawWaveCurrentKnightsLabel(); 
                levelController.getGameWindow().drawWaveRemainingKnightsLabel();
            }
        }        
        
        //TODO - FAAS 1 - määra tulistamised ja asjad
        //checkid, et kas saab tulistada ja kas vastane on ranges
        //tehakse toweri fire() meetodis 
        for (Tower tower : wave.getLevel().getTowerList()) {
            tower.fire();
        }
        
        
        //arvutame kollide liikumise..
        for (Enemy enemy : wave.getEnemyList()) {
            enemy.calculateMovePath();
        }                                
        
        //protsessime efektid
        levelController.getGameWindow().drawMoneyPerWaveLabel();
        levelController.getGameWindow().update();
        
        for (Enemy enemy : wave.getEnemyList()) {
            enemy.hitBuilding();
        }
        
        //protsessime efektid
        for (Effect effect : wave.getEffectList()) {
            effect.process();
        }            
        
        for (Enemy enemy : wave.getEnemyList()) {
            enemy.setNewGrid();
        }        
        
        ArrayList<Effect> tmpEffectList = new ArrayList<Effect>();        
        for (Effect effect : wave.getEffectList()) {
            if (effect.isElapsed()) 
                tmpEffectList.add(effect);
        }                
        
        
        for (Effect effect : tmpEffectList) {
            wave.removeEffect(effect);
        }  
        tmpEffectList = null;
                
        //eemaldame surnud vastased        
        ArrayList<Enemy> tmpList = new ArrayList<Enemy>();
        for (Enemy enemy : wave.getEnemyList()) {
            if (enemy.getCurrentHealth() <= 0)
                tmpList.add(enemy);
        }
        
        for (Enemy enemy : tmpList) {                
            wave.removeEnemy(enemy); 
            if (enemy.getType().equals(Configuration.ENEMY_INFANTRY_TYPE))
                levelController.getGameWindow().drawWaveCurrentInfantryLabel();
            else if (enemy.getType().equals(Configuration.ENEMY_CAVALRY_TYPE))
                levelController.getGameWindow().drawWaveCurrentCavalryLabel();
            else if (enemy.getType().equals(Configuration.ENEMY_KNIGHT_TYPE)) 
                levelController.getGameWindow().drawWaveCurrentKnightsLabel();
        }         
        tmpList = null;
    }
    

    private boolean isGameOver() {
        return wave.getLevel().isCastleBurned();
    }
    
    private void setWaveOver() {
        waveOver = (wave.getEnemyList().isEmpty() && !wave.hasInactiveEnemies());

    }
}
