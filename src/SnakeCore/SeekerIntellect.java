package SnakeCore;

import java.awt.Point;

public class SeekerIntellect implements IIntellect {

    private Direction dir;
    private GameState game;
    private Snake snake;
    @Override
    public void init(GameState game, Direction dir) {
        this.game=game;
        this.dir=dir;
    }
    
    @Override
    public Direction getDir() {
        Point head=snake.getHead();
        Point targ=game.getObjsArr().stream()
                         .filter((p)->{return p instanceof Food;})
                         .map((p)->{
                                 return new Tuple<IObject,Double>
                                     (p,(Math.pow(p.getLocs()[0].x-head.x,2)+
                                         Math.pow(p.getLocs()[0].y-head.y,2)));
                             })
                         .min((p1,p2)->Double.compare(p1.getY(), p2.getY())).get().getX().getLocs()[0];
        Point delt =new Point(targ.x-head.x,targ.y-head.y);
        
        /*
        if(Math.abs(delt.x)>Math.abs(delt.y)) {
            dir=new Direction(new Point((int) Math.signum(delt.x),0));
        }else{
            dir=new Direction(new Point(0,(int) Math.signum(delt.y)));
        }
        */
        
        if(Math.abs(delt.x)>Math.abs(delt.y)) {
            if(-dir.getDir().x!=Math.signum(delt.x)) {
                dir=new Direction(new Point((int) Math.signum(delt.x),0));
            }else if(delt.y!=0){
                dir=new Direction(new Point(0,(int) Math.signum(delt.y)));
            }
        }else{
            if(-dir.getDir().y!=Math.signum(delt.y)){
                dir=new Direction(new Point(0,(int) Math.signum(delt.y)));
            }else if(delt.x!=0){
                dir=new Direction(new Point((int) Math.signum(delt.x),0));
            }
        }
        
        
        int count=0;
        Point p=game.getBoundedCord(new Point(head.x+dir.getDir().x,head.y+dir.getDir().y));
        while(count<4 && !game.isSafeSafe(p)) {
            dir=dir.nextDir();
            p=game.getBoundedCord(new Point(head.x+dir.getDir().x,head.y+dir.getDir().y));
            count+=1;
        }
        return dir;
    }

    @Override
    public void setSnake(Snake snake) {
        this.snake=snake;
    }
}
