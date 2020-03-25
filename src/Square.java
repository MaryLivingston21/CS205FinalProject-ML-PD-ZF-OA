public class Square {
    private int row;
    private int col;
    private int userNumber;

    public Square(int r, int c){
        row = r;
        col = c;
        userNumber = 0;
    }

    public void setUser(int userNumber){
        this.userNumber = userNumber;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getUser(){
        return userNumber;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            Square sOther = (Square)other;
            if (this.row == sOther.getRow() && this.getCol() == sOther.getCol() && this.getUser() == sOther.getUser()){
                return true;
            } else {
                return false;
            }
        }
    }

}
