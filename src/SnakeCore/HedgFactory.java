package SnakeCore;

import java.awt.Point;
import java.util.Arrays;

public class HedgFactory extends IObjFactory {

    private GameState game;

    @Override
    public Hedg[] create(GameState game, Point[] ps) {
        this.game = game;
        return (Hedg[]) Arrays.stream(ps).map((Point p)->new Hedg(this,p)).toArray(Hedg[]::new);
        
    }

    public Hedg[] baseConf(GameState game, Integer[] args) {
        this.game = game;
        Hedg[] tmp=new Hedg[args[0]];
        for(int i=0;i<args[0];i++) {
            tmp[i]=new Hedg(this, new Point[] {game.getRndFreePoint()});
        }
        return tmp;
    }

    @Override
    public Hedg[] utilize(IObject obj) {
        Hedg hedg;
        if (obj instanceof Hedg) {
            hedg = Hedg.class.cast(obj);
        } else {
            return null;
        }
        Point loc = game.getRndFreePoint();
        while (game.getCell(game.getBoundedCord( // TODO what
                new Point(loc.x + hedg.getDir().getDir().x,
                          loc.y + hedg.getDir().getDir().y))) != '.') {
            loc = game.getRndFreePoint();
        }
        return new Hedg[] {new Hedg(this, loc)};
    }

    @Override
    public Hedg[] tick() {
        return null;
    }

    @Override
    public Hedg[] getProducts() {
        return null;
    }

}
