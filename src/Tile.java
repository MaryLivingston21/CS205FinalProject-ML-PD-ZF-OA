/**This a Tile class. It represents one tile on the board of 64. It will store an
 * integer(representing which player the tile belongs to,
 * 0 for unclaimed, 1 for player 1, 2 for player 2),
 * the row and column location, and whether or not the tile was the last tile played.
 * The tile class has the ability to return all fields and alter the player
 * field.*/
public class Tile
{

    //CLASS FIELDS
    private int control = 0;
    private int row, column;
    private boolean isLastPlayed;
    /**
     Constructor that accepts 3 integers
     @param a is an integer representing who owns the tile
     @param c is an int that is that tiles column
     @param r is an int that is the tiles row
     */
    public Tile(int a, int c, int r)
    {

        control=a;
        row=r;
        column=c;
        isLastPlayed=false;
    }
    /**
     getRow method returns row of tile
     @return returns row of tile as int
     */
    public int getRow()
    {
        return row;
    }

    /**
     getCol method returns column of tile
     @return returns column of tile as int
     */
    public int getCol()
    {
        return column;
    }
    /**
     setControl method sets control field
     @param c integer control
     */
    public void setControl(int c)
    {
        control = c;
    }
    /**
     getChar method returns an integer representing who owns the tile
     @return returns integer stored in control
     */
    public int getControl()
    {
        return control;
    }
    /**
     setLastPlayed, sets whether or not this tile was the last tile played
     @param b boolean isLastPlayed
     */
    public void setLastPlayed(boolean b)
    {
        isLastPlayed = b;
    }
    /** isLastPlayed method returns the boolean representing whether or not this tile was the last played tile
     @return returns boolean isLastPlayed
     */
    public boolean isLastPlayed()
    {
        return isLastPlayed;
    }
    /**
     toString method represents a string of this tile
     @return returns a string of control
     */
    @Override
    public String toString()
    {
        return Integer.toString(control);
    }
    /**
     equals method determines if two tile objects are equal
     @param other is a tile object that is to be compared to this tile object
     @return returns a boolean value
     */
    @Override
    public boolean equals(Object other)
    {
        if(other==null)
            return false;
        if(other==this)
            return true;
        if(other.getClass()!=this.getClass())
            return false;
        Tile otherA= (Tile)other;
        return((this.control == otherA.control) && otherA.row==this.row && otherA.column==this.column);
    }
}

