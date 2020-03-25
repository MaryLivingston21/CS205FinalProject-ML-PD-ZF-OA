// PROGRAM: BoggleDie.java
// Written by Mary Livingston
// This class represents a boggle Die

import java.util.Random;

public class BoggleDie{  // I asked Jackie and she said it's okay that I added a BoggleDie class
   
   // constants
   private static final int DEFAULT_SIDES = 6;
   
   // instance variables
   private int dieSide;         
   private Random rand; 
   private String s1,s2,s3,s4,s5,s6;   
   private String value;    
   
   /** Creates a boggle die and rolls it
   @ param a, b, c, d, e, f - letter for one side of the die
   */
   public BoggleDie(String a, String b, String c, String d, String e, String f) { 
      // stores value for each die of dice
      s1 = a;
      s2 = b;
      s3 = c;
      s4 = d;
      s5 = e;
      s6 = f;
      // Call the roll method to randomly set the value of the die.
      roll();                       
   } 
   
   
   // methods 
   /** The roll method sets the value of the die to a random number. */
   public void roll(){
      Random rand = new Random();
      dieSide = rand.nextInt(DEFAULT_SIDES) + 1; 
      switch (dieSide){
      case 1:
         value = s1;
         break;
      case 2:
         value = s2;
         break;
      case 3:
         value = s3;
         break;
      case 4:
         value = s4;
         break;
      case 5:
         value = s5;
         break;
      case 6:
         value = s6;
         break;
      }
   }
      
   /** The getValue method returns a side of the dice(value). 
   @return value
   */
   public String getValue(){
      return value;
   }
   

}