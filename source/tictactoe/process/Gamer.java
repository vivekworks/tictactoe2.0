package tictactoe.process;

import tictactoe.interfaces.GamerInterface;

public class Gamer{
    private String name;
    private int position;
    private String tictacSymbol;
    private int winCount;
    private String movesHistory;
    private int currentMove;
    private GamerInterface gamerType;

    public Gamer(GamerInterface gamerType){
        this.gamerType = gamerType;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPosition(){
        return this.position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public String getTictacSymbol(){
        return this.tictacSymbol;
    }

    public void setTictacSymbol(String tictacSymbol){
        this.tictacSymbol = tictacSymbol;
    }

    public int getWinCount(){
        return this.winCount;
    }

    public void setWinCount(int winCount){
        this.winCount = winCount;
    }

    public String getMovesHistory(){
        return this.movesHistory;
    }

    public void setMovesHistory(String movesHistory){
        if(movesHistory != null && !(movesHistory.equals("")))
            this.movesHistory = this.movesHistory+movesHistory;
    }

    public int getCurrentMove(){
        if(this.currentMove == 0)
            findNextMove();
        return this.currentMove;
    }

    public void setCurrentMove(int currentMove){
        this.currentMove = currentMove;
    }    

    public void findNextMove(){
        this.currentMove = gamerType.computeNextMove();
    }

    public void refresh(){
        this.currentMove = 0;
        this.movesHistory = null;
    }
}