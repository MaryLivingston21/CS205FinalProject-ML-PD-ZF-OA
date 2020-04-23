import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**TilePane class is a single tile on the board. Contains a
 * Circle object to represent piece and can
 return tile object associated with tilepane. Uses HBox */
public class TilePane extends HBox{


    //FIELDS
    private Square s; //is the Tile object that TilePane is based off of
    private  Circle piece; //Circle object whose color will change depend on who controls the board
    public int row,col;
    private int control;
    private boolean isValid;
    // shadedMediumBlue #2d706f   for potential valid moves functionality
    private Color mediumBlue = Color.web("#429E9D");
    Color neonLightBlue = Color.rgb(102,252,241);
    Background neonLightBlueBackground = new Background(new BackgroundFill(Color.web("#66fcf1"), CornerRadii.EMPTY, Insets.EMPTY));
    Background darkBlueBackground = new Background(new BackgroundFill(Color.web("#1f2833"), CornerRadii.EMPTY, Insets.EMPTY));
    Background pastelRedBackground = new Background(new BackgroundFill(Color.web("#FF9AA2"), CornerRadii.EMPTY, Insets.EMPTY));
    Background pastelGreenBackground = new Background(new BackgroundFill(Color.web("#77dd77"), CornerRadii.EMPTY, Insets.EMPTY));
    Background pastelDarkRedBackground = new Background(new BackgroundFill(Color.web("#FF3A49"), CornerRadii.EMPTY, Insets.EMPTY));
    Background mediumBlueBackground = new Background(new BackgroundFill(Color.web("#429E9D"), CornerRadii.EMPTY, Insets.EMPTY));
    Background darkGreenBackground = new Background(new BackgroundFill(Color.web("#0A3A2A"), CornerRadii.EMPTY, Insets.EMPTY));
    Border neonLightBlueBorder = new Border(new BorderStroke(neonLightBlue, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    Border darkGreenBorder = new Border(new BorderStroke(Color.web("#0A3A2A"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    Border darkRedBorder = new Border(new BorderStroke(Color.web("#FF3A49"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    Border pastelGreenBorder = new Border(new BorderStroke(Color.web("#77dd77"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

    /**
     Sole Constructor that writes text and displays unselected Tile HBoxes
     @param s is a tile object from which the tilepane will be contructed from
     */

    public TilePane(Square s){

        this.s = s;//binds object to field t
        row=s.getRow();
        col=s.getCol();
        control = s.getUser();
        //isValid = s.isValidMove();
        isValid = false;


        //set alignment of TilePane
        this.setAlignment(Pos.CENTER);
        //set size dimensions of tile pane
        this.setPrefSize(75, 75);
        //sets width and color of border initially 
        this.setBorder(darkGreenBorder);
        this.setBackground(mediumBlueBackground);
        //create Circle object with information from tile object
        piece = new Circle();
        //set radius
        piece.setRadius(30.0f);
        //set Position in pane
        piece.setCenterX(25.0f);
        piece.setCenterY(25.0f);
        //Set color of piece, depends on tile
        setPlayerControl(s.getUser());

        //if this is the most recently played square
        if(s.isMostRecent()){

            //create an indicator
            Circle lastIndicator = new Circle(5.0f, Color.DARKRED);
            //create a stackpane for stacking the indicator and the piece
            StackPane stack = new StackPane();

            //add the piece and indicator to the stack
            stack.getChildren().addAll(piece, lastIndicator);
            //add the stack to the scene
            this.getChildren().add(stack);
        }
        else{
            //add just the piece to the scene
            this.getChildren().add(piece);
        }

    }
    /**
     setPlayer Control method will set the control and change the color of the circle object
     */

    public void setPlayerControl(int control)
    {
        //Change control
        this.control = control;
        //Update circle color
        if(s.getUser()==0){
            //Color will be changed later
            piece.setFill(mediumBlue);
        }
        else if(s.getUser()==1){
            piece.setFill(Color.BLACK);
        }
        else{
            piece.setFill(Color.WHITE);
        }

    }
    //Will set border style depending on if this square is a valid move
    public void setBorderStyle(boolean isValid){
        if(isValid){
            this.setBorder(pastelGreenBorder);
        }
        else{
            this.setBorder(darkRedBorder);
        }
    }
    public Square getSquare()
    {
        return s;
    }

    /**getRow method returns row of square
     @return returns row */
    public int getRow()
    {
        return row;
    }

    /**getCol method returns col of square
     @return returns int that is col*/
    public int getCol()
    {
        return col;
    }

    /**getControl method returns number representing which player controls the square
     @return int control*/
    public int getControl()
    {
        return control;
    }
    /**
     * Method to change color of circle
     */
    public void changePieceColor(Color c){
        this.piece.setFill(c);
    }


}