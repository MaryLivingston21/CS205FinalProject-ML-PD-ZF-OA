// PROGRAM: Dictionary.java
// Written by Mary Livingston
// This class represents a dictionary 

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Dictionary {
   
   //variables 
   private final int NUM_WORDS;
   private ArrayList<String> words;
   private Scanner txtFile;
   
   /** Creates a dictionary
   @ param file - the file containing the dictionary
   */
   public Dictionary(String file) throws IOException{
      // create scanner attached to dictionary file
      txtFile = new Scanner(new File(file));
      // put words into arrayList      
      words = new ArrayList<>();
      while (txtFile.hasNext()){
         words.add(txtFile.nextLine());
      }
      //get number of words
      NUM_WORDS = words.size();
   }


   // methods
   /** The isValidWord method returns boolean
   @return true if the word is in the dictionary, false otherwise
   */   
   public boolean isValidWord(ArrayList<Tile> t){ 
      // Variables
      Word w = new Word(t);
      String sW = w.getWord();
      // determine if dictionary has word
      if(words.contains(sW)){
         return true;
      }else{
         return false;
      }
   }
   
}