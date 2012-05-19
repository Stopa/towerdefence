package towerdefence.gui;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.io.*; 
import javax.imageio.*; 
import java.util.EnumMap;
import java.util.HashMap; 

import towerdefence.Configuration; 
import towerdefence.gameelements.Enemy;
import towerdefence.gameelements.Grid.GridType;
import towerdefence.gameelements.Tower.TowerType;
import towerdefence.gameelements.Tower;
import towerdefence.gameelements.AmmoSprite;

public class LevelPanel extends JPanel {
    
    private Image pathImage;
    private Image grassImage;
    private Image forestImage;
    private Image villageImage;
    private Image castleImage;
    private Image burnedVillageImage;
    private Image burnedCastleImage;    
    
    private Image arrowtowerImage;
    private Image cannontowerImage; 
    
    private Image infantryImage;
    private Image cavalryImage;
    private Image knightImage;
    
    private EnumMap<GridType,Image> gridImageMap; 
    private EnumMap<TowerType,Image> towerImageMap;
    private HashMap<String, Image> enemyImageMap; 

    int i = 0;     
    
    private final GameWindow gameWindow;         
    
    LevelPanel (final GameWindow gameWindow) {
        this.gameWindow = gameWindow;  
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                x = (x - (x % 30)) / 30;
                y = (y - (y % 30)) / 30;                
                
                gameWindow.respondToClick(x, y);                
            }
        });
        
        initImages();
        initGridImageMap();
        initEnemyImageMap();
        initTowerImageMap(); 
    }
    
    private void initImages() {
        try {
            pathImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\road.png"));
            grassImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\grass.png"));
            forestImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\forest.png"));
            villageImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\village.png"));
            burnedVillageImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\villageBurned.png"));
            castleImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\fort1.png"));
            burnedCastleImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\fort1Burned.png"));
            
            arrowtowerImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\arrowtower.png"));
            cannontowerImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\cannontower.png"));
            
            infantryImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\infantry.png"));
            cavalryImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\cavalry.png"));
            knightImage = ImageIO.read(new File("src\\towerdefence\\bitmapimages\\knight.png"));
        }
        catch (IOException ioe) {
           ioe.printStackTrace(); 
        }          
    }
    
    private void initGridImageMap() {
        gridImageMap = new EnumMap<GridType, Image>(GridType.class);
        
        gridImageMap.put(GridType.PATH, pathImage);
        gridImageMap.put(GridType.GRASS, grassImage);                
        gridImageMap.put(GridType.FOREST, forestImage);
        gridImageMap.put(GridType.VILLAGE, villageImage);
        gridImageMap.put(GridType.CASTLE, castleImage);           
        gridImageMap.put(GridType.BURNED_VILLAGE, burnedVillageImage);
        gridImageMap.put(GridType.BURNED_CASTLE, burnedCastleImage);         
    }
    
    private void initEnemyImageMap() {        
        enemyImageMap = new HashMap<String, Image>();
        
        enemyImageMap.put(Configuration.ENEMY_CAVALRY_TYPE, cavalryImage);
        enemyImageMap.put(Configuration.ENEMY_INFANTRY_TYPE, infantryImage);
        enemyImageMap.put(Configuration.ENEMY_KNIGHT_TYPE, knightImage);
    }
    
    private void initTowerImageMap() {
        towerImageMap = new EnumMap<TowerType, Image>(TowerType.class);
        
        towerImageMap.put(TowerType.ArrowTower, arrowtowerImage);
        towerImageMap.put(TowerType.CannonTower, cannontowerImage); 
    }

    private void microturn() {
        
        for (Enemy enemy : gameWindow.getLevel().getCurrentWave().getEnemyList()) {
            enemy.micromove();
        }
        
        for (AmmoSprite ammo : gameWindow.getLevel().getCurrentWave().getAmmoSpriteList()) {
            ammo.micromove();
        }
    }

    public void draw() {
        
        for (int i = 0; i < Configuration.MICROTURNS; i++) {
            if (i != 0 && i!= Configuration.MICROTURNS - 1) {
            try {
                Thread.sleep(Configuration.MICROTURN_INTERVAL);
            }
            catch (InterruptedException ie) {
                throw new AssertionError(); //TODO - ???
            }
            }
            else {
            try {
                //TODO - teeb NATUKE paremaks hüplemise
                //aga muuta ümber, eriti kui kõik funktsioonid on paigas!!
                Thread.sleep(Configuration.MICROTURN_INTERVAL * 4 / 7);
            }
            catch (InterruptedException ie) {
                throw new AssertionError(); //TODO - ???
            }
            }            
            microturn();
            repaint(); 
            //System.out.println("asd");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        
        int levelWidth = Configuration.LEVEL_WIDTH;
        int levelHeight = Configuration.LEVEL_HEIGHT;
        int gridSide = 30; //TODO - pane confi
        
        //joonistame gridide taustad..
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {   
                Image image = gridImageMap.get(
                        this.gameWindow.getLevel().getGridAt(i, j).getGridType());
                g.drawImage(image, i * gridSide, j * gridSide, null);
                
            }
        }
        
        //joonistame tornid
        for (int i = 0; i < gameWindow.getLevel().getTowerList().size(); i++) {
            for (Tower tower : gameWindow.getLevel().getTowerList()) {
                g.drawImage(
                        towerImageMap.get(tower.getTowerType()),
                        tower.getGrid().getX() * gridSide,
                        tower.getGrid().getY() * gridSide,
                        null);
            }            
        }
        
        //joonistame vastased
        for (int i = 0; i < gameWindow.getLevel().getCurrentWave().getEnemyList().size(); i++) {
            for (Enemy enemy : gameWindow.getLevel().getCurrentWave().getEnemyList()) {
                try {
                g.drawImage(
                        enemyImageMap.get(enemy.getType()),
                        enemy.getCoords()[0], 
                        enemy.getCoords()[1],
                        null);
                }
                catch (NullPointerException npe) {
                    System.out.println("npe!"); // TODO - eemaldada..
                }
            }
        }
    }
}