import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Group;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

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
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.shape.Circle;

import javax.swing.text.View;

public class Othello extends Application {
    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * Various Object and Container Declarations. If you add a container, please add it here
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */

    private Game g;
    private Board b;
    String gameType;
    ArrayList<Player> players;


    public GridPane gridPane; //GridPane that will hold 64 tilePane objects
    public BorderPane borderPane = new BorderPane(); //BorderPane will hold all other containers(VBox and HBox)
    Scene scene;
    HBox topHBox = new HBox();//holds points VBoxes and titleVBox
    HBox bottomHBox = new HBox();//BottomHBow will hold buttons such as new game,pass turn, timer
    VBox titleVBox = new VBox();//TitleHBox will hold title
    VBox messageVBox = new VBox();//will contain messages that update, such as whose turn, if the game in ended, if a move is invalid, ect.
    VBox p1PointsVBox = new VBox(5);//VBox to contain players points
    VBox p2PointsVBox = new VBox(5);
    VBox menuVBox = new VBox();//will hold the menu of options
    VBox ruleVBox = new VBox();//will contain rules
    VBox gameTypeVBox; //VBox to display options for type players at beginning of game
    VBox middle;
    VBox turnVBox;
    Button newGameButton, passTurnButton,exitButton,gameTypeCompButton,gameTypeHumanButton;//buttons
    Pane menuPane, rulePane; //panes to hold text
    Text messageText, p1Points, p2Points,playerMenuText,titleText,menuText,ruleText,gameTypeDescriptionHH,gameTypeDescriptionHC,turnText;

    //Circle Objects
    Circle wCircle = new Circle(30, Color.WHITE);//Symbols that represent player
    Circle bCircle = new Circle(30, Color.BLACK);
    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * Color and Background Declarations. Feel free to change colors if you want a different aesthetic
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */



