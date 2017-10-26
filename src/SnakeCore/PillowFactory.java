package SnakeCore;

import java.awt.Point;

public class PillowFactory implements IObjFactory {

	@Override
	public IObject produce(GameState game, Point[] ps) {
		return new Pillow(game,ps);
	}

	@Override
	public IObject produce(GameState game) {
		return new Pillow(game,new Point[] {game.getRndFreePoint()});
	}

}
