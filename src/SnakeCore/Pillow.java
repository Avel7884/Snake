package SnakeCore;

import java.awt.Point;

public class Pillow extends IObject {

    private Point loc;
    private int timer = 0;
    private int maxTimer = 15;
    private Snake snake;

    public Pillow(PillowFactory fact, Point[] p) {
        this.fact = fact;
        loc = p[0];
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
    public boolean interact(Snake snake, Point p) {
        this.snake = snake;
        snake.stop();
        return false;
    }

    protected Snake getSnakeOn() {
        return snake;
    }

}
