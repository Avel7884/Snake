package SnakeCore;

import java.awt.Point;

public class Teleport implements IObject {

	private char ico;
	private Point enterA;
	private Point enterB;
	private Point[] ps;
	private GameState game;
	
	
	public Teleport(GameState game, Point[] ps) {
		this.game=game;
		this.ps=ps;
		enterA=ps[0];
		enterB=ps[1];
		ico=initIco();
	}
	
	private char initIco() {
		int i=0;
		for(IObject obj: game.getObjsArr())
			if (this.getClass().isInstance(obj)) 
				i+=1;
		switch(i) {
		case 0:return'P';
		case 1:return'p';
		case 2:return'T';
		case 3:return't';
		}
		return'F';
	}
	
	@Override
	public Point[] getLocs() {
		return ps; 
	}

	@Override
	public char getIcon() {
		return ico;
	}

	@Override
	public void tick() {}

	@Override
	public boolean interact(Point p) {
		Point targ;
		if(p.x==enterA.x && p.y==enterA.y)
			targ=enterB;
		else //(p.x==enterB.x && p.y==enterB.y)
			targ=enterA;
		Point dir=game.getSnakeDir().getDir();
		game.teleportHead(new Point(targ.x+dir.x,targ.y+dir.y));
		return false;
	}

}
