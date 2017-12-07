package SnakeCore;

import java.awt.Point;

public abstract class IActiveObject extends IObject {

    abstract public boolean isMoving();
    
    abstract public void die() ;
    
    abstract public boolean isAlive();
    
    abstract public Point getNext() ;
    
    abstract public Direction getDir() ;
    
    abstract public void setNext(Point newNext) ;
    
    abstract public Point getHead();
}
