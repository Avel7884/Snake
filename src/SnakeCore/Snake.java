package SnakeCore;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Snake extends IObject {
    private LinkedList<Point> body =new LinkedList<Point>();
    private IIntellect intel;
    private char ico;
    private int buffer=0;
    private boolean isMoving=true; 
    private boolean isAlive=true; 
    private Point next;
    
    Snake(Integer[] ps,IObjFactory fact,IIntellect intel,char ico) {
        this.fact=fact;
        this.ico=ico;
        this.intel=intel;
        for(int i=0;i<ps.length;i+=2) {
            getBody().add(new Point(ps[i],ps[i+1]));//TODO Make dir and intel number 
        }
    }
    
    @Override
    Point[] getLocs() {
        return getBody().toArray(new Point[getBody().size()]);
    }

    @Override
    public char getIcon() {
        return ico;
    }

    @Override
    void tick() {
        if (!isMoving || !isAlive) return;
        
        if (body.contains(getNext())) {
            stop();
            die();
            fact.utilize(this);
            return;
        }
        body.add(getNext());
        next=null;
        if(buffer>0) {
            buffer-=1;            
        }else {
            body.removeFirst();
        }
        next=null;
    }

    @Override
    boolean interact(Snake snake, Point p) {
        return false;
    }

    public void setBody(LinkedList<Point> membs) {
        this.body = membs;
    }
    public List<Point> getBody(){
        return body;
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
        isAlive=false;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public void grow(int val) {
        buffer+=val;
    }
    
    public Point getNext() {
        if(next == null) {
            Point d=getDir().getDir();
            next=new Point(getHead().x+d.x,getHead().y+d.y);
        }
        return next;
    }
    
    public Direction getDir() {
        return intel.getDir();
    }
    
    public void setNext(Point newNext) {
        next=newNext;
    }
    
    public Point getHead() {
        return body.getLast();
    }
    

}
