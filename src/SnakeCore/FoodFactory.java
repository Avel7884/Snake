package SnakeCore;

import java.awt.Point;

public final class FoodFactory implements IObjFactory {

	@Override
	public Food produce(GameState game, Point[] p) {
		return new Food(game.getSnake(),p);
	}
	public Food produce(GameState game) {
		return new Food(game.getSnake(),new Point[] {game.getRndFreePoint()});
	}

}
