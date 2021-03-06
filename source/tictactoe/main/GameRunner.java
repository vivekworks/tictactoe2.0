package tictactoe.main;

import java.util.Scanner;
import tictactoe.process.Game;
import tictactoe.utility.GameUtil;

public class GameRunner{
    private GameUtil gameUtility = GameUtil.getInstance();
    private Scanner terminalInput;

    public GameRunner(){
        this.terminalInput = gameUtility.getGameTerminal();
    }

    public static void main(String[] args){
        GameRunner gameRunner = new GameRunner();
        gameRunner.begin();
    }

    void begin(){
        while(true){
            System.out.println();
            System.out.println("******************************************");
            System.out.println("*             *************              *");
            System.out.println("*             * TICTACTOE *              *");
            System.out.println("*             *************              *");
            System.out.println("*                                        *");
            System.out.println("*      Player 1 : X ; Player 2 : O       *");
            System.out.println("*                                        *");
            System.out.println("*           1. One Player                *");
            System.out.println("*           2. Two Player                *");
            System.out.println("*           3. Quit                      *");
            System.out.println("*                                        *");
            System.out.println("******************************************");
            System.out.println();
            System.out.print("Choose an option ---> ");
            int gameOption = terminalInput.nextInt();
            terminalInput.nextLine();
            if(gameOption ==1 || gameOption == 2){
                Game game = new Game(gameOption);
                game.startGame();
                if(game.getGameOver())
                    break;
            } else{
                if(gameOption == 3){
                    break;
                }
                System.out.println();
                System.out.println("Please enter one of the given options");
            }
        }
        System.out.println();
        System.out.println("Bye! Have a nice day!");
        System.out.println("Come back later!");
        System.out.println();
    }
}