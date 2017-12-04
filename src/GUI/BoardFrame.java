package GUI;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BoardFrame extends JFrame {
	
	 private Board board;

	
	 public BoardFrame(Board boardNew) {
	        add(boardNew);
	        board = boardNew;
	        
	        Font font = new Font("Comic Sans MS", Font.PLAIN, 11);
	       
	         
	        JMenuBar menuBar = new JMenuBar();
	         
	        JMenu fileMenu = new JMenu("Menu");
	        fileMenu.setFont(font);
	         
	        JMenu newMenu = new JMenu("New Game");
	        newMenu.setFont(font);
	        fileMenu.add(newMenu);
	        
	        JMenuItem lvl1 = new JMenuItem("Lvl 1");
	        lvl1.setFont(font);
	        lvl1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	board.initMap(".\\levels\\Simple.txt");
	            }
	        });
	        newMenu.add(lvl1);
	        
	        JMenuItem lvl2 = new JMenuItem("Lvl 2");
	        lvl2.setFont(font);
	        lvl2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	board.initMap(".\\levels\\Simple2.txt");
	            }
	        });
	        newMenu.add(lvl2);
	         
	        fileMenu.addSeparator();
	         
	        JMenuItem exitItem = new JMenuItem("Exit");
	        exitItem.setFont(font);
	        fileMenu.add(exitItem);
	         
	        exitItem.addActionListener(new ActionListener() {           
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);             
	            }           
	        });
	         
	        menuBar.add(fileMenu);
	        setJMenuBar(menuBar);

	        
	        setResizable(false);
	        pack();

	        setTitle("Snake");
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	 
	 

}
