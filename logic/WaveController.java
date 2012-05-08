package towerdefence.logic;

import towerdefence.Configuration;
import towerdefence.gameelements.*;
import towerdefence.gui.*; 
import java.util.ArrayList; 




public class WaveController {
    
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
    
    public void start() {
        

        //TODO - FAAS 1 - vastaste lisamine jne? 
        //praegune mõte - kõik tulevad järjest mingisse ruutu, mis ei asu laual?
        //ja kust järgmine ruut on mingi algusruut? 
        //või.. mingi taoline lähenemine. kõik ei saa ju korraga tulla. 
        
        while(!waveOver) {                      
            turn();         

            this.setWaveOver();
        }
        
    }
    
    private void turn() {                

        //TODO - FAAS 1 - arvuta raha
        
        //TODO - FAAS 2/3 - arvuta mana jne
        
        
       
        //TODO - FAAS 1 - määrata kollide liikumine
        for (Enemy enemy : wave.getEnemyList()) {
            
        }
        
        //TODO - FAAS 1 - määra tulistamised ja asjad
        //checkid, et kas saab tulistada ja kas vastane on ranges
        //tehakse toweri fire() meetodis 
        for (Tower tower : wave.getLevel().getTowerList()) {
            tower.fire();
        }
        
        
        //TODO - FAAS 1 - tee kollide liikumine
        for (Enemy enemy : wave.getEnemyList()) {
            enemy.calculateMovePath();
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

        
        levelController.getGameWindow().update();
    }
    
    //TODO - siin siis teha kõik microliikumine.. 
    //mõelda.. kuidas täpselt.. 
    private void microturn() {
        

        //TODO - lahendada asi nii, et igale enemyle paned külge mingi linkedlisti vms?
        //kust võtab järjest uusi positsiooni vastavalt microturnile? sest need positsioonid
        //saab ju turni alguses välja arvutada..? 
        
        //ja kuna moveEnemy tehakse? 
        

    }
    
    private void setWaveOver() {
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
