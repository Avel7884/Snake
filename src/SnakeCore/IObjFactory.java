package SnakeCore;

import java.awt.Point;

public interface IObjFactory{
    public IObject[] utilize(IObject obj);
    public IObject[] tick();
	public IObject[] configure(GameState game,Point[] ps);
	public IObject[] configure(GameState game,Integer[] args);
	public IObject[] getProducts();
}
