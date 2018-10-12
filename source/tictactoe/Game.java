public class Game{
    String result;
    String gameLevel;
    Gamer player1;
    Gamer player2;
    public final String HUMAN = "HUMAN";
    public final String COMPUTER = "COMPUTER";
    HashMap<Integer, String> moveMap;
    public Game(int gameOption){
        player1 = new Gamer(new Human());
        if(gameOption == 1){
            player2 = new Gamer(new Computer());
        } else if(gameOption == 2){
            player2 = new Gamer(new Human());
        }
    }

    void startGame(){
        initializeMap();
        displayTicTacToe();
    }
    
    void processCurrentMove(){

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

    void assignTicTacToe(){
        
    }

    void initializeMap(){
        moveMap = new HashMap<Integer, String>();
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
}