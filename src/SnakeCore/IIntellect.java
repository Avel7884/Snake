package SnakeCore;

public interface IIntellect {
    void init(GameState game,Direction dir);
    Direction getDir();
    void setSnake(Snake snake);
}
