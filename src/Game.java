import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class Game {
    private static Board gameBoard;
    private ArrayList<Player> players;
    private Player whoseTurn;

    public Game(Board board, ArrayList<Player> players){
        gameBoard = board;
        this.players = players;
        whoseTurn = players.get(0);
    }

    public boolean playPiece(Square s){
        Player currP;
        Player otherP;
        if (whoseTurn == players.get(0)){
            currP = players.get(0);
            otherP = players.get(1);
        } else {
            currP = players.get(1);
            otherP = players.get(0);
        }
        boolean isValidMove = isValidMove(currP, s);
        //TODO ensure user has Valid moves???
        Square s1 = s;
        Square s2 = getSandwichEnd(s);
        if (s1.getCol() != -1){
            int points = gameBoard.adjustBoard(currP,s1,s2);
            currP.adjustScore(points + 1);
            otherP.adjustScore(points * -1);
            whoseTurn = otherP;
            return true;
        } else {
            //if not valid move
            return false;
        }
    }

    /**
     * Method passTurn modifies whoseTurn to switch to next player
     */
    public void passTurn(){
        // if player 1 has turn, pass to player 2
        if (whoseTurn == players.get(0)){
            whoseTurn = players.get(1);
        }
        // if player 1 does not have turn, give player 1 turn
        else {
            whoseTurn = players.get(0);
        }
    }

    public static boolean isValidMove(Player p, Square s){
        ArrayList<ArrayList<Square>> validMoves  = getValidMoves(p);
        assert validMoves != null;
        for (ArrayList<Square> validMove : validMoves) {
            if (s == validMove.get(0)) {
                return true;
            }
        }
        return false;

        // NOTES:
        // validMoves.get(i).get(0) returns empty square user can play on
        // validMoves.get(i).get(1) returns sandwich end square
    }

    public static Square getSandwichEnd(Square squarePlayedOn){
        //TODO: THIS --> MARY
        return new Square(-1,-1);
    }


    public static ArrayList<ArrayList<Square>> getValidMoves(Player p){
        ArrayList<ArrayList<Square>> possibleMoves = new ArrayList<ArrayList<Square>>();
        ArrayList<Square> currentBoard = gameBoard.getBoard();
            for (int i=0; i<currentBoard.size(); i++){
                // if square is not occupied
                if (currentBoard.get(i).getUser() == 0){
                    Square s = currentBoard.get(i);
                    int row = s.getRow();
                    int col = s.getCol();
                    int oRow = row;
                    int oCol = col;
                    // horizontals

                    // verticals


                    // diagonals

                }

            }

        return possibleMoves;
    }

    public Player getCurrentPlayer(){
        return whoseTurn;
    }

    public int getScore(Player p){
        return p.getScore();
    }

    public Square getSquare(int r, int c)
    {

        return gameBoard.getSquare(r,c);

    }
}
