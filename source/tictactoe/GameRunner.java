public class GameRunner{
    String gameType;
    String gameCount;
    String nextPlayer;

    public static void main(String[] args){
        GameRunner gameRunner = new GameRunner();
        gameRunner.begin();
    }

    void begin(){
        while(true){
            System.out.println("****Welcome to TicTacToe****");
            System.out.println("Player 1 : X ; Player 2 : O");
            System.out.println("1. Single Player Game");
            System.out.println("2. Two Player Game");
            System.out.println("3. Quit Game");
            System.out.print("Choose an option ---> ");
            Scanner terminalInput = new Scanner(System.in);
            int gameOption = terminalInput.nextInt();
            if(gameOption ==1 || gameOption == 2){
                Game game = new Game(gameOption);
                game.startGame();
            } else{
                if(gameOption == 3){
                    break;
                }
                System.out.println();
                System.out.println("Please enter either 1 or 2 in the given options");
            }
        }
        System.out.println();
        System.out.println("Bye! Have a nice day!");
        System.out.println("Come back later!");
    }
}