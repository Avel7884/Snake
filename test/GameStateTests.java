import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import SnakeCore.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

public class GameStateTests {


    @Test
    public void testMakeGameFailure2(){
        GameState game = StateParser.makeGame("test\\tests\\T1123.txt");

        assertNull(game);
    }

    @Test
    public void testMakeGameFailure3(){
        GameState game = StateParser.makeGame("test\\tests\\T15.txt");
        assertNotNull(game);
    }


    @Test
    public void testGameStateMakeTickSuccessfully(){
        GameState game = StateParser.makeGame("test\\tests\\T3.txt");
        boolean result = game.makeTick();

        assertTrue(result);
    }


    @Test
    public void testMakeGameFailure1(){
        GameState game = StateParser.makeGame("tests\\T22.txt");
        assertNull(game);
    }


    @Test
    public void testGameStateMakeTickIsNotAlive(){
        GameState game = StateParser.makeGame("test\\tests\\T3.txt");
        game.setIsAlive(false);
        boolean result = game.makeTick();

        assertFalse(result);
    }




	/*
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
    */

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

    @Test ()
    public void testDirectionSetDirEx(){
        Direction dir = new Direction(new Point(0, -1));
    }

    @Test ()
    public void testDirectionNextDirEx(){
        Direction dir = new Direction(new Point(0, -1));
        dir.setErrorDir(5);
    }

    @Test
    public void testDirectionWhatDir4(){
        Direction dir = new Direction(new Point(0, -1));
        assertEquals(8, dir.getDirN());
    }

    @Test
    public void testDirectionGetDir1(){
        Direction dir = new Direction(new Point(-1, 0));
        Assert.assertEquals(new Point(-1, 0), dir.getDir());
    }

    @Test
    public void testDirectionGetDir2(){
        Direction dir = new Direction(new Point(0, -1));
        Assert.assertEquals(new Point(0, -1), dir.getDir());
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
    public void testControlIntellectsetSnake(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");

        Snake[] snakes = game.getSnake();
        Direction dir = new Direction(new Point(1, 0));
        ControlIntellect con = new ControlIntellect();
        con.init(game, dir);
        con.setSnake(snakes[0]);


    }

    @Test
    public void testSnakeEatFood(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");

        Snake[] snakes = game.getSnake();

        FoodFactory f = new FoodFactory();
        Food food = new Food(f, new Point(0,5));
        food.interact(snakes[0], new Point(0,5));
        assertEquals(1, snakes[0].getBuffer());
    }
    @Test
    public void testSnakeNotAlive(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");

        Snake[] snakes = game.getSnake();

        snakes[0].setAlive(false);
        snakes[0].start();
        snakes[0].tick();
        assertFalse(snakes[0].isAlive());
    }

    @Test
    public void testSnakeBodyContaneNext(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");

        Snake[] snakes = game.getSnake();

        snakes[0].setNext(snakes[0].getBody().get(1));
        snakes[0].tick();
        assertFalse(snakes[0].isAlive());
    }

    @Test
    public void testSnakeBufferMoreThan0(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");

        Snake[] snakes = game.getSnake();

        snakes[0].grow(3);
        snakes[0].tick();
        assertTrue(snakes[0].isAlive());
    }

    @Test
    public void testSnakeInteract(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");
        Snake[] snakes = game.getSnake();
        assertTrue(snakes[0].interact(snakes[1], new Point(0, 5)));
    }

    @Test
    public void testSnakeSetBody(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");
        Snake[] snakes = game.getSnake();

        LinkedList<Point> membs = new LinkedList<Point>();
        membs.add(new Point(0, 5));
        membs.add(new Point(0, 6));
        membs.add(new Point(0, 7));

        snakes[0].setBody(membs);
        Assert.assertEquals(membs, snakes[0].getBody());
    }

    @Test
    public void testSnakeGetIcon(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");
        Snake[] snakes = game.getSnake();
        assertEquals('1', snakes[0].getIcon());
    }

    @Test
    public void testSimpleIntelInit(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T3.txt");
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
        GameState game = g.makeGame("test\\tests\\T5.txt");
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
        GameState game = g.makeGame("test\\tests\\T6.txt");
        boolean b = game.makeTick();

        assertTrue(b);
    }

    @Test
    public void testSnakeFactoryIIntel0(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T8.txt");
        boolean b = game.makeTick();

        assertTrue(b);
    }
    @Test
    public void testSnakeFactoryIIntel1(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T9.txt");
        boolean b = game.makeTick();

        assertTrue(b);
    }


    @Test
    public void testFoodFactoryUtilize(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T12.txt");
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
        GameState game = g.makeGame("test\\tests\\T13.txt");
        char[][] map = game.getMap();

        assertEquals(new Point(9,9), game.getSize());

    }

    @Test
    public void testGameStateIsSafe1(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T13.txt");
        assertTrue(game.isSafeSafe(new Point(4, 4)));

    }

    @Test
    public void testGameStateIsSafe2(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T13.txt");
        assertTrue(game.isSafeSafe(new Point(1, 6)));

    }

    @Test
    public void testGameStateIsSafe3(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T13.txt");
        assertFalse(game.isSafeSafe(new Point(7, 7)));

    }

    @Test
    public void testGameStateIsSafe4(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T13.txt");
        assertTrue(game.isSafeSafe(new Point(7, 5)));

    }

    @Test
    public void testGameStateIsSafe5(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T13.txt");
        assertTrue(game.isSafeSafe(new Point(1, 4)));

    }

    @Test
    public void testGameStateGetCell(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T14.txt");
        assertFalse(game.isSafeSafe(new Point(7, 7)));

    }

    @Test
    public void testGameStateSnakeBomp(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T15.txt");
        Snake[] snakes = game.getSnake();
        assertTrue(game.makeTick());


    }

	/*
	@Test
	public void testGameStateCollision(){
		StateParser g = new StateParser();
		GameState game = g.makeGame("tests\\T17.txt");
		Snake[] snakes = game.getSnake();
		Point p = game.collise(snakes[0]);


	}
	*/

    @Test
    public void testIOojectGetFact(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T17.txt");
        Snake snake = game.getSnake()[0];
        assertTrue( snake.getFact() instanceof SnakeFactory);
    }
    @Test
    public void testWrongDirection(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T18.txt");
    }
    @Test
    public void testCretanObjectCreation(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T19.txt");
    }
    @Test
    public void testWrongGarbage(){
        StateParser g = new StateParser();
        GameState game = g.makeGame("test\\tests\\T17.txt");
        Snake snake = game.getSnake()[0];
        assertNull( snake.getFact().utilize(new Food(null,new Point(0,0))));
    }
    @Test
    public void testWrongFoodGarbage(){
        StateParser g = new StateParser();
        FoodFactory f=new FoodFactory();
        f.utilize(null);
        //.configure(g.makeGame("tests\\T17.txt"),new Integer[]{1,2});
    }



}