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
            ArrayList<Square> s2 = m.getEndSquare();
            //adjust board in every direction
            for (int i=0;i<s2.size();i++){
                gameBoard.adjustBoard(currP, s1, s2.get(i));
            }
            currP.adjustScore(m.getNumFlipped() + 1);
            otherP.adjustScore(m.getNumFlipped() * -1);
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
            ArrayList<Square> s2 = m.getEndSquare();
            //adjust board in every direction
            for (int i=0;i<s2.size();i++){
                gameBoard.adjustBoard(currP, s1, s2.get(i));
            }
            currP.adjustScore(m.getNumFlipped() + 1);
            otherP.adjustScore(m.getNumFlipped() * -1);
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
            // if player 1 has turn, pass to player 2
            if (whoseTurn == players.get(0)){
                whoseTurn = players.get(1);
            }
            // if player 1 does not have turn, give player 1 turn
            else {
                whoseTurn = players.get(0);
            }
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
            // if square is not occupied
            if (currentBoard.get(i).getUser() == 0) {
                Square s = currentBoard.get(i);
                int row = s.getRow();
                int col = s.getCol();
                // check down vertical
                int numFlipped = 0;
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
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(j, col));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; //-->break
                    }
                }
                // check up vertical
                numFlipped = 0;
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
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(j, col));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 0; // --> break
                    }
                }
                // check right horizontal
                numFlipped = 0;
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
                        Move m = new Move(s, getSquare(row, j), numFlipped);
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(row, j));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        break;
                    }
                }
                // check left horizontal
                numFlipped = 0;
                for (int j = col + 1; j < 9; j++) {
                    if (getSquare(row, j).getUser() == 0) {
                        // if square has not been played on
                        j = 9; // --> break
                    } else if (getSquare(row, j).getUser() != playerNum) {
                        // if belongs to other user, add to flipped count
                        numFlipped++;
                    } else if (numFlipped > 0) {
                        // if belongs to current user && 1+ squares are flipped
                        Move m = new Move(s, getSquare(row, j), numFlipped);
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(row, j));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; // --> break
                    }
                }

                // bottom right diagonal
                numFlipped = 0;
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
                        Move m = new Move(s, getSquare(k, j), numFlipped);
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(k,j));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; // --> break
                    }
                    j++;
                    k++;
                }
                // bottom left diagonal
                numFlipped = 0;
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
                        Move m = new Move(s, getSquare(k,j), numFlipped);
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(k,j));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 9; // --> break
                    }
                    j++;
                    k--;
                }
                // top left diagonal
                numFlipped = 0;
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
                        Move m = new Move(s, getSquare(k,j), numFlipped);
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(k,j));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
                    } else {
                        // if belongs to current user && no squares flipped
                        j = 0; // --> break
                    }
                    j--;
                    k--;
                }
                // top right diagonal
                numFlipped = 0;
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
                        Move m = new Move(s, getSquare(k,j), numFlipped);
                        //check if already in possible moves
                        boolean inValidMoves = false;
                        for (int z = 0; z<possibleMoves.size(); z++){
                            if (possibleMoves.get(z).equals(m)){
                                possibleMoves.get(z).addEndSquare(getSquare(k,j));
                                possibleMoves.get(z).setNumFlipped(numFlipped + possibleMoves.get(z).getNumFlipped());
                                inValidMoves = true;
                            }
                        }
                        if (!inValidMoves) {
                            possibleMoves.add(m);
                        }
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

    public Player getPlayer(int n){return players.get(n);}

    public int getScore(Player p){
        return p.getScore();
    }

    public static Square getSquare(int r, int c)
    {

        return gameBoard.getSquare(r,c);

    }
}
