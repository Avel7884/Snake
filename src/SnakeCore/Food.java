package SnakeCore;

import java.awt.Point;

public final class Food implements IObject {

	private Point loc;
	private Snake snake;
	
	public Food(Snake snake,Point[] p) {
		loc=p[0];
		this.snake=snake;
	}
	
	public Food(Snake snake) {
		this.snake=snake;
	}
	
	/*private void setFood() {
		loc=new Point(rnd.nextInt(game.width), rnd.nextInt(game.height));
		while (game.getCell(loc)!='.')
			loc=new Point(rnd.nextInt(game.width), rnd.nextInt(game.height));
	}*/
	
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
	public boolean interact(Point p) {
		snake.grow(1);
		return false;
	}

}
