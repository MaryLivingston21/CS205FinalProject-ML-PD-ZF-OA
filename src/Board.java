import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Board {
    private final int boardSize = 8;
    private ArrayList<ArrayList<Integer>> board;

    public Board(){
        board = initializeBoard();
    }


    @Override
    public String toString(){
        String s = "";
        for (int i=0; i < board.size(); i++){
            ArrayList<Integer> row = board.get(i);
            for (int j=0; j < row.size(); j++){
                s = s + (row.get(j) + " ");
            }
            s = s + "\n";
        }
        return s;
    }

    public int adjustBoard(Player p, int r, int c){
        int count = 0;

        return count;
    }

    public ArrayList<ArrayList<Integer>> getBoard(){
        return board;
    }

    private ArrayList<ArrayList<Integer>> initializeBoard(){
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0));
        ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(0,0,0,1,2,0,0,0));
        ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(0,0,0,2,1,0,0,0));
        for (int i=0; i<boardSize; i++){
            if (i == 3) {
                board.add(row1);
            } else if (i == 4){
                board.add(row2);
            } else {
                board.add(row);
            }
        }
        return board;
    }
}
