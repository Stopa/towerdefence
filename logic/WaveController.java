package towerdefence.logic;

import towerdefence.gameelements.*;
import java.util.ArrayList; 

public class WaveController implements Runnable {
    
    private Wave wave; 
    private LevelController levelController; 
    private boolean waveOver;
    private final ArrayList<Effect> effectList;
    
    public WaveController(Wave wave, LevelController levelController) {
        
        this.wave = wave;
        this.levelController = levelController;
        this.effectList = new ArrayList<Effect>();
        
        waveOver = false;                         
    }
    
    public void addEffect(Effect effect) {
        this.effectList.add(effect);
    }
    
    public void removeEffect(Effect effect) {
        this.effectList.remove(effect);

    }
    
    @Override
    public void run() {
        this.waveOver = false;
        levelController.getLevel().setCastleBurned(false);
        
        while(!waveOver) {                  
            turn();         
            this.setWaveOver();
        } 
        System.out.println("Game over!"); //TODO - tee vingemaks!                
        levelController.startBuildingPhase();    

    }
    
    private void turn() {       
        
        
        //TODO - turni lõpus peab kollile määrama ka uue positsiooni reaalselt.. 

        //TODO - FAAS 1 - arvuta raha
        
        //TODO - FAAS 2/3 - arvuta mana jne
        
               
        //lisame uusi kolle platsile.. 
        if (wave.hasInactiveEnemies()) {
            wave.addNewEnemy();
            
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
        
        for (Enemy enemy : wave.getEnemyList()) {
            enemy.hitBuilding();
        }
        
        //protsessime efektid
        for (Effect effect : effectList) {
            effect.process();
        }        
        
        //eemaldame surnud vastased
        for (Enemy enemy : wave.getEnemyList()) {
           if (enemy.getCurrentHealth() <= 0) {
               wave.removeEnemy(enemy);
           }
        }               
        
        for (Enemy enemy : wave.getEnemyList()) {
            enemy.setNewGrid();
        }
        
        levelController.getGameWindow().update();
    }
    

    
    private void setWaveOver() {
        waveOver = wave.getLevel().isCastleBurned();
        //TODO - arvuta kas wave on läbi.. 
        
        //siin vaata seda ka kas effectslist on tühi vms..? 
        

    }
    
    
    //põhimõtteliselt timed loop, mis iga aja tagant protsessib ühe turni
    
    
    //kui kõik on läbi (kas mingi player objekt hävitatud või kõik vastased surnud)
    //siis timer peatub 
    //pmst see tingimus võib isegi olla while (???) eks
    //ja iga turni lõpus arvutatakse
    
    
    //aga jah kui timer peatub
    
    //siis kutsutakse levelcontrolleris välja uus see
    //eks
    //ee
    //meetod tähendab
    //mille peale levelcontroller nullib praeguse wavecontrolleri
    //laseb ehitada
    //ja kui start vajutatakse siis algab uus wavecontroller.. 
    

}
