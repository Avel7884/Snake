package SnakeConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import SnakeCore.GameState;
import SnakeCore.StateParser;
//import java.util.Timer;
//import java.util.TimerTask;

public class Program {
	static GameState game= StateParser.makeGame(".\\levels\\Die.txt");
	
	public static void main(String[] arguments ) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     System.out.println("Hello, User"); 
		 while( game.makeTick()) {
			 char[][] map= game.getMap();
			 for(int i=0;i<game.getSize().y;i++) {
				 System.out.println(new String(map[i]));
			 }
			 System.out.println();
			 
	        try{
	        	while(!game.turnSnake(Integer.parseInt(br.readLine())));
	        }catch(NumberFormatException nfe){
	            System.err.println("Invalid Format!");
	        }
		 }
	     /*
		 Timer timer =new Timer();
		 
		 timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  Tick();
			  }
			}, (long)1000 , (long)1000);
		*/
	 }
	 
}
