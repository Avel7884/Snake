package SnakeCore;
import java.util.LinkedList;//Queue required
import java.awt.Point;

public class Snake{
	protected LinkedList<Point> body;
    //public LinkedList<Integer> dirs;
    private int buffer;
    public Direction dir;
    protected boolean isMoving;
    private Point next;

    public Snake(Point[] b,int dir) { 
    	isMoving=true; 
    	buffer=0;
    	body=new LinkedList<Point>();
    	//dirs=new LinkedList<Integer>();
    	for(Point p:b ) { //проверка на замктнутость
    		body.add(p);
    		//dirs.add(0);
    	}
    	this.dir=new Direction(dir);
    }
    
    public boolean turn(Direction newdir){
        if (dir.IsOpposit(newdir)) return false;
        dir=newdir;
        next=null;
        next=getNext();
        return true;
    }

    public boolean makeStep(){
    	if (!isMoving) return true;
    	
    	if (body.contains(getNext())) {
    		stop();
    		return false;
    	}
        body.add(getNext());
        //dirs.add(0);//Divide
        if (buffer>0) buffer-=1;
        else {
        	body.removeFirst();
        	//dirs.removeFirst();
        }
    	next=null;
        return true;
    }
    
    public void stop() {
    	isMoving=false;
    }
    
    public void start() {
    	isMoving=true;
    }
    
    public void grow(int val) {
    	buffer+=val;
    }
    
    public Point getNext() {
    	if(next == null) {
	    	Point d=dir.getDir();
	    	next=new Point(getHead().x+d.x,getHead().y+d.y);
    	}
    	return next;
    }
    
    public void setNext(Point newNext) {
    	next=newNext;
    }
    
    public Point getHead() {
    	return body.getLast();
    }
}

	