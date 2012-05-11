package towerdefence.gameelements;

import towerdefence.Configuration;
import java.util.*; 
import java.io.*; 

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

    public Wave getCurrentWave() {
        return waveArray[currentWaveIndex]; 
    }
    
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
    
    public ArrayList<Tower> getTowerList() {
        return this.towerList;
    }
        
    //TODO - FAAS 1        
    /**
     * Laeb failist sisse leveli ja määrab ära kõik leveli parameetrid. 
     * @param filename 
     */
    private void loadLevel(String filename) {
        
        File f = null;
        FileReader fr = null;
        BufferedReader bfr = null;  
        String scontent = null;
        
        try {
            f = new File(filename);        
            fr = new FileReader(f);
            bfr = new BufferedReader(fr);        
            char[] ccontent = new char[(int)f.length()];                    
            bfr.read(ccontent);                                    
            scontent = new String(ccontent);
            ccontent = null;            
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        int ptr = 0;  
        
        int levelHeight = Integer.parseInt(scontent.substring(ptr, ptr+2));
        ptr += 2; 
        int levelWidth = Integer.parseInt(scontent.substring(ptr, ptr+2));
        ptr += 2;

		//TODO - linked grids
        //siin hoitakse linked gride.. sama indeks mis gridil, [0] = xcoord, [1] = ycoord
        ArrayList<int[]>[][] tmpLinkedGrids = new ArrayList[levelWidth][levelHeight];
        
        Grid[][] tmpGridArray = new Grid[levelWidth][levelHeight];
        
        for(int y = 0;y<levelHeight;y++) {
        	for(int x = 0;x<levelWidth;x++) {
        		int tx = Integer.parseInt(scontent.substring(ptr, ptr+2));
        		ptr += 2;
        		int ty = Integer.parseInt(scontent.substring(ptr, ptr+2));
        		ptr += 2;
        		Grid.GridType gt = Grid.GridType.getById(Integer.parseInt(scontent.substring(ptr, ptr+2)));
        		ptr += 2;
        		Grid gd = new Grid(tx,ty,gt,this);
        		tmpGridArray[tx][ty] = gd;
        	}
        }    

        
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
        
        int waveAmount = Integer.parseInt(scontent.substring(ptr,ptr+2));
        ptr += 2;
        
        Wave[] tmpWaveArray = new Wave[waveAmount];
             
        for (int i = 0; i < waveAmount; i++) {
        	int infantry =Integer.parseInt(scontent.substring(ptr, ptr+3));
        	ptr += 3;
        	int knights =Integer.parseInt(scontent.substring(ptr, ptr+3));
        	ptr += 3;
        	int cavalry =Integer.parseInt(scontent.substring(ptr, ptr+3));
        	ptr += 3;
            tmpWaveArray[i] = new Wave(this, infantry, knights, cavalry); 
        }        
        
        
        this.waveArray = tmpWaveArray;
        this.gridArray = tmpGridArray;
    }    
}


/*
 *         //TODO 
        
        //esimesed 3 numbrit: x posid
        //3-6: y posid
        
        //ehk x * y - gridide arv
        
        //iga gridi kohta X numbrit:
        //0-1: 1 taseme layer (grid type), int näol
        //2-3: 2 taseme layer (tornid jne), int näol
        //4: lingitud gridide arv (0-9?)
        //lingitud gridide arv * 4: lingitud gridid formaadis xx-yy
        
        //järgmised 2: wavede hulk
        //iga wave puhul:
        //esimene number: kollide hulk waves
        //kollide hulk * 2: (iga kolli puhul 2 numbrit): kolli tüüp

                
        

            
                      
            for(int iy = 0;iy < height;iy++) {
            	for(int ix = 0; ix < width; ix++) {
            		int tx = Integer.parseInt(scontent.substring(ptr, ptr+2));
            		ptr += 2;
            		int ty = Integer.parseInt(scontent.substring(ptr, ptr+2));
            		ptr += 2;
            		GridButton gb = new GridButton(tx,ty);
            		gb.addActionListener(this);
            		gb.setTerrainType(TerrainType.getById(Integer.parseInt(scontent.substring(ptr, ptr+2))));
            		ptr += 2;
            		//TODO - linked grid squares
            		this.gridButtonsPanel.add(gb);
            		this.gridButtons[tx][ty] = gb;
            	}
            }
            this.gridButtonsPanel.doLayout();
 * 
 * 
 */


/*
            
            nrOfWaves = Integer.parseInt(scontent.substring(ptr, ptr+2));
            ptr += 2; 
            
            waves = new ArrayList<Wave>();
            waveNoComboBox.removeActionListener(this); // holla holla get dolla
            waveNoComboBox.removeAllItems(); // fuck the police
            waveNoComboBox.addActionListener(this); // sue me
            
            for (int i = 0; i < nrOfWaves; i++) {
            	int currentWaveInfantry = Integer.parseInt(scontent.substring(ptr, ptr+3));
            	ptr += 3;
            	int currentWaveKnights = Integer.parseInt(scontent.substring(ptr, ptr+3));
            	ptr += 3;
            	int currentWaveArchers = Integer.parseInt(scontent.substring(ptr, ptr+3));
            	ptr += 3;
                Wave wv = new Wave(currentWaveInfantry, currentWaveKnights, currentWaveArchers);
                addWave(wv);
            }
        }
 */