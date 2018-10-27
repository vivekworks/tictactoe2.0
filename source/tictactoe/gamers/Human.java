package tictactoe.gamers;

import tictactoe.interfaces.GamerInterface;

public class Human implements GamerInterface{
	private String gamerTictac;
	private String oppponentTictac;
	public final String TICTACTOE_X = "X";
    public final String TICTACTOE_O = "O";
	public Human(String tictacSymbol){
		this.gamerTictac = gamerTictac;
		this.oppponentTictac = tictacSymbol == TICTACTOE_X ? TICTACTOE_O : TICTACTOE_X;
	}
    public int computeNextMove(){
        return 0;
    }
}