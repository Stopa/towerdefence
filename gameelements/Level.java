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
    private int money;
    private int totalMoneyPerTurn;
    private final String name; 
    private ArrayList<Grid> startingGrids; //leveli ääres olevat gridid, kust vastased alustada saavad
    private boolean castleBurned;

	public Level(String filename) {
        this.castleBurned = false; 
        this.towerList = new ArrayList<Tower>(); 
        this.currentWaveIndex = 0; 
        this.name = filename; 
        this.loadLevel(filename); //laeb leveli sisse failist;   
        this.setStartingGrids();
        this.money = Configuration.STARTING_MONEY;
        initTotalMoneyperTurn();
    }       
        
    public boolean isCastleBurned() {
        return this.castleBurned;
    }
    
    public void setCastleBurned() {
        this.castleBurned = true; 
    }
        
    public int getTotalMoneyPerTurn() {
        return this.totalMoneyPerTurn;
    }
    
    public void setTotalMoneyPerTurn(int newValue) {
        this.totalMoneyPerTurn = newValue; 
    }
        
    
    private void setStartingGrids() {
        this.startingGrids = new ArrayList<Grid>();
        
        for (int i = 0; i < gridArray[0].length; i++) {
            Grid current = gridArray[0][i];
            if (current.getGridType().isPassable()) {
                if (!startingGrids.contains(current))
                startingGrids.add(current);
            }
            current = gridArray[gridArray.length-1][i];
            if (current.getGridType().isPassable()) {
                if (!startingGrids.contains(current))
                startingGrids.add(gridArray[gridArray.length-1][i]);
            }
            current = gridArray[i][0];
            if (current.getGridType().isPassable()) {
                if (!startingGrids.contains(current))
                startingGrids.add(gridArray[i][0]);
            }
            current = gridArray[i][gridArray[i].length-1];
            if (current.getGridType().isPassable()) {
                if (!startingGrids.contains(current))
                startingGrids.add(gridArray[i][gridArray[i].length-1]);
            }                                            
        }
        
        //TODO - testimiseks/debugimiseks - kustutada
        /*
        for (Grid grid : startingGrids) {
            System.out.print(grid.getX() + " ");
            System.out.println(grid.getY());
        }
         * 
         */
        
        /*
        for (int i = 0; i < 10; i++) {
            Grid grid = getRandomStartingGrid();
            System.out.print(grid.getX() + " ");
            System.out.println(grid.getY());            
        }
         * 
         */
        
    }
    
    public Grid getRandomStartingGrid() {   
        return startingGrids.get(new Random().nextInt(startingGrids.size()));
    }
        
    public String getName() {
        return this.name; 
    }
    
    public void addMoney(int money) {
    	this.money += money;
    }
    
    public void removeMoney(int money) {
    	this.money += money;
    }
    
    public int getMoney() {
    	return this.money;
    }
    // Kas nii ?
    private void initTotalMoneyperTurn() {
		for (int i=0;i<gridArray.length;i++) {
			for (int j=0;j<gridArray.length;j++) {
				this.totalMoneyPerTurn += gridArray[i][j].getGridType().getMoneyPerTurn();
			}
		}
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
        		Grid gd = new Grid(tx,ty,gt, this);
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
        //TODO - FAAS 1 - VAADATA �?LE JA TESTIDA!!!
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
