import javafx.scene.layout.HBox;
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
    private Color red = Color.web("#429E9D");
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
        setBorderStyle(isValid);
        //create Circle object with information from tile object
        piece = new Circle();
        //set radius
        piece.setRadius(30.0f);
        //set Position in pane
        piece.setCenterX(25.0f);
        piece.setCenterY(25.0f);
        //Set color of piece, depends on tile
        setPlayerControl(s.getUser());
        //adds this text object to the children of hbox
        this.getChildren().add(piece);

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
            piece.setFill(red);
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
            this.setStyle("-fx-border-width: 1;" +
                    "-fx-border-color:#FFFFFF;"+"-fx-background-color:#429E9D;");
        }
        else{
            this.setStyle("-fx-border-width: 1;" +
                    "-fx-border-color:#0A3A2A;"+"-fx-background-color:#429E9D;");
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


}