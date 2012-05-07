package towerdefence.logic;

import towerdefence.Configuration;
import towerdefence.gameelements.*;
import towerdefence.gui.*; 
<<<<<<< HEAD
import java.util.ArrayList; 

    //TODO - iga turni lõpus kontrollitakse üle terve enemyList
    //vaadates kas health on <= 0
    //kui jah, invoketakse wave.removeEnemy
    
    //tõmmatakse wavest..    
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86

public class WaveController {
    
    private Wave wave; 
    private LevelController levelController; 
    private boolean waveOver;
<<<<<<< HEAD
    private final ArrayList<Effect> effectList;
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    
    public WaveController(Wave wave, LevelController levelController) {
        
        this.wave = wave;
<<<<<<< HEAD
        this.levelController = levelController;
        this.effectList = new ArrayList<Effect>();
        
        waveOver = false;                         
    }
    
    public void addEffect(Effect effect) {
        this.effectList.add(effect);
    }
    
    public void removeEffect(Effect effect) {
        this.effectList.remove(effect);
=======
        this.levelController = levelController; 
        
        waveOver = false;                 
        
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    }
    
    public void start() {
        
<<<<<<< HEAD
        //TODO - FAAS 1 - vastaste lisamine jne? 
        //praegune mõte - kõik tulevad järjest mingisse ruutu, mis ei asu laual?
        //ja kust järgmine ruut on mingi algusruut? 
        //või.. mingi taoline lähenemine. kõik ei saa ju korraga tulla. 
        
        while(!waveOver) {                      
            turn();         
=======
        while(!waveOver) {
            //TODO
        
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
            this.setWaveOver();
        }
        
    }
    
    private void turn() {
               
        //TODO - siin siis tehakse kõik!!!!!
                
        
<<<<<<< HEAD
        //TODO - FAAS 1 - arvuta raha
        
        //TODO - FAAS 2/3 - arvuta mana jne
        
        
       
        //TODO - FAAS 1 - määrata kollide liikumine
        for (Enemy enemy : wave.getEnemyList()) {
            
        }
        
        //TODO - FAAS 1 - määra tulistamised ja asjad
        
        
        //TODO - FAAS 1 - tee kollide liikumine
                
        
        
        //protsessime efektid
        for (Effect effect : effectList) {
            effect.process();
        }        
        
        //eemaldame surnud vastased
        for (Enemy enemy : wave.getEnemyList()) {
           if (enemy.getHealth() <= 0) {
               wave.removeEnemy(enemy);
           }
        }        
=======
        //arvuta rahad, manad, jne
        
        
        //liiguta kollid uutele kohtadele
        
        
        //protsessi efektid
        
        
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
        
        levelController.getGameWindow().update();
    }
    
    //TODO - siin siis teha kõik microliikumine.. 
    //mõelda.. kuidas täpselt.. 
    private void microturn() {
        
<<<<<<< HEAD
        //TODO - lahendada asi nii, et igale enemyle paned külge mingi linkedlisti vms?
        //kust võtab järjest uusi positsiooni vastavalt microturnile? sest need positsioonid
        //saab ju turni alguses välja arvutada..? 
        
        //ja kuna moveEnemy tehakse? 
        
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    }
    
    private void setWaveOver() {
        //TODO - arvuta kas wave on läbi.. 
        
<<<<<<< HEAD
        //siin vaata seda ka kas effectslist on tühi vms..? 
        
=======
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
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
