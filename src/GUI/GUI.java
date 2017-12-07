package GUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import SnakeCore.ControlIntellect;
import SnakeCore.GameState;
import SnakeCore.StateParser;
//import SnakeCore.IIntellect;
//import SnakeCore.Saver;
//import java.util.Timer;
//import java.util.TimerTask;

public class GUI {	
	private static Display display = new Display();
	private static Shell shell = new Shell(display);
	private static GameState gameState;
    private static ControlIntellect[] intel;
	private static Font font = new Font(display,"Arial",14,SWT.BOLD | SWT.ITALIC);
	private static boolean flag = false;
	private static Image hedgA = new Image(display, ".\\sprites\\hedgA.png");
	private static Image hedgW = new Image(display,".\\sprites\\hedgW.png");
	private static Image hedgD = new Image(display, ".\\sprites\\hedgD.png");
	private static Image hedgS = new Image(display, ".\\sprites\\hedgS.png");
	private static Image tel1 = new Image(display, ".\\sprites\\\\port1.png");
	private static Image tel2 = new Image(display, ".\\sprites\\port2.png");
	private static Image pil = new Image(display, ".\\sprites\\pil.png");
	private static Image apple = new Image(display, ".\\sprites\\apple.gif");

	private static String level=".\\levels\\Snakeling.txt";
	
	private static final String[][] FILTERS = {
            {"Все файлы (*.*)"     , "*.*"  }};

	private static void setFilters(FileDialog dialog)
	{
	String[] names = new String[FILTERS.length];
	String[] exts  = new String[FILTERS.length];
	for (int i = 0; i < FILTERS.length; i++) {
	names[i] = FILTERS[i][0];
	exts [i] = FILTERS[i][1];
	}
	dialog.setFilterNames(names);
	dialog.setFilterExtensions(exts);
	}

	
	private static void drawSprite(PaintEvent e, Image im, int x, int y, int size)
	{
		drawColoredSq(e, x, y, size - 1, SWT.COLOR_BLUE);
		Image image = new Image(display, im.getImageData().scaledTo(size, size ));	
		e.gc.drawImage(image, x, y);
	}
	private static void drawColoredSq(PaintEvent e, int x, int y, int size, int color)
	{
		e.gc.setBackground(shell.getDisplay().getSystemColor(color));
		e.gc.fillRectangle(x, y, size, size);
	}
	public static void runGame(Canvas canvas, String path, Runnable gameTick)
	{
		gameState = StateParser.makeGame(path);
		intel = gameState.getCtrlIntel();
		display.timerExec(500, gameTick);		
	}
	
	public static void main(String[] arguments ) {
		
		Canvas canvas = new Canvas(shell, SWT.BORDER);
		shell.setText( "Snake Game" );
		shell.setSize (400, 400);
		shell.setLayout(new FillLayout());
		
		Runnable gameTick = new Runnable() {
			public void run() {
				if (!gameState.makeTick())
				{
					flag = true;
					canvas.redraw();
					display.timerExec(-1, this);
				}
				else
				{
					canvas.redraw();
					display.timerExec(500, this);	
				}
			}
		};
		
		canvas.addPaintListener(new PaintListener() 
		{
			public void paintControl(PaintEvent e)
			{	
				e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				e.gc.setFont(font);
				
				e.gc.fillRectangle(canvas.getBounds());
				char a[][] = gameState.getMap();
				int sqWidth = a[0].length;
				int sqHeight = a.length;
				sqHeight = (canvas.getBounds().height - 20) / sqHeight;
				sqWidth = canvas.getBounds().width / sqWidth;
				int sqRez = Math.min(sqWidth, sqHeight);
				for (int i = 0 ; i < a.length; i++)
					for (int j = 0; j < a[i].length; j++)
					{
						switch(a[i][j]) {
						case('A'):
							drawSprite(e, hedgA, j*sqRez, i*sqRez, sqRez);
							break;
						case('W'):
							drawSprite(e, hedgW, j*sqRez, i*sqRez, sqRez);
							break;
						case('S'):
							drawSprite(e, hedgS, j*sqRez, i*sqRez, sqRez);
							break;
						case('D'):
							drawSprite(e, hedgD, j*sqRez, i*sqRez, sqRez);
							break;
							/*
						case('@'):
							if (j == gameState.getHead().x && i == gameState.getHead().y)
								drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_DARK_GREEN);
							else
								drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_GREEN);
							break;
							*/
                        case('1'):
                            drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_GREEN);
                            break;
						case('#'):
							drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_BLACK);
							break;
						case('+'):
							drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_WHITE);
							break;
						case('%'):
							drawSprite(e, pil, j*sqRez, i*sqRez, sqRez);
							break;
						case('.'):
							drawColoredSq(e, j*sqRez, i*sqRez, sqRez - 1, SWT.COLOR_BLUE);
							break;
						case('*'):
							drawSprite(e, apple, j*sqRez, i*sqRez, sqRez);
							break;
						case('P'):
							drawSprite(e, tel1, j*sqRez, i*sqRez, sqRez);
							break;
						case('p'):
							drawSprite(e, tel2, j*sqRez, i*sqRez, sqRez);
							break;
						}
					}
				
				//e.gc.drawText("Длина змейки:  " + gameState.getLenght(), 10, sqRez * (a.length));
				if (flag) {
					e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
					e.gc.drawText("Game over", 100, 100);
				}
			}
		});
		
		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				switch(e.keyCode){
			 	 	case(SWT.F3):
			 	 		display.timerExec(-1, gameTick);
			 	 		FileDialog dlg = new FileDialog(shell, SWT.OPEN);
				 	 	setFilters(dlg);
				 	 	dlg.open();
				 	 	String fname = dlg.getFilterPath() + "\\" + dlg.getFileName();
				 	 	flag = false;
				 	 	runGame(canvas, fname, gameTick);
			 	 		break;
			 	 	case(SWT.F4):
			 	 		display.timerExec(-1, gameTick);
			 	 		flag = false;
			 	 		runGame(canvas, level, gameTick);
			 	 		break;
			 	 		/*
			 	 	case(SWT.F2):
			 	 		display.timerExec(-1, gameTick);
			 	 		FileDialog dlg2 = new FileDialog(shell, SWT.SAVE);
				 	 	setFilters(dlg2);
				 	 	dlg2.open();
				 	 	String fname2 = dlg2.getFilterPath() + "\\" + dlg2.getFileName();
				 	 	Saver.save(fname2, gameState);
				 	 	display.timerExec(500, gameTick);
			 	 		break;
			 	 		*/
					case(SWT.KEYPAD_4):
						intel[0].setDir(4);
						break;
					case(SWT.KEYPAD_6):
					    intel[0].setDir(6);
						break;
					case(SWT.KEYPAD_2):
					    intel[0].setDir(2);
						break;
					case(SWT.KEYPAD_8):
					    intel[0].setDir(8);
						break;
                    case(SWT.KEYPAD_9):
                        intel[0].makeSpawn();
                        break;
                    case('a'):
                        intel[1].setDir(4);
                        break;
                    case('d'):
                        intel[1].setDir(6);
                        break;
                    case('s'):
                        intel[1].setDir(2);
                        break;
                    case('w'):
                        intel[1].setDir(8);
                        break;
                    case('e'):
                        intel[1].makeSpawn();
                        break;
				}
					
				
			}
			public void keyReleased(KeyEvent arg0) {
			}
		});
		shell.open(); 
		shell.setSize(600, 600);
		runGame(canvas, level, gameTick);

		canvas.redraw();
		
		while (!shell.isDisposed())
		{
			
			if(!display.readAndDispatch())
				display.sleep();

		}
	 }
	 
}

