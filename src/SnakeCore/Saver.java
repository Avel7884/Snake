package SnakeCore;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver {	
	public static void save(String path, GameState game)
	{
		StringBuffer rezultStr = new StringBuffer();
		int telCounter = 0;
		int hedgCounter = 0;
		int foodCounter  = 0;
		int pilCounter = 0;
		ArrayList<Point> food = new ArrayList();
		ArrayList<Point> pil = new ArrayList();
		ArrayList<Point> hedg = new ArrayList();
		ArrayList<Point> tel = new ArrayList();
		
		char[][] a = game.getMap();
		rezultStr.append(Integer.toString(a[1].length - 4) + ' ' + Integer.toString(a.length) + '\n');
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
			{
				switch(a[i][j]) {	
					case('A'):
						Point p1 = new Point(i, j);
						hedg.add(p1);
						p1 = new Point(-1, 0);
						hedg.add(p1);
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('W'):
						Point p2 = new Point(i, j);
						hedg.add(p2);
						p2 = new Point(0, -1);
						hedg.add(p2);
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('S'):
						Point p3 = new Point(i, j);
						hedg.add(p3);
						p3 = new Point(1, 0);
						hedg.add(p3);
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('D'):
						Point p4 = new Point(i, j);
						hedg.add(p4);
						p4 = new Point(0, 1);
						hedg.add(p4);
						rezultStr.append('.');
						hedgCounter++;
						break;
					case('%'):
						Point p = new Point(i, j);
						pil.add(p);
						rezultStr.append('.');
						pilCounter++;
						break;
					case('P'):
						rezultStr.append('.');
						telCounter++;
						break;
					case('T'):
						rezultStr.append('.');
						telCounter++;
						break;
					case('*'):
						Point p5 = new Point(i, j);
						food.add(p5);
						rezultStr.append('.');
						foodCounter++;
						break;
					case('.'):
						rezultStr.append('.');
						break;
					case('#'):
						rezultStr.append('#');
						break;
					case('+'):
						rezultStr.append('+');
						break;
					case('@'):
						rezultStr.append('.');
						break;
				}
			}
			rezultStr.append('\n');
		}
		List<Point> snake = game.getSnake().getBody();
		for (int i= 0; i < snake.size(); i++)
		{
			int x = (int)snake.get(i).getX();
			int y = (int)snake.get(i).getY();
			rezultStr.append(Integer.toString(x) + ' '  + Integer.toString(y) + ' ');
		}
		rezultStr.append('\n');
		rezultStr.append(Integer.toString(game.getSnakeDir().getDirN()) + '\n');
		if (pilCounter != 0) 
		{
			rezultStr.append("Pillow -1 ");
			for (int i = 0; i < pilCounter; i++)
				rezultStr.append(Integer.toString(pil.get(i).x) + ' '  + Integer.toString(pil.get(i).y) + ' ');
		}
		if (foodCounter != 0) 
		{
			rezultStr.append("Food -1 " + Integer.toString(foodCounter) + '\n');
			for (int i = 0; i < foodCounter; i++)
				rezultStr.append(Integer.toString(food.get(i).x) + ' '  + Integer.toString(food.get(i).y) + ' ');
			
		}
		if (telCounter != 0)
		{
			rezultStr.append("Teleport " + Integer.toString(telCounter) + '\n');
		}
		if (hedgCounter != 0)
		{
			rezultStr.append("Hedg -1 " + Integer.toString(hedgCounter) + '\n');
			for (int i = 0; i < hedgCounter; i++)
			{
				rezultStr.append(Integer.toString(hedg.get(i).x) + ' '  + Integer.toString(hedg.get(i).y) + ' ');
				rezultStr.append(Integer.toString(hedg.get(i + 1).x) + ' '  + Integer.toString(hedg.get(i + 1).y) + ' ');
			}
			
		}
		try {
			FileWriter writer = new FileWriter(path, false);
			writer.write(rezultStr.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
