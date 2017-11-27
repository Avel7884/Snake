package SnakeTests;

import static org.junit.Assert.*;


import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.swt.widgets.Display;
import org.junit.*;
import SnakeCore.FoodFactory;
import SnakeCore.GameState;
import SnakeCore.IObject;
import SnakeCore.StateParser;
import SnakeCore.Tuple;
import SnakeCore.Snake;
import SnakeCore.SnakeFactory;
import SnakeCore.IIntellect;
import SnakeCore.IObjFactory;
import SnakeCore.Food;
import SnakeCore.FoodFactory;
import GUI.GUI;
import SnakeCore.ControlIntellect;
import SnakeCore.Direction;
import SnakeCore.SimpleIntel;

import org.junit.Test;



public class CoreTestsNew {

	@Test
    public void testMakeGameSuccessfully(){
		StateParser game = new StateParser();
        GameState result = game.makeGame("tests\\T4.txt");
        
        char[][] maze = {
        		{'.','.'},
        		{'.','.'},
        		{'.','.'}
        	};
     
        assertEquals(maze, result.getMap());
    }
	
	@Test
	public void testMakeGameFailure(){
        GameState game = StateParser.makeGame("tests\\hvm.txt");

        assertNull(game);
    }
	
	@Test
	public void testMakeGameFailure2(){
        GameState game = StateParser.makeGame("tests\\T15.txt");

        assertNotNull(game);
    }
	
	@Test
	public void testMakeGameFailure2(){
        GameState game = StateParser.makeGame("tests\\K15.txt");

        assertNull(game);
    }
	
	@Test
	public void testGameStateMakeTickSuccessfully(){
        GameState game = StateParser.makeGame("tests\\T3.txt");
        boolean result = game.makeTick();

        assertTrue(result);
    }
	
	@Test
	public void testGameStateGetMap(){
        GameState game = StateParser.makeGame("tests\\T7.txt");
        char[][] result = game.getMap();
        
        char[][] maze = {
        		 {'+','+','+','+','+','+','+','+','+','+','+','+','+'},
        		 {'+','.','.','.','.','.','.','.','.','.','.','.','+'},
        		 {'+','.','+','+','.','+','+','+','.','+','+','.','+'},
        		 {'+','.','.','+','.','.','+','.','.','+','.','.','+'},
        		 {'+','.','.','+','.','.','.','.','.','+','.','.','+'},
        		 {'+','.','.','+','.','.','+','.','.','+','.','.','+'},
        		 {'+','.','+','+','+','+','+','+','+','+','+','.','+'},
        		 {'+','.','.','+','.','.','+','.','.','+','.','.','+'},
        		 {'+','.','.','+','.','.','.','.','.','+','.','.','+'},
        		 {'+','.','.','+','.','.','+','.','.','+','.','.','+'},
        		 {'+','.','+','+','.','+','+','+','.','+','+','.','+'},
        		 {'+','.','.','.','.','.','.','.','.','.','.','.','+'},
        		 {'+','+','+','+','+','+','+','+','+','+','+','+','+'}
        };

        assertEquals(maze, result);
    }
	
	@Test
	public void testGameStateMakeTickIsNotAlive(){
        GameState game = StateParser.makeGame("tests\\T3.txt");
        game.setIsAlive(false);
        boolean result = game.makeTick();

        assertFalse(result);
    }
	
	@Test
	public void testGameStateMakegetBoundedCord1(){
        GameState game = StateParser.makeGame("tests\\T4.txt");
        Point result = game.getBoundedCord(new Point(2, 0));

        assertEquals(new Point(0, 0), result);
    }
	
	@Test
	public void testGameStateMakegetBoundedCord2(){
        GameState game = StateParser.makeGame("tests\\T4.txt");
        Point result = game.getBoundedCord(new Point(0, 3));

        assertEquals(new Point(0, 0), result);
    }
	
	@Test
	public void testGameStateMakegetBoundedCord3(){
        GameState game = StateParser.makeGame("tests\\T4.txt");
        Point result = game.getBoundedCord(new Point(-1, 0));

        assertEquals(new Point(1, 0), result);
    }
	
	@Test
	public void testGameStateMakegetBoundedCord4(){
        GameState game = StateParser.makeGame("tests\\T4.txt");
        Point result = game.getBoundedCord(new Point(0, -1));

        assertEquals(new Point(0, 2), result);
    }
	
	@Test
	public void testSnakeFactoryCreate1(){
        GameState game = StateParser.makeGame("tests\\T4.txt");
        Point[] p = {
        		new Point(0, 0),
        		new Point(0, 1) 
        };
        Snake[] result = new SnakeFactory().create(game, p);

        assertEquals(null, result);
    }
	@Test
	public void testSnakeFactoryCreate2(){
        GameState game = StateParser.makeGame("tests\\T4.txt");
        Point[] p = {
        		new Point(0, 0),
        		new Point(0, -1) 
        };
        Snake[] result = new SnakeFactory().create(game, p);

        assertEquals(null, result);
    }
	
	
	
	@Test
	public void testGameOneStep(){
		GameState gameState = StateParser.makeGame("tests\\T3.txt");
		IIntellect intel = gameState.getCtrlIntel();
		boolean b = gameState.makeTick();
		assertTrue(b);
		
    }
	
	
	@Test
	public void testGameFiveStep(){
		GameState gameState = StateParser.makeGame("tests\\T3.txt");
		IIntellect intel = gameState.getCtrlIntel();
		boolean b = true;
		for (int i =0; i < 5; i++) {
			b = gameState.makeTick();
			if (b)
			{
				break;
			}
			
		}
		assertTrue(b);
		
    }
	
	@Test
	public void testFood(){
		FoodFactory f = new FoodFactory();
		Food food = new Food(f, new Point(0,5));
		assertEquals(new Point[] {new Point(0,5)}, food.getLocs());
	}
		
	@Test
	public void testFoodGetIcon(){
		FoodFactory f = new FoodFactory();
		Food food = new Food(f, new Point(0,5));
		assertEquals('*', food.getIcon());
	}
	
	@Test
	public void testDirectionWhatDir1(){
		Direction dir = new Direction(new Point(1, 0));
		assertEquals(6, dir.getDirN());	
	}
	
	@Test
	public void testDirectionWhatDir2(){
		Direction dir = new Direction(new Point(-1, 0));
		assertEquals(4, dir.getDirN());	
	}
	
	@Test
	public void testDirectionWhatDir3(){
		Direction dir = new Direction(new Point(0, 1));
		assertEquals(2, dir.getDirN());	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDirectionWhatDirEx(){
		new Direction(new Point(-2, -1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDirectionSetDirEx(){
		Direction dir = new Direction(new Point(0, -1));
		dir.setDir(11);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDirectionGetDirEx(){
		Direction dir = new Direction(new Point(0, -1));
		dir.setErrorDir(5);
		dir.getDir();
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDirectionNextDirEx(){
		Direction dir = new Direction(new Point(0, -1));
		dir.setErrorDir(5);
		dir.nextDir();
	}
	
	@Test
	public void testDirectionWhatDir4(){
		Direction dir = new Direction(new Point(0, -1));
		assertEquals(8, dir.getDirN());	
	}
	
	@Test
	public void testDirectionGetDir1(){
		Direction dir = new Direction(new Point(-1, 0));
		assertEquals(new Point(-1, 0), dir.getDir());	
	}
	
	@Test
	public void testDirectionGetDir2(){
		Direction dir = new Direction(new Point(0, -1));
		assertEquals(new Point(0, -1), dir.getDir());	
	}
	
	@Test
	public void testDirectionIsOpposit1(){
		Direction dir1 = new Direction(new Point(-1, 0));
		Direction dir2 = new Direction(new Point(0, 1));
		assertFalse(dir2.isOpposit(dir1));
	}
	
	@Test
	public void testDirectionIsOpposit2(){
		Direction dir1 = new Direction(new Point(-1, 0));
		Direction dir2 = new Direction(new Point(0, 1));
		assertFalse(dir1.isOpposit(dir2));
	}
	
	@Test
	public void testDirectionIsOpposit31(){
		Direction dir1 = new Direction(2);
		Direction dir2 = new Direction(8);
		assertTrue(dir1.isOpposit(dir2));
	}
	@Test
	public void testDirectionIsOpposit32(){
		Direction dir1 = new Direction(2);
		Direction dir2 = new Direction(8);
		assertTrue(dir2.isOpposit(dir1));
	}
	
	@Test
	public void testControlIntellectSetDir1(){
		GameState gameState = StateParser.makeGame("tests\\T2.txt");
		Direction dir1 = new Direction(new Point(-1, 0));
		Direction dir2 = new Direction(new Point(1, 0));
		ControlIntellect con = new ControlIntellect();
		con.init(gameState, dir1);
		con.setDir(dir2);

		assertEquals(dir1, con.getDir());	
	}
	
	@Test
	public void testControlIntellectSetDir2(){
		GameState gameState = StateParser.makeGame("tests\\T2.txt");
		Direction dir1 = new Direction(new Point(1, 0));
		Direction dir2 = new Direction(new Point(0, 1));
		ControlIntellect con = new ControlIntellect();
		con.init(gameState, dir1);
		con.setDir(dir2);

		assertEquals(dir2, con.getDir());	
	}
	
	@Test
	public void testControlIntellectSetDirInt1(){
		GameState gameState = StateParser.makeGame("tests\\T2.txt");
		Direction dir1 = new Direction(new Point(1, 0));
		ControlIntellect con = new ControlIntellect();
		con.init(gameState, dir1);
		con.setDir(8);
		assertEquals(new Direction(8).getDir(), con.getDir().getDir());

	}
	@Test
	public void testControlIntellectSetDirInt2(){
		GameState gameState = StateParser.makeGame("tests\\T2.txt");
		Direction dir1 = new Direction(new Point(1, 0));
		ControlIntellect con = new ControlIntellect();
		con.init(gameState, dir1);
		con.setDir(4);
		
		assertEquals(dir1.getDir(), con.getDir().getDir());

	}
	
	@Test
	public void testControlIntellectsetSnake(){
		StateParser g = new StateParser();
        GameState game = g.makeGame("tests\\T3.txt");
        
        Snake[] snakes = game.getSnake();
		Direction dir = new Direction(new Point(1, 0));
		ControlIntellect con = new ControlIntellect();
		con.init(game, dir);
		con.setSnake(snakes[0]);
		

	}
	
	@Test
    public void testSnakeEatFood(){
		StateParser g = new StateParser();
        GameState game = g.makeGame("tests\\T3.txt");
        
        Snake[] snakes = game.getSnake();
        
        FoodFactory f = new FoodFactory();
		Food food = new Food(f, new Point(0,5));
		food.interact(snakes[0], new Point(0,5));
		assertEquals(1, snakes[0].getBuffer());
    }
	@Test
    public void testSnakeNotAlive(){
		StateParser g = new StateParser();
        GameState game = g.makeGame("tests\\T3.txt");
        
        Snake[] snakes = game.getSnake();
        
        snakes[0].setAlive(false);
        snakes[0].start();
        snakes[0].tick();
        assertFalse(snakes[0].isAlive());
    }
	
	@Test
	public void testSnakeBodyContaneNext(){
		StateParser g = new StateParser();
	    GameState game = g.makeGame("tests\\T3.txt");
	        
	    Snake[] snakes = game.getSnake();
	        
	    snakes[0].setNext(snakes[0].getBody().get(1));
	    snakes[0].tick();
	    assertFalse(snakes[0].isAlive());
	}
	
	@Test
	public void testSnakeBufferMoreThan0(){
		StateParser g = new StateParser();
	    GameState game = g.makeGame("tests\\T3.txt");
	        
	    Snake[] snakes = game.getSnake();
	        
	    snakes[0].grow(3);
	    snakes[0].tick();
	    assertTrue(snakes[0].isAlive());
	}
	
	@Test
	public void testSnakeInteract(){
		StateParser g = new StateParser();
	    GameState game = g.makeGame("tests\\T3.txt");	        
	    Snake[] snakes = game.getSnake();
	    assertTrue(snakes[0].interact(snakes[1], new Point(0, 5)));
	}
	
	@Test
	public void testSnakeSetBody(){
		StateParser g = new StateParser();
	    GameState game = g.makeGame("tests\\T3.txt");	        
	    Snake[] snakes = game.getSnake();

	    LinkedList<Point> membs = new LinkedList<Point>();
	    membs.add(new Point(0, 5));
	    membs.add(new Point(0, 6));
	    membs.add(new Point(0, 7));
	    
	    snakes[0].setBody(membs);
	    assertEquals(membs, snakes[0].getBody());
	}
	
	@Test
	public void testSnakeGetIcon(){
		StateParser g = new StateParser();
	    GameState game = g.makeGame("tests\\T3.txt");	        
	    Snake[] snakes = game.getSnake();
	    assertEquals('1', snakes[0].getIcon());
	}
	
	@Test
	public void testSimpleIntelInit(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T3.txt");
		Snake[] snakes = game.getSnake();
		Direction dir = new Direction(6);
		SimpleIntel in = new SimpleIntel();
		in.init(game, dir);
		in.setSnake(snakes[0]);
		
		assertEquals(dir.getDir(), in.getDir().getDir());
	}
	@Test
	public void testSimpleIntelGameIsSafe(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T5.txt");
		Snake[] snakes = game.getSnake();
		Direction dir = new Direction(2);
		SimpleIntel in = new SimpleIntel();
		in.init(game, dir);
		in.setSnake(snakes[0]);
		
		assertNotEquals(dir.getDir(), in.getDir().getDir());
	}
	
	
	
	@Test
	public void testSnakeFactoryUtiliz(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T6.txt");
		boolean b = game.makeTick();
		
		assertTrue(b);
	}
	
	@Test
	public void testSnakeFactoryIIntel0(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T8.txt");
		boolean b = game.makeTick();
		
		assertTrue(b);
	}
	@Test
	public void testSnakeFactoryIIntel1(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T9.txt");
		boolean b = game.makeTick();
		
		assertTrue(b);
	}
	
	@Test
	public void testSnakeFactoryIIntelNull(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T10.txt");
		boolean b = game.makeTick();
		
		assertTrue(b);
	}
	
	@Test
	public void testSnakeFactoryIIntel(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T11.txt");
		boolean b = game.makeTick();
		
		assertTrue(b);
	}
	
	@Test
	public void testFoodFactoryCreateFood(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T10.txt");
		FoodFactory ff = new FoodFactory();
		Point[] coor = {
				new Point(0,5),
				new Point(0,4),
				new Point(0,3)
		};
		Food[] foods = ff.create(game, coor);
		assertEquals(3, foods.length);
	}
	
	@Test
	public void testFoodFactoryUtilize(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T12.txt");
		boolean b = game.makeTick();
		
		assertTrue(b);
	}
	
	@Test
	public void testFoodFactoryGetProduct(){
		FoodFactory ff = new FoodFactory();
		Food[] foods = ff.getProducts();
		
		assertNull(foods);
	}
	

	@Test
	public void testGameStateGetMapSnake(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T13.txt");
		char[][] map = game.getMap();
		
		assertEquals(new Point(9,9), game.getSize());
		
	}
	
	@Test
	public void testGameStateIsSafe1(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T13.txt");
		assertTrue(game.isSafeSafe(new Point(4, 4)));
		
	}
	
	@Test
	public void testGameStateIsSafe2(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T13.txt");
		assertTrue(game.isSafeSafe(new Point(1, 6)));
		
	}
	
	@Test
	public void testGameStateIsSafe3(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T13.txt");
		assertFalse(game.isSafeSafe(new Point(7, 7)));
		
	}
	
	@Test
	public void testGameStateIsSafe4(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T13.txt");
		assertTrue(game.isSafeSafe(new Point(7, 5)));
		
	}
	
	@Test
	public void testGameStateIsSafe5(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T13.txt");
		assertTrue(game.isSafeSafe(new Point(1, 4)));
		
	}
	
	@Test
	public void testGameStateGetCell(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T14.txt");
		assertFalse(game.isSafeSafe(new Point(7, 7)));
		
	}
	
	@Test
	public void testGameStateSnakeBomp(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T15.txt");
		Snake[] snakes = game.getSnake();
		assertTrue(game.makeTick());
		
		
	}
	
	@Test
	public void testGameStateCollision(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T17.txt");
		Snake[] snakes = game.getSnake();
		Point p = game.collise(snakes[0]);
		
		
	}

	
}
