package SnakeCore;

import java.awt.Point;

public class HedgFactory implements IObjFactory {

    private GameState game;

    @Override
    public Hedg[] configure(GameState game, Point[] p) {
        this.game = game;
        return new Hedg[] {new Hedg(this, p)};
    }

    public Hedg[] configure(GameState game, Integer[] args) {
        this.game = game;
        return new Hedg[] {new Hedg(this, game.getRndFreePoint())};
    }

    @Override
    public Hedg[] utilize(IObject obj) {
        Hedg hedg;
        if (obj instanceof Hedg)
            hedg = Hedg.class.cast(obj);
        else
            return null;
        Point loc = game.getRndFreePoint(); 
        while (game.getCell(game.getBoundedCord(    //TODO what
                            new Point(loc.x + hedg.getDir().getDir().x,
                                      loc.y + hedg.getDir().getDir().y))) != '.')
            loc = game.getRndFreePoint();
        return new Hedg[] {new Hedg(this, loc)};
    }

    @Override
    public Hedg[] tick() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Hedg[] getProducts() {
        // TODO Auto-generated method stub
        return null;
    }

}
