package tictactoe.gamers;

import tictactoe.interfaces.GamerInterface;
import java.util.HashMap;
import tictactoe.utility.GameUtil;
import java.util.ArrayList;
import tictactoe.process.GameProcessor;

public class Computer implements GamerInterface{
	private HashMap<Integer, String> gameMap = null;
	private String gamerTictac;
	private String opponentTictac;
	public final String TICTACTOE_X = "X";
    public final String TICTACTOE_O = "O";

	public Computer(String tictacSymbol){
		this.gamerTictac = tictacSymbol;
		this.opponentTictac = tictacSymbol == TICTACTOE_X ? TICTACTOE_O : TICTACTOE_X;
		this.gameMap = GameUtil.getInstance().getGameMap();
	}

    public int computeNextMove(){
    	int nextMove = 0;
    	ArrayList<int[]> allPositions = getAllPositions();
    	nextMove = attackOnFullThrottle(allPositions);
    	//System.out.println("aoft - "+nextMove);
    	if(nextMove <= 0)
    		nextMove = defendOnTopLevel(allPositions);
    	//System.out.println("dotl - "+nextMove);
    	if(nextMove <= 0)
    		nextMove = attackOnAverageThrottle(allPositions);
    	//System.out.println("aoat - "+nextMove);
    	if(nextMove <= 0)
    		nextMove = attackOnLowThrottle(allPositions);
    	//System.out.println("aolt - "+nextMove);
    	if((gameMap.get(nextMove) != null && (gameMap.get(nextMove).trim() != null && !(gameMap.get(nextMove).trim().equals("")))) || (nextMove <= 0))
    		nextMove = 0;
    	if(nextMove <= 0)
    		nextMove = primaryMove(allPositions);
    	return nextMove;   
    }

    public ArrayList<int[]> getAllPositions(){
    	StringBuilder strBuild = new StringBuilder();
    	ArrayList<int[]> positionArray = new ArrayList<int[]>();
    	////System.out.println("allPositions - "+gameMap.size());
    	for(int i=1;i<=gameMap.size();i++){
    		if (gameMap.get(i) != null && (gameMap.get(i).trim() != null && !(gameMap.get(i).trim().equals("")))){
    			strBuild.append(i);
    			////System.out.println("gameMap.get(i).trim() - "+gameMap.get(i).trim()+" - "+i);
    		}
    	}
    	////System.out.println("strBuild - "+strBuild.toString());
    	if(strBuild != null){
	    	for(int j=0;j<strBuild.length();j++){
	    		////System.out.println("strBuild.charAt(j) - "+strBuild.charAt(j));
	    		////System.out.println("Character.getNumericValue(strBuild.charAt(j)) - "+Character.getNumericValue(strBuild.charAt(j)));
	    		positionArray.add(GameProcessor.computeHorizontalPosition(Character.getNumericValue(strBuild.charAt(j))));
	    		positionArray.add(GameProcessor.computeVerticalPosition(Character.getNumericValue(strBuild.charAt(j))));
	    		ArrayList<int[]> tempDiagonalArray = GameProcessor.computeDiagonalPosition(Character.getNumericValue(strBuild.charAt(j)));
	    		for(int[] diagonal :tempDiagonalArray){
	    			positionArray.add(diagonal);
	    		}
	    	}
	    }
	    ////System.out.println("positionArraylength "+positionArray.size());
	    return positionArray;
    }

    public int primaryMove(ArrayList<int[]> allPositions){
    	int position = 0;
    	if(gameMap.get(5) == null || (gameMap.get(5) != null && (gameMap.get(5).trim() == null || (gameMap.get(5).trim().equals(""))))){
    		int choice = (int)(Math.random()*2);
    		if(choice == 0)
	    		position = 5;
	    	else
	    		position = generateRandomValidPosition();
    	}
	    else{
	    	position = generateRandomValidPosition();	
	    }
	    return position;	    
    }

    public int generateRandomValidPosition(){
    	int position = 0;
    	do{
    		position = (int)(Math.random()*10);
    		if((gameMap.get(position) != null && (gameMap.get(position).trim() != null && !(gameMap.get(position).trim().equals("")))) || (position <= 0))
    			continue;
    		else
    			break;
	    } while(true);
	    return position;
    }

    public int defendOnTopLevel(ArrayList<int[]> allPositions){
    	int position = 0;
	    String ttSymbol0 = null,ttSymbol1 = null,ttSymbol2 = null;
	    if(allPositions.size() > 0){
	    	for(int[] ttPositions :allPositions){
	    		ttSymbol0 = gameMap.get(ttPositions[0]) != null && (gameMap.get(ttPositions[0]).trim() != null && !(gameMap.get(ttPositions[0]).trim().equals(""))) ? gameMap.get(ttPositions[0]).trim() : null;
	    		ttSymbol1 = gameMap.get(ttPositions[1]) != null && (gameMap.get(ttPositions[1]).trim() != null && !(gameMap.get(ttPositions[1]).trim().equals(""))) ? gameMap.get(ttPositions[1]).trim() : null;
	    		ttSymbol2 = gameMap.get(ttPositions[2]) != null && (gameMap.get(ttPositions[2]).trim() != null && !(gameMap.get(ttPositions[2]).trim().equals(""))) ? gameMap.get(ttPositions[2]).trim() : null;
	    		// //System.out.println("ttSymbol0 -"+ttSymbol0 +" - "+ttPositions[0]);
	    		// //System.out.println("ttSymbol1 -"+ttSymbol1 +" - "+ttPositions[1]);
	    		// //System.out.println("ttSymbol2 -"+ttSymbol2 +" - "+ttPositions[2]);
	    		//Level 2 defense
	    		if(ttSymbol0 != null && ttSymbol1 != null){
	    			if(ttSymbol0.equals(ttSymbol1) && ttSymbol2 == null && ttSymbol0.equals(this.opponentTictac))
	    				position = ttPositions[2];
	    		}
	    		if(ttSymbol1 != null && ttSymbol2 != null){
	    			if(ttSymbol1.equals(ttSymbol2) && ttSymbol0 == null && ttSymbol1.equals(this.opponentTictac))
	    				position = ttPositions[0];
	    		}
	    		if(ttSymbol0 != null && ttSymbol2 != null){
	    			if(ttSymbol0.equals(ttSymbol2) && ttSymbol1 == null && ttSymbol2.equals(this.opponentTictac))
	    				position = ttPositions[1];
	    		}

	    		if((gameMap.get(position) != null && (gameMap.get(position).trim() != null && !(gameMap.get(position).trim().equals("")))) || (position <= 0)){
	    			position = 0;
	    			continue;
	    		}
	    		else
	    			break;
	    	}
	    }
	    return position;
    }

    public int attackOnFullThrottle(ArrayList<int[]> allPositions){
    	int position = 0;
	    String ttSymbol0 = null,ttSymbol1 = null,ttSymbol2 = null;
	    ////System.out.println("Inside attackOnFullThrottle - "+allPositions.size());
	    if(allPositions.size() > 0){
	    	for(int[] ttPositions :allPositions){
	    		ttSymbol0 = gameMap.get(ttPositions[0]) != null && (gameMap.get(ttPositions[0]).trim() != null && !(gameMap.get(ttPositions[0]).trim().equals(""))) ? gameMap.get(ttPositions[0]).trim() : null;
	    		ttSymbol1 = gameMap.get(ttPositions[1]) != null && (gameMap.get(ttPositions[1]).trim() != null && !(gameMap.get(ttPositions[1]).trim().equals(""))) ? gameMap.get(ttPositions[1]).trim() : null;
	    		ttSymbol2 = gameMap.get(ttPositions[2]) != null && (gameMap.get(ttPositions[2]).trim() != null && !(gameMap.get(ttPositions[2]).trim().equals(""))) ? gameMap.get(ttPositions[2]).trim() : null;
	    		//System.out.println("this.gamerTictac - "+this.gamerTictac);
	    		//System.out.println("this.opponentTictac - "+this.opponentTictac);
	    		//System.out.println("ttSymbol0 -"+ttSymbol0 +" - "+ttPositions[0]);
	    		//System.out.println("ttSymbol1 -"+ttSymbol1 +" - "+ttPositions[1]);
	    		//System.out.println("ttSymbol2 -"+ttSymbol2 +" - "+ttPositions[2]);
	    		//Level 2 Attack
	    		if(ttSymbol0 != null && ttSymbol1 != null){
	    			if(ttSymbol0.equals(ttSymbol1) && ttSymbol2 == null && ttSymbol0.equals(this.gamerTictac))
	    				position = ttPositions[2];
	    		}
	    		if(ttSymbol1 != null && ttSymbol2 != null){
	    			if(ttSymbol1.equals(ttSymbol2) && ttSymbol0 == null && ttSymbol1.equals(this.gamerTictac))
	    				position = ttPositions[0];
	    		}
	    		if(ttSymbol0 != null && ttSymbol2 != null){
	    			if(ttSymbol0.equals(ttSymbol2) && ttSymbol1 == null && ttSymbol2.equals(this.gamerTictac))
	    				position = ttPositions[1];
	    		}

	    		if((gameMap.get(position) != null && (gameMap.get(position).trim() != null && !(gameMap.get(position).trim().equals("")))) || (position <= 0)){
	    			position = 0;
	    			continue;
	    		}
	    		else
	    			break;
	    	}
	    }
	    return position;
    }

    public int attackOnAverageThrottle(ArrayList<int[]> allPositions){
    	int position = 0;
	    String ttSymbol0 = null,ttSymbol1 = null,ttSymbol2 = null;
	    if(allPositions.size() > 0){
	    	for(int[] ttPositions :allPositions){
	    		ttSymbol0 = gameMap.get(ttPositions[0]) != null && (gameMap.get(ttPositions[0]).trim() != null && !(gameMap.get(ttPositions[0]).trim().equals(""))) ? gameMap.get(ttPositions[0]).trim() : null;
	    		ttSymbol1 = gameMap.get(ttPositions[1]) != null && (gameMap.get(ttPositions[1]).trim() != null && !(gameMap.get(ttPositions[1]).trim().equals(""))) ? gameMap.get(ttPositions[1]).trim() : null;
	    		ttSymbol2 = gameMap.get(ttPositions[2]) != null && (gameMap.get(ttPositions[2]).trim() != null && !(gameMap.get(ttPositions[2]).trim().equals(""))) ? gameMap.get(ttPositions[2]).trim() : null;
	    		
	    		//Level 1 Attack
	    		if(ttSymbol0 != null || ttSymbol1 != null || ttSymbol2 != null){
	    			if(ttSymbol0 != null && ttSymbol0.equals(this.gamerTictac) && ttSymbol1 == null && ttSymbol2 == null)
	    				position = (int)(Math.random()*2) == 0 ? ttPositions[1] : ttPositions[2];
	    			if(ttSymbol1 != null && ttSymbol1.equals(this.gamerTictac) && ttSymbol2 == null && ttSymbol0 == null)
	    				position = (int)(Math.random()*2) == 0 ? ttPositions[0] : ttPositions[2];
	    			if(ttSymbol2 != null && ttSymbol2.equals(this.gamerTictac) && ttSymbol1 == null && ttSymbol0 == null)
	    				position = (int)(Math.random()*2) == 0 ? ttPositions[1] : ttPositions[0];
	    		}

	    		if((gameMap.get(position) != null && (gameMap.get(position).trim() != null && !(gameMap.get(position).trim().equals("")))) || (position <= 0)){
	    			position = 0;
	    			continue;
	    		}
	    		else
	    			break;
	    	}
	    }
	    return position;
    }

    public int attackOnLowThrottle(ArrayList<int[]> allPositions){
    	int position = 0;
	    String ttSymbol0 = null,ttSymbol1 = null,ttSymbol2 = null;
	    if(allPositions.size() > 0){
	    	for(int[] ttPositions :allPositions){
	    		ttSymbol0 = gameMap.get(ttPositions[0]) != null && (gameMap.get(ttPositions[0]).trim() != null && !(gameMap.get(ttPositions[0]).trim().equals(""))) ? gameMap.get(ttPositions[0]).trim() : null;
	    		ttSymbol1 = gameMap.get(ttPositions[1]) != null && (gameMap.get(ttPositions[1]).trim() != null && !(gameMap.get(ttPositions[1]).trim().equals(""))) ? gameMap.get(ttPositions[1]).trim() : null;
	    		ttSymbol2 = gameMap.get(ttPositions[2]) != null && (gameMap.get(ttPositions[2]).trim() != null && !(gameMap.get(ttPositions[2]).trim().equals(""))) ? gameMap.get(ttPositions[2]).trim() : null;
	    		
	    		//Level 0 Attack
	    		if(ttSymbol0 != null && ttSymbol1 != null){
	    			position = ttPositions[2];
	    		}
	    		if(ttSymbol1 != null && ttSymbol2 != null){
	    			position = ttPositions[0];
	    		}
	    		if(ttSymbol0 != null && ttSymbol2 != null){
	    			position = ttPositions[1];
	    		}

	    		if((gameMap.get(position) != null && (gameMap.get(position).trim() != null && !(gameMap.get(position).trim().equals("")))) || (position <= 0)){
	    			position = 0;
	    			continue;
	    		}
	    		else
	    			break;
	    	}
	    }
	    return position;
    }

}