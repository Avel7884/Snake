package SnakeCore;

import java.awt.Point;

public abstract class IObjFactory{
    public abstract IObject[] utilize(IObject obj);
    public abstract IObject[] tick();
	public abstract IObject[] create(GameState game,Point[] ps);
	public IObject[] configure(GameState game,Integer[] args) {
	    Point[] tmp;
	    if(args[0]==-1) {
	        tmp= new Point[(args.length-1)/2];
	        for(int i=1;i<args.length;i+=2) {
	            tmp[i/2]=new Point(args[i],args[i+1]);
	        }
	        return create(game,tmp);
	    }
	    return baseConf(game,args);
	}
	protected abstract IObject[] baseConf(GameState game,Integer[] args);
	public abstract IObject[] getProducts();
}
