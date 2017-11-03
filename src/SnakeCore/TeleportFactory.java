package SnakeCore;

import java.awt.Point;

public class TeleportFactory extends IObjFactory {
    //TODO count teleports
    @Override
    public Teleport[] create(GameState game, Point[] ps) {
        Teleport[] tmp=new Teleport[ps.length/2];
        for(int i=0;i<ps.length;i+=2) {
            tmp[i/2]=new Teleport(this,getChar(i/2),ps[i],ps[i+1]);
        }
        return tmp;
    }

    @Override
    public Teleport[] baseConf(GameState game, Integer[] args) {
        Teleport[] tmp=new Teleport[args[0]];
        for(int i=0;i<args[0];i++) {
            tmp[i]=new Teleport(this, getChar(i),new Point[] {game.getRndFreePoint(), game.getRndFreePoint()});
        }
        return tmp;
    }
    
    private char getChar(int i) {
        switch (i) {
            case 0:
                return 'P';
            case 1:
                return 'p'; 
            case 2:
                return 'T';
            case 3:
                return 't';
        }
        return 'F';
    }

    @Override
    public Teleport[] utilize(IObject obj) {
        return null;
    }

    @Override
    public Teleport[] tick() {
        return null;
    }

    @Override
    public Teleport[] getProducts() {
        return null;
    }

}
