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
import java.util.Arrays;
import javafx.geometry.Insets;

public class Othello extends Application {
    //Game Object
    private Game g;
    //GridPane that will hold 64 tiles
    public GridPane gridPane;
    //BorderPane will hold all other containers
    public BorderPane borderPane = new BorderPane();
    //Scene
    Scene scene;
    //TopHbox will hold score for each player, whose turn it is
    HBox topHBox = new HBox();
    //BottomHBow will hold buttons such as new game. will hold timer
    HBox bottomHBox = new HBox();
    //buttons
    Button newGame;
    @Override
    public void start(Stage primaryStage){
        Board b = new Board();
        //TODO:: Change. This is tempory because I'm confused
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),new Player(2,"human")));
        g = new Game(b,players);

        //TODO:: Add timer class and create

        //TODO:: Add new game button as well as button event
        newGame = new Button("New Game");
        newGame.setOnAction(event->
        {
            //TODO::Add fucntionality
        });

        //Construct topHBox
        //TODO:: Add Points functionality and whose turn fucntionality
        topHBox.setStyle("-fx-background-color:#1f2833;");
        topHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        topHBox.setMinHeight(80);
        topHBox.setMaxHeight(80);
        topHBox.setAlignment(Pos.CENTER);

        //Construct bottomHBox
        //TODO:: Addd new game and timer
        bottomHBox.setPadding(new Insets(20));
        bottomHBox.setSpacing(15);
        bottomHBox.setStyle("-fx-background-color:#1f2833;");
        bottomHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        bottomHBox.setMinHeight(80);
        bottomHBox.setMaxHeight(80);
        bottomHBox.setAlignment(Pos.CENTER);


        //Construct board and get gridPane
        setGridPane();


        //Add panes to borderpane
        borderPane.setTop(topHBox);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(bottomHBox);

        scene = new Scene(borderPane,1000,1000);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Othello");
        primaryStage.show();
    }

    public void handleClick(MouseEvent e) {

    }
    public void setGridPane()
    {
        gridPane=drawBoard();
        gridPane.setMaxHeight(400);
        gridPane.setMinHeight(400);
        gridPane.setMaxWidth(400);
        gridPane.setMinWidth(400);
        //Give GridPane a border and color it
        gridPane.setStyle("-fx-border-color:#45a29e;"+"-fx-border-width:4px;");
    }


    public GridPane drawBoard(){
        GridPane gp = new GridPane();
        TilePane tp;
        Square s;
        for(int r=1; r<9;r++)
        {
            for(int c=1;c<9;c++)
            {
                s=g.getSquare(r,c);//get Tile object from game(which gets it from board)
                tp=new TilePane(s);
                tp.setOnMouseClicked(this::handleClick);
                gp.add(tp,c,r);
            }
        }
        return gp;

    }
    public static void main(String[] args){
        launch(args);
    }

}