    Color neonLightBlue = Color.rgb(102,252,241);
    Color darkBlue = Color.web("#1f2833");
    Background neonLightBlueBackground = new Background(new BackgroundFill(Color.web("#66fcf1"), CornerRadii.EMPTY, Insets.EMPTY));
    Background darkBlueBackground = new Background(new BackgroundFill(Color.web("#1f2833"), CornerRadii.EMPTY, Insets.EMPTY));
    Background pastelRedBackground = new Background(new BackgroundFill(Color.web("#FF9AA2"), CornerRadii.EMPTY, Insets.EMPTY));
    Background pastelDarkRedBackground = new Background(new BackgroundFill(Color.web("#FF3A49"), CornerRadii.EMPTY, Insets.EMPTY));
    Background mediumBlueBackground = new Background(new BackgroundFill(Color.web("#429E9D"), CornerRadii.EMPTY, Insets.EMPTY));
    Background darkGreenBackground = new Background(new BackgroundFill(Color.web("#0A3A2A"), CornerRadii.EMPTY, Insets.EMPTY));
    Border neonLightBlueBorder = new Border(new BorderStroke(neonLightBlue, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    Border darkGreenBorder = new Border(new BorderStroke(Color.web("#0A3A2A"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));



    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * start method, sets properties of and displays GUI.
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    @Override
    public void start(Stage primaryStage){

        b = new Board();
        //TODO:: Change. This is temporary because I'm confused
        //ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),new Player(2,"human")));
        //g = new Game(b,players);

        //TODO:: Add timer class and create timer




        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * Various Buttons
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */




        newGameButton = new Button("New Game");
        newGameButton.setBackground(neonLightBlueBackground);
        newGameButton.setOnMouseEntered(this::mouseEnterButton);
        newGameButton.setOnMouseExited(this::mouseExitButton);
        newGameButton.setOnMouseReleased(this::mouseReleasedButton);
        newGameButton.setOnMousePressed(this::mousePressedButton);
        newGameButton.setOnAction(event->
        {
            //TODO::Add fucntionality
            newGameButton.setBackground(pastelDarkRedBackground);

        });
        passTurnButton = new Button("Pass Turn");
        passTurnButton.setBackground(neonLightBlueBackground);
        passTurnButton.setOnMouseEntered(this::mouseEnterButton);
        passTurnButton.setOnMouseExited(this::mouseExitButton);
        passTurnButton.setOnMouseReleased(this::mouseReleasedButton);
        passTurnButton.setOnMousePressed(this::mousePressedButton);
        passTurnButton.setOnAction(event->
        {
            passTurnButton.setBackground(pastelDarkRedBackground);
            //TODO::ADD functionality
        });

        exitButton = new Button("Exit Program");
        exitButton.setBackground(neonLightBlueBackground);
        exitButton.setOnMouseEntered(this::mouseEnterButton);
        exitButton.setOnMouseExited(this::mouseExitButton);
        exitButton.setOnMouseReleased(this::mouseReleasedButton);
        exitButton.setOnMousePressed(this::mousePressedButton);
        exitButton.setOnAction(event ->
        {
            newGameButton.setBackground(pastelDarkRedBackground);
            Platform.exit();

        });
        gameTypeCompButton = new Button("Human vs. Computer");
        gameTypeCompButton.setBackground(neonLightBlueBackground);
        gameTypeCompButton.setOnMouseEntered(this::mouseEnterButton);
        gameTypeCompButton.setOnMouseExited(this::mouseExitButton);
        gameTypeCompButton.setOnMouseReleased(this::mouseGameTypeButton);
        gameTypeCompButton.setOnMousePressed(this::mousePressedButton);

        gameTypeHumanButton = new Button("  Human vs. Human  ");
        gameTypeHumanButton.setBackground(neonLightBlueBackground);
        gameTypeHumanButton.setOnMouseEntered(this::mouseEnterButton);
        gameTypeHumanButton.setOnMouseExited(this::mouseExitButton);
        gameTypeHumanButton.setOnMouseReleased(this::mouseGameTypeButton);
        gameTypeHumanButton.setOnMousePressed(this::mousePressedButton);



        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * A lot of messy container stuff. All Boxes and Texts
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */
        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * topHBox, pointsVBox, titleVBbox and associated objects
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */




        //Construct VBoxes for players points -> will be contained in topHBox
        p1Points = new Text("2 Pieces");//TODO::Construct a function to update these values
        p1Points.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        p1Points.setFill(neonLightBlue);
        p2Points = new Text("2 Pieces");
        p2Points.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        p2Points.setFill(neonLightBlue);
        //p1PointsVBox.getChildren().addAll(bCircle,p1Points);//Add circles and texts to VBoxes
        //p2PointsVBox.getChildren().addAll(wCircle,p2Points);
        p1PointsVBox.setAlignment(Pos.CENTER_LEFT);//set position in box
        p2PointsVBox.setAlignment(Pos.CENTER_RIGHT);
        p1PointsVBox.setMinWidth(300);//set width to keep from squishing containers together
        p2PointsVBox.setMinWidth(300);

        //Construct titleVBox and title text -> will be contained in topHBox
        titleText = new Text("Othello");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titleText.setFill(neonLightBlue);
        titleVBox.setMinHeight(20);
        titleVBox.setMaxHeight(20);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.getChildren().add(titleText);

        //Construct topHBox -> contains points and title
        //TODO:: Add Points functionality and whose turn fucntionality
        topHBox.setBackground(darkBlueBackground);
        topHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        topHBox.setMinHeight(150);
        topHBox.setMaxHeight(150);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.getChildren().addAll(p1PointsVBox,titleVBox,p2PointsVBox);




        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * bottomHBox, contains buttons for newGame, exit, passTurn, will contain timer
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */




        //Construct bottomHBox -> contains buttons and timer(when I add it)
        //TODO:: Add new game and timer
        bottomHBox.setPadding(new Insets(20));
        bottomHBox.setSpacing(15);
        bottomHBox.setBackground(darkBlueBackground);
        bottomHBox.setStyle("fx-border-width:2px;"+ "-fx-border-color:#45a29e;");
        bottomHBox.setMinHeight(150);
        bottomHBox.setMaxHeight(150);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.getChildren().add(exitButton);




        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * menuVBox(left side of borderpane)
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */




        //Construct menuVBox -> will hold the menu of options
        menuText = new Text("Menu");
        menuText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        menuText.setFill(neonLightBlue);
        menuPane = new Pane(menuText);
        //menuVBox.getChildren().add(menuText);
        menuVBox.setPadding(new Insets(20,50,20,50));
        menuVBox.setStyle("-fx-border-color:#45a29e; -fx-border-width : 0 2 0 2 ");
        menuVBox.setMinWidth(300);
        menuVBox.setMaxWidth(300);
        //TODO:: Add Menu options and associated functionality



    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * turnVBox and turn text
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */


        turnText = new Text("Your Turn");
        turnText.setFill(Color.BLACK);
        turnText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        turnVBox = new VBox(turnText);
        turnVBox.setMaxHeight(20);
        turnVBox.setMinHeight(20);
        turnVBox.setMinWidth(100);
        turnVBox.setMaxWidth(100);
        turnVBox.setAlignment(Pos.CENTER);
        turnVBox.setBackground(neonLightBlueBackground);




        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * ruleVBox(right side of borderpane) only contains text
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */




        //Construct ruleVBox -> will hold the menu of options
        ruleText = new Text();
        ruleText.setText("\n          Rules:\n\n-The objective of the\n game is to have the\n most pieces flipped\n to your color when\n the game ends" +
                "\n\n-Flip opponents pieces\n to your color by\n sandwiching their\n pieces between\n your own. This can\n be done horizontally," +
                "\n vertically or diagonally\n\n"+ "-You can only place\n pieces adjacent to\n " +
                "your opponents pieces\n in a move that\n that would sandwich\n one or more of \n their pieces\n\n-The game ends when\n no more valid\n" +
                " moves exist\n\n   Good Luck!");
        ruleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ruleText.setFill(neonLightBlue);
        rulePane = new Pane(ruleText);
        //ruleVBox.getChildren().add(ruleText);
        ruleVBox.setPadding(new Insets(20,50,20,50));
        ruleVBox.setStyle("-fx-border-color:#45a29e; -fx-border-width : 0 2 0 2 ");
        ruleVBox.setMinWidth(300);
        ruleVBox.setMaxWidth(300);




        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * playerMenu displayed, determinePlayers called, game object initialized
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */


        //Text
        playerMenuText = new Text("Select Game Type");
        playerMenuText.setFill(neonLightBlue);
        playerMenuText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        gameTypeDescriptionHC = new Text("\n\n Human vs Computer: Play against a computer AI trained by Mary");
        gameTypeDescriptionHH = new Text("Human vs Human: Play against a friend... or your self");
        gameTypeDescriptionHC.setFill(neonLightBlue);
        gameTypeDescriptionHC.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        gameTypeDescriptionHH.setFill(neonLightBlue);
        gameTypeDescriptionHH.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        //VBox
        gameTypeVBox = new VBox();
        gameTypeVBox.setPadding(new Insets(20,20,20,20));
        gameTypeVBox.setSpacing(25);
        gameTypeVBox.setAlignment(Pos.CENTER);
        gameTypeVBox.setMaxHeight(600);
        gameTypeVBox.setMinHeight(600);
        gameTypeVBox.setMaxWidth(600);
        gameTypeVBox.setMinWidth(600);
        gameTypeVBox.getChildren().addAll(playerMenuText, gameTypeCompButton, gameTypeHumanButton,gameTypeDescriptionHC,gameTypeDescriptionHH);
        //Add all panes to borderPane and set style properties of borderPane
        borderPane.setTop(topHBox);
        borderPane.setRight(ruleVBox);
        borderPane.setLeft(menuVBox);
        borderPane.setCenter(middle);
        borderPane.setCenter(gameTypeVBox);
        borderPane.setBottom(bottomHBox);
        borderPane.setBackground(darkBlueBackground);

        //Create Scene and setScene
        scene = new Scene(borderPane,1350,1050);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Othello");
        primaryStage.show();
    }



    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * Associated methods and event handlers.
     * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */




    public void handleClick(MouseEvent e) {
        /**
         * Determine if valid move, flip approprite pieces, redraw board
         */
        TilePane tp = (TilePane) e.getSource();
        messageText.setText("Selected Tile - Row: " + tp.getRow() + " Col: " + tp.getCol());
        // TODO: This is where game logic should go I think
        Color playerColor;
        Player player = g.getCurrentPlayer();
        if(g.isValidMove(player,tp.getSquare()) != null){
            if(tp.getControl() == 0){
                int x = player.getScore() -1 ;
                g.playPiece(tp.getSquare());
                messageText.setText(player.getScore()-x + " piece(s) flipped!");
                updateGridPane();
            }
        }
        else{
            messageText.setText("Invalid Move");
        }
        /**
         * Update Score in GUI
         */
        if(player.getPlayerNumber()==1){
            p1Points.setText(player.getScore()+ " Pieces");
            p2Points.setText(g.getPlayer(1).getScore()+ " Pieces");
        }
        else{
            p2Points.setText(player.getScore()+ " Pieces");
            p1Points.setText(g.getPlayer(0).getScore()+ " Pieces");
        }
        updateTurnText(player);
    }
    public void mouseGameTypeButton( MouseEvent e){
        Button bu = (Button)e.getSource();
        Player opponent;
        if(bu.getText()=="Human vs. Computer"){
            opponent = new Player(2, "computer");
            gameType = "withComp";
        }
        else{
            opponent = new Player(2, "human");
            gameType = "Humans";
        }
        //Game will initialized with two human players
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),opponent));
        g = new Game(b,players);
        //Replace with gridPane now
       updateBorderPaneToGame();


    }
    public void mouseEnterButton(MouseEvent e){
        Button bu = (Button)e.getSource();
        bu.setBackground(pastelRedBackground);
    }
    public void mouseExitButton(MouseEvent e){
        Button bu = (Button)e.getSource();
        bu.setBackground(neonLightBlueBackground);
    }
    public void mousePressedButton(MouseEvent e){
        Button bu = (Button)e.getSource();
        bu.setBackground(pastelDarkRedBackground);
    }
    public void mouseReleasedButton(MouseEvent e){
        Button bu = (Button)e.getSource();
        bu.setBackground(pastelRedBackground);
    }
    public void mouseEnter(MouseEvent e){
        TilePane tp=(TilePane)e.getSource();
        Color playerColor;
        if (g.getCurrentPlayer().getPlayerNumber() == 1) {
            playerColor = Color.BLACK;
        } else {
            playerColor = Color.WHITE;
        }
        //If tp is not a valid move, will highlight read to indicate not a valid move
        if(g.isValidMove(g.getCurrentPlayer(),tp.getSquare()) == null){
            if(tp.getControl() == 0){
                tp.changePieceColor(playerColor);
            }
            tp.setBorderStyle(false);
        }
        else {
            tp.changePieceColor(playerColor);
            tp.setBorderStyle(true);
        }
    }
    public void mouseExit(MouseEvent e) {
        TilePane tp = (TilePane) e.getSource();
        tp.setPlayerControl(tp.getControl());
        tp.setBorder(darkGreenBorder);
        tp.setBackground(mediumBlueBackground);
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
                tp.setOnMouseEntered(this::mouseEnter);
                tp.setOnMouseExited(this::mouseExit);
                gp.add(tp,c,r);
            }
        }
        //GUI properties
        gp.setMaxHeight(600);
        gp.setMinHeight(600);
        gp.setMaxWidth(600);
        gp.setMinWidth(600);
        gp.setStyle("-fx-border-color:#0A3A2A;"+"-fx-border-width:6px;");
        return gp;
    }

    /**
     Experimental only, this is bad code
     */
    public void updateGridPane(){
        //Experimental, not good code
        GridPane gp = drawBoard();
        middle.getChildren().clear();
        middle.getChildren().addAll(messageVBox,gp,turnVBox);
        borderPane.setCenter(middle);
    }
    public void updateTurnText(Player player){
        if(player.getPlayerNumber()==1){
            if(gameType=="withComp"){
                turnText.setText("Computer Turn");
            }
            else{
                turnText.setText("Player 2 Turn");
            }
        }
        else{
            if(gameType=="withComp"){
                turnText.setText("Your Turn");
            }
            else{
                turnText.setText("Player 1 Turn");
            }
        }
    }

    /**
     * When the game first starts, oly the options to choose player type should display(along with title and rules)
     * All other containers should be empty. This will also be state when new game is called. But after option
     * is selected and game object is initialized, all containers should be populated. This is this function:
     *
     * Menu VBox will be populated
     * BottomVBox with buttons will be populated
     * TopVBox will have points added to it
     * Center of Borderpane will be replaced the the gridpane and messageVBox via the middle VBox
     */
    public void updateBorderPaneToGame(){
        /**
         * Populate Menu VBox with: menuText, additional stuff?
         */
        menuVBox.getChildren().clear();
        menuVBox.getChildren().add(menuText);
        /**
         * Populate TopVBox with: points, timer? (Title is already in place)
         */
        p1PointsVBox.getChildren().addAll(bCircle,p1Points);//Add circles and texts to VBoxes
        p2PointsVBox.getChildren().addAll(wCircle,p2Points);
        /**
         * Replace Game Type menu with grid pane and message
         */
        gridPane = drawBoard();
        //Construct board and get gridPane
        middle = new VBox();
        //Construct messageVBox and message text. Message text will be updated through out game
        messageText = new Text("Game Type: " + gameType);
        //Styling
        messageText.setFill(neonLightBlue);
        messageText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        messageVBox.setMaxHeight(50);
        messageVBox.setMinHeight(50);
        messageVBox.setMinWidth(400);
        messageVBox.setAlignment(Pos.CENTER);
        messageVBox.setPadding(new Insets(0,0,20,0));
        middle.setAlignment(Pos.CENTER);
        middle.setPadding(new Insets(10,10,10,10));
        middle.setSpacing(15);
        //Adding children
        messageVBox.getChildren().add(messageText);
        middle.getChildren().addAll(messageVBox,gridPane,turnVBox);
        borderPane.setCenter(middle);
        //update turn text
        if(gameType=="withComp"){
            turnText.setText("Your Turn");
        }
        else{
            turnText.setText("Player 1 Turn");
        }
        /**
         * Populate BottomVBox with buttons
         */
        bottomHBox.getChildren().addAll(newGameButton,passTurnButton);
        /**
         * Populate RulesVBox
         */
        ruleVBox.getChildren().add(ruleText);
    }

    /**
     * This method is to be used by newGame functionality when menu will be needed again to determine
     * gameType
     */
    public void updateBorderPaneToMenu(){

    }
    public static void main(String[] args){
        launch(args);
    }

}
