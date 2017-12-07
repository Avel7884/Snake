package SnakeCore;

import java.awt.Point;


public abstract class IObject {
    protected IObjFactory fact;
    abstract Point[] getLocs();
	public abstract char getIcon();
	abstract void tick();
	abstract boolean interact(IActiveObject snake,Point p);
	public IObjFactory getFact() {
	  return fact;
	}
}
