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

}
