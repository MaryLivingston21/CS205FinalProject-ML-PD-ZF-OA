// PROGRAM: Board.java
// Written by Mary Livingston
// This class represents a boggle board

import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.*; 

public class Board {

   // constants
   private final BoggleDie DIE0 = new BoggleDie("R","I","F","O","B","X");
   private final BoggleDie DIE1 = new BoggleDie("I","F","E","H","E","Y");
   private final BoggleDie DIE2 = new BoggleDie("D","E","N","O","W","S");
   private final BoggleDie DIE3 = new BoggleDie("U","T","O","K","N","D");
   private final BoggleDie DIE4 = new BoggleDie("H","M","S","R","A","O");
   private final BoggleDie DIE5 = new BoggleDie("L","U","P","E","T","S");
   private final BoggleDie DIE6 = new BoggleDie("A","C","I","T","O","A");
   private final BoggleDie DIE7 = new BoggleDie("Y","L","G","K","U","E");
   private final BoggleDie DIE8 = new BoggleDie("Qu","B","M","J","O","A");
   private final BoggleDie DIE9  = new BoggleDie("E","H","I","S","P","N");
   private final BoggleDie DIE10 = new BoggleDie("V","E","T","I","G","N");
   private final BoggleDie DIE11 = new BoggleDie("B","A","L","I","Y","T");
   private final BoggleDie DIE12 = new BoggleDie("E","Z","A","V","N","D");
   private final BoggleDie DIE13 = new BoggleDie("R","A","L","E","S","C");
   private final BoggleDie DIE14 = new BoggleDie("U","W","I","L","R","G");
   private final BoggleDie DIE15 = new BoggleDie("P","A","C","E","M","D");
   
   private final int NUM_DIE = 16;
   private final int NUM_COLM = 4;
   private final int NUM_ROW = 4;
   
   //variables
   private int randDie;
   private ArrayList<BoggleDie> allDie;
   private ArrayList<Tile> tileList;
   private ArrayList<ArrayList<Tile>> board;
   
   
   /** Creates a new boggle board
   */
   public Board(){
      //Store all dice
      allDie = storeDie(DIE0, DIE1, DIE2, DIE3, DIE4, DIE5, DIE6, DIE7, DIE8, DIE9, DIE10, 
      DIE11, DIE12, DIE13, DIE14,  DIE15);
      //Create an arraylist of Die
      tileList = fillBoard(allDie);
      //Create an arraylist of arraylists of dice --> create board
      board = createBoard(tileList);     
   }
   
   
   // methods
   /** The storeDie method stores 16 die into an ArrayList of BoggleDie
   @ param a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p - the BoggleDie to be stored in the Array
   @ return ArrayList of BoggleDie
   */
   private ArrayList<BoggleDie> storeDie(BoggleDie a, BoggleDie b,BoggleDie c,BoggleDie d,BoggleDie e,BoggleDie f,BoggleDie g,BoggleDie h,BoggleDie i,BoggleDie j,BoggleDie k,BoggleDie l,BoggleDie m,BoggleDie n,BoggleDie o,BoggleDie p){
      allDie = new ArrayList<>(NUM_DIE);
      allDie.add(a);
      allDie.add(b);
      allDie.add(c);
      allDie.add(d);
      allDie.add(e);
      allDie.add(f);
      allDie.add(g);
      allDie.add(h);
      allDie.add(i);
      allDie.add(j);
      allDie.add(k);
      allDie.add(l);
      allDie.add(m);
      allDie.add(n);
      allDie.add(o);
      allDie.add(p);
      return allDie;   
   }
   
   /** The fillBoard method takes an ArrayList of Dice, shuffes their order in the array and the 
   side facing up, creates a tile using the letter on the dice and its position in the array, and 
   creates an ArrayList of the tiles.  
   @ param Dice - ArrayList of Boggle Die to be used for the game
   @ return ArrayList of Tiles
   */
   private ArrayList<Tile> fillBoard(ArrayList<BoggleDie> Dice){
      //variables 
      Tile tile;
      int row = 0;
      int column = 0;
      ArrayList<Tile> t = new ArrayList<Tile>(NUM_DIE); 
      //shuffle all dice
      Collections.shuffle(Dice); 
      // store tiles in array 
      for (int i = 0; i < NUM_DIE; i++){
         BoggleDie die = Dice.get(i); // gets die object
         die.roll(); // get random side of die
         String randChar = die.getValue(); // get value on tile
         if (column > 3){ // get row and column of tile
            column = 0;
            row = row + 1;
         }
        // create tile object 
        tile = new Tile(randChar,row, column);
        // store tile in array
        t.add(tile); 
   
        column++;
      }
      return t;
   }
   
   /** The createBoard method takes an ArrayList of Tiles, splits the list into four parts(representing
   the four rows in a boggle board) and stores those four segments into an ArrayList. 
   @ param tileList - ArrayList of Tiles used in the Boggle Game
   @ return ArrayList of ArrayList of Tiles
   */
   private ArrayList<ArrayList<Tile>> createBoard(ArrayList<Tile> tileList){
      //variables
      ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>(NUM_ROW);
      ArrayList<Tile> t1 = copyList(tileList);
      ArrayList<Tile> t2 = copyList(tileList);
      ArrayList<Tile> t3 = copyList(tileList);
      ArrayList<Tile> t4 = copyList(tileList);
      // create four arrayLists of tiles, each representing a row
         // row 1
      for (int i = 0; i < 12; i++)
         t1.remove(4);
         // row 2
      for(int i = 0; i < 4; i++)
         t2.remove(0);
      for(int i = 0; i < 8; i++)
         t2.remove(4);
         //row 3
      for(int i = 0; i < 8; i++)
         t3.remove(0);
      for(int i = 0; i < 4; i++)
         t3.remove(4);
         // row 4
      for (int i = 0; i < 12; i++) 
         t4.remove(0);
      //create arrayList of arrayList of tiles
      board.add(0,t1);
      board.add(1,t2);
      board.add(2,t3);
      board.add(3,t4);
      
      return board;
   }
   
   /** The copyList method takes an ArrayList of Tiles and makes a copy.
   @ param t - ArrayList of tiles
   @ return ArrayList of Tiles
   */
   private ArrayList<Tile> copyList(ArrayList<Tile> t){
      // variables
      ArrayList<Tile> copy = new ArrayList<Tile>(t.size());
      //copies ArrayList
      for (int i = 0; i < t.size(); i++){
         copy.add(i,t.get(i));
      }     
      return copy;
   }
   /** the getBoard method returns the ArrayList of ArrayList of Tiles
   @ return ArrayList of ArrayList of Tiles
   */
   public ArrayList<ArrayList<Tile>> getBoard(){
      return board;
   }
   
   /** override the toString method
   @ returns a string representation of the boggle board.
   */
   @Override
   public String toString(){
      // variables
      int row = 0;
      String s = "";
      //creates string equivilent of board 
      for(int i = 0; i < NUM_DIE; i++){
         if (i % 4 == 0)  // creates new tile after 4 tiles printed
            s = s + "\n";
         Tile t = tileList.get(i); // gets tile
         String letter = t.getChar(); // gets char of tile
         if (letter == "Qu")
            s = s + " " + letter;
         else
            s = s + " " + letter + " ";
      }
      return s; 
   }
}