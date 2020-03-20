import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Board {
    public ArrayList<ArrayList<Integer>> board;

    public Board(){
        initializeBoard();
    }

    private ArrayList<ArrayList<Integer>> getBoard(){
        return board;
    }

    private void initializeBoard(){
        ArrayList<Integer> row = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0));
        for (int i=0; i<8; i++){
            board.add(row);
        }
    }
}
