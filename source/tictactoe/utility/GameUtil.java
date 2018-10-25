package tictactoe.utility;

import java.util.Scanner;
import java.util.HashMap;

public class GameUtil{

	private static GameUtil gameInstance = null;
	private static Scanner gameTerminal = null;
	private static HashMap<Integer, String> gameMap = null;

	private GameUtil(){

	}

	public static GameUtil getInstance(){
		if(gameInstance == null)
			gameInstance = new GameUtil();
		return gameInstance;
	}

	public Scanner getGameTerminal(){
		if(gameTerminal == null)
			gameTerminal = new Scanner(System.in);
		return gameTerminal;
	}

	public HashMap<Integer, String> getGameMap(){
		if(gameMap == null)
			gameMap = new HashMap<Integer, String>();
		return gameMap;
	}
}