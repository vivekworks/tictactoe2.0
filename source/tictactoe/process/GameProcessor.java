public class GameProcessor{
	
	public boolean computePosition(int position, HashMap<Integer, String> moveMap){
	    int v1=0,v2=0,v3=0;
        //Horizontal Check
        if(position%3 == 1){
            v1 = position;
            v2 = position+1;
            v3 = position+2;
        } else if(position%3 == 2){
            v1 = position-1;
            v2 = position;
            v3 = position+1;
        } else if(position%3 == 0){
            v1 = position-2;
            v2 = position-1;
            v3 = position;
        } 
        if((moveMap.get(v1) != null && moveMap.get(v2) != null && moveMap.get(v3) != null)
            && (moveMap.get(v1).equals(moveMap.get(v2)) && moveMap.get(v3).equals(moveMap.get(v2)))){
            return true;
        }

        //Vertical Check
        v1=0;v2=0;v3=0;
        if(position-3 <= 0){
            v1 = position;
            v2 = position+3;
            v3 = position+6;
        } else if((position-3 > 0) && (position+3 <= 9)){
            v1 = position-3;
            v2 = position;
            v3 = position+3;
        } else if(position+3 > 9){
            v1 = position-6;
            v2 = position-3;
            v3 = position;
        }
        if((moveMap.get(v1) != null && moveMap.get(v2) != null && moveMap.get(v3) != null)
            && (moveMap.get(v1).equals(moveMap.get(v2)) && moveMap.get(v3).equals(moveMap.get(v2)))){
            return true;
        }

        //Diagonal Check
        v1=0;v2=0;v3=0;
        if(position+4 == 5 || position-4 == 5){
            if(position+4 == 5){
                v1 = position;
                v2 = position+4;
                v3 = position+8;
            } else{
                v1 = position-8;
                v2 = position-4;
                v3 = position;
            }
        } else if(position+2 == 5 || position-2 == 5){
            if(position+2 == 5){
                v1 = position;
                v2 = position+2;
                v3 = position+4;
            } else{
                v1 = position-4;
                v2 = position-2;
                v3 = position;
            }
        }
        if((moveMap.get(v1) != null && moveMap.get(v2) != null && moveMap.get(v3) != null)
            && (moveMap.get(v1).equals(moveMap.get(v2)) && moveMap.get(v3).equals(moveMap.get(v2)))){
            return true;
        }

        if(position == 5){
            if(((moveMap.get(3) != null && moveMap.get(5) != null && moveMap.get(7) != null)
            && (moveMap.get(3).equals(moveMap.get(5)) && moveMap.get(7).equals(moveMap.get(5)))) || ((moveMap.get(1) != null && moveMap.get(5) != null && moveMap.get(9) != null)
            && (moveMap.get(1).equals(moveMap.get(5)) && moveMap.get(9).equals(moveMap.get(5))))){
                return true;
            }
        }
        return false;
    }
}