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
	        
	        JMenu colorMenu = new JMenu("Choose color");
	        colorMenu.setFont(font);
	        fileMenu.add(colorMenu);
	        
	        JMenu snake1 = new JMenu("Snake 1");
	        snake1.setFont(font);
	        colorMenu.add(snake1);
	        
	        addColor(snake1, "Blue", "blue", font, 1);
	        addColor(snake1, "Red", "red", font, 1);
	        addColor(snake1, "Green", "green", font, 1);
	        
	        JMenu snake2 = new JMenu("Snake 2");
	        snake2.setFont(font);
	        colorMenu.add(snake2);
	        
	        addColor(snake2, "Blue", "blue", font, 2);
	        addColor(snake2, "Red", "red", font, 2);
	        addColor(snake2, "Green", "green", font, 2);

	        addLvl(font, "Lvl 1", ".\\levels\\Simple.txt", newMenu);
	        addLvl(font, "Lvl 2", ".\\levels\\Simple2.txt", newMenu);
	        
	         
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
	 
	 	public void addColor(JMenu colorMenu, String name, String color, Font font, int n) {
	 		JMenuItem col = new JMenuItem(name);
	        col.setFont(font);
	        col.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	board.setColor(color, n);
	            }
	        });
	        colorMenu.add(col);
	        
	 	}
	 	
	 	public void addLvl(Font font, String name, String path, JMenu newMenu) {
	 		JMenuItem lvl = new JMenuItem(name);
	        lvl.setFont(font);
	        lvl.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	board.initMap(path);
	            }
	        });
	        newMenu.add(lvl);
	 	}

}
