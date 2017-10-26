package SnakeCore;

import java.awt.Point;

public interface IObjFactory {
	public IObject produce(GameState game,Point[] ps);
	public IObject produce(GameState game);
}
