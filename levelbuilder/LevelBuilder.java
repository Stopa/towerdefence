package towerdefence.levelbuilder;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.*; 
import java.io.*; 

public class LevelBuilder extends JFrame implements ActionListener {
    

    private GridButton[][] gridButtons; 
    private JPanel gridButtonsPanel; 
    private JTextField filenameField; 
    private JLabel filenameLabel;
    private JButton loadButton;
    private JButton saveButton;
    private JLabel ioStatusLabel; //kas faili ei leitud vms, või laeti edukalt, jne.    
    private JButton newButton;
    private JTextField newXField;
    private JLabel newXLabel;
    private JTextField newYField; 
    private JLabel newYLabel;
    private JLabel wavesLabel;
    private JLabel waveNoLabel;
    private JTextField waveNoTextField;
    private JButton waveNoButton;
    private JComboBox waveNoComboBox;
    private JLabel currentWaveLabel;
    private JTextArea currentWaveArea; 
    private JButton currentWaveButton;
        
    //Level data:
    private int height;
    private int width; 
    
    private int nrOfWaves; 
    private ArrayList<int[]> waves; 
    
    //?
    //
    
    //TODO - pane hoiatus kui pole leveli x ja y 50 mõlemad
    
    public static void main(String[] args) {
        new LevelBuilder(); //TODO
    }
    
    public LevelBuilder() {   
        
        gridButtonsPanel = new JPanel();
        gridButtonsPanel.setBounds(200,10,500,500);
        gridButtonsPanel.setBackground(Color.RED); //TODO - võta ära, testiks lihtsalt..
        this.add(gridButtonsPanel);
        
        filenameField = new JTextField();
        filenameField.setBounds(10,32,100,20);
        this.add(filenameField);
        
        filenameLabel = new JLabel("Failinimi");
        filenameLabel.setBounds(10,10,50,20);
        this.add(filenameLabel);
        
        loadButton = new JButton("Lae");
        loadButton.setBounds(10,55,90,30);
        loadButton.addActionListener(this);
        this.add(loadButton);
        
        saveButton = new JButton("Salvesta");
        saveButton.setBounds(100,55,90,30);
        saveButton.addActionListener(this);
        this.add(saveButton);
        
        
        ioStatusLabel = new JLabel("testingtesting"); //TODO - hiljem ära
        ioStatusLabel.setForeground(Color.RED);
        ioStatusLabel.setBounds(10,90,150,20);
        this.add(ioStatusLabel);
        
        newButton = new JButton("Uus");
        newButton.setBounds(10,145,100,30);
        newButton.addActionListener(this);
        this.add(newButton);
        
        newXField = new JTextField();
        newXField.setBounds(10,205,50,20);
        this.add(newXField);
        
        newXLabel = new JLabel("Laius");
        newXLabel.setBounds(10,180,50,20);
        this.add(newXLabel);
        
        newYField = new JTextField();
        newYField.setBounds(70,205,50,20);
        this.add(newYField);
        
        newYLabel = new JLabel("Kõrgus");
        newYLabel.setBounds(70,180,50,20);
        this.add(newYLabel);        
        
        wavesLabel = new JLabel("Lained");
        wavesLabel.setBounds(10,220,50,20);
        this.add(wavesLabel);
        
        waveNoLabel = new JLabel("Lainete arv");
        waveNoLabel.setBounds();
        this.add(waveNoLabel);
                
        waveNoTextField = new JTextField();
        waveNoTextField.setBounds();
        this.add(waveNoTextField);
        
        waveNoButton = new JButton("Rakenda");
        waveNoButton.setBounds();
        waveNoButton.addActionListener(this);
        this.add(waveNoButton);
        
        waveNoComboBox = new JComboBox();
        waveNoComboBox.setBounds();
        waveNoComboBox.addActionListener(this); //TODO - kas on mingit muud vaja tegelikult?
        this.add(waveNoComboBox);
        
        currentWaveLabel = new JLabel("Valitud laine info");
        currentWaveLabel.setBounds();
        this.add(currentWaveLabel);
                
        currentWaveArea = new JTextArea();
        currentWaveArea.setBounds();
        this.add(currentWaveArea);
        
        currentWaveButton = new JButton("Rakenda");
        currentWaveButton.setBounds();
        currentWaveButton.addActionListener(this);
        this.add(currentWaveButton);
        
        this.setSize(715,700);
        this.setResizable(false);
        this.setLayout(null);                        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }
    
