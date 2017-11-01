package SnakeCore;

import java.awt.Point;

public class Teleport extends IObject {

    private char ico;
    private Point enterA;
    private Point enterB;
    private Point[] ps;

    public Teleport(TeleportFactory fact,char ico, Point[] ps) {
        this.fact = fact;
        this.ps = ps;
        enterA = ps[0];
        enterB = ps[1];
        this.ico=ico;
    }

    @Override
    public Point[] getLocs() {
        return ps;
    }

    @Override
    public char getIcon() {
        return ico;
    }

    @Override
    public void tick() {}

    @Override
    public boolean interact(Snake snake, Point p) {
        Point targ;
        if (p.x == enterA.x && p.y == enterA.y)
            targ = enterB;
        else // (p.x==enterB.x && p.y==enterB.y)
            targ = enterA;
        Point dir = snake.dir.getDir();
        snake.setNext(new Point(targ.x + dir.x, targ.y + dir.y));
        return false;
    }

    public IObjFactory getProducer() {
        return fact;
    }

}
