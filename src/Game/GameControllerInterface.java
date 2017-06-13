package Game;

import Game.Model.Card;
import Game.Model.Cards.HeroCard;
import Game.View.CardView;

public interface GameControllerInterface {
    interface FromView{
        void startGame(GameViewInterface view);
        void drawCard();

        void playCard(CardView cardView);
        void changePhase();

        void setActiveCard(HeroCard card);
        Card getActiveCard();

        void fight(HeroCard selectedCard);

        void getDamage();
    }
    interface FromModel{
        void changePhase();
        void showBoard();

        void setMainPhase();
        void setAttackPhase();
        void setBeginningPhase();
        void setEvaluationPhase();

        void untap();

        void setMana();

        void setActivePlayerLife();
        void setOpponentLife();

        void endGame();

    }
}
