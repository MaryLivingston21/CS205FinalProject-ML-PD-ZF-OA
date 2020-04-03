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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;

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
    //TitleHBox will hold title
    HBox titleHBox = new HBox();
    //VBox to contain players points
    VBox p1PointsVBox;
    VBox p2PointsVBox; 
    //buttons
    Button newGame, passTurn;
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

        //Construct Title HBox and title
        Text titleText = new Text("Othello");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titleText.setFill(Color.web("#0A3A2A"));
        topHBox.setStyle("-fx-background-color:#1f2833;");
        topHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#000000;");
        topHBox.setMinHeight(20);
        topHBox.setMaxHeight(20);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.getChildren().add(titleText);

        //Construct VBoxes for players points



        //Construct topHBox
        //TODO:: Add Points functionality and whose turn fucntionality
        topHBox.setStyle("-fx-background-color:#1f2833;");
        topHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        topHBox.setMinHeight(150);
        topHBox.setMaxHeight(150);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.getChildren().add(titleHBox);
        //Construct bottomHBox
        //TODO:: Addd new game and timer
        bottomHBox.setPadding(new Insets(20));
        bottomHBox.setSpacing(15);
        bottomHBox.setStyle("-fx-background-color:#1f2833;");
        bottomHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        bottomHBox.setMinHeight(150);
        bottomHBox.setMaxHeight(150);
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
        gridPane.setMaxHeight(600);
        gridPane.setMinHeight(600);
        gridPane.setMaxWidth(600);
        gridPane.setMinWidth(600);
        //Give GridPane a border and color it
        gridPane.setStyle("-fx-border-color:#0A3A2A;"+"-fx-border-width:4px;");
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
