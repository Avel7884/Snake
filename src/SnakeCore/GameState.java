package SnakeCore;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

public class GameState {
    private SnakeFactory snakes;
    private char[][] maze;
    private char[][] map;
    private Random rnd;
    private boolean isAlive;
    private List<IObject> objs = new LinkedList<IObject>();
    private int height;
    private int width;

    private HashMap<String, IObjFactory> dic;
    {
        dic = new HashMap<String, IObjFactory>();
        dic.put("Snake", new SnakeFactory());
        dic.put("Food", new FoodFactory());
        //dic.put("Hedg", new HedgFactory());
        //dic.put("Teleport", new TeleportFactory());
        //dic.put("Pillow", new PillowFactory());
    }

    public GameState(char[][] maze,
            ArrayList<Tuple<String, Integer[]>> objsCreators) {
        isAlive = true;
        this.maze = maze;
        map = new char[maze.length][];
        rnd = new Random();
        height = maze.length;
        width = maze[0].length;// bad
        snakes = (SnakeFactory) dic.get("Snake");
        for (Tuple<String, Integer[]> tup : objsCreators) {
                setObjs(dic.get(tup.getX()).configure(this, tup.getY()));
        }
    }


    public char[][] getMap() {
        for (int i = 0; i < map.length; i++)
            map[i] = maze[i].clone();
        /*
        for (Snake snake:snakes.getProducts()) {
            for (int i = 0; i < snake.getBody().size(); i++) {
                Point p = snake.getBody().get(i);
                map[p.y][p.x] = '@';
            }
        }
        *///TODO

        for (int i = 0; i < getObjsArr().size(); i++) {
            Point[] ps = getObjsArr().get(i).getLocs();
            char ico = getObjsArr().get(i).getIcon();
            for (int j = 0; j < ps.length; j++) {
                Point p = ps[j];// Проверка на пересечение
                map[p.y][p.x] = ico;
            }
        }
        return map;
    }


    public boolean makeTick() {
        if (!isAlive)
            return false;

        ExecutorService service;
        List<IActiveObject> lst = getActives();

        if (lst.isEmpty()) {
            isAlive=false;
        }
        
        service = Executors.newFixedThreadPool(lst.size());
        tickFactorys();
        List<Future<Point>> tmp = lst.stream().map((x)->{return service.submit(()->{return collise(x);});}).collect(Collectors.toList());
        try {
            forab(lst,tmp,new ArrayList<Point>(Collections.nCopies(lst.size(), null)));
        }catch(InterruptedException e1){
            e1.printStackTrace();
        }catch(ExecutionException e2){
            e2.printStackTrace();
        }
        cleanSnakes();
        tickObjs();
        return true;
    } 
    
    private void forab(List<IActiveObject> lst, List<Future<Point>> tmp,List<Point> asd) throws InterruptedException, ExecutionException {
        if(lst.size()==0) {
            return;
        }
        boolean b=true;
        while(b) {
            b=false;
            for(int i=0;i<lst.size();i++) {
                if(asd.get(i)!=null) {
                    continue;
                }else if(tmp.get(i).isDone()) {
                    Point p=tmp.get(i).get();
                    if(p==null) {
                        continue;
                    }
                    asd.set(i,p);
                    killCrashed(lst,asd,i);
                }else{
                    b=true;
                    continue;
                }
            }
        }
        
    }
    
     /*
        ArrayList<Thread> ts = new ArrayList<Thread>();
        for(IActiveObject snake:getActives()){
            if (snake.isMoving() && 
                snake.isAlive()) {
                Thread t=new Thread(()->collise(snake));
                ts.add(t);
                t.start();
                //collise(snake);
            }
        }

    public Point collise(Snake snake) {
        snake.setNext(getBoundedCord(snake.getNext()));
        IObject col = objsCollision(snake.getNext());
        for (Point nextTmp = null; 
                nextTmp==null || 
                        (nextTmp.x != snake.getNext().x && 
                         nextTmp.y != snake.getNext().y);) {
            if (col == null) {
                break;
            }
            nextTmp = (Point) snake.getNext().clone();
            if (col.interact(snake, snake.getNext())) { //TODO make interact better
                snake.die();
                return null;
            } else {
                snake.setNext(getBoundedCord(snake.getNext()));
                col = objsCollision(snake.getNext());
            }
        }
        if(col!=null) {
            setObjs(col.getFact().utilize(col));//TODO Make it better
            objs.remove(col);
        }
        snake.setNext(getBoundedCord(snake.getNext()));
        return snake.getNext();
    }
    */
    
    public Point collise(IActiveObject snake) {
        if (!(snake.isAlive() && snake.isMoving())) {
            return null;
        }
        snake.setNext(getBoundedCord(snake.getNext()));
        IObject col = objsCollision(snake.getNext());
        if (col == null) {
            if (maze[snake.getNext().y][snake.getNext().x] != '.') {//maze[next.y][next.x] == '+' || //TODO
                snake.die();
            }        
        }else if (col.interact(snake, snake.getNext())) { //TODO make interact better
            snake.die();
        } else {
            setObjs(col.getFact().utilize(col));//TODO Make it better
            objs.remove(col);
        }
        return snake.isAlive() && snake.isMoving() ? snake.getNext() : null;
    }

    private boolean killCrashed(List<IActiveObject> snks,List<Point> nexts,int idx) {
        for(int i=0;i<snks.size();i++){
            if(idx!=i && nexts.get(i)!=null &&
               nexts.get(i).x==nexts.get(idx).x && 
               nexts.get(i).y==nexts.get(idx).y && 
               snks.get(i).isMoving()) {
                snks.get(idx).die();
                snks.get(i).die();
                return true;
            }
        }
        return false;
    }
    
    private void cleanSnakes() {
        Object[] lst = getActives().toArray();
        for(int i=0;i<lst.length;i++) {
            if(!((IActiveObject)lst[i]).isAlive()) {
                decay((IActiveObject)lst[i]);
            }
        }
    }
    
    private void tickFactorys() {
        for (IObjFactory fact : dic.values())
            setObjs(fact.tick());
    }

    private void tickObjs() {
        for (IObject obj : objs)
            obj.tick();
    }
    /*
    private void tickSnakes() {
        for(Snake snake :snakes.getProducts()) {
            snake.tick();
            if(!snake.isAlive()) {
                decay(snake);
            }
        }
    }
    */

    public Point getBoundedCord(Point p) {
        if (!(p.x >= 0 && p.x < width && p.y >= 0 && p.y < height)) {
            p = new Point((p.x + width) % width, (p.y + height) % height);
        }
        return p;
    }

    private IObject objsCollision(Point p) {
        for (IObject obj : getObjsArr())
            for (Point el : obj.getLocs())
                if (p.x == el.x && p.y == el.y)
                    return obj;
        return null;
    }
    
    private List<IActiveObject> getActives(){
        return objs.stream().filter(IActiveObject.class::isInstance).map(IActiveObject.class::cast).collect(Collectors.toList());
    }

    protected char getCell(Point p) {
        if (maze[p.y][p.x] == '#' || maze[p.y][p.x] == '+')
            return '#';
        //if (maze[p.y][p.x] == '+') //TODO
        //    return '+';
        for(IActiveObject snake:getActives())
            for (Point part : snake.getLocs())
                if (part.x == p.x && part.y == p.y)
                    return '@';
        IObject obj = objsCollision(p);
        if (obj != null)
            return obj.getIcon();
        return '.';
    }
    
    public ControlIntellect[] getCtrlIntel() {
        return snakes.getCtrlIntel();
    }

    protected Point getRndFreePoint() {
        Point loc = new Point(rnd.nextInt(width), rnd.nextInt(height));
        while (getCell(loc) != '.')
            loc = new Point(rnd.nextInt(width), rnd.nextInt(height));
        return loc;
    }

    protected List<IObject> getObjsArr() {
        return objs;
    }

    public Point getSize() {
        return new Point(width, height);
    }

    public void setObjs(IObject[] objs) {
        if (objs == null)
            return;
        for (IObject obj : objs) {
            this.objs.add(obj);
        }
    }
    public void setObj(IObject obj) {
        this.objs.add(obj);
    }
    public IObjFactory getFact(String s) {
        return dic.get(s);
    }
    public void decay(IActiveObject s) {
        setObjs(s.fact.utilize(s));
        objs.remove(s);
    }
    public boolean isSafe(Point p) {
        char ico =getCell(p);
        return ico =='.' || ico=='*';
    }
    public boolean isSafeSafe(Point p) {
        return isSafe(p) && 
               (isSafe(getBoundedCord(new Point(p.x+1,p.y)))||
                isSafe(getBoundedCord(new Point(p.x-1,p.y)))||
                isSafe(getBoundedCord(new Point(p.x,p.y+1)))||
                isSafe(getBoundedCord(new Point(p.x,p.y-1))));
    }
    /*
     * private void setObjs(IObject obj) { this.objs.add(obj); }
     */
    public Snake[] getSnake() {
    	return snakes.getProducts();
    }
    public void setIsAlive(boolean b) {
    	isAlive = b;
    }
}


