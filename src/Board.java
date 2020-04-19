import java.lang.*;
import java.util.*;
import java.util.ArrayList;

public class Board {
    private final int boardSize = 8;
    private ArrayList<Square> boardOfSquares;

    public Board(){
        boardOfSquares = initializeBoard();
    }


    @Override
    public String toString(){
        String board = "";
        for (int i = 0; i<boardOfSquares.size(); i++){
            Square s = boardOfSquares.get(i);
            board = board + (s.getUser() + " ");
            if (s.getCol() == 8){
                board = board + "\n";
            }
        }
        return board;
    }

    public void adjustBoard(Player p, Square s1, Square s2){
        //TODO:: does not need int return!!
        System.out.println("Running adjustBoard between  ("+s1.getRow()+","+s1.getCol()+") and ("+s2.getRow()+","+s2.getCol()+")");
        //s1 is the square played, s2 is the square on the other side of the sandwich (middle pieces flipped)
        // setUser(p.getPLayerNumber()) for each of the squares being flipped and the square the user just put a piece on
        int count = 0; //count for number of pieces flipped

        //variable for looping through rows, if needed
        int currentRow;

        //variable for looping through columns, if needed
        int currentCol;

        //the player who just played and is causing the adjustment
        int currentPlayer = p.getPlayerNumber();

        //assign Square 1 now belongs to this player
        s1.setUser(p.getPlayerNumber());

        //s1 and s2 are in the same col (vertical flip)
        if(s1.getCol() == s2.getCol()){
            System.out.print("Vertical - ");

            //if s1 has a higher row than s2
            if(s1.getRow() > s2.getRow()){
                System.out.print("Bottom to Top\n");
                currentRow = s1.getRow();
                //loop down to s2's row
                while(currentRow >= s2.getRow()){
                    //get the  square in currentRow, and set the user to the player who flipped it
                    getSquare(currentRow, s1.getCol()).setUser(currentPlayer);
                    currentRow--;
                    count++;
                }
            }

            //if s1 has a lower row than s2
            else if(s1.getRow() < s2.getRow()){
                System.out.print("Top to Bottom\n");
                currentRow = s1.getRow();
                //loop up to s2's row
                while(currentRow <= s2.getRow()){
                    //get the  square in currentRow, and set the user to the player who flipped it
                    getSquare(currentRow, s1.getCol()).setUser(currentPlayer);
                    currentRow++;
                    count++;
                }
            }

            //row and col of s1 and s2 were the same, error
            else{
                System.out.println("Error running adjustBoard, the provided squares have the same coordinates");
            }

        }

        //s1 and s2 are in the same row (horizontal flip)
        else if(s1.getRow() == s2.getRow()){
            System.out.print("Horizontal - ");
            //if s1 has a higher column than s2
            if(s1.getCol() > s2.getCol()){
                System.out.print("Right to Left\n");
                currentCol = s1.getCol();
                //loop down to s2's column
                while(currentCol >= s2.getCol()){
                    //get the  square in currentCol, and set the user to the player who flipped it
                    getSquare(s1.getRow(), currentCol).setUser(currentPlayer);
                    currentCol--;
                    count++;
                }
            }

            //if s1 has a lower column than s2
            if(s1.getCol() < s2.getCol()){
                currentCol = s1.getCol();
                System.out.print("Left to Right\n");
                //loop up to s2's column
                while(currentCol <= s2.getCol()){
                    //get the  square in currentCol, and set the user to the player who flipped it
                    getSquare(s1.getRow(), currentCol).setUser(currentPlayer);
                    currentCol++;
                    count++;
                }
            }
        }

        //s1 and s2 are in different rows and cols (diagonal flip)
        else{
            System.out.print("Diagonal - ");
            //s1 vs s2: higher col and higher row
            if(s1.getCol() > s2.getCol() && s1.getRow() > s2.getRow()){

                currentCol = s1.getCol();
                currentRow = s2.getRow();

                //loop down to s2's column and row (if truly diagonal, both conditions should be satisfied at the same time
                while(currentCol > s2.getCol() && currentRow > s2.getRow()){
                    //get the  square in currentCol, and set the user to the player who flipped it
                    getSquare(s1.getRow(), currentCol).setUser(currentPlayer);
                    currentCol--;
                    currentRow--;
                    count++;
                }
            }

            //s1 vs s2: lower col and lower row
            else if(s1.getCol() < s2.getCol() && s1.getRow() < s2.getRow()){

                currentCol = s1.getCol();
                currentRow = s2.getRow();

                //loop up to s2's column and row (if truly diagonal, both conditions should be satisfied at the same time
                while(currentCol < s2.getCol() && currentRow < s2.getRow()){
                    //get the  square in currentCol, and set the user to the player who flipped it
                    getSquare(s1.getRow(), currentCol).setUser(currentPlayer);
                    currentCol++;
                    currentRow++;
                    count++;
                }
            }

            //s1 vs s2: higher col and lower row
            else if(s1.getCol() > s2.getCol() && s1.getRow() < s2.getRow()){

                currentCol = s1.getCol();
                currentRow = s2.getRow();

                //loop to s2's column and row (if truly diagonal, both conditions should be satisfied at the same time
                while(currentCol > s2.getCol() && currentRow < s2.getRow()){
                    //get the  square in currentCol, and set the user to the player who flipped it
                    getSquare(s1.getRow(), currentCol).setUser(currentPlayer);
                    currentCol--;
                    currentRow++;
                    count++;
                }
            }

            //s1 vs s2: lower col and higher row
            else if(s1.getCol() < s2.getCol() && s1.getRow() > s2.getRow()){

                currentCol = s1.getCol();
                currentRow = s2.getRow();

                //loop to s2's column and row (if truly diagonal, both conditions should be satisfied at the same time
                while(currentCol < s2.getCol() && currentRow > s2.getRow()){
                    //get the  square in currentCol, and set the user to the player who flipped it
                    getSquare(s1.getRow(), currentCol).setUser(currentPlayer);
                    currentCol++;
                    currentRow--;
                    count++;
                }
            }

            else{
                System.out.println("Error running adjustBoard, no diagonal condition was met");
            }


        }

        //Todo:this
        // https://www.java67.com/2016/08/how-to-replace-element-of-arraylist-in-java.html
        //return count;
    }

    public ArrayList<Square> getBoard(){
        return boardOfSquares;
    }

    private ArrayList<Square> initializeBoard(){
        ArrayList<Square> board = new ArrayList<Square>();
        for (int r=1; r < 9; r++){
            for (int c=1; c <9; c++){
                Square s;
                if ((r == 4 && c == 4) || (r == 5 && c == 5)){
                    s = new Square(r,c);
                    s.setUser(1);
                } else if ((r == 5 && c == 4) || (r == 4 && c == 5)) {
                    s = new Square(r, c);
                    s.setUser(2);
                } else {
                    s = new Square(r,c);
                }
                board.add(s);

            }
        }
        return board;
    }

    public Square getSquare(int r, int c) {
        if((r<9) && (c<9) && (r>0) && (c>0)){
            for(int i=0; i<boardOfSquares.size();i++){
                if(boardOfSquares.get(i).getCol()==c &&boardOfSquares.get(i).getRow()==r){
                    return boardOfSquares.get(i);
                }
            }
        }
        return null;
    }

    //TODO:: Does this need an overridden equals method?
}
