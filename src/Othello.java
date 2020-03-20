import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Othello {
    //private Game g;

    public static void main(String[] args){
        Board board = new Board();
        System.out.print(board);

        //TODO:: get numUsers, initialize users
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),new Player(2,"human")));

        Game g = new Game(board, players);
    }

}
