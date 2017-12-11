package SnakeCore;

import java.awt.Point;

public class Pillow extends IObject {

    private Point loc;
    private int timer = 0;
    private int maxTimer = 5;
    private Snake snake;

    public Pillow(PillowFactory fact, Point[] p) {
        this.fact = fact;
        loc = p[0];
    }
    
    public Pillow(PillowFactory fact, Point p) {
        this.fact = fact;
        loc = p;
    }

    @Override
    public Point[] getLocs() {
        return new Point[] {loc};
    }

    @Override
    public char getIcon() {
        return '%';
    }

    @Override
    public void tick() {
        if (snake != null)
            timer += 1;
        if (timer == maxTimer) {
            snake.start();
            snake = null;
        }
    }

    @Override
    public boolean interact(IActiveObject iao, Point p) {
        if(iao instanceof Snake) {
            this.snake = (Snake) iao;
            snake.stop();
        }
        return false;
    }

    protected Snake getSnakeOn() {
        return snake;
    }

}
