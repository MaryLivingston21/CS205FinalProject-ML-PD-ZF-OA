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

    public void playPiece(int r, int c){
        Player currP;
        Player otherP;
        if (whoseTurn == players.get(0)){
            currP = players.get(0);
            otherP = players.get(1);
        } else {
            currP = players.get(1);
            otherP = players.get(0);
        }

        if (isValidMove(currP,r,c)){
            int points = gameBoard.adjustBoard(currP,r,c);
            currP.adjustScore(points + 1);
            otherP.adjustScore(points * -1);
            whoseTurn = otherP;
        }

        //TODO:: option to pass if you can't / don't want to play -> adjusts whose turn it is

    }

    public static boolean isValidMove(Player p, int r, int c){
        //TODO:: determine whether valid move
        return true;
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
}
