package towerdefence.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

import towerdefence.levelbuilder.*;
import towerdefence.Configuration;

public class TitleWindow extends JFrame implements ActionListener {
	JButton playButton;
	JButton editButton;
	JButton exitButton;
	
	public TitleWindow() {
		setTitle("Kalevite Kangeim Kaitsja Game of the Year edition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520,500);
		getContentPane().setBackground(Color.black);
		setLayout(null);
		
		JLabel banner = new JLabel(new ImageIcon(Configuration.BANNER_PATH));
		banner.setBounds(0,0,500,250);
		add(banner);
		
		playButton = new JButton("Mängi mängu");
		playButton.addActionListener(this);
		playButton.setBounds(150,260,200,40);
		playButton.setBackground(Color.gray);
		add(playButton);
		
		editButton = new JButton("Ehita leveleid");
		editButton.addActionListener(this);
		editButton.setBounds(150,320,200,40);
		editButton.setBackground(Color.gray);
		add(editButton);
		
		exitButton = new JButton("Välju");
		exitButton.addActionListener(this);
		exitButton.setBounds(150,380,200,40);
		exitButton.setBackground(Color.gray);
		add(exitButton);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource();
		
		if(target == playButton) {
			new LevelListWindow();
			dispose();
		} else if(target == editButton) {
			new LevelBuilder();
			dispose();
		} else if(target == exitButton) {
			dispose();
		}
	}
	
	public static void main(String[] args) {
		new TitleWindow();
	}
}
