package SnakeCore;

import java.awt.Point;
import java.util.Random;

public final class Hedg implements IObject {

	private Direction dir;
	private GameState game;
	private char ico;
	private Point loc;
	private Random rnd;
	
	public Hedg(GameState game,Point[] p) {
		this.game=game;
		loc=p[0];
		commonInit();
	}
	
	public Hedg(GameState game) {
		this.game=game;
		commonInit();
		replace();
	}
	
	
	private void commonInit() {
		rnd=new Random();
		dir=new Direction((rnd.nextInt(4)+1)*2);
		ico=makeIco(dir.getDirN());
	}
	
	private char makeIco(int dir) {
		switch(dir) {
		case 6: return 'D';
		case 8: return 'W';
		case 2: return 'S';
		case 4: return 'A';
		}
		return 'F';
	}
	
	@Override
	public Point[] getLocs() {
		return new Point[] {loc};
	}

	@Override
	public char getIcon() {
		return ico;
	}

	@Override
	public void tick() {}
	
	private void replace() {
		loc=game.getRndFreePoint();
		while(game.getCell(game.getBoundedCord(new Point(loc.x+dir.getDir().x,loc.y+dir.getDir().y))) != '.')
			loc=game.getRndFreePoint();	
	}

	@Override
	public boolean interact(Point p) {
		if (game.getSnakeDir().IsOpposit(dir)){
			game.feedSnake(2);
			commonInit();
			replace();
			return false;
		}else{
			return true;
		}
	}

}
