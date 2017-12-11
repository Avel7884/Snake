package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
//import java.awt.color.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import SnakeCore.ControlIntellect;
import SnakeCore.GameState;
import SnakeCore.StateParser;
import SnakeCore.Snake;
//import SnakeCore.Direction;
import java.awt.Point;
//import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
//import javax.swing.ImageIcon;
//import org.eclipse.swt.SWT;

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.widgets.FileDialog;

@SuppressWarnings("serial")
public class Board extends JPanel {


	private final Craft tel2;
	private final Craft tel1;
	private GameState gameState;
	private char[][] map;
	private Snake[] snakes;
	private ControlIntellect[] intel;
	private boolean flag = false;
	
	private boolean pause = false;
	
	private Craft apple;
    private Craft pillow;
	private Craft wall;
    private Craft crate;
	private Craft background;
	private Craft snake1;
	private Craft snake2;
	
	private String color1 = "green";
	private String color2 = "red";
	
	public void setColor(String color, int i) {
		if (i == 1) {
			color1 = color;
		}
		else {
			color2 = color;
		}
		
	}
	
	public boolean isPause() {
		return pause;
	}
     
    public Board() {//createGUI() {
        
        addKeyListener(new Keys());
        setBackground(Color.BLACK);
        setFocusable(true);
                
        initMap(".\\levels\\Simple2.txt");

        setPreferredSize(new Dimension(1120, 600));
        
        apple = new Craft(".\\sprites\\apple.gif");
        wall = new Craft(".\\sprites\\wall.png");
        crate = new Craft(".\\sprites\\wall.png");
        pillow = new Craft(".\\sprites\\pil.png");
        background = new Craft(".\\sprites\\ground.jpg");
        snake1 = new Craft(".\\sprites\\snake.jpg");
        snake2 = new Craft(".\\sprites\\snake2.png");
		tel1 = new Craft(".\\sprites\\port1.jpg");
		tel2 = new Craft(".\\sprites\\port2.png");

    }
   
    
    public void initMap(String path) {
    	//StateParser sp = new StateParser();
    	gameState = StateParser.makeGame(path);
		intel = gameState.getCtrlIntel();	
		snakes = gameState.getSnake();
		map = gameState.getMap();
    }
    
   
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*if (map.snakeIsLife()) {
            draw(g);
            printScore(g);
            printParty(g);
        } else {
            endGame(g);
        }*/
        if (!flag) {
        	drawMap(g);
        	drawSnakeStep(g);
        }
        else {
        	drawEnd(g);
        }
    }
    
    /*private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);        
    }*/
    
    private int cellSize = 20;

    public void drawMap(Graphics g) {
    	Color col = Color.BLACK;
    	g.setColor(col);
    	Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background.getImage(), 0, 0, map[0].length * cellSize, map.length * cellSize, this); 
    	//g.fillRect(0, 0, map[0].length * cellSize, map.length * cellSize);
    	for (int y = 0; y < map.length; y++) {
    		for (int x = 0; x < map[0].length; x++) {
    			g.setColor(col);
    			draw2D(x, y, g2d, getCraft(map[y][x]));
    		}
    	}
    }
    
    private Craft getCraft(char c) {
        switch(c) {
            case('A'):
                return new Craft(".\\sprites\\hedgA.png");
            case('W'):
                return new Craft(".\\sprites\\hedgW.png");
            case('S'):
                return new Craft(".\\sprites\\hedgS.png");
            case('D'):
                return new Craft(".\\sprites\\hedgD.png");
            /*
            case('1'):
                drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_GREEN);
                break;
            case('.'):
                drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_BLUE);
                break;
            */
            case('P'):
				return tel1;
            case('p'):
				return tel2;
            case('#'):
                return wall;
            case('+'):
                return crate; 
            case('%'):
                return pillow;
            case('*'):
                return apple;
            default: 
                return background;
            }
    }
    
    public void draw2D(int x, int y, Graphics g2d, Craft sprite) {
    		g2d.drawImage(sprite.getImage(), x * cellSize, y * cellSize, cellSize, cellSize, this); 
    	
    }
   
    
    public boolean makeTick() {
    	if (pause) {
    		return true;
    	}
		if (!gameState.makeTick())
		{
			flag = true;
			repaint();
			return false;
		}
		map = gameState.getMap();
		repaint();
		return true;
		
	}
    
    void drawSnakeStep(Graphics g) {
    	//char[][] newMap = gameState.getMap();
    	//for (int i = 4; i > 0; i--) {
    		drawSnakesOnBoard(g, 1);
    	//}
    	
    }
    
    void drawSnakesOnBoard(Graphics g, int step) {
    	Graphics2D g2d = (Graphics2D) g;
    	if (snakes[0].isAlive()) {
    		drawSnake(snakes[0], g2d, snake1, color1, step);
    	}
    	if (snakes[1].isAlive()) {
    		drawSnake(snakes[1], g2d, snake2, color2, step);
    	}
    }
    
    public void drawSnake(Snake snake, Graphics2D g2d, Craft pic, String color, int step) {
    	List<Point> body = snake.getBody();
    	for (int i = 0; i < body.size(); i++) {
    		if (i == 0){
    			String tail = getPicTail(body.get(i), body.get(1));
    			pic =  new Craft(".\\sprites\\snake\\" + color + tail);
    		} else if (i == body.size() - 1) {
    			int dir = snake.getDir().getDirN();
    			pic =  new Craft(".\\sprites\\snake\\" + color + "\\head" + dir + ".png");
    		}
    		else {
    			String n = getPic(i, body);
    			pic =  new Craft(".\\sprites\\snake\\" + color + n);
    		}
			draw2D(body.get(i).x, body.get(i).y, g2d, pic);
    	}
    }
    
    public String getPicTail(Point p0, Point p1) {
    	int dir = determineDirection(p0, p1);
    	return "\\tail" + dir + ".png";
    }
    
    public String getPic(int i, List<Point> body) {
    	int dir1 = determineDirection(body.get(i), body.get(i - 1));
    	int dir2 = determineDirection(body.get(i), body.get(i + 1));
    	switch(dir1 + dir2) {
    	case 12:
    		return "\\48.png";
    	case 14:
    		return "\\86.png";
    	case 8:
    		return "\\62.png";
    	case 6:
    		return "\\24.png";
    	default:
    		if (dir1 == 4  && dir2 == 6 || dir2 == 4 && dir1 == 6) {
    			return "\\46.png";
    		}
    		return "\\28.png";
    		
    	}
    }
    
    public int determineDirection(Point p0, Point p1) {
    	//int x_e = map[0].length - 1;
    	//int y_e = map.length - 1;
    	if (p0.y == p1.y  && ((p0.x > p1.x && !(p0.x == map[0].length - 1 && p1.x == 0))  
    			|| (p1.x == map[0].length - 1 && p0.x == 0))) { 
    		return 4; }
    	if (p0.y == p1.y ) { 
    		return 6; }
    	if (p0.x == p1.x && ((p0.y > p1.y && !(p0.y == map.length - 1 && p1.y == 0))
    			|| (p1.y == map.length - 1 && p0.y == 0)) ) { 
    		return 8; }
    	return 2;
    }
    
    public void drawEnd(Graphics g) {
    	String message = "Game over";
        g.setColor(Color.red);
        Font font = new Font("Times New Roman", Font.BOLD, 25);

        g.setFont(font);

        g.drawString(message, 200, 200);
    }
    
    private class Keys extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
        	switch(e.getKeyCode()){
        	case(KeyEvent.VK_NUMPAD4):
        		intel[0].setDir(4);
				break;
			case(KeyEvent.VK_NUMPAD6):
			    intel[0].setDir(6);
				break;
			case(KeyEvent.VK_NUMPAD5):
			    intel[0].setDir(2);
				break;
            case(KeyEvent.VK_NUMPAD8):
                intel[0].setDir(8);
				break;
			case(KeyEvent.VK_NUMPAD9):
			    intel[0].makeSpawn();
			    break;
            case(KeyEvent.VK_A):
                intel[1].setDir(4);
                break;
            case(KeyEvent.VK_D):
                intel[1].setDir(6);
                break;
            case(KeyEvent.VK_S):
                intel[1].setDir(2);
                break;
            case(KeyEvent.VK_W):
                intel[1].setDir(8);
                break;
            case(KeyEvent.VK_E):
                intel[1].makeSpawn();
                break;
            case(KeyEvent.VK_SPACE):
                pause = !pause;
                break;
            /*default:
            	 System.out.print(e.getKeyCode());*/
		}
        }
    }
  

}