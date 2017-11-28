package SnakeCore;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SnakeFactory extends IObjFactory{

    //private GameState game;
    private FoodFactory foodFact;
    private List<Snake> snakes=new ArrayList<Snake>();
    private ControlIntellect mainIntel;

    @Override
    public Snake[] create(GameState game, Point[] ps) {
        //this.game = game;
        Iterator<Point> itr = Arrays.stream(ps).iterator();
        while (itr.hasNext()) {
            Point t=itr.next();//TODO need to do Something
            /*
            if (t.x==-1)
                return null;
            */
        }
        return null;
    }
    
    @Override
    protected Snake[] baseConf(GameState game, Integer[] args) {
        //this.game = game;
        foodFact=(FoodFactory) game.getFact("Food");
        Iterator<Integer> itr = Arrays.stream(args).iterator();
        List<Integer> tmp=new ArrayList<Integer>();
        while (itr.hasNext()) {
            int t=itr.next();
            if (t==-1 && tmp.size()>2) {
                IIntellect intel= getIntel(tmp.get(0));
                intel.init(game, new Direction(tmp.get(tmp.size()-1)));
                tmp.remove(0);
                tmp.remove(tmp.size()-1);
                Snake snake=new Snake((Integer[]) tmp.toArray(new Integer[tmp.size()]),this,intel,'1');
                snakes.add(snake);//TODO remove cast
                intel.setSnake(snake);
                tmp.clear();
            }else{
                tmp.add(t);
            }
        }
        return snakes.toArray(new Snake[snakes.size()]);
    }
    
    private IIntellect getIntel(int i) {
        switch (i) {
            case 0:return getCtrlIntel();
            case 1:return new SimpleIntel();
            case 2:return new SeekerIntellect();
            default: return null;
        }
    }
    
    public ControlIntellect getCtrlIntel() {
        if(mainIntel==null) {
            mainIntel=new ControlIntellect();
        }
        return mainIntel;
    }
    
    @Override
    public Snake[] utilize(IObject obj) {
        Snake snake;
        if (obj instanceof Snake) {
            snake = Snake.class.cast(obj);
        } else {
            return null;            //TODO removing exemplar from gamestate
        }
        for(Point p :snake.getBody()) {
            foodFact.setFood(p);
        }
        snakes.remove(snake);
        return null;
    }

    @Override
    public Snake[] tick() {
        return null;
    }
    
    @Override
    public Snake[] getProducts() {
        return snakes.toArray(new Snake[snakes.size()]);
    }

}
