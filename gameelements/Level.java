package towerdefence.gameelements;

import java.util.*; 

public class Level {
    
    
    //TODO - iga turni lõpus kontrollitakse üle terve enemyList
    //kasutades isActive meetodi iga enemy peal
    //kui returnib false, siis see Enemy on selle turni ajal surnud
    //eemaldatakse listist    
    
    //tõmmatakse wavest..    
    private final Wave[] waveArray; 
    private int currentWaveIndex; 
    
    public Level(Wave[] waveArray) {
        this.waveArray = waveArray; 
    }
            
    public void nextWave() {
        this.currentWaveIndex += 1; 
        if (currentWaveIndex >= waveArray.length) throw new RuntimeException(); //TODO
        
    }
    
    public Wave getCurrentWave() {
        return waveArray[currentWaveIndex]; 
    }
    
    

    
    
    
    
    

    
}
