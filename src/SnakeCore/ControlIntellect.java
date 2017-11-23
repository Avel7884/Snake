package SnakeCore;

public class ControlIntellect implements IIntellect {

    private Direction dir;
    @Override
    public void init(GameState game,Direction dir) {
        this.dir=dir;
    }

    @Override
    public Direction getDir() {
        return dir;
    }
    
    public void setDir(Direction dir) {
        if ( this.dir.isOpposit(dir)) return;
        this.dir=dir;
    }
    
    public void setDir(int dir) {
        Direction newdir=new Direction(dir);
        if ( this.dir.isOpposit(newdir)) return;
        this.dir=newdir;
    }

    @Override
    public void setSnake(Snake snake) {
        return;
    }
}
