package Game.Model;


/**
 * Interfejs funkcyjny zawierający spcjalną umiejętnośc kart
 */

public interface Special {
    void activate(Card currentCard, Player currentPlayer, Player opponent);
}
