package Game.Model;

public class Board {
    private Player player1;
    private Player player2;
    private Card activeCard;

    private int roundNumber;

    private Player activePlayer;
    private Player opponent;

    public Board(Player player1, Player player2){


        this.player1 = player1;
        this.player2 = player2;

        activePlayer = player1;
        opponent = player2;

    }

    public void changePlayer(){
        if (activePlayer.equals(player1)){
            activePlayer = player2;
            opponent = player1;
        }else {
            activePlayer = player1;
            opponent = player2;
        }
    }


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Card getActiveCard() {
        return activeCard;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getOpponent() {
        return opponent;
    }
}
