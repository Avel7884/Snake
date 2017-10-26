package SnakeCore;

import java.awt.Point;


public interface IObject {
	Point[] getLocs();
	char getIcon();
	void tick();
	boolean interact(Point p);
}
