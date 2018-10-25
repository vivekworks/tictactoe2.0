package tictactoe.process;

import java.util.HashMap;
import java.util.ArrayList;
import tictactoe.utility.GameUtil;

public class GameProcessor{

    public static int[] computeHorizontalPosition(int position){
        int[] horizontalArray = {0,0,0};
        if(position%3 == 1){
            horizontalArray[0] = position;
            horizontalArray[1] = position+1;
            horizontalArray[2] = position+2;
        } else if(position%3 == 2){
            horizontalArray[0] = position-1;
            horizontalArray[1] = position;
            horizontalArray[2] = position+1;
        } else if(position%3 == 0){
            horizontalArray[0] = position-2;
            horizontalArray[1] = position-1;
            horizontalArray[2] = position;
        }
        return horizontalArray;
    }

    public static int[] computeVerticalPosition(int position){
        int[] verticalArray = {0,0,0};
        if(position-3 <= 0){
            verticalArray[0] = position;
            verticalArray[1] = position+3;
            verticalArray[2] = position+6;
        } else if((position-3 > 0) && (position+3 <= 9)){
            verticalArray[0] = position-3;
            verticalArray[1] = position;
            verticalArray[2] = position+3;
        } else if(position+3 > 9){
            verticalArray[0] = position-6;
            verticalArray[1] = position-3;
            verticalArray[2] = position;
        }
        return verticalArray;
    }

    public static ArrayList<int[]> computeDiagonalPosition(int position){
        int[] diagonalArray = {0,0,0};
        ArrayList<int[]> diagonalList = new ArrayList<int[]>();
        if(position+4 == 5 || position-4 == 5){
            if(position+4 == 5){
                diagonalArray[0] = position;
                diagonalArray[1] = position+4;
                diagonalArray[2] = position+8;
            } else{
                diagonalArray[0] = position-8;
                diagonalArray[1] = position-4;
                diagonalArray[2] = position;
            }
        } else if(position+2 == 5 || position-2 == 5){
            if(position+2 == 5){
                diagonalArray[0] = position;
                diagonalArray[1] = position+2;
                diagonalArray[2] = position+4;
            } else{
                diagonalArray[0] = position-4;
                diagonalArray[1] = position-2;
                diagonalArray[2] = position;
            }
        } else if(position == 5){
            int[] diagonalArrayTwo = {1,5,9};
            diagonalList.add(diagonalArrayTwo);
            diagonalArray[0] = 3;
            diagonalArray[1] = 5;
            diagonalArray[2] = 7;
        }
        diagonalList.add(diagonalArray);
        return diagonalList;
    }

    public static boolean computePosition(int position){

        int[] horizontalArray = computeHorizontalPosition(position);
        int[] verticalArray = computeVerticalPosition(position);
        ArrayList<int[]> diagonalArray = computeDiagonalPosition(position);
        HashMap<Integer, String> moveMap = GameUtil.getInstance().getGameMap();

        //Horizontal Check
        if((moveMap.get(horizontalArray[0]) != null && moveMap.get(horizontalArray[1]) != null && moveMap.get(horizontalArray[2]) != null)
            && (moveMap.get(horizontalArray[0]).equals(moveMap.get(horizontalArray[1])) && moveMap.get(horizontalArray[2]).equals(moveMap.get(horizontalArray[1])))){
            return true;
        }

        //Vertical Check
        if((moveMap.get(verticalArray[0]) != null && moveMap.get(verticalArray[1]) != null && moveMap.get(verticalArray[2]) != null)
            && (moveMap.get(verticalArray[0]).equals(moveMap.get(verticalArray[1])) && moveMap.get(verticalArray[2]).equals(moveMap.get(verticalArray[1])))){
            return true;
        }

        //Diagonal Check
        for(int[] diagonal :diagonalArray){
            if((moveMap.get(diagonal[0]) != null && moveMap.get(diagonal[1]) != null && moveMap.get(diagonal[2]) != null)
                && (moveMap.get(diagonal[0]).equals(moveMap.get(diagonal[1])) && moveMap.get(diagonal[2]).equals(moveMap.get(diagonal[1])))){
                return true;
            }            
        }
        return false;
    }	
}