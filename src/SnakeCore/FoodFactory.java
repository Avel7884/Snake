package SnakeCore;

import java.awt.Point;
import java.util.Random;

public final class FoodFactory implements IObjFactory {

    private Random rnd = new Random();
    private GameState game;
    private int foodCount;
    
	@Override
	public Food[] configure(GameState game, Point[] p) {
	    this.game=game;
		return new Food[] { new Food(this,p)};
	}
    @Override
	public Food[] configure(GameState game,Integer[] args) {
	    this.game=game;
	    foodCount=args[0];
	    Food[] tmp= new Food[foodCount]; 
	    for(int i=0;i<foodCount;i++) {
	      tmp[i]=new Food(this,new Point[] {game.getRndFreePoint()});
	    }
		return tmp;
	}
    private Food[] configure(Point[] points) {
      return new Food[] { new Food(this,points)}; 
    }
    @Override
    public Food[] utilize(IObject obj) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override
    public Food[] tick() {
        if(rnd.nextInt(25)<=foodCount)
            return configure(new Point[] {game.getRndFreePoint()});
        else return null;
    }
    @Override
    public Food[] getProducts() {
      // TODO Auto-generated method stub
      return null;
    }

}
