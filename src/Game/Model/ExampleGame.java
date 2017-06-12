package Game.Model;


import Game.Model.Data.PTDeck;

public class ExampleGame {
    private String player1Name = "p1";
    private String player2Name = "p2";

    private Player player1;
    private Player player2;


    private DataProvider data;
    private Board board;

    public ExampleGame(){
        data = new PTDeck();
        this.player1 = new Player("Player 1", data.getDeck());
        this.player2 = new Player("Player 2", data.getDeck());
        board = new Board(player1, player2);
    }

    public void startGame(){
//        board.showBoard();

    }
}