    private void drawLevel() {
        //TODO
        //võtab heightid ja widthid ja asjad
    }
    
    private void drawGrid(int x, int y) {
        //TODO
    }
    
    private void newLevel() {
        //TODO
    }
    
    private void saveLevel() {
        //TODO
    }
    
    private void loadLevel(String filename) {
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
            ccontent = null; //??
            
            int ptr = 0;             
            
            height = Integer.parseInt(scontent.substring(ptr, ptr+3));
            ptr += 3; 
            width = Integer.parseInt(scontent.substring(ptr, ptr+3));
            ptr += 3;
            
            gridButtons = new GridButton[height][width]; 
            
            //TODO - sea siin ka gridbuttonpanels layout üles.. 

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    GridButton gb = new GridButton(height, width); //TODO - TEST - vt et õige jk
                    gb.addActionListener(this);
                    gb.setLayerOne(Integer.parseInt(scontent.substring(ptr, ptr+2)));
                    ptr += 2; 
                    gb.setLayerTwo(Integer.parseInt(scontent.substring(ptr, ptr+2))); 
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
            }
            
            nrOfWaves = Integer.parseInt(scontent.substring(ptr, ptr+2));
            ptr += 2; 
            
            waves = new ArrayList<int[]>();
            
            for (int i = 0; i < nrOfWaves; i++) {
                int currentWaveEnemies = Integer.parseInt(scontent.substring(ptr, ptr+1));
                ptr += 1; 
                int[] currentWave = new int[currentWaveEnemies];
                for (int j = 0; j < currentWaveEnemies; j++) {
                    currentWave[j] = Integer.parseInt(scontent.substring(ptr, ptr+2));
                    ptr += 2; 
                }
            }
                        
            drawLevel(); 
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
    
    
    private class GridButton extends JButton {
        private final int posx;
        private final int posy;        
        private int layer1;
        private int layer2; 
        private ArrayList<int[]> nextGrids;
        
        public GridButton(int posx, int posy) {
            this.posx = posx;
            this.posy = posy;
            this.layer1 = 0;
            this.layer2 = 0; 
            this.nextGrids = new ArrayList<int[]>(); 
        }
        
        public void nextLayerOne() {
            //TODO - klikkimisel kutsutakse välja.. 
        }
        
        public void nextLayerTwo() {
            //TODO
        }
        
        public int getLayerOne() {
            return this.layer1;
        }
        
        public int getLayerTwo() {
            return this.layer2; 
        }
        
        public void setLayerOne(int layer1) {
            this.layer1 = layer1; 
        }
        
        public void setLayerTwo(int layer2) {
            this.layer2 = layer2; 
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
    
    
            
            
            

}


















/* SALVESTAMISEKS MIDAGI TAOLIST.. 
    try {
    String filename = fname.getText();
    File f = new File("C:\\" + filename + ".txt");
    f.delete();
    f.createNewFile();
    FileWriter fw = new FileWriter(f);
    BufferedWriter bfw = new BufferedWriter(fw);

    String lh;
    if (height <= 99) lh = "0" + Integer.toString(height);
    else lh = Integer.toString(height);
    bfw.write(lh);
    String lw;
    if (width <= 99) lw = "0" + Integer.toString(width);
    else lw = Integer.toString(width);
    bfw.write(lw);
    
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            String s = tileMapGrids[i][j].getText();
            int g = Integer.parseInt(s);
            int gc = convertToGroundCodes(g);
            String wr = Integer.toString(gc);
            bfw.write(wr);
         } }

    String sh;
    if (selfHeight <= 9) sh = "0" + Integer.toString(selfHeight);
    else sh = Integer.toString(selfHeight);
    bfw.write(sh);
    String sw;
    if (selfWidth <= 9) sw = "0" + Integer.toString(selfWidth);
    else sw = Integer.toString(selfWidth);
    bfw.write(sw);
    bfw.close();
    fw.close();
    }

    catch (Exception e) {
        fname.setText(e.getMessage());
    }
 * 
 */