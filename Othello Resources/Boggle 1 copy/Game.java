// PROGRAM: Game.java
// Written by Mary Livingston
// This class represents a boggle game

import java.util.ArrayList;
import java.io.*;

public class Game {

   // variables 
   private int row;
   private int column;
   private Board board;
   private ArrayList<ArrayList<Tile>> tiles;
   private ArrayList<Tile> selected;
   private Tile tileSelected;
   private ArrayList<Word> words;
   private int lastRow = -6;
   private int lastColumn = -6;
   private int score = 0;
   private Dictionary dict;
   
   
   /** Creates a boggle game */
   public Game()throws IOException {
      board = new Board(); // creates board
      tiles = board.getBoard(); // gets board
      selected = new ArrayList<Tile>();
      words = new ArrayList<Word>();
      dict = new Dictionary("dictionary.txt");
   }
   
   
   //methods
   /** The isValidWord method returns boolean
   @return true if the word is in the dictionary, false otherwise
   */
   public boolean isValidSelection(int r, int c){
      row = r;
      column = c;
      if (row > 3 || row < 0 || column < 0 || column > 3) // returns false if tile is not within bounds of board
         return false;
      if(lastRow == -6 && lastColumn == -6){  // returns true if first selection 
         lastColumn = column;
         lastRow = row;
         ArrayList<Tile> list =  tiles.get(r); // gets row of tiles
         tileSelected = list.get(c); // gets specificed tiles and stores it
         return true;
      } else if(row == lastRow || row + 1 == lastRow || row - 1 == lastRow) {
         if(column == lastColumn || column + 1 == lastColumn || column - 1 == lastColumn){
            lastColumn = column;
            lastRow = row;
            ArrayList<Tile> list =  tiles.get(r); // gets row of tiles
            tileSelected = list.get(c); // gets specificed tiles and stores it
            return true;
         }
      }
      return false;
   } 
   
   /** The addToSelected method adds selected tiles to an ArrayList of Tiles
   @param r - row of tile
   @param c - column of tile
   */
   public void addToSelected(int r, int c){
      tileSelected = getTile(r,c);
      selected.add(tileSelected);
   } 
   
   /** The removeFromSelected method removes last selected tile from the ArrayList of Tiles if valid selected
   @param r - row of tile
   @param c - column of tile
   @ returns true if valid selection, false otherwise 
   */
   public boolean removeFromSelected(int r, int c) {
      tileSelected = getTile(r,c);
      if (selected.get(selected.size()-1)== tileSelected){
         int length = selected.size();
         selected.remove(length - 1);
         return true;
      }else
         return false;
      
   }
   
   /** The getTile method returns tile at specific location
   @return tileSelected - tile at that location
   */
   public Tile getTile(int r, int c){
      ArrayList<Tile> row = tiles.get(r);
      tileSelected = row.get(c);
      return tileSelected;
   }
   
   /** The clearSelected method clears the tiles selected */
   public void clearSelected(){
      lastRow = -6; 
      lastColumn = -6;
      selected.clear();
   }
   
   /** The testSelected method tests whether the selected tiles makes a word, if the word is valid, the word is 
   stored and it's points are added to the score.  If the word is in-valid, the user is notified.
   @ return true if valid word, returns falso otherwise
   */
   public boolean testSelected(){
      if (dict.isValidWord(selected)){
         Word w = new Word(selected);
         if (words.contains(w)){
            clearSelected();
            return false;
         }else {
            words.add(w);
            score = score + w.getPoints();
            clearSelected();
            return true;
         }
      }
         clearSelected();
         return false;
   }
   
   /** The getScore method returns the game score
   @ return score - score of game
   */
   public int getScore(){
      return score;
   }
   
   /** The getSelectedTiles method returns an ArrayList of Tiles selected
   @ return selected - ArrayList of Tiles selected
   */
   public ArrayList<Tile> getSelectedTiles(){ 
      return selected;
   }
   
   /** override the toString method
   @ returns a string representation of the boggle board.
   */
   @Override
   public String toString(){
      return "selected: " + selected + "\nwords: " + words + "\nscore: " + score; 
   }


}