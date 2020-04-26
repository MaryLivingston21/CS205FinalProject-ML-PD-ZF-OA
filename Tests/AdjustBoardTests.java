// Patrick Dundas, Garrett Faucher
// 4-5-2020
// CS205 Jason Hibbeler
/*
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AdjustBoardTests {
    static Board board;
    static ArrayList<Player> players;
    static Game g;


    @BeforeClass
    public static void setUp() {

        // create ArrayList of players
        players = new ArrayList<Player>(Arrays.asList(new Player(1,"human"),new Player(2, "human")));


    }

    @After
    public void tearDown() {
        // ??????
    }

    @Test
    public void verticalTopToBottom() {
        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(3,5),g.getSquare(5,5));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(3,5).getUser());
        Assert.assertEquals(1, board.getSquare(4,5).getUser());
        Assert.assertEquals(1, board.getSquare(5,5).getUser());
    }

    @Test
    public void verticalBottomToTop() {

        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(5,5),g.getSquare(3,5));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(5,5).getUser());
        Assert.assertEquals(1, board.getSquare(4,5).getUser());
        Assert.assertEquals(1, board.getSquare(3,5).getUser());

    }

    @Test
    public void horizontalLeftToRight() {

        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(4,4),g.getSquare(4,6));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(4,4).getUser());
        Assert.assertEquals(1, board.getSquare(4,5).getUser());
        Assert.assertEquals(1, board.getSquare(4,6).getUser());

    }


    @Test
    public void horizontalRightToLeft() {
        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(4,6),g.getSquare(4,4));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(4,6).getUser());
        Assert.assertEquals(1, board.getSquare(4,5).getUser());
        Assert.assertEquals(1, board.getSquare(4,4).getUser());

    }

    @Test
    public void diagonalTopRightToBottomLeft() {
        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(1,7),g.getSquare(4,4));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(1,7).getUser());
        Assert.assertEquals(1, board.getSquare(2,6).getUser());
        Assert.assertEquals(1, board.getSquare(3,5).getUser());
        Assert.assertEquals(1, board.getSquare(4,4).getUser());
    }

    @Test
    public void diagonalBottomLeftToTopRight() {
        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(4,4),g.getSquare(1,7));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(4,4).getUser());
        Assert.assertEquals(1, board.getSquare(3,5).getUser());
        Assert.assertEquals(1, board.getSquare(2,6).getUser());
        Assert.assertEquals(1, board.getSquare(1,7).getUser());
    }

    @Test
    public void diagonalBottomRightToTopLeft() {
        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(8,8),g.getSquare(5,5));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(8,8).getUser());
        Assert.assertEquals(1, board.getSquare(7,7).getUser());
        Assert.assertEquals(1, board.getSquare(6,6).getUser());
        Assert.assertEquals(1, board.getSquare(5,5).getUser());

    }

    @Test
    public void diagonalTopLeftToBottomRight() {
        board = new Board();
        g = new Game(board, players);

        board.adjustBoard(players.get(0),g.getSquare(5,5),g.getSquare(8,8));

        System.out.print(board);

        Assert.assertEquals(1, board.getSquare(5,5).getUser());
        Assert.assertEquals(1, board.getSquare(6,6).getUser());
        Assert.assertEquals(1, board.getSquare(7,7).getUser());
        Assert.assertEquals(1, board.getSquare(8,8).getUser());
    }



}
*/
