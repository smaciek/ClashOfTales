package Game;

import Game.Model.Cards.HeroCard;
import Game.View.CardView;
import javafx.scene.Scene;

import java.util.List;

public interface GameViewInterface {
    void showActiveCards(List<CardView> player1ActiveCards, List<CardView> player2ActiveCards);

    void setScene(Scene scene);
    void showText(String text);
}
