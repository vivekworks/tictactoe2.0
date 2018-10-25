package tictactoe.gamers;

import tictactoe.interfaces.GamerInterface;

public class Computer implements GamerInterface{
	int position;
    public int computeNextMove(){
     	return this.position;   
    }

    public void defend(){

    }

    public void attack(){

    }
}