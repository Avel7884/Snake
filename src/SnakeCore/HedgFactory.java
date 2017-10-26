package SnakeCore;

import java.awt.Point;

public class HedgFactory implements IObjFactory {

	@Override
	public Hedg produce(GameState game, Point[] p) {
		return new Hedg(game,p);
	}
	public Hedg produce(GameState game) {
		return new Hedg(game);
	}

}
