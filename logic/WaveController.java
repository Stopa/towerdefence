package towerdefence.logic;

import towerdefence.Configuration;
import towerdefence.gameelements.*;
import towerdefence.gui.*; 

public class WaveController {
    
    private Wave wave; 
    private LevelController levelController; 
    private boolean waveOver;
    
    public WaveController(Wave wave, LevelController levelController) {
        
        this.wave = wave;
        this.levelController = levelController; 
        
        waveOver = false;                 
        
    }
    
    public void start() {
        
        while(!waveOver) {
            //TODO
        
            this.setWaveOver();
        }
        
    }
    
    private void turn() {
               
        //TODO - siin siis tehakse kõik!!!!!
                
        
        //arvuta rahad, manad, jne
        
        
        //liiguta kollid uutele kohtadele
        
        
        //protsessi efektid
        
        
        
        levelController.getGameWindow().update();
    }
    
    //TODO - siin siis teha kõik microliikumine.. 
    //mõelda.. kuidas täpselt.. 
    private void microturn() {
        
    }
    
    private void setWaveOver() {
        //TODO - arvuta kas wave on läbi.. 
        
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
