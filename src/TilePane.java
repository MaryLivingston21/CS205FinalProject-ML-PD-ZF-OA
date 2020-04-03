import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**TilePane class is a single tile on the board. Contains a
 * Circle object to represent piece and can
 return tile object associated with tilepane. Uses HBox */
public class TilePane extends HBox{


    //FIELDS
    private Tile t; //is the Tile object that TilePane is based off of 
    private  Circle piece; //Circle object whose color will change depend on who controls the board
    public int row,col;
    private int control;

    /**
     Sole Constructor that writes text and displays unselected Tile HBoxes
     @param t is a tile object from which the tilepane will be contructed from
     */

    public TilePane(Tile t){

        this.t = t;//binds object to field t
        row=t.getRow();
        col=t.getCol();
        control = t.getControl();


        //set alignment of TilePane
        this.setAlignment(Pos.CENTER);
        //set size dimensions of tile pane
        this.setPrefSize(50, 50);
        //sets width and color of border initially 
        this.setStyle("-fx-border-width: 1;" +
                "-fx-border-color:#000000;"+"-fx-background-color:#36893C;");

        //create Circle object with information from tile object
        piece = new Circle();
        //set radius
        piece.setRadius(15.0f);
        //set Position in pane
        piece.setCenterX(25.0f);
        piece.setCenterY(25.0f);
        //Set color of piece, depends on tile
        setPlayerControl(t.getControl());
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
        if(t.getControl()==0){
            //Color will be changed later
            piece.setFill(Color.GRAY);
        }
        else if(t.getControl()==1){
            piece.setFill(Color.BLACK);
        }
        else{
            piece.setFill(Color.WHITE);
        }
    }
    public Tile getTile()
    {
        return t;
    }

    /**getRow method returns row of tile
     @return returns row */
    public int getRow()
    {
        return row;
    }

    /**getCol method returns col of tile 
     @return returns int that is col*/
    public int getCol()
    {
        return col;
    }

    /**getControl method returns number representing which player controls the tile
     @return int control*/
    public int getControl()
    {
        return control;
    }


}