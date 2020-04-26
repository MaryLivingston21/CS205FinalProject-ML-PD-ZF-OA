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
    GridPane gp;
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
    VBox gameTypeVBox = new VBox(); //VBox to display options for type players at beginning of game
    VBox middleVBox = new VBox();
    VBox turnVBox;
    VBox gameOverVBox = new VBox();
    Button newGameButton, passTurnButton,exitButton,gameTypeCompButton,gameTypeHumanButton;//buttons
    Pane menuPane, rulePane; //panes to hold text
    Text messageText, p1Points, p2Points,playerMenuText,titleText,menuText,ruleText,gameTypeDescriptionHH,gameTypeDescriptionHC,turnText,gameOverText, finalScoreText,winnerText;

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
            newGameButton.setBackground(pastelDarkRedBackground);
            updateBorderPaneToMenu();
        });
        passTurnButton = new Button("Forfeit Turn");
        passTurnButton.setBackground(neonLightBlueBackground);
        passTurnButton.setOnMouseEntered(this::mouseEnterButton);
        passTurnButton.setOnMouseExited(this::mouseExitButton);
        passTurnButton.setOnMouseReleased(this::mouseReleasedButton);
        passTurnButton.setOnMousePressed(this::mousePressedButton);
        passTurnButton.setOnAction(event->
        {
            passTurnButton.setBackground(pastelDarkRedBackground);
            boolean valid = g.forfeitTurn(g.getCurrentPlayer());
            if(valid){
                messageText.setText("Turn Forfeited");
                updateTurnText();
            } else{
                messageText.setText("Valid move exists, cannot pass");
            }
            if(gameType=="withComp"){
                try {
                    // delay .3 seconds
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    System.err.format("IOException: %s%n", ex);
                }
                g.computerPlayPiece();
                /*
                for(int i = 0; i < b.getBoard().size(); i++){
                    b.getBoard().get(i).setMostRecent(false);
                }
                tp.getSquare().setMostRecent(true);updateGridPane();
                 */
                updateGridPane();
                updateTurnText();
                updateScore();

            }
            if(g.isGameOver()){
                gameOver();
            }
        });
        exitButton = new Button("Exit Program");
        exitButton.setBackground(neonLightBlueBackground);
        exitButton.setOnMouseEntered(this::mouseEnterButton);
        exitButton.setOnMouseExited(this::mouseExitButton);
        exitButton.setOnMouseReleased(this::mouseReleasedButton);
        exitButton.setOnMousePressed(this::mousePressedButton);
        exitButton.setOnAction(event ->
        {
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
        p1Points = new Text("2 Pieces");
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
         * styling for center of border pane ie. message and
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */


        messageText = new Text();
        messageText.setFill(neonLightBlue);
        messageText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        messageVBox.setMaxHeight(50);
        messageVBox.setMinHeight(50);
        messageVBox.setMinWidth(400);
        messageVBox.setAlignment(Pos.CENTER);
        messageVBox.setPadding(new Insets(0,0,20,0));
        middleVBox.setAlignment(Pos.CENTER);
        middleVBox.setPadding(new Insets(10,10,10,10));
        middleVBox.setSpacing(15);




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
        gameTypeVBox.setPadding(new Insets(20,20,20,20));
        gameTypeVBox.setSpacing(25);
        gameTypeVBox.setAlignment(Pos.CENTER);
        gameTypeVBox.setMaxHeight(600);
        gameTypeVBox.setMinHeight(600);
        gameTypeVBox.setMaxWidth(600);
        gameTypeVBox.setMinWidth(600);
        gameTypeVBox.getChildren().addAll(playerMenuText, gameTypeCompButton, gameTypeHumanButton,gameTypeDescriptionHC,gameTypeDescriptionHH);




        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         * gameOver screen properties-> gameOverVBox, texts, buttons
         * ////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */
        gameOverText = new Text("Game Over");
        gameOverText.setFill(neonLightBlue);
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        finalScoreText = new Text();
        finalScoreText.setFill(neonLightBlue);
        finalScoreText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        winnerText = new Text();
        winnerText.setFill(neonLightBlue);
        winnerText.setFont(Font.font("Arial", FontWeight.BOLD, 27));

        gameOverVBox.setPadding(new Insets(20,20,20,20));
        gameOverVBox.setSpacing(25);
        gameOverVBox.setAlignment(Pos.CENTER);
        gameOverVBox.setMaxHeight(600);
        gameOverVBox.setMinHeight(600);
        gameOverVBox.setMaxWidth(600);
        gameOverVBox.setMinWidth(600);
        gameOverVBox.getChildren().addAll(gameOverText, finalScoreText,winnerText);

        //Add all panes to borderPane and set style properties of borderPane
        borderPane.setTop(topHBox);
        borderPane.setRight(ruleVBox);
        borderPane.setLeft(menuVBox);
        borderPane.setCenter(middleVBox);
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



    /**
     * handleClick handles when a tile pane is clicked on
     */
    public void handleClick(MouseEvent e) {
        TilePane tp = (TilePane) e.getSource();
        int x;
        Player player = g.getCurrentPlayer();
        if (gameType == "withComp") {
            if(g.isValidMove(player,tp.getSquare()) != null) {
                if(tp.getControl()==0) {
                    x = g.getPlayer(0).getScore();
                    g.playPiece(tp.getSquare());
                    messageText.setText("You flipped: " + (g.getPlayer(0).getScore() - x) + " piece(s)");
                    for(int i = 0; i < b.getBoard().size(); i++){
                        b.getBoard().get(i).setMostRecent(false);
                    }
                    tp.getSquare().setMostRecent(true);
                    updateGridPane();
                    updateScore();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        System.err.format("IOException: %s%n", ex);
                    }
                    x = g.getPlayer(1).getScore();
                    if(g.computerPlayPiece()){
                        messageText.setText("Computer flipped: " + (g.getPlayer(1).getScore() - x) + " piece(s)");

                    }
                    else{
                        //Computer had no valid moves, and now player control must switch
                        g.passTurn();
                    }
//                    for(int i = 0; i < b.getBoard().size(); i++){
//                        b.getBoard().get(i).setMostRecent(false);
//                    }
////                    tp.getSquare().setMostRecent(true);
                    updateGridPane();
                    updateTurnText();
                    updateScore();
                }
            }
            else {
                messageText.setText("Invalid Move");
            }
        }
        else if(gameType == "Humans") {
            if (g.isValidMove(player, tp.getSquare()) != null) {
                if (tp.getControl() == 0) {
                    x = player.getScore() + 1;
                    g.playPiece(tp.getSquare());
                    messageText.setText("Player " + player.getPlayerNumber() + " flipped: "+ (player.getScore() - x) + " piece(s)");
                    for(int i = 0; i < b.getBoard().size(); i++){
                        b.getBoard().get(i).setMostRecent(false);
                    }
                    tp.getSquare().setMostRecent(true);


                }
            } else {
                messageText.setText("Invalid Move");
            }
        }
//        for(int i = 0; i < b.getBoard().size(); i++){
//            b.getBoard().get(i).setMostRecent(false);
//        }
//        tp.getSquare().setMostRecent(true);
        updateGridPane();
        updateTurnText();
        updateScore();
        if(g.isGameOver()){
            gameOver();
        }
    }


    /**
     * mouseGameTypeButton detects which game type has been chosen.
     * In this function the game object is initialized
     * GUI is also updated to display board
     */
    public void mouseGameTypeButton( MouseEvent e){
        Button bu = (Button)e.getSource();
        Player opponent;
        if(bu.getText()=="Human vs. Computer"){
            opponent = new Player(2, "computer");
            gameType = "withComp";
            //messageText = new Text("Game Type: Human vs Computer");
            messageText.setText("Game Type: Human vs Computer");
        }
        else{
            opponent = new Player(2, "human");
            gameType = "Humans";
           // messageText = new Text("Game Type: Human vs Human");
            messageText.setText("Game Type: Human vs Human");
        }
        //Game will initialized with two human players
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),opponent));
        b = new Board();
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
        //If tp is not a valid move, will highlight red to indicate not a valid move
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
        updateGridPane();
    }

    /**
     * gameOver method is called when game is over. Replaces various boxes and panes
     * to represent end screen.
     */
    public void gameOver(){
        //remove rule text
        ruleVBox.getChildren().clear();
        //remove forfeit turn button
        bottomHBox.getChildren().remove(passTurnButton);
        //remove menu
        menuVBox.getChildren().clear();
        //Determine and display who won
        if(gameType == "withComp"){
            if(g.getPlayer(0).getScore()>g.getPlayer(1).getScore()){
                winnerText.setText("You Won!");
            }
            else if(g.getPlayer(0).getScore()<g.getPlayer(1).getScore()){
                winnerText.setText("The computer won...a silicon wafer is better than you");
            }
            else{
                winnerText.setText("Tie...embarrassing");
            }
            finalScoreText.setText("Final Score: \n\n\nYou           - " + g.getPlayer(0).getScore() +
                    "\nComputer - " + g.getPlayer(1).getScore());
        }
        else{
            if(g.getPlayer(0).getScore()>g.getPlayer(1).getScore()){
                winnerText.setText("Player 1 Won!");
            }
            else if(g.getPlayer(0).getScore()<g.getPlayer(1).getScore()){
                winnerText.setText("Player 2 Won!");
            }
            else{
                winnerText.setText("Tie!");
            }
            finalScoreText.setText("Final Score: \n\nPlayer 1 - " + g.getPlayer(0).getScore() +
                    "\nPlayer 2 - " + g.getPlayer(1).getScore());
        }
        //replace middleVBox with gameOverVBox
        borderPane.setCenter(gameOverVBox);
    }
    /**
     * drawBoard will update GUI board depending on properties stored in board object
     */
    public void drawBoard(){
        GridPane newGridPane = new GridPane();
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
                newGridPane.add(tp,c,r);
            }
        }
        //GUI properties
        newGridPane.setMaxHeight(600);
        newGridPane.setMinHeight(600);
        newGridPane.setMaxWidth(600);
        newGridPane.setMinWidth(600);
        newGridPane.setStyle("-fx-border-color:#0A3A2A;"+"-fx-border-width:5px;");
        gp = newGridPane;
    }
    /**
     Update GridPane(The Actual Board) to display current state of the board
     */
    public void updateGridPane(){
        drawBoard();
        middleVBox.getChildren().clear();
        middleVBox.getChildren().addAll(messageVBox,gp,turnVBox);
        borderPane.setCenter(middleVBox);
    }
    /**
     Update the points for each player
     */
    public void updateScore(){
        p1Points.setText(g.getPlayer(0).getScore()+ " Pieces");
        p2Points.setText(g.getPlayer(1).getScore()+ " Pieces");
    }

    /**
     * updateTurnText method updates the text displaying whose turn it is.
     */
    public void updateTurnText(){
        int player = g.getCurrentPlayer().getPlayerNumber();
        if(player==2){
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
     * When the game first starts, only the options to choose player type should display(along with title and rules)
     * All other containers should be empty. This will also be state when new game is called. But after option
     * is selected and game object is initialized, all containers should be populated. This is this function:
     *
     * BottomVBox with buttons will be populated
     * TopVBox will have points added to it
     * Center of Borderpane will be replaced the the gridpane and messageVBox via the middle VBox
     */
    public void updateBorderPaneToGame(){
        /**
         * Populate TopVBox with: points, timer? (Title is already in place)
         */
        p1PointsVBox.getChildren().clear();
        p2PointsVBox.getChildren().clear();
        p1PointsVBox.getChildren().addAll(bCircle,p1Points);//Add circles and texts to VBoxes
        p2PointsVBox.getChildren().addAll(wCircle,p2Points);
        /**
         * Replace Game Type menu with grid pane and message
         */
        drawBoard();
        //Construct board and update/set gridpane
        //Construct messageVBox and message text. Message text will be updated through out game
        //Adding children
        messageVBox.getChildren().clear();
        messageVBox.getChildren().add(messageText);
        middleVBox.getChildren().clear();
        middleVBox.getChildren().addAll(messageVBox,gp,turnVBox);
        borderPane.setCenter(middleVBox);
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
        bottomHBox.getChildren().clear();
        bottomHBox.getChildren().addAll(newGameButton,exitButton,passTurnButton);
        /**
         * Populate RulesVBox
         */
        ruleVBox.getChildren().clear();
        ruleVBox.getChildren().add(ruleText);
        /**
         * Reset various styling details
         */
        updateScore();
        newGameButton.setBackground(neonLightBlueBackground);
        passTurnButton.setBackground(neonLightBlueBackground);
        exitButton.setBackground(neonLightBlueBackground);

    }

    /**
     * This method is to be used by newGame functionality when menu will be needed again to determine
     * gameType
     */
    public void updateBorderPaneToMenu(){
        //Reset Game and Redraw Board
        //Remove the following from the border pane:
            //points
        p1PointsVBox.getChildren().clear();
        p2PointsVBox.getChildren().clear();
            //rules
        ruleVBox.getChildren().clear();
            //buttons
        bottomHBox.getChildren().clear();
        bottomHBox.getChildren().add(exitButton);
        //Replace center pane
        borderPane.setCenter(gameTypeVBox);
        //newGameButton needs to be updated when game is reset.

    }
    public static void main(String[] args){
        launch(args);
    }

}
