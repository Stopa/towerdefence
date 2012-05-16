package towerdefence;

public final class Configuration {
    
    //LEVEL
    public static final int LEVEL_HEIGHT = 20;
    public static final int LEVEL_WIDTH = 20; 
    
    //TOWERS
    //Arrowtower
    public static final int TOWER_ARROWTOWER_BASEATTACK = 1; 
    public static final int TOWER_ARROWTOWER_ATTACKLEVELCOEFFICIENT = 1; 
    public static final int TOWER_ARROWTOWER_BASESPEED = 1; 
    public static final int TOWER_ARROWTOWER_SPEEDLEVELCOEFFICIENT = 1; 
    public static final int TOWER_ARROWTOWER_BASERANGE = 1;
    public static final int TOWER_ARROWTOWER_RANGELEVELCOEFFICIENT = 1;
    
    //Cannontower
    public static final int TOWER_CANNONTOWER_BASEATTACK = 1; 
    public static final int TOWER_CANNONTOWER_ATTACKLEVELCOEFFICIENT = 1; 
    public static final int TOWER_CANNONTOWER_BASESPEED = 1; 
    public static final int TOWER_CANNONTOWER_SPEEDLEVELCOEFFICIENT = 1; 
    public static final int TOWER_CANNONTOWER_BASERANGE = 1;
    public static final int TOWER_CANNONTOWER_RANGELEVELCOEFFICIENT = 1;     
    
    
    //ENEMIES
    //Cavalry
    public static final int ENEMY_CAVALRY_HEALTH = 10;
    public static final int ENEMY_CAVALRY_SPEED = 5;
    public static final int ENEMY_CAVALRY_DAMAGE = 10;
    public static final String ENEMY_CAVALRY_TYPE = "cavalry";
    
    //Infantry
    public static final int ENEMY_INFANTRY_HEALTH = 5;
    public static final int ENEMY_INFANTRY_SPEED = 2;
    public static final int ENEMY_INFANTRY_DAMAGE = 5;
    public static final String ENEMY_INFANTRY_TYPE = "infantry";
        
    //Knight
    public static final int ENEMY_KNIGHT_HEALTH = 20;
    public static final int ENEMY_KNIGHT_SPEED = 1;
    public static final int ENEMY_KNIGHT_DAMAGE = 20;
    public static final String ENEMY_KNIGHT_TYPE = "knight";
        
    //CASTLE
    public static final int GRID_CASTLE_MAXHEALTH = 100;
    
    //VILLAGE
    public static final int GRID_VILLAGE_MAXHEALTH = 10; 

    
    //UI
    //GameWindow
    public static final int GAMEWINDOW_HEIGHT = 800;
    public static final int GAMEWINDOW_WIDTH = 600; 
    
    public static final int GAMEWINDOW_STARTCOMBATBUTTON_HEIGHT = 30;
    public static final int GAMEWINDOW_STARTCOMBATBUTTON_WIDTH = 100;
    public static final int GAMEWINDOW_STARTCOMBATBUTTON_POSX = 10;
    public static final int GAMEWINDOW_STARTCOMBATBUTTON_POSY = 610;
    public static final String GAMEWINDOW_STARTCOMBATBUTTON_TEXT = "Combat";    
            
    //LevelPanel
    public static final int LEVELPANEL_POSX = 0;
    public static final int LEVELPANEL_POSY = 0; 
    public static final int LEVELPANEL_HEIGHT = 600;
    public static final int LEVELPANEL_WIDTH = 600; 
    
    public static final int MICROTURNS = 10; 
    public static final int MICROTURN_INTERVAL = 100; 
    
    //Money
    public static final int STARTING_MONEY = 1000;
    public static final int CASTLE_MONEYPERWAVE = 300;
    public static final int VILLAGE_MONEYPERWAVE = 100;
    
    

}
