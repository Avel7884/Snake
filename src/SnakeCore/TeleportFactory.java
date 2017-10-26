package SnakeCore;

import java.awt.Point;

public class TeleportFactory implements IObjFactory {

	@Override
	public IObject produce(GameState game, Point[] ps) {
		return new Teleport(game,ps);
	}

	@Override
	public IObject produce(GameState game) {
		return new Teleport(game,new Point[] {game.getRndFreePoint(),game.getRndFreePoint()});
	}

}
