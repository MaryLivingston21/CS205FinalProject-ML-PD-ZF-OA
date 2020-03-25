// PROGRAM: Boggle.java
// Written by Mary Livingston
// This class represents a boggle game with user interface

import javafx.application.Application; 
import javafx.application.Platform;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;


public class Boggle extends Application {

   private final int NUM_TILES = 16;
   private final int NUM_ROWS = 4;
   private final int NUM_COLUMNS = 4;
   private int row, col, selectd = 0;
   private Game g;
   private BorderPane pane;
   private VBox left, right;
   private HBox bottom;
   private GridPane tiles;
   private Label title, selected, error, finalScore;
   private Button nGame, dSelect, bSelect, clearW, testW, exit, eGame; 

   @Override
   public void start(Stage primaryStage){
      primaryStage.setTitle("Boggle Game");
      //create panes
      pane = new BorderPane();
      left = new VBox();
      left.setPrefWidth(200);
      left.setSpacing(10);
      right = new VBox();
      right.setPrefWidth(200);
      bottom = new HBox();
      tiles = new GridPane();
      tiles.setPrefWidth(200);
      tiles.setHgap(5);
      tiles.setVgap(5);
      //initialize game
      try {
         g = new Game();
      } catch (IOException q){
         System.out.println("Error opening dictionary");
      }
      createBoard();
      // Top
      title = new Label("Boggle"); 
      title.setMinHeight(75);
      title.setFont(Font.font("Arial",24));
      pane.setAlignment(title, Pos.CENTER);
      pane.setTop(title);
      //Right
      setRight();
      // Center
      setCenter();
      // Left
      setLeft();
      //Bottom
      setBottom();
      //Complete Setup
      Scene scene = new Scene(pane,600,400);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   /** This method stores the row and column of the tile when pressed.  It also adds the tile to selected
   tiles if it is a valid selection */
   public void handleClick(MouseEvent e) {
      TilePane tp = (TilePane)(e.getSource());
      row = tp.getRow();       
      col = tp.getColumn();    
      
      if(selectd == 0){
         if (g.isValidSelection(row, col)){
            // add tile to selected tiles
            if (g.getSelectedTiles().contains(g.getTile(row,col)))
               error.setText("Tile is already selected");
            else
               g.addToSelected(row, col);
               tp.setSelected();
               error.setText("");
         } else {
               error.setText("Invalid selection! Please select a letter adjacent to the previously selected letter.");
         }
      }else if (selectd == 1){
         // remove tile
         if (g.removeFromSelected(row,col)){
            tp.setUnselected();
         }else if (g.getSelectedTiles().size() == 0) {
            error.setText("Please click select tiles button to select a tile");
         }else{
            error.setText("Must pick last selected tile");
         }
      }
      selected.setText(g.toString());
   }
   /** This method uses information from the Boggle game to create a GridPane of buttons */
   public void createBoard(){
      tiles.getChildren().clear(); // clear board
      for (int r = 0; r < NUM_ROWS; r++) {
         for (int c = 0; c < NUM_COLUMNS; c++) { 
            Tile t = g.getTile(r,c); 
            TilePane tp = new TilePane(t);
            tp.setOnMouseClicked(this::handleClick);
            tiles.add(tp,c,r);
         }
      }
   }
   /** This method sets up the left side of the border pane for boggle */
   public void setLeft(){
      nGame = new Button("New Game");
      clearW = new Button ("Clear Selected");
      testW = new Button ("Test Selected");
      eGame = new Button("End Game");
      exit = new Button("Exit");
      // end game button
      eGame.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            clearPane();
            int sc = g.getScore();
            String score = new String ("Congratulations!\nYour final score is " + Integer.toString(sc));
            finalScore = new Label(score);   
            tiles.getChildren().add(finalScore); 
            bottom.getChildren().add(exit); 
            left.getChildren().add(nGame);          
         }
      });
      // exit button
      exit.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
             Platform.exit();                 
         }
      });
      // new game button
      nGame.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            clearPane();
            try {
               g = new Game();
            } catch (IOException q){
               System.out.println("Error opening dictionary");
            }
            createBoard();
            setLeft();
            setRight();
            setBottom();
                 
         }
      });
      // Test Selected Button
      testW.setOnAction(new EventHandler<ActionEvent>()
      {  @Override
         public void handle(ActionEvent e)
         {
            if (g.testSelected())
               error.setText("");
            else
               error.setText("In-valid word");
            selected.setText(g.toString());
            createBoard();
         }
      });
      // Clear Selected Button
      clearW.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            g.clearSelected();
            selected.setText(g.toString());
            createBoard();
            error.setText("");
         }
      });
      
      left.getChildren().add(testW);
      left.getChildren().add(clearW);
      left.getChildren().add(eGame);
      pane.setLeft(left);
   }
   /** This method sets up the right side of the border pane for boggle */
   public void setRight(){
      selected = new Label(g.toString());
      dSelect = new Button("Deselect Tiles");
      bSelect = new Button("Select Tiles");
      //Select Tiles button 
      bSelect.setOnAction(new EventHandler<ActionEvent>()
      {  @Override
         public void handle(ActionEvent e)
         {
            selectd = 0; 
         }
      });
      //Deselect Tiles button
      dSelect.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            selectd = 1;
         }
      });
      
      right.getChildren().add(selected);
      right.getChildren().add(dSelect);
      right.getChildren().add(bSelect);
      pane.setRight(right);
   }
   /** This method sets up the bottom of the border pane for boggle */
   public void setBottom(){
      error = new Label();
      bottom.getChildren().add(error);
      pane.setBottom(bottom);
   }
   /** This method sets up the center of the border pane for boggle */
   public void setCenter(){
      pane.setAlignment(tiles, Pos.CENTER);
      pane.setCenter(tiles);
   }
   /** This method clears all of the border border pane but the top. */
   public void clearPane(){
      tiles.getChildren().clear();
      left.getChildren().clear();
      right.getChildren().clear(); 
      bottom.getChildren().clear();
   }
   
   public static void main (String [] args){
      launch(args);
   }
}