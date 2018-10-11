public class Gamer{
    private String name;
    private String position;
    private String tictacSymbol;
    private String winCount;
    private String movesHistory;
    GamerInterface gamerType;

    public Gamer(GamerInterface gamerType){
        this.gamerType = gamerType;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPosition(){
        return this.position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getTictacSymbol(){
        return this.tictacSymbol;
    }

    public void setTictacSymbol(String tictacSymbol){
        this.tictacSymbol = tictacSymbol;
    }

    public String getWinCount(){
        return this.winCount;
    }

    public void setWinCount(String winCount){
        this.winCount = winCount;
    }

    public String getMovesHistory(){
        return this.movesHistory;
    }

    public void setMovesHistory(String movesHistory){
        this.movesHistory = movesHistory;
    }

    public void findNextMove(){
        gamerType.computeNextMove();
    }
}