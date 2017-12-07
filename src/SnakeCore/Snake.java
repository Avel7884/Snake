package SnakeCore;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Snake extends IActiveObject {
    private LinkedList<Point> body =new LinkedList<Point>();
    private IIntellect intel;
    private char ico;
    private int buffer=0;
    private Direction curDir;
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
    public void tick() {
        if (!isMoving || !isAlive) 
        	return;
        
        if (body.contains(getNext())) {
            stop();
            die();
            return;
        }
        body.add(next);
        next=null;
        curDir=null;
        if(buffer>0) {
            buffer-=1;            
        }else if(buffer<0){
            body.removeFirst();
            body.removeFirst();//TODO improve!!@@!!
            if(body.size()==1) {
                die();
            }
        }else{
            body.removeFirst();
        }
    }

    @Override
    public boolean interact(IActiveObject obj, Point p) {
        if(obj instanceof Snakeling) {
            grow(-2*((Snakeling) obj).getLocs().length);
            return true;
        }
        return true;
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
        isMoving=false;
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
        if(curDir==null) {
            curDir=intel.getDir();
        }
        return curDir;
    }
    
    public void setNext(Point newNext) {
        next=newNext;
    }
    
    public Point getHead() {
        return body.getLast();
    }
    
    public int getBuffer() {
    	return buffer;
    }
    
    public void setAlive(boolean b) {
    	isAlive = b;
    }
    
    public void spawn(GameState game) {
        if(body.size()>3) {
            game.setObj(new Snakeling(getTail(3),this));
        }
    }
    
    public Point[] getTail(int len) {
        Point[] p=new Point[3];
        for(int i=0;i<p.length;i++) {
            p[i]=body.pollFirst();
        }
        return p;
    }
}
