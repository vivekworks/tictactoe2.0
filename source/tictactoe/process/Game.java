package tictactoe.process;

import tictactoe.process.Gamer;
import tictactoe.process.GameProcessor;
import java.util.Scanner;
import java.util.HashMap;
import tictactoe.gamers.Computer;
import tictactoe.gamers.Human;
import tictactoe.utility.GameUtil;

public class Game{

    private String result;
    private boolean gameOver;
    private int gameCount, moveCount;
    private String gameLevel;
    private Gamer gamer1, gamer2, currentGamer;
    public final String TICTACTOE_X = "X";
    public final String TICTACTOE_O = "O";
    private GameUtil gameUtility = GameUtil.getInstance();
    private Scanner terminalInput;
    private HashMap<Integer, String> gameMap;

    public Game(int gameOption){
        this.terminalInput = gameUtility.getGameTerminal();
        gamer1 = new Gamer(new Human(TICTACTOE_X));
		if(gameOption == 1)
			gamer2 = new Gamer(new Computer(TICTACTOE_O));
		else
			gamer2 = new Gamer(new Human(TICTACTOE_O));
        setupGamers(gameOption);
        gameMap = gameUtility.getGameMap();
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
        gamer1.setTictacSymbol(TICTACTOE_X);
        //Setup Gamer2
        gamer2.setPosition(2);
        gamer2.setTictacSymbol(TICTACTOE_O);

        if(gameOption == 1){
            System.out.println();
            System.out.print("Enter your name : ");
            setGamer(gamer1);
            System.out.println();
            System.out.println("Hi! My name is Venom. I'm your opponent for the day.");
            System.out.print("Do you want to change my name ? Y / N --> ");
            try{
	            String nameOption = terminalInput.nextLine();
	            if(nameOption != null && !(nameOption.equalsIgnoreCase("")) && nameOption.equalsIgnoreCase("Y")){
                    System.out.println();
	            	System.out.print("Enter a name for PLAYER 2 : ");
	            	setGamer(gamer2);
	        	} else{
                    System.out.println();
	        		System.out.println("Venom it is! Cool!");
                    gamer2.setName("Venom");
	        	}
	        } catch(Exception e){
                System.out.println();
	        	System.out.println("Venom it is! Cool!");
                gamer2.setName("Venom");
	        }
        } else if(gameOption == 2){
            System.out.println();
            System.out.print("Enter a name for PLAYER 1 : ");
            setGamer(gamer1);
            System.out.println();
            System.out.print("Enter a name for PLAYER 2 : ");
            setGamer(gamer2);
        }
        
        System.out.println();
    }

    public void startGame(){
    	initializeMap();
        System.out.println(gamer1.getName()+" ("+gamer1.getTictacSymbol()+") vs "+gamer2.getName()+" ("+gamer2.getTictacSymbol()+")");
        while(true){
            System.out.println();
            displayTicTacToe();
    		currentGamer = currentGamer == null ? gamer1 : (currentGamer.getPosition() == 1 ? gamer2 : gamer1);
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
                System.out.println();
                displayTicTacToe();
                if(result.equalsIgnoreCase("W")){
                    System.out.println();
                    System.out.println(currentGamer.getName().toUpperCase()+" WINS!");
                    terminalInput.nextLine();
                    currentGamer.setWinCount(currentGamer.getWinCount()+1);
                } else{
                    System.out.println("It's a glorious Tie! Well played both!");
                }
                System.out.println();
                System.out.println("********************");
                System.out.println();
                System.out.println("SCOREBOARD");
                System.out.println("-----------");
                System.out.println("GAMES PLAYED - "+gameCount);
                System.out.println(gamer1.getName()+" - "+ gamer1.getWinCount());
                System.out.println(gamer2.getName()+" - "+ gamer2.getWinCount());
                System.out.println();
                System.out.println("********************");
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
        System.out.println("| "+gameMap.get(1)+" | "+gameMap.get(2)+" | "+gameMap.get(3)+" |");
        System.out.println("-------------");
        System.out.println("| "+gameMap.get(4)+" | "+gameMap.get(5)+" | "+gameMap.get(6)+" |");
        System.out.println("-------------");
        System.out.println("| "+gameMap.get(7)+" | "+gameMap.get(8)+" | "+gameMap.get(9)+" |");
        System.out.println("-------------");
    }

    void initializeMap(){
        gameMap.put(1," ");
        gameMap.put(2," ");
        gameMap.put(3," ");
        gameMap.put(4," ");
        gameMap.put(5," ");
        gameMap.put(6," ");
        gameMap.put(7," ");
        gameMap.put(8," ");
        gameMap.put(9," ");
    }

    void setGamer(Gamer gamer){
    	//System.out.print("Enter a valid name for Gamer "+gamer.getPosition()+" : ");
    	while(true){
    		String gamerName = terminalInput.nextLine();
    		if(gamerName != null && !(gamerName.equals(""))){
	    		gamer.setName(gamerName);
	    		break;
    		} else{
                System.out.println();
    			System.out.print("Enter a valid name for PLAYER "+gamer.getPosition()+" : ");
                gamerName = terminalInput.nextLine();
                if(gamerName != null && !(gamerName.equals("")))
                    gamer.setName(gamerName);
                else{
                    System.out.println("Default name is assigned for PLAYER "+gamer.getPosition());
                    gamer.setName("PLAYER "+gamer.getPosition());
                    break;
                }
    		}
    	}
    }

    public void getGamerInput(){
    	int gamerInput = 0;
        System.out.println();
        
        do{
            try{
                if(currentGamer.getGamerType() instanceof Human){
                    System.out.print(currentGamer.getName()+" : Please enter a position from 1 to 9 --> ");
                    gamerInput = terminalInput.nextInt();
                } else if(currentGamer.getGamerType() instanceof Computer){
                    gamerInput = currentGamer.computeNextMove();
                    System.out.println("Move from "+currentGamer.getName()+" --> "+gamerInput);
                }
            } catch(Exception e){
                System.out.println("Undesirable input!");
                terminalInput.next();
                //System.out.println(currentGamer.getName()+" : Please enter a legal position from 1 to 9 --> ");
            }
        } while(!validateInput(gamerInput));
        
        currentGamer.setCurrentMove(gamerInput);
    }

    public boolean validateInput(int gamerInput){
        if(gamerInput <= 0 || gamerInput > 9 ){
            System.out.println("Illegal position.");
            System.out.println();
            currentGamer.setCurrentMove(0);
            return false;
        }
        if (gameMap.get(gamerInput) != null && (gameMap.get(gamerInput).trim() != null && !(gameMap.get(gamerInput).trim().equals("")))){
            System.out.println("Position already occupied.");
            System.out.println();
            currentGamer.setCurrentMove(0);
            return false;
        }
        return true;
    }

    public boolean processGamerMove(){
        gameMap.put(currentGamer.getCurrentMove(),currentGamer.getTictacSymbol());
        if(gameMap.size() >= 5)
        	return GameProcessor.computePosition(currentGamer.getCurrentMove());
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