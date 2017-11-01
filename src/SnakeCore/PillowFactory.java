package SnakeCore;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PillowFactory implements IObjFactory {

    private List<Pillow> toUtil = new ArrayList<Pillow>();

    @Override
    public Pillow[] configure(GameState game, Point[] ps) {
        return new Pillow[] {new Pillow(this, ps)};
    }

    @Override
    public Pillow[] configure(GameState game, Integer[] args) {
        return new Pillow[] {new Pillow(this, new Point[] {game.getRndFreePoint()})};
    }

    @Override
    public Pillow[] utilize(IObject obj) {
        toUtil.add(Pillow.class.cast(obj));
        return null;
    }

    @Override
    public Pillow[] tick() {
        for (Pillow pil : toUtil) {
            pil.tick();
            if (pil.getSnakeOn() == null)
                toUtil.remove(pil);
        }
        return null;
    }

    @Override
    public Pillow[] getProducts() {
        // TODO Auto-generated method stub
        return null;
    }


}
