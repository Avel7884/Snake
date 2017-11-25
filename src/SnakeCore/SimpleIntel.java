package SnakeCore;

import java.awt.Point;

public class SimpleIntel implements IIntellect {

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
        int count=0;
        Point p=game.getBoundedCord(new Point(head.x+dir.getDir().x,head.y+dir.getDir().y));
        while(count<5 && !game.isSafe(p)) {
            dir=dir.nextDir();
            count+=1;
        }
        return dir;
    }

    @Override
    public void setSnake(Snake snake) {
        this.snake=snake;
    }
}
