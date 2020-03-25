// PROGRAM: Word.java
// Written by Mary Livingston
// This class represents a word

import java.util.ArrayList;

public class Word {

   // variables
   private String word;
   private int length;
   private StringBuilder sb;
   private int score;
   
   /** Creates a word
   @ param list - an ArrayList of tiles
   */
   public Word(ArrayList<Tile> list){
      // variables
      length = list.size();
      sb = new StringBuilder(length);
      // put characters into String Builder
      for(int i = 0; i < length; i++){  
         Tile tile1 = list.get(i);
         sb.append(tile1.getChar());
      }
      // create word  
      word = sb.toString(); 
      word = word.toLowerCase(); // put word in lower case --> dictionary is lower case
      // set points
      score = setPoints(length,word);
   }
   
   
   // methods
   /** The setPoints method sets how many points(score) the word is work
   @ param len - the length of the word
   @ param word - the word that is being scored 
   @ return score
   */
   private int setPoints(int len, String word){ 
      if (word.contains("qu")){
         len = len + 1;
      }
      switch (len){
         case 1:
            score = 0;
            break;
         case 2:
            score = 0;
            break;
         case 3:
            score = 1;
            break;
         case 4:
            score = 1;
            break;
         case 5:
            score = 2;
            break;
         case 6:
            score = 3;
            break;
         case 7:
            score = 5;
            break;
         default:
            score = 11;
            break;
         }
      return score;
   }
   /** The getWord method returns the word
   @ return word
   */
   public String getWord(){ 
      return word;
   }
   /** The getLength method returns the length of the word
   @ return length
   */
   public int getLength(){
      return length;
   }
   /** The getPoints method returns how many points the word is worth (score)
   @ return score
   */
   public int getPoints(){
      return score;
   }
   /** Compares two words to determine if they are the same.
   @ return true if the two are the same String, false otherwise.
   */
   @Override
   public boolean equals(Object other){
      if (other == null)
         return false;
      if(this == other)
         return true;
      if(other.getClass() != getClass())
         return false;
      Word tOther = (Word)other; // if same class, cast object to Word
      if((this.word).equalsIgnoreCase(tOther.getWord()))
         return true;
      return false;
   }
   
   /** override the toString method
   @ returns a string representation of the word and how many points it is worth(score).
   */
   @Override
   public String toString(){
      return word;
   }
}