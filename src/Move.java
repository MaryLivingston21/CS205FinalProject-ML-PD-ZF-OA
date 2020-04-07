public class Move {
    private Square sPlaced;
    private Square sEnd;
    private int numFlipped;

    public Move(Square s1, Square s2, int num){
        sPlaced = s1;
        sEnd = s2;
        numFlipped = num;
    }

    public Square getSquarePlaced(){
        return sPlaced;
    }

    public Square getEndSquare(){
        return sEnd;
    }

    public int getNumFlipped() {
        return numFlipped;
    }

    @Override
    public String toString(){
        return sPlaced + "numFlipped = " + numFlipped;
    }

    //TODO:: DOES THIS NEED AN EQUALS METHOD


}
