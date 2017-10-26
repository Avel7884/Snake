package SnakeCore;

import java.awt.Point;

public class Pillow implements IObject {

	private Point loc;
	private GameState game;
	private int timer=0; 
	private int maxTimer=15;
	private boolean inUse;
	
	public Pillow(GameState game,Point[] p) {
		loc=p[0];
		this.game=game;
	}
	
	@Override
	public Point[] getLocs() {
		return new Point[] {loc};
	}

	@Override
	public char getIcon() {
		return '%';
	}

	@Override
	public void tick() {
		if(inUse) timer+=1;
		if(timer==maxTimer) {
			game.getSnake().start();
			game.getObjsArr().remove(this);
		}
	}

	@Override
	public boolean interact(Point p) {
		game.getSnake().stop();
		inUse=true;
		return false;
	}

}
