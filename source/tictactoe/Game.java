public class Game{
    String result;
    String gameLevel;
    Gamer player1;
    Gamer player2;
    public final String HUMAN = "HUMAN";
    public final String COMPUTER = "COMPUTER";
    HashMap<Integer, String> moveMap;
    public Game(int gameOption){
        if(gameOption == 1){
            player1 = new Gamer(new Human());
            player2 = new Gamer(new Computer());
        } else if(gameOption == 2){
            player1 = new Gamer(new Human());
            player2 = new Gamer(new Human());
        }
    }

    void startGame(){

    }
    
    void processCurrentMove(){

    }

    void displayTicTacToe(){

    }

    void assignTicTacToe(){
        
    }
}