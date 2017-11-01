package SnakeCore;

import java.awt.Point;

public final class Food extends IObject {

    private Point loc;

    public Food(FoodFactory fact, Point[] p) {
        this.fact = fact;
        loc = p[0];
    }

    /*
     * private void setFood() { loc=new Point(rnd.nextInt(game.width), rnd.nextInt(game.height));
     * while (game.getCell(loc)!='.') loc=new Point(rnd.nextInt(game.width),
     * rnd.nextInt(game.height)); }
     */

    @Override
    public Point[] getLocs() {
        return new Point[] {loc};
    }

    @Override
    public char getIcon() {
        return '*';
    }

    @Override
    public void tick() {}

    @Override
    public boolean interact(Snake snake, Point p) {
        snake.grow(1);
        return false;
    }

}
