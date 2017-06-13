package Game;

import Game.Model.Card;
import Game.Model.Cards.HeroCard;
import Game.Model.Deck;
import Game.Model.Player;

public interface GameModelInterface {
    Player getPlayer1();

    Player getPlayer2();

    Player getActivePlayer();

    Player getOpponent();

    Deck getPlayer1Deck();

    Deck getPlayer2Deck();

    boolean isSomeoneDead();

    //    void startBeginningPhase(int roundNumber);
//    void startMainPhase(int roundNumber);
//    void startAttackPhase(int roundNumber);
    void changePlayer();

    void setActiveCard(HeroCard card);

    Card getActiveCard();

    void fight(HeroCard selectedCard);

    void untapAll();
}
