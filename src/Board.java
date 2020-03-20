import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Board {
    public ArrayList<ArrayList<Integer>> board;
    private int boardSize = 8;

    public Board(){
        board = initializeBoard();
    }

//    public static void playPiece(Player p, int r, int c){
//        if (isValidMove(p,r,c)){
//
//        }
//
//    }
//
//    public static boolean isValidMove(Player p, int r, int c){
//
//    }
//
//    public static ArrayList<ArrayList<Integer>> getValidMoves(Player p){
//
//    }

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

    public  ArrayList<ArrayList<Integer>> getBoard(){
        return board;
    }

    private ArrayList<ArrayList<Integer>> initializeBoard(){
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0));
        for (int i=0; i<boardSize; i++){
            board.add(row);
        }
        return board;
    }
}
