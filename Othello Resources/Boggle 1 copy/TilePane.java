import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.geometry.Pos;

public class TilePane extends VBox {

   private int row;
   private int column;
   private String letter;
   private Tile tile;
   private Rectangle rect;
   private int selected;
   private StackPane stack;
   private Label label;

   public TilePane(Tile t){
      this.setAlignment(Pos.CENTER);
      this.setPrefSize(40,40);
      setTile(t);
   }
   
   public void setSelected(){ // changes color of tile selected
      rect.setFill(Color.MEDIUMSEAGREEN);
   }
   
   public void setUnselected(){  // changes color of tile thats deselected
      rect.setFill(Color.ALICEBLUE);
   }
   
   public Tile getTile(){
      return tile;
   }
   
   public void setTile(Tile t){
      //selected = 1;
      tile = t;
      letter = tile.getChar();
      letter = letter.toLowerCase();
      label = new Label(letter);
      row = tile.getRow();
      column= tile.getColumn();
      
      if (!letter.equals("qu"))
         letter = " " + letter + " ";
      rect = new Rectangle(40,40);
      rect.setFill(Color.ALICEBLUE);
      rect.setStroke(Color.BLACK);
      stack = new StackPane();
      stack.getChildren().addAll(rect,label);
      this.getChildren().add(stack);
   }
   
   public int getRow(){
      return row;
   }
   
   public int getColumn(){
      return column;
   }
   
   public String getChar(){
      return letter;
   }
}