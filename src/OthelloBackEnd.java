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

        //right now set to run for 100 turns for testing purposes
        for (int i = 0; i < 100; i++){
            playerMenu(g, g.getCurrentPlayer());
            System.out.println("\r\n\r\n\r\n");
            System.out.print(board);
            System.out.println("\r\n\r\n\r\n");
        }


    }

    //test
    public static void playerMenu(Game g, Player p){


        //Tell the user it's their turn
        System.out.println("Player "+p.getPlayerNumber()+", it\'s your turn!");
        System.out.println("Possible Moves:");
        ArrayList<Move> allMoves = g.getValidMoves(p);
        for (Move move : allMoves){
            System.out.println(move);
        }

        //ask if the user would like to take a turn
        System.out.print("Take a turn (y/n)? ");
        Scanner s = new Scanner(System.in);
        String answer = s.nextLine();

        //if they say yes
        if(answer.equals("y") || answer.equals("Y")){

            //prompt and save row
            System.out.print("Enter row: ");
            int chosenRow = s.nextInt();

            //prompt and save column
            System.out.print("Enter column: ");
            int chosenCol = s.nextInt();

            //call game method playPiece on the chosen square
            Square chosenSq = g.getSquare(chosenRow,chosenCol);
            g.playPiece(chosenSq);

        }
        //if the user did not say yes
        else{
            //output to user
            System.out.println("You skipped your turn");
            //call game passTurn method
            g.passTurn();
        }
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
