package tictactoe.process;

import tictactoe.process.Gamer;
import tictactoe.process.GameProcessor;
import java.util.Scanner;
import java.util.HashMap;
import tictactoe.gamers.Computer;
import tictactoe.gamers.Human;

public class Game{

    private String result;
    private boolean gameOver;
    private int gameCount, moveCount;
    private String gameLevel;
    private Gamer gamer1, gamer2, currentGamer;
    public final String TICTACTOE_X = "X";
    public final String TICTACTOE_O = "O";
    private Scanner terminalInput;
    private HashMap<Integer, String> moveMap;
    private GameProcessor gameProcessor;

    public Game(int gameOption, Scanner terminalInput){
        gamer1 = new Gamer(new Human());
		if(gameOption == 1)
			gamer2 = new Gamer(new Computer());
		else
			gamer2 = new Gamer(new Human());
		this.terminalInput = terminalInput;
        setupGamers(gameOption);
        gameProcessor = new GameProcessor();
        moveMap = new HashMap<Integer, String>();
    }

    public void setGameOver(boolean gO){
        this.gameOver = gO;
    }

    public boolean getGameOver(){
        return this.gameOver;
    }

    void setupGamers(int gameOption){
    	//Setup Gamer1
    	gamer1.setPosition(1);
        System.out.println();
    	System.out.print("Enter a name for Player 1 : ");
        setGamer(gamer1);
        gamer1.setTictacSymbol(TICTACTOE_X);

        //Setup Gamer2
        if(gameOption == 1){
            gamer1.setPosition(2);
            System.out.print("Hi! My name is Venom. I'm your opponent for the day. Do you want to change my name ? Y / N --> ");
            try{
	            String nameOption = terminalInput.nextLine();
	            if(nameOption != null && !(nameOption.equals("")) && nameOption.equals("Y")){
	            	System.out.print("Enter a name for Player 2 : ");
	            	setGamer(gamer2);
	        	} else{
	        		System.out.println("Venom it is! Cool!");
	        	}
	        } catch(Exception e){
	        	System.out.println("Venom it is! Cool!");
	        }
        } else if(gameOption == 2){
            System.out.print("Enter a name for Player 2 : ");
            setGamer(gamer2);
        }
        gamer2.setTictacSymbol(TICTACTOE_O);
        System.out.println();
    }

    public void startGame(){
    	initializeMap();
        System.out.println(gamer1.getName()+" ("+gamer1.getTictacSymbol()+") vs "+gamer2.getName()+" ("+gamer2.getTictacSymbol()+")");
        while(true){
            System.out.println();
            displayTicTacToe();
    		currentGamer = currentGamer == null ? gamer1 : (currentGamer.getPosition() == 1 ? gamer2 : gamer1);
    		if(currentGamer.getCurrentMove() <= 0)
    			getGamerInput();
    		if(processGamerMove()){
    			result = "W";
			} else{
                if(moveCount>= 8){
                    result = "T";
                }
            }
            moveCount++;
            if(result != null){
                gameCount++;
                displayTicTacToe();
                if(result.equalsIgnoreCase("W")){
                    System.out.println(currentGamer.getName()+" wins!");
                    currentGamer.setWinCount(currentGamer.getWinCount()+1);
                } else{
                    System.out.println("It's a glorious Tie! Well played both!");
                }
                System.out.println();
                System.out.println("Scoreboard");
                System.out.println("-----------");
                System.out.println("Games played - "+gameCount);
                System.out.println(gamer1.getName()+" - "+ gamer1.getWinCount());
                System.out.println(gamer2.getName()+" - "+ gamer2.getWinCount());
                System.out.println();
                System.out.println("Do you want to continue playing this game ? Y / N");
                String option = terminalInput.nextLine();
                if(option != null && !(option.equals("")) && option.equalsIgnoreCase("Y")){
                    gamer1.refresh();
                    gamer2.refresh();
                    initializeMap();
                    this.moveCount = 0;
                    this.result = null;
                    this.currentGamer = null;
                    System.out.println();
                    System.out.println(gamer1.getName()+" ("+gamer1.getTictacSymbol()+") vs "+gamer2.getName()+" ("+gamer2.getTictacSymbol()+")");
                    continue;
                }
                else{
                    displayBoard();
                    System.out.println();
                    System.out.println("Do you want to return back to main menu  ? Y / N");
                    option = terminalInput.nextLine();
                    if(option != null && !(option.equals("")) && option.equalsIgnoreCase("N"))
                        this.gameOver = true;
                    break;
                }
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
    	//System.out.print("Enter a valid name for Gamer "+gamer.getPosition()+" : ");
    	while(true){
    		String gamerName = terminalInput.nextLine();
    		if(gamerName != null && !(gamerName.equals(""))){
	    		gamer.setName(gamerName);
	    		break;
    		} else{
    			System.out.print("Enter a valid name for Gamer "+gamer.getPosition()+" : ");
    		}
    	}
    }

    public void getGamerInput(){
    	int gamerInput = 0;
        System.out.println();
        System.out.print(currentGamer.getName()+" : Please enter a position from 1 to 9 --> ");
        do{
            try{
                gamerInput = terminalInput.nextInt();
                terminalInput.nextLine();
            } catch(Exception e){
                System.out.println("Undesirable input!");
                //System.out.println(currentGamer.getName()+" : Please enter a legal position from 1 to 9 --> ");
            }
        }while(!validateInput(gamerInput));
        currentGamer.setCurrentMove(gamerInput);
    }

    public boolean validateInput(int gamerInput){
        if(gamerInput <= 0 || gamerInput > 9 ){
            System.out.println("Illegal position.");
            System.out.println();
            System.out.println(currentGamer.getName()+" : Please enter a legal position from 1 to 9");
            currentGamer.setCurrentMove(0);
            return false;
        }
        if (moveMap.get(gamerInput) != null && (moveMap.get(gamerInput).trim() != null && !(moveMap.get(gamerInput).trim().equals("")))){
            System.out.println("Position already occupied.");
            System.out.print(currentGamer.getName()+" : Please enter a legal position from 1 to 9 --> ");
            currentGamer.setCurrentMove(0);
            return false;
        }
        return true;
    }

    public boolean processGamerMove(){
        moveMap.put(currentGamer.getCurrentMove(),currentGamer.getTictacSymbol());
        if(moveMap.size() >= 5)
        	return gameProcessor.computePosition(currentGamer.getCurrentMove(), moveMap);
        return false;
    }

    public void displayBoard(){
        if(gamer1.getWinCount() != gamer2.getWinCount()){
            String winnerDisplay = (((gamer1.getWinCount() > gamer2.getWinCount() ? gamer1.getName() : gamer2.getName()).toUpperCase())+"                    ").substring(0,21);
            System.out.println();
            System.out.println("******************************************");
            System.out.println("*                                        *");
            System.out.println("*   CONGRATULATIONS "+winnerDisplay+"*");
            System.out.println("*   THE CHAMPION!!!                      *");
            System.out.println("*                                        *");
            System.out.println("******************************************");
        } else if(gamer1.getWinCount() == gamer2.getWinCount()){
            System.out.println();
            System.out.println("******************************************");
            System.out.println("*      ****************************      *");
            System.out.println("*      *   WELL PLAYED CHAMPIONS  *      *");
            System.out.println("*      ****************************      *");
            System.out.println("******************************************");
        }
    }
}