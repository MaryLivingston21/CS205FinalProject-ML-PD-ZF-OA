import java.lang.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OthelloBackEnd {
    public static void main(String[] args){
        // Get user input for number of users
        int numPlayers = newGame();

        // Initialize a new Board
        Board board = new Board();
        System.out.print(board);

        // initialize opponent Player based on user input
        Player opponent;
        if (numPlayers == 1){
            opponent = new Player(2, "computer");
        }
        else{
            opponent = new Player(2, "human");
        }

        // create ArrayList of players
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),opponent));

        // Initialize a new game
        Game g = new Game(board, players);
    }

    public static int newGame(){
        // Prompt user and get input
        System.out.println("Type 1 to play against the computer, or 2 to play against a human: ");
        Scanner input = new Scanner(System.in);
        int numPlayers = input.nextInt();

        // Validate input
        while(numPlayers != 2 && numPlayers != 1){
            System.out.println("Please type 1 or 2 only: ");
            numPlayers = input.nextInt();
        }

        return numPlayers;
    }
}
