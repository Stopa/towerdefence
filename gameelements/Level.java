package towerdefence.gameelements;

<<<<<<< HEAD
import towerdefence.Configuration;
import java.util.*; 

//TODO - FAAS 1
//mängija raha
//iga wave lõpus suurendatakse raha?

public class Level {
       
    private Wave[] waveArray; //"lainete" massiiv
    private int currentWaveIndex; //praeguse laine indeks massiivis
    private ArrayList<Tower> towerList; //tornide järjend
    private Grid[][] gridArray; //gridide kahemõõtmeline massiiv
    
    public Level(String filename) {
        this.towerList = new ArrayList<Tower>(); 
        this.currentWaveIndex = 0; 
        this.loadLevel(filename); //laeb leveli sisse failist
    }         
    
            
    /**
     * Liigutame järgmise laine peale indeksi. 
     */
    public void nextWave() {
        this.currentWaveIndex += 1; //suurendame laine indeksit
        if (currentWaveIndex >= waveArray.length) throw new AssertionError(); 
    }
    
    /**
     * Tagastame nende koordinaatidel oleva gridi. 
     * @param x
     * @param y
     * @return 
     */
    public Grid getGridAt(int x, int y) {
        return gridArray[x][y];
    }
    
    /**
     * Tagastame viite praegu aktiivsele lainele. 
     * @return 
     */
=======
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
    
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
    public Wave getCurrentWave() {
        return waveArray[currentWaveIndex]; 
    }
    
<<<<<<< HEAD
    /**
     * Lisame torni. 
     * @param tower
     * @param grid 
     */
    public void addTower(Tower tower, Grid grid) {
        towerList.add(tower);
        tower.setGrid(grid);
        grid.setTower(tower);        
    }
        
    /**
     * Eemaldame torni. 
     * @param tower 
     */
    public void removeTower(Tower tower) {
        tower.getGrid().setTower(null);
        towerList.remove(tower);
    }    
        
    //TODO - FAAS 1        
    /**
     * Laeb failist sisse leveli ja määrab ära kõik leveli parameetrid. 
     * @param filename 
     */
    private void loadLevel(String filename) {
                            
        
        //LOE LEVEL SISSE
        
        int waveAmount = 0; //TODO - FAAS 1
        
        Wave[] tmpWaveArray = new Wave[waveAmount];
        
        ///TODO - FAAS 1 - SET UP WAVES        
        for (int i = 0; i < waveAmount; i++) {
            tmpWaveArray[i] = new Wave(); 
            //TODO - rest.. 
        }
        
        int levelWidth = 0; //TODO - FAAS 1
        int levelHeight = 0; //TODO - FAAS 1
        
        //siin hoitakse gride..
        Grid[][] tmpGridArray = new Grid[levelWidth][levelHeight]; //TODO - FAAS 1
        //siin hoitakse linked gride.. sama indeks mis gridil, [0] = xcoord, [1] = ycoord
        ArrayList<int[]>[][] tmpLinkedGrids = new ArrayList[levelWidth][levelHeight];
        
        //tekitame ajutise massiivi, kus hoiame lingitud gridide koordinaate - sest
        //objekte endid ei pruugi veel olemas olla.. 
        for (int i = 0; i < levelWidth;i++) {
            for (int j = 0; j < levelHeight; j++) {
                tmpLinkedGrids[i][j] = new ArrayList<int[]>(); 
            }
        }        
                
        //lisame linked gridid nüüd igale gridile.. 
        //TODO - FAAS 1 - VAADATA ÜLE JA TESTIDA!!!
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                ArrayList<Grid> currentLinkedGrids = new ArrayList<Grid>();
                for (int[] coords : tmpLinkedGrids[i][j]) {
                    currentLinkedGrids.add(tmpGridArray[coords[0]][coords[1]]);
                }
                tmpGridArray[i][j].setLinkedGrids(currentLinkedGrids);
            }
        }
        
        
        this.waveArray = tmpWaveArray;
        this.gridArray = tmpGridArray;
    }    
}
   
=======
    

    
    
    
    
    

    
}
>>>>>>> 110b5b1768f56f6d15057b230056ed7088a50e86
