package towerdefence.gui;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import towerdefence.gameelements.Level;
import towerdefence.Configuration;
import towerdefence.logic.LevelController;

public class LevelListWindow extends JFrame implements ActionListener {
	File[] Levels;
	JScrollPane levelsScrollContainer;
	JPanel levelsContainer;
	JLabel titleLabel;
	JButton exitButton;
	
	public LevelListWindow() {
		setTitle("Vali tase - Kalevite Kangeim Kaitsja Game of the Year edition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(520,500);
		getContentPane().setBackground(Color.black);
		
		titleLabel = new JLabel("Vali tase:", SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", 0, 40));
		titleLabel.setBounds(150,20,200,40);
		titleLabel.setForeground(Color.red);
		add(titleLabel);
		
		levelsContainer = new JPanel();
		levelsContainer.setLayout(null);
		levelsContainer.setBackground(Color.black);
                
                
		levelsScrollContainer = new JScrollPane(levelsContainer);
		levelsScrollContainer.getViewport().setBackground(Color.black);
		levelsScrollContainer.setBounds(100,80,300,300);
		levelsScrollContainer.setPreferredSize(new Dimension(300,300));
		levelsScrollContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(levelsScrollContainer);                
		

		File lvlDir = new File(Configuration.MAPS_PATH);
		Levels = lvlDir.listFiles(new LevelFilter());
                levelsContainer.setPreferredSize(new Dimension(280, Levels.length * 20));
		int y = 0;
		
		for(File f: Levels) {
			String mapName = f.getName().replace(Configuration.MAPS_EXTENSION, "");
			LevelButton b = new LevelButton(mapName.substring(0,1).toUpperCase()+mapName.substring(1).toLowerCase());
			b.setBackground(Color.gray);
			b.setBounds(0,y,300,20);
			y += 25;
			b.setLevelFile(f);
			b.addActionListener(this);
			levelsContainer.add(b);
		}
                
		exitButton = new JButton("Tagasi");
		exitButton.setBounds(150,400,200,40);
		exitButton.setBackground(Color.gray);
		exitButton.addActionListener(this);
                exitButton.setFocusable(false);
		add(exitButton);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource();
		
		if(target == exitButton) {
			new TitleWindow();
			dispose();
		} else {
			GameWindow gw = new GameWindow(new towerdefence
	                .gameelements.Level(((LevelButton)target).getLevelFile().getAbsolutePath()));
	        
	        LevelController lc = new LevelController(gw);
	        
	        gw.setLevelController(lc);
	        lc.startBuildingPhase();
			
			dispose();			
		}
	}

	public static void main(String[] args) {
		new LevelListWindow();
	}
	
	private class LevelFilter implements FileFilter {
		public boolean accept(File f) {
			return f.getName().endsWith(".map");
		}
	}
	
	private class LevelButton extends JButton {
		private File levelFile;
		
		public LevelButton(String name) {
			super(name);
                        this.setFocusable(false);
		}
		
		public void setLevelFile(File f) {
			levelFile = f;
		}
		
		public File getLevelFile() {
			return levelFile;
		}
	}
}
