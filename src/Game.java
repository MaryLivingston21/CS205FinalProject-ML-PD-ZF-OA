import java.util.ArrayList;

public class Game {
    private Board gameBoard;
    private ArrayList<Player> players;
    private Player whoseTurn;

    public Game(Board board, ArrayList<Player> players){
        this.gameBoard = board;
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
        ArrayList<Square> listOfSquares = isValidMove(currP, s);
        Square s1 = listOfSquares.get(0);
        Square s2 = listOfSquares.get(1);
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

    public static ArrayList<Square> isValidMove(Player p, Square s){
        ArrayList<Square> arrayOfSquares  = new ArrayList<Square>();
        //TODO::get all valid moves
        // return ArrayList<Squares> --> has two squares
        // if move is in valid moves --> return ArrayList<Squares>
        //          square where the person placed the piece Square(r,c)
        //          square on the other end (all piece in-between will be flipped)
        // else return ArrayList<Squares> where the two squares are Square(-1,-1)
        return arrayOfSquares;
    }

    public static ArrayList<ArrayList<Integer>> getValidMoves(Player p){
        //TODO:  return all valid moves for player p ex: ((1,2),(3,5),(6,1))
        return null;
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
