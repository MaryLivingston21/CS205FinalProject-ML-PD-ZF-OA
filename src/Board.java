import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Board {
    private final int boardSize = 8;
    private ArrayList<ArrayList<Integer>> board;
    private ArrayList<Square> boardOfSquares;

    public Board(){
        board = initializeBoard();
        boardOfSquares = initializeBoard2();
    }


    @Override
    public String toString(){
        //TODO: FIX THIS METHOD SO IT WORKS WITH ARRAYLIST<SQUARES>
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

    public int adjustBoard(Player p, Square s1, Square s2){
        //s1 is the square played, s2 is the square on the other side of the sandwich (middle pieces flipped)
        // setUser(p.getPLayerNumber()) for each of the squares being flipped and the square the user just put a piece on
        int count = 0; //count for number of pieces flipped
        //Todo:this
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

    private ArrayList<Square> initializeBoard2(){
        ArrayList<Square> board = new ArrayList<Square>();
        for (int r=1; r < 9; r++){
            for (int c=1; c <9; c++){
                Square s;
                if ((r == 4 && c == 4) || (r == 5 && c == 5)){
                    s = new Square(r,c);
                    s.setUser(1);
                } else if ((r == 5 && c == 4) || (r == 4 && c == 5)) {
                    s = new Square(r, c);
                    s.setUser(2);
                } else {
                    s = new Square(r,c);
                }
                board.add(s);

            }
        }
        return board;
    }
}
