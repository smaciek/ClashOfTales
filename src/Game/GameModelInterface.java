package Game;

import Game.Model.Card;
import Game.Model.Cards.HeroCard;
import Game.Model.Deck;
import Game.Model.Player;

/**
 * Interfejs modelu gry
 */
public interface GameModelInterface {
    Player getActivePlayer();

    Player getOpponent();


    boolean isSomeoneDead();

    void changePlayer();

    void setActiveCard(HeroCard card);

    Card getActiveCard();

    void fight(HeroCard selectedCard);

    void untapAll();
}
