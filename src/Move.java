import java.util.ArrayList;

public class Move {
    private Square sPlaced;
    private ArrayList<Square> sEnd = new ArrayList<Square>();
    private int numFlipped;

    public Move(Square s1, ArrayList<Square> s2, int num){
        sPlaced = s1;
        sEnd = s2;
        numFlipped = num;
    }

    public Move(Square s1, Square s2, int num){
        sPlaced = s1;
        sEnd.add(s2);
        numFlipped = num;
    }

    public Square getSquarePlaced(){
        return sPlaced;
    }

    public ArrayList<Square> getEndSquare(){
        return sEnd;
    }

    public void addEndSquare (Square s){
        sEnd.add(s);
    }

    public int getNumFlipped() {
        return numFlipped;
    }

    public void setNumFlipped(int num){
        numFlipped = num;
    }

    @Override
    public String toString(){
        return sPlaced + " numFlipped = " + numFlipped;
    }

    // Only checks if piece placed is equal
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            Move mOther = (Move)other;
            if (this.getSquarePlaced() == mOther.getSquarePlaced()){
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String toString(){
        return this.sPlaced + " is placed " + this.col;
    }


}
