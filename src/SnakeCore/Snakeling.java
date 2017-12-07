package SnakeCore;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Snakeling extends IActiveObject{
    private LinkedList<Point> body;
    private boolean isMoving=true; 
    private boolean isAlive=true; 
    private Snake parent;
    private Point next;
    private int liveScale;
    private int liveCount;
    
    Snakeling(Point[] ps,Snake parent) {
        this.fact=new SnakelingFactory();//TODO Fix dat stab.
        this.parent=parent;
        liveScale=ps.length;
        liveCount=liveScale;
        body=new LinkedList<Point>(Arrays.asList(ps));
        Collections.reverse(body);
    }
    
    @Override
    Point[] getLocs() {
        return body.toArray(new Point[body.size()]);
    }

    @Override
    public char getIcon() {
        return '0';
    }

    @Override
    public void tick() {
        if (!isMoving || body.size()==0 || !isAlive) 
            return;
        
        if (body.contains(getNext())) {
            stop();
            die();
            return;
        }
        body.add(next);
        next=null;
        if(liveCount>0) {
            liveCount-=1;    
            body.removeFirst();        
        }else {
            body.removeFirst();
            body.removeFirst();
            if(body.size()==1) {
                die();
            }
            liveCount=liveScale;
        }
    }

    @Override
    public boolean interact(IActiveObject snake, Point p) {
        if(!(snake instanceof Snake)) {
            return true;
        }
        ((Snake) snake).grow(body.size());
        return false;
    }
    
    public boolean isMoving(){
        return isMoving;
    }
    
    public void stop() {
        isMoving=false;
    }
    
    public void start() {
        isMoving=true;
    }
    
    public void die() {
        isMoving=false;
        isAlive=false;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public Point getNext() {
        if(next == null) {
            Point d=getDir().getDir();
            next=new Point(getHead().x+d.x,getHead().y+d.y);
        }
        return next;
    }
    
    public Direction getDir() {
        return parent.getDir().getOpposit();
    }
    
    public void setNext(Point newNext) {
        next=newNext;
    }
    
    public Point getHead() {
        return body.getLast();
    }

}
