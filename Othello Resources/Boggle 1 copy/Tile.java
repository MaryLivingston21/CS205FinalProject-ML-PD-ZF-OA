// PROGRAM: Tile.java
// Written by Mary Livingston
// This class represents a boggle tile

public class Tile {

   // variables 
   private String letter;
   private int row;
   private int column;


   /** Creates a new tile
   @ param a - the letter value of this tile
   @ param r - the row value of this tile
   @ param c - the column value of this tile
   */
   public Tile(char a, int r, int c){
      letter = String.valueOf(a);
      row = r;
      column = c;
   }
   
   /** Creates a new tile
   @ param a - the letter value of this tile ("Qu")
   @ param r - the row value of this tile
   @ param c - the column value of this tile
   */
   public Tile(String a, int r, int c){
      letter = a;
      row = r;
      column = c;
   }
   
   /** Creates a new tile
   @ param other - the tile to be copied
   */
   public Tile(Tile other){
      letter = other.getChar();
      row = other.getRow();
      column = other.getColumn();
   }
   
   
   // methods
   /** The getChar method returns letter on the tile 
   @return letter
   */
   public String getChar(){ 
      return letter;
   }
   /** The getRow method returns row of the tile 
   @return row
   */
   public int getRow(){ 
      return row;
   }
   /** The getColumn method returns column of the tile 
   @return column
   */
   public int getColumn(){ 
      return column;
   }
   /** Compares two tiles to determine if they have the same letter, row, and column.
   @ return true if the two have the same values, false otherwise.
   */
   @Override
   public boolean equals(Object other){
      if (other == null)
         return false;
      if(this == other)
         return true;
      if(other.getClass() != getClass())
         return false;
      Tile tOther = (Tile)other; // if same class, cast object to Tile
      if(this.letter == tOther.getChar())
         if(this.row == tOther.getRow())
            if(this.column == tOther.getColumn())
               return true;
      return false;
   }
   /** override the toString method
   @ returns a string representation of the tile "letter".
   */
   @Override
   public String toString(){
      return letter;
   }
}