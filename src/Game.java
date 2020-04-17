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

    public boolean computerPlayPiece(){
        Player currP = players.get(1);
        Player otherP = players.get(0);
        ArrayList<Move> moves = getValidMoves(currP);
        if (moves.size() > 0) {
            Random rand = new Random();
            int num = rand.nextInt(moves.size());
            Move m = moves.get(num);
            Square s1 = m.getSquarePlaced();
            Square s2 = m.getEndSquare();
            int squaresFlipped = m.getNumFlipped();
            gameBoard.adjustBoard(currP, s1, s2);
            //TODO:: adjust for multidirctional flips
            currP.adjustScore(squaresFlipped + 1);
            otherP.adjustScore(squaresFlipped * -1);
            whoseTurn = otherP;
            return true;
        } else{
            // if no moves
            return false;
        }

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
        if (isValidMove(currP, s) != null){
            Move m = isValidMove(currP, s);
            Square s1 = m.getSquarePlaced();
            Square s2 = m.getEndSquare();
            int sqauresFlipped = m.getNumFlipped();
            gameBoard.adjustBoard(currP,s1,s2);
            currP.adjustScore(sqauresFlipped + 1);
            otherP.adjustScore(sqauresFlipped * -1);
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

    /**
     * Method forfeitTurn passes turn to next player if no valid moves
     */
    public boolean forfeitTurn(Player p){
        ArrayList<Move> moves = getValidMoves(p);
        if (moves.isEmpty()){
            return false;
        } else{
            passTurn();
            return true;
        }
    }

    public Move isValidMove(Player p, Square s){
        ArrayList<Move> validMoves  = getValidMoves(p);
        assert validMoves != null;
        for (Move m : validMoves) {
            if (s == m.getSquarePlaced()) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Move> getValidMoves(Player p){
        //TODO: eventually make this method smaller and more efficient
        //TODO: account for multi directional flips
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        int playerNum = p.getPlayerNumber();
        ArrayList<Square> currentBoard = gameBoard.getBoard();
        for (int i=0; i<currentBoard.size(); i++){
            int numFlipped = 0;
            // if square is not occupied
            if (currentBoard.get(i).getUser() == 0) {
                Square s = currentBoard.get(i);
                int row = s.getRow();
                int col = s.getCol();
                // check down vertical
                for (int j = row + 1; j < 9; j++) {
                    if (getSquare(j, col).getUser() == 0) {
                        // if square has not been played on
                        j = 9; // <- break;
                    } else if (getSquare(j, col).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; //-->break
                    }
                }
                // check up vertical
                for (int j = row - 1; j > 0; j--) {
                    if (getSquare(j, col).getUser() == 0) {
                        // if square has not been played on
                        j = 0; // -->break
                    } else if (getSquare(j, col).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 0; // --> break
                    }
                }
                // check right horizontal
                for (int j = col - 1; j > 0; j--) {
                    if (getSquare(row, j).getUser() == 0) {
                        // if square has not been played on
                        j = 0;
                        break;
                    } else if (getSquare(row, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        break;
                    }
                }
                // check left horizontal
                for (int j = col + 1; j < 9; j++) {
                    if (getSquare(row, j).getUser() == 0) {
                        // if square has not been played on
                        j = 9; // --> break
                    } else if (getSquare(row, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; // --> break
                    }
                }

                // bottom right diagonal
                int j = col + 1;
                int k = row + 1;
                while (j < 9 && k < 9) {
                    if (getSquare(k, j).getUser() == 0) {
                        // if square has not been played on
                        j = 9; // --> break
                    } else if (getSquare(k, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; // --> break
                    }
                    j++;
                    k++;
                }
                // bottom left diagonal
                j = col + 1;
                k = row - 1;
                while (j < 9 && k > 0) {
                    if (getSquare(k, j).getUser() == 0) {
                        // if square has not been played on
                        j = 9; // --> break
                    } else if (getSquare(k, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; // --> break
                    }
                    j++;
                    k--;
                }
                // top left diagonal
                j = col - 1;
                k = row - 1;
                while (j > 0 && k > 0) {
                    if (getSquare(k, j).getUser() == 0) {
                        // if square has not been played on
                        j = 0; // --> break
                    } else if (getSquare(k, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 0; // --> break
                    }
                    j--;
                    k--;
                }
                // top right diagonal
                j = col - 1;
                k = row + 1;
                while (j > 0 && k < 9) {
                    if (getSquare(k, j).getUser() == 0) {
                        // if square has not been played on
                        j = 0; // --> break
                    } else if (getSquare(k, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(j, col), numFlipped);
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                m.setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                possibleMoves.remove(z);
                            }
                        }
                        possibleMoves.add(m);
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 0; // --> break
                    }
                    j--;
                    k++;
                }
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

    public static Square getSquare(int r, int c)
    {

        return gameBoard.getSquare(r,c);

    }
}
