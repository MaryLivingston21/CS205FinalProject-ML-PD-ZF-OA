public class Player {
    private int playerNumber; // 1 vs 2
    private String playerType; //computer vs human
    private int score;

    public Player(int num, String type){
        playerNumber = num;
        playerType = type;
        score = 2;
    }

    public void adjustScore(int n){
        score = score + n;
    }

    public int getScore(){
        return score;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            Player p2 = (Player)other;
            return this.playerNumber == p2.playerNumber;
        }
    }
}
