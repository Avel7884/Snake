package SnakeCore;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PillowFactory extends IObjFactory {

    private List<Pillow> toUtil = new ArrayList<Pillow>();

    @Override
    public Pillow[] create(GameState game, Point[] ps) {
        return (Pillow[]) Arrays.stream(ps).map((Point p)->new Pillow(this,p)).toArray(Pillow[]::new);
    }

    @Override
    public Pillow[] baseConf(GameState game, Integer[] args) {
        Pillow[] tmp=new Pillow[args[0]];
        for(int i=0;i<args[0];i++) {
            tmp[i]=new Pillow(this, new Point[] {game.getRndFreePoint()});
        }
        return tmp;
    }

    @Override
    public Pillow[] utilize(IObject obj) {
        toUtil.add(Pillow.class.cast(obj));
        return null;
    }

    @Override
    public Pillow[] tick() {
        toUtil=toUtil.stream().filter(pil->{
            pil.tick();
            return pil.getSnakeOn() != null;
        }).collect(Collectors.toList());
        return null;
    }

    @Override
    public Pillow[] getProducts() {
        return null;
    }


}
