import java.util.ArrayList;

public class CurrentGameState {
    private Board gameBoard;
    private ArrayList<Player> players;
    private Player whoseTurn;

    public CurrentGameState(Board board, ArrayList<Player> players){
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

    }

    public static boolean isValidMove(Player p, int r, int c){
        return true;
    }

    public static ArrayList<ArrayList<Integer>> getValidMoves(Player p){
        return null;
    }
}
