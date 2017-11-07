package SnakeCore;

import java.awt.Point;
import java.util.Random;

public final class Hedg extends IObject {

    private Direction dir;
    private char ico;
    private Point loc;
    private Random rnd;

    public Hedg(HedgFactory fact, Point[] p) {
        this.fact = fact;
        loc = p[0];
        commonInit();
    }

    public Hedg(HedgFactory fact, Point p) {
        this.fact = fact;
        loc = p;
        commonInit();
    }
    public Hedg(HedgFactory fact, Point p,Point d) {
        this.fact = fact;
        loc = p;
        dir = new Direction(d);
        ico = makeIco(dir.getDirN());
    }

    public Hedg(HedgFactory fact) {
        this.fact = fact;
        commonInit();
        // replace();
    }


    private void commonInit() {
        rnd = new Random();
        dir = new Direction((rnd.nextInt(4) + 1) * 2);
        ico = makeIco(dir.getDirN());
    }

    private char makeIco(int dir) {
        switch (dir) {
            case 6:
                return 'D';
            case 8:
                return 'W';
            case 2:
                return 'S';
            case 4:
                return 'A';
        }
        return 'F';
    }

    @Override
    public Point[] getLocs() {
        return new Point[] {loc};
    }

    @Override
    public char getIcon() {
        return ico;
    }

    @Override
    public void tick() {}

    /*
     * private void replace() { }
     */
    @Override
    public boolean interact(Snake snake, Point p) {
        if (snake.getDir().IsOpposit(dir)) {
            snake.grow(2);
            commonInit();
            // replace();
            return false;
        } else {
            return true;
        }
    }

    public Direction getDir() {
        return dir;
    }

}
