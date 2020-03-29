import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Board {
    private final int boardSize = 8;
    private ArrayList<Square> boardOfSquares;

    public Board(){
        boardOfSquares = initializeBoard();
    }


    @Override
    public String toString(){
        String board = "";
        for (int i = 0; i<boardOfSquares.size(); i++){
            Square s = boardOfSquares.get(i);
            board = board + (s.getUser() + " ");
            if (s.getCol() == 8){
                board = board + "\n";
            }
        }
        return board;
    }

    public int adjustBoard(Player p, Square s1, Square s2){
        //s1 is the square played, s2 is the square on the other side of the sandwich (middle pieces flipped)
        // setUser(p.getPLayerNumber()) for each of the squares being flipped and the square the user just put a piece on
        int count = 0; //count for number of pieces flipped
        //Todo:this
        // https://www.java67.com/2016/08/how-to-replace-element-of-arraylist-in-java.html 
        return count;
    }

    public ArrayList<Square> getBoard(){
        return boardOfSquares;
    }

    private ArrayList<Square> initializeBoard(){
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

    //TODO:: Does this need an overridden equals method?
}
