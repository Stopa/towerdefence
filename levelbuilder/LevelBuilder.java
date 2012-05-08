package towerdefence.levelbuilder;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.*; 
import java.io.*; 

public class LevelBuilder extends JFrame implements ActionListener {
    

    private GridButton[][] gridButtons; 
    private JPanel gridButtonsPanel;
    
    /*
     * io
     */
    private JTextField filenameField; 
    private JLabel filenameLabel;
    private JButton loadButton;
    private JButton saveButton;
    private JLabel ioStatusLabel; //kas faili ei leitud vms, või laeti edukalt, jne.    
    private JButton newButton;
    
    /*
     * level size
     */
    private JTextField newXField;
    private JLabel newXLabel;
    private JTextField newYField; 
    private JLabel newYLabel;
    private JButton newSizeConfirm;
    
    /*
     * waves
     */
    private JLabel wavesLabel;
    private JButton waveAddButton;
    private JButton waveRemoveButton;
    private JComboBox waveNoComboBox;
    
    /*
     * fields responsible for enemies in current wave
     */
    private JLabel waveInfantryLabel;
    private JTextField waveInfantryField;
    private JLabel waveKnightLabel;
    private JTextField waveKnightField;
    private JLabel waveArcherLabel;
    private JTextField waveArcherField;
    private JButton saveWaveButton;
    
    /*
     * terrain drawing
     */
    private JLabel drawingLabel;
    private JButton grassButton;
    private JButton pathButton;
    private JButton forestButton;
    private JButton outpostButton;
    //private JButton cleanButton;
    private TerrainType currentTerrainType;
        
    //Level data:
    private int height;
    private int width; 
    
    private int nrOfWaves; 
    private ArrayList<Wave> waves; 
    
    //?
    
    public static void main(String[] args) {
        new LevelBuilder(); //TODO
    }
    
    public LevelBuilder() {   
        
        gridButtonsPanel = new JPanel();
        gridButtonsPanel.setLayout(new GridLayout(20,20));
        gridButtonsPanel.setBounds(200,10,500,500);
        this.add(gridButtonsPanel);
        
        /*
         * File control inputs - loading, saving, creating new level
         */
        
        ioStatusLabel = new JLabel();
        //ioStatusLabel.setForeground(Color.RED);
        ioStatusLabel.setBounds(10,10,180,20);
        this.add(ioStatusLabel);
        
        filenameField = new JTextField();
        filenameField.setBounds(10,52,180,20);
        this.add(filenameField);
        
        filenameLabel = new JLabel("Failinimi");
        filenameLabel.setBounds(10,30,50,20);
        this.add(filenameLabel);
        
        loadButton = new JButton("Lae");
        loadButton.setBounds(10,75,90,30);
        loadButton.addActionListener(this);
        this.add(loadButton);
        
        saveButton = new JButton("Salvesta");
        saveButton.setBounds(100,75,90,30);
        saveButton.addActionListener(this);
        this.add(saveButton);

        newButton = new JButton("Uus");
        newButton.setBounds(10,105,180,30);
        newButton.addActionListener(this);
        this.add(newButton);
        
        /*
         * Level size controls
         */
        
        newXLabel = new JLabel("Laius");
        newXLabel.setBounds(10,180,85,20);
        this.add(newXLabel);
        
        newXField = new JTextField("20");
        newXField.setBounds(10,205,85,20);
        this.add(newXField);
        
        newYLabel = new JLabel("Kõrgus");
        newYLabel.setBounds(100,180,90,20);
        this.add(newYLabel);
        
        newYField = new JTextField("20");
        newYField.setBounds(100,205,90,20);
        this.add(newYField);
        
        newSizeConfirm = new JButton("Rakenda");
        newSizeConfirm.setBounds(10,230,180,20);
        newSizeConfirm.addActionListener(this);
        this.add(newSizeConfirm);
        
        /*
         * Waves
         */
        
        wavesLabel = new JLabel("Lained");
        wavesLabel.setBounds(10,270,180,20);
        this.add(wavesLabel);
        
        waveNoComboBox = new JComboBox();
        waveNoComboBox.setBounds(10,295,90,20);
        waveNoComboBox.addActionListener(this);
        this.add(waveNoComboBox);
        
        waveAddButton = new JButton("+");
        waveAddButton.setBounds(100,295,45,20);
        waveAddButton.addActionListener(this);
        this.add(waveAddButton);
        
        waveRemoveButton = new JButton("-");
        waveRemoveButton.setBounds(145,295,45,20);
        waveRemoveButton.addActionListener(this);
        this.add(waveRemoveButton);
        
        /*
         * Wave creeps
         */
        
        waveInfantryLabel = new JLabel("Jalavägi");
        waveInfantryLabel.setBounds(10,320,140,20);
        this.add(waveInfantryLabel);
        
        waveInfantryField = new JTextField();
        waveInfantryField.setBounds(150,320,40,20);
        this.add(waveInfantryField);
        
        waveKnightLabel = new JLabel("Rüütlid");
        waveKnightLabel.setBounds(10,345,140,20);
        this.add(waveKnightLabel);
        
        waveKnightField = new JTextField();
        waveKnightField.setBounds(150,345,40,20);
        this.add(waveKnightField);
        
        //las jääda hetkel nii.. 
        waveArcherLabel = new JLabel("Ratsavägi");
        waveArcherLabel.setBounds(10,370,140,20);
        this.add(waveArcherLabel);
        
        waveArcherField = new JTextField();
        waveArcherField.setBounds(150,370,40,20);
        this.add(waveArcherField);
        
        saveWaveButton = new JButton("Salvesta");
        saveWaveButton.setBounds(10,395,180,20);
        saveWaveButton.addActionListener(this);
        this.add(saveWaveButton);
        
        /*
         * Drawing terrain
         */
        drawingLabel = new JLabel("Joonistamine");
        drawingLabel.setBounds(10,440,180,20);
        this.add(drawingLabel);
        
        grassButton = new JButton("Rohi");
        grassButton.setBounds(10,465,90,20);
        grassButton.addActionListener(this);
        grassButton.setBackground(Color.green);
        grassButton.setForeground(Color.white);
        this.add(grassButton);
        
        pathButton = new JButton("Tee");
        pathButton.setBounds(100,465,90,20);
        pathButton.addActionListener(this);
        pathButton.setBackground(Color.orange);
        pathButton.setForeground(Color.white);
        this.add(pathButton);
        
        forestButton = new JButton("Mets");
        forestButton.setBounds(10,485,90,20);
        forestButton.addActionListener(this);
        forestButton.setBackground(Color.black);
        forestButton.setForeground(Color.white);
        this.add(forestButton);
        
        outpostButton = new JButton("Küla");
        outpostButton.setBounds(100,485,90,20);
        outpostButton.addActionListener(this);
        outpostButton.setBackground(Color.blue);
        outpostButton.setForeground(Color.white);
        this.add(outpostButton);
        
        /*cleanButton = new JButton("Kustuta");
        cleanButton.setBounds(100,485,90,20);
        cleanButton.addActionListener(this);
        this.add(cleanButton);*/
        
        /*
         * end input fields
         */
        
        this.setSize(715,550);
        this.setResizable(false);
        this.setLayout(null);                        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true);
        
        this.currentTerrainType = TerrainType.GRASS;
        
        newLevel();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	this.ioStatusLabel.setText("");
    	
    	/*
    	 * IO
    	 */
        if(e.getSource() == this.newButton) {
        	newLevel();
        } else if(e.getSource() == this.saveButton) {
        	saveLevel();
        } else if(e.getSource() == this.loadButton) {
        	loadLevel();
        } /* new level size */else if(e.getSource() == this.newSizeConfirm) {
        	int newx = Integer.parseInt(newXField.getText());
        	int newy = Integer.parseInt(newYField.getText());
        	height = newy;
        	width = newx;
        	gridButtonsPanel.removeAll();
        	gridButtonsPanel.setLayout(new GridLayout(newx, newy));
        	
        	gridButtons = new GridButton[newx][newy];
        	for(int y = 0;y<newy;y++) {
        		for(int x = 0; x<newx;x++) {
        			GridButton gb = new GridButton(x,y);
        			gb.addActionListener(this);
        			this.gridButtonsPanel.add(gb);
        			this.gridButtons[x][y] = gb;
        		}
        	}
        	
        	gridButtonsPanel.doLayout();
        } /* waves */else if(e.getSource() == this.waveAddButton) {
        	addWave(new Wave());
        } else if(e.getSource() == this.waveRemoveButton) {
        	removeCurrentWave();
        } else if(e.getSource() == this.waveNoComboBox) {
        	openWave();
        } else if(e.getSource() == this.saveWaveButton) {
        	saveCurrentWave();
        } /* drawing */else if(e.getSource() == this.grassButton) {
        	this.currentTerrainType = TerrainType.GRASS;
        } else if(e.getSource() == this.pathButton) {
        	this.currentTerrainType = TerrainType.PATH;
        } else if(e.getSource() == this.forestButton) {
        	this.currentTerrainType = TerrainType.FOREST;
        } else if(e.getSource() == this.outpostButton) {
        	this.currentTerrainType = TerrainType.OUTPOST;
        } /*else if(e.getSource() == this.cleanButton) {
        	this.currentTerrainType = TerrainType.EMPTY;
        }*/ /* grid buttons */else if(e.getSource().getClass().getName() == "towerdefence.levelbuilder.LevelBuilder$GridButton") {
        	GridButton object = (GridButton)e.getSource();
        	object.setTerrainType(this.currentTerrainType);
        }
    }
    
    /*
     * Add a new wave
     */
    private void addWave(Wave wv) {
    	if(waves.size() == 99) {
    		return;
    	}
    	waves.add(wv);
    	waveNoComboBox.addItem(waves.indexOf(wv)+1);
    	waveNoComboBox.setSelectedIndex(waves.indexOf(wv));
    }
    
    private void removeCurrentWave() {
    	if(waves.size() == 1) {
    		return;
    	}
    	int index = waveNoComboBox.getSelectedIndex();
    	
    	waves.remove(index);
    	waveNoComboBox.removeItemAt(waves.size());
    	waveNoComboBox.setSelectedIndex(0);
    }
    
    private void openWave() {
    	int index = waveNoComboBox.getSelectedIndex();
    	Wave currwave = waves.get(index);
    	waveInfantryField.setText(currwave.getInfantry()+"");
    	waveKnightField.setText(currwave.getKnights()+"");
    	waveArcherField.setText(currwave.getArchers()+"");
    }
    
    private void saveCurrentWave() {
    	Wave currwave = waves.get(waveNoComboBox.getSelectedIndex());
    	
    	currwave.setInfantry(Integer.parseInt(waveInfantryField.getText()));
    	currwave.setKnights(Integer.parseInt(waveKnightField.getText()));
    	currwave.setArchers(Integer.parseInt(waveArcherField.getText()));
    }
    
    private void newLevel() {
    	int x = 20;
    	width = x;
    	int y = 20;
    	height = y;
    	
    	this.gridButtonsPanel.removeAll();
    	this.gridButtonsPanel.setLayout(new GridLayout(x,y));
    	
    	this.gridButtons = new GridButton[x][y];
    	
        for(int iy = 0;iy < y;iy++) {
        	for(int ix = 0; ix < x; ix++) {
        		GridButton gb = new GridButton(ix,iy);
        		gb.addActionListener(this);
        		this.gridButtonsPanel.add(gb);
        		this.gridButtons[ix][iy] = gb;
        	}
        }
        this.gridButtonsPanel.doLayout();
        
        waves = new ArrayList<Wave>();
        waveNoComboBox.removeActionListener(this); // holla holla get dolla
        waveNoComboBox.removeAllItems(); // fuck the police
        waveNoComboBox.addActionListener(this); // sue me
        
        addWave(new Wave());
    }
    
    private void saveLevel() {
        String filename = this.filenameField.getText();
        if(filename.length() == 0) {
        	this.ioStatusLabel.setText("Sisestage failinimi!");
        	return;
        }
        
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        String toWrite = this.width+""+this.height;
        
        for(int y = 0;y < height;y++) {
        	for(int x = 0;x<width;x++) {
        		GridButton gb = gridButtons[x][y];
        		String xs;
        		String ys;
        		if((x+"").length() < 2) {
        			xs = "0"+x;
        		} else {
        			xs = ""+x;
        		}
        		if((y+"").length() < 2) {
        			ys = "0"+y;
        		} else {
        			ys = ""+y;
        		}
        		toWrite += xs+ys;
        		toWrite += gb.getTerrainType().getIdString();
        	}
        }
        
        if((""+waves.size()).length() < 2) {
        	toWrite += "0"+waves.size();
        } else {
        	toWrite += ""+waves.size();        	
        }
        for(Wave wv: waves) {
        	toWrite += wv.getInfantryString();
        	toWrite += wv.getKnightsString();
        	toWrite += wv.getArchersString();
        }
        
        try {
        	f = new File(filename);
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            
            bw.write(toWrite);
        } catch(Exception e) {
        	this.ioStatusLabel.setText("Midagi läks väga valesti salvestamisel!");
        } finally {
        	try {
        		bw.close();
        		fw.close();
        	} catch(Exception e) {}
        }
    }
    
    private void loadLevel() {
        //TODO 
        
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
    	
    	String filename = this.filenameField.getText();
    	if(filename.length() == 0) {
    		this.ioStatusLabel.setText("Sisestage failinimi!");
    		return;
    	}
                
        
        File f = null;
        FileReader fr = null;
        BufferedReader bfr = null;    
        
        try {
            f = new File(filename);        
            fr = new FileReader(f);
            bfr = new BufferedReader(fr);        
            char[] ccontent = new char[(int)f.length()];                    
            bfr.read(ccontent);                                    
            String scontent = new String(ccontent);
            ccontent = null;
            
            int ptr = 0;             
            
            height = Integer.parseInt(scontent.substring(ptr, ptr+2));
            ptr += 2; 
            width = Integer.parseInt(scontent.substring(ptr, ptr+2));
            ptr += 2;
            
        	this.gridButtonsPanel.removeAll();
        	this.gridButtonsPanel.setLayout(new GridLayout(width, height));
        	
        	this.gridButtons = new GridButton[width][height];
        	
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
            /*
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    GridButton gb = new GridButton(height, width); //TODO - TEST - vt et õige jk
                    gb.addActionListener(this);
                    //gb.setLayerOne(Integer.parseInt(scontent.substring(ptr, ptr+2))); //TODO - mida kuradit
                    ptr += 2; 
                    //gb.setLayerTwo(Integer.parseInt(scontent.substring(ptr, ptr+2))); 
                    ptr += 2; 
                    
                    int linkedgrids = Integer.parseInt(scontent.substring(ptr,ptr+1)); 
                    ptr += 1; 
                    
                    for (int l = 0; l < linkedgrids; l++) {
                        int lgx = Integer.parseInt(scontent.substring(ptr, ptr+2));
                        ptr += 2; 
                        int lgy = Integer.parseInt(scontent.substring(ptr, ptr+2));
                        ptr += 2; 
                        int[] ng = {lgx, lgy};
                        gb.addNextGrid(ng);
                    }
                    
                    gridButtons[i][j] = gb; 
                }
            }*/
            
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
        catch (IOException ioe) {}
                
        finally {
            try {
	            bfr.close();
	            fr.close(); 
            }
            catch (IOException ioe) {}
        }
    }
    
    
    private enum TerrainType {
    	GRASS(01,Color.green),
    	PATH(02,Color.orange),
    	FOREST(03,Color.black),
    	OUTPOST(04,Color.blue),
    	EMPTY(00, Color.white);
    	
    	private int id;
    	private Color color;
    	
    	private TerrainType(int id, Color color) {
    		this.id = id;
    		this.color = color;
    	}
    	
    	public int getId() {
    		return id;
    	}
    	
    	public String getIdString() {
    		if((""+id).length() < 2) {
    			return "0"+id;
    		} else {
    			return ""+id;
    		}
    	}
    	
    	public Color getColor() {
    		return color;
    	}
    	
    	public static TerrainType getById(int id) {
    		TerrainType result = null;
    		
    		for(TerrainType tt: TerrainType.values()) {
    			if(tt.id == id) {
    				result = tt;
    			}
    		}
    		return result;
    	}
    }
    
    private class GridButton extends JButton {
		private final int posx;
        private final int posy;        
        private TerrainType terrainType; 
        private ArrayList<int[]> nextGrids;
        
        public GridButton(int posx, int posy) {
            this.posx = posx;
            this.posy = posy;
            this.terrainType = TerrainType.GRASS;
            setBackground(terrainType.getColor());
            this.nextGrids = new ArrayList<int[]>(); 
        }
        
        public void nextLayerOne() {
            //TODO - klikkimisel kutsutakse välja.. 
        }
        
        public void nextLayerTwo() {
            //TODO
        }
        
        public TerrainType getTerrainType() {
        	return terrainType;
        }
        
        public void setTerrainType(TerrainType newType) {
        	terrainType = newType;
        	setBackground(terrainType.getColor());
        }
        
        public int getPosX() {
            return this.posx; 
        }
        
        public int getPosY() {
            return this.posy; 
        }
        
        public void addNextGrid(int[] nextGrid) {
            if (this.nextGrids.contains(nextGrid)) throw new AssertionError(); 
            else this.nextGrids.add(nextGrid); 
        }
        
        public void removeNextGrids() {
            this.nextGrids.clear();
        }                
    }
    
    private class Wave {
    	private int infantry;
    	private int knights;
    	private int archers;
    	
    	public Wave() {
    		this(0,0,0);
    	}
    	
    	public Wave(int inum, int knum, int anum) {
    		this.infantry = inum;
    		this.knights = knum;
    		this.archers = anum;
    	}
    	
    	public void setInfantry(int number) {
    		this.infantry = number;
    	}
    	
    	public int getInfantry() {
    		return this.infantry;
    	}
    	
    	public String getInfantryString() {
    		if((""+infantry).length() < 3) {
    			if((""+infantry).length() < 2) {
    				return "00"+infantry;
    			} else {
    				return "0"+infantry;
    			}
    		} else {
    			return infantry+"";
    		}
    	}
    	
    	public void setKnights(int number) {
    		this.knights = number;
    	}
    	
    	public int getKnights() {
    		return this.knights;
    	}
    	
    	public String getKnightsString() {
    		if((""+knights).length() < 3) {
    			if((""+knights).length() < 2) {
    				return "00"+knights;
    			} else {
    				return "0"+knights;
    			}
    		} else {
    			return knights+"";
    		}
    	}
    	
    	public void setArchers(int number) {
    		this.archers = number;
    	}
    	
    	public int getArchers() {
    		return this.archers;
    	}
    	
    	public String getArchersString() {
    		if((""+archers).length() < 3) {
    			if((""+archers).length() < 2) {
    				return "00"+archers;
    			} else {
    				return "0"+archers;
    			}
    		} else {
    			return archers+"";
    		}
    	}
    	
    	
    }      

}
