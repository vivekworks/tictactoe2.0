package tictactoe.process;

import tictactoe.process.Gamer;
import tictactoe.process.GameProcessor;
import java.util.Scanner;
import java.util.HashMap;
import tictactoe.gamers.Computer;
import tictactoe.gamers.Human;

public class Game{

    String result;
    int gameCount;
    String gameLevel;
    Gamer gamer1;
    Gamer gamer2;
    Gamer currentGamer;
    public final String TICTACTOE_X = "X";
    public final String TICTACTOE_O = "O";
    Scanner gameInput;
    HashMap<Integer, String> moveMap;
    GameProcessor gameProcessor;

    public Game(int gameOption){
        gamer1 = new Gamer(new Human());
		if(gameOption == 1)
			gamer2 = new Gamer(new Computer());
		else
			gamer2 = new Gamer(new Human());
		gameInput = new Scanner(System.in);
        setupGamers(gameOption);
        gameProcessor = new GameProcessor();
        moveMap = new HashMap<Integer, String>();
    }

    void setupGamers(int gameOption){
    	//Setup Gamer1
    	gamer1.setPosition(1);
    	System.out.print("Enter a name for Player 1 : ");
        setGamer(gamer1);
        gamer1.setTictacSymbol(TICTACTOE_X);

        //Setup Gamer2
        if(gameOption == 1){
            System.out.print("Hi! My name is Kamikaze. I'm your opponent for the day. Do you want to change my name ? Y / N --> ");
            try{
	            String nameOption = gameInput.nextLine();
	            if(nameOption != null && !(nameOption.equals("")) && nameOption.equals("Y")){
	            	System.out.print("Enter a name for Player 2 : ");
	            	setGamer(gamer2);
	        	} else{
	        		System.out.println("Kamikaze it is! Cool!");
	        	}
	        } catch(Exception e){
	        	System.out.println("Kamikaze it is! Cool!");
	        }
        } else if(gameOption == 2){
            System.out.print("Enter a name for Player 2 : ");
            setGamer(gamer2);
        }
        gamer2.setTictacSymbol(TICTACTOE_O);
    }

    public void startGame(){
    	System.out.println(gamer1.getName()+" ("+gamer1.getTictacSymbol()+") vs "+gamer2.getName()+" ("+gamer2.getTictacSymbol()+")");
        initializeMap();
        while(true){
        	gameCount++;
    		displayTicTacToe();
    		currentGamer = currentGamer == null ? gamer1 : (currentGamer.getPosition() == 1 ? gamer2 : gamer1);
    		if(currentGamer.getCurrentMove() <= 0)
    			getGamerInput();
    		if(processGamerMove()){
    			System.out.println(currentGamer.getName()+" wins!");
    			currentGamer.setWinCount(currentGamer.getWinCount()+1);
    			System.out.println("Scoreboard");
    			System.out.println("-----------");
    			System.out.println("Games played - "+gameCount);
    			System.out.println(gamer1.getName()+" - "+ gamer1.getWinCount());
    			System.out.println(gamer2.getName()+" - "+ gamer2.getWinCount());
    			System.out.println("Do you want to continue playing ? Y / N");
    			String option = gameInput.nextLine();
    			if(option != null && !(option.equals("")) && option.equals("Y")){
    				gamer1.refresh();
    				gamer2.refresh();
    				initializeMap();
    				continue;
    			}
    			else
					break;
			}
			currentGamer.setMovesHistory(String.valueOf(currentGamer.getCurrentMove()));
			currentGamer.setCurrentMove(0);
    	}
    }
    
    void displayTicTacToe(){
        System.out.println("-------------");
        System.out.println("| "+moveMap.get(1)+" | "+moveMap.get(2)+" | "+moveMap.get(3)+" |");
        System.out.println("-------------");
        System.out.println("| "+moveMap.get(4)+" | "+moveMap.get(5)+" | "+moveMap.get(6)+" |");
        System.out.println("-------------");
        System.out.println("| "+moveMap.get(7)+" | "+moveMap.get(8)+" | "+moveMap.get(9)+" |");
        System.out.println("-------------");
    }

    void initializeMap(){
        moveMap.put(1," ");
        moveMap.put(2," ");
        moveMap.put(3," ");
        moveMap.put(4," ");
        moveMap.put(5," ");
        moveMap.put(6," ");
        moveMap.put(7," ");
        moveMap.put(8," ");
        moveMap.put(9," ");
    }

    void setGamer(Gamer gamer){
    	System.out.print("Enter a valid name for Gamer "+gamer.getPosition()+" : ");
    	while(true){
    		String gamerName = gameInput.nextLine();
    		if(gamerName != null && !(gamerName.equals(""))){
	    		gamer.setName(gamerName);
	    		break;
    		} else{
    			System.out.print("Enter a valid name for Gamer "+gamer.getPosition()+" : ");
    		}
    	}
    	System.out.println();
    }

    public void getGamerInput(){
    	int gamerInput = 0;
        try{
            gamerInput = gameInput.nextInt();
        } catch(Exception e){
            System.out.println("Illegal position.");
            System.out.println(currentGamer.getName()+" : Please enter a legal position from 1 to 9");
            gameInput.next();
            getGamerInput();
        }
        validateInput(gamerInput);
        currentGamer.setCurrentMove(gamerInput);
    }

    public void validateInput(int gamerInput){
        if(gamerInput <= 0 || gamerInput > 9 ){
            System.out.println("Illegal position.");
            System.out.println();
            System.out.println(currentGamer.getName()+" : Please enter a legal position from 1 to 9");
            getGamerInput();
            if (moveMap.get(gamerInput) != null) {
            	System.out.println("Position already occupied.");
            	getGamerInput();
        	}
        }
    }

    public boolean processGamerMove(){
        moveMap.put(currentGamer.getCurrentMove(),currentGamer.getTictacSymbol());
        if(moveMap.size() >= 5)
        	return gameProcessor.computePosition(currentGamer.getCurrentMove(), moveMap);
        return false;
    }
}