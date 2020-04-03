import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Group;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

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
    /**
     * Various Object and Container Declarations. If you add a container, please add it here
     */
    private Game g;
    private Board b;
    public GridPane gridPane; //GridPane that will hold 64 tilePane objects
    public BorderPane borderPane = new BorderPane(); //BorderPane will hold all other containers(VBox and HBox)
    Scene scene;
    HBox topHBox = new HBox();//holds points VBoxes and titleVBox
    HBox bottomHBox = new HBox();//BottomHBow will hold buttons such as new game,pass turn, timer
    VBox titleVBox = new VBox();//TitleHBox will hold title
    VBox p1PointsVBox = new VBox(5);//VBox to contain players points
    VBox p2PointsVBox = new VBox(5);
    VBox menuVBox = new VBox();//will hold the menu of options
    Button newGame, passTurn;//buttons

    @Override
    public void start(Stage primaryStage){
        b = new Board();
        //TODO:: Change. This is temporary because I'm confused
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),new Player(2,"human")));
        g = new Game(b,players);

        //TODO:: Add timer class and create timer

        /**
         *
         *
         * Various Buttons. If you add a button please add it here.
         *
         *
         */
        newGame = new Button("New Game");
        newGame.setOnAction(event->
        {
            //TODO::Add fucntionality
        });
        passTurn = new Button("Pass Turn");
        passTurn.setOnAction(event->
        {
            //TODO::ADD functionality
        });

        /**
         *
         * A lot of messy container stuff.
         *
         */
        //Construct VBoxes for players points -> will be contained in topHBox
        Circle wCircle = new Circle(30, Color.WHITE);//Symbols that represent player
        Circle bCircle = new Circle(30, Color.BLACK);
        Text p1Points = new Text("0 Points");//TODO::Construct a function to update these values
        p1Points.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        p1Points.setFill(Color.rgb(102,252,241));
        Text p2Points = new Text("0 Points");
        p2Points.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        p2Points.setFill(Color.rgb(102,252,241));
        p1PointsVBox.getChildren().addAll(bCircle,p1Points);//Add circles and texts to VBoxes
        p2PointsVBox.getChildren().addAll(wCircle,p2Points);
        p1PointsVBox.setAlignment(Pos.CENTER_LEFT);//set position in box
        p2PointsVBox.setAlignment(Pos.CENTER_RIGHT);
        p1PointsVBox.setMinWidth(300);//set width to keep from squishing containers together
        p2PointsVBox.setMinWidth(300);

        //Construct titleVBox and title text -> will be contained in topHBox
        Text titleText = new Text("Othello");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titleText.setFill(Color.rgb(102,252,241));
        titleVBox.setMinHeight(20);
        titleVBox.setMaxHeight(20);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.getChildren().add(titleText);


        //Construct topHBox -> contains points and title
        //TODO:: Add Points functionality and whose turn fucntionality
        topHBox.setStyle("-fx-background-color:#1f2833;");
        topHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        topHBox.setMinHeight(150);
        topHBox.setMaxHeight(150);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.getChildren().addAll(p1PointsVBox,titleVBox,p2PointsVBox);

        //Construct bottomHBox -> contains buttons and timer(when I add it)
        //TODO:: Addd new game and timer
        bottomHBox.setPadding(new Insets(20));
        bottomHBox.setSpacing(15);
        bottomHBox.setStyle("-fx-background-color:#1f2833;");
        bottomHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        bottomHBox.setMinHeight(150);
        bottomHBox.setMaxHeight(150);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.getChildren().addAll(newGame,passTurn);

        //Construct board and get gridPane
        setGridPane();

        //Add all panes to borderPane and set style properties of borderPane
        borderPane.setTop(topHBox);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(bottomHBox);
        borderPane.setStyle("-fx-background-color:#1f2833;");

        //Create Scene and setScene
        scene = new Scene(borderPane,1000,1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Othello");
        primaryStage.show();
    }

    public void handleClick(MouseEvent e) {

    }
    //Method will call drawBoard and set style of properties of gridPane
    public void setGridPane()
    {
        gridPane=drawBoard();
        gridPane.setMaxHeight(600);
        gridPane.setMinHeight(600);
        gridPane.setMaxWidth(600);
        gridPane.setMinWidth(600);
        gridPane.setStyle("-fx-border-color:#0A3A2A;"+"-fx-border-width:4px;");//Give GridPane a border and color it
    }
    //Method will use game class to get tiles and construct tilePanes, and add to gridPane
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
