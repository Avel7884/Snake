package SnakeCore;

import java.awt.Point;

public class TeleportFactory implements IObjFactory {

    @Override
    public Teleport[] configure(GameState game, Point[] ps) {
        return new Teleport[] {new Teleport(this, getChar(0), ps)};
    }

    @Override
    public Teleport[] configure(GameState game, Integer[] args) {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Teleport[] tick() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Teleport[] getProducts() {
        // TODO Auto-generated method stub
        return null;
    }

}
