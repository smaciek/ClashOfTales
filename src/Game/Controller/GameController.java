package Game.Controller;

import Game.GameControllerInterface;
import Game.GameModelInterface;
import Game.GameViewInterface;
import Game.Model.Card;
import Game.Model.Cards.HeroCard;
import Game.Model.Cards.SpecialCard;
import Game.Model.Decks;
import Game.Model.GameModel;
import Game.Model.Phase;
import Game.Model.Phases.AttackPhase;
import Game.Model.Phases.BeginningPhase;
import Game.Model.Phases.EvaluationPhase;
import Game.Model.Phases.MainPhase;
import Game.View.CardView;
import Game.View.GameView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa kontorolera gry
 */

public class GameController implements GameControllerInterface.FromModel, GameControllerInterface.FromView {
    public static int MAX_HAND = 5;
    public static int MAX_ACTIVE = 5;

    private String player1Name = "Player1";
    private String player2Name = "Player2";

    private Scene gameScene;

    private GameViewInterface view;
    private GameModelInterface model;
    private GameView gameView;

    private Phase currentPhase;

    private int roundNumber = 1;
    private int cycleNumber = 0;

    public GameController() {
        createGameScene();

    }

    private void createGameScene() {
        gameView = new GameView(this);
        gameScene = gameView.getGameScene();
    }

    @Override
    public void startGame(GameViewInterface view) {
        this.view = view;
        model = new GameModel(this, player1Name, Decks.DZIADY, player2Name, Decks.PAN_TADEUSZ);

        showBoard();

        view.setScene(gameScene);


        currentPhase = new BeginningPhase(this, model.getActivePlayer(), roundNumber);
    }

    @Override
    public void changePhase() {
        if (currentPhase instanceof BeginningPhase) {
            currentPhase = new MainPhase(this, model.getActivePlayer());
            gameView.setButtonText("Przejdź do ataku");
        } else if (currentPhase instanceof MainPhase) {
            currentPhase = new AttackPhase(this, model.getActivePlayer());
            gameView.setButtonText("Zakończ atak");
        } else if (currentPhase instanceof AttackPhase) {
            currentPhase = new EvaluationPhase(this, model.getActivePlayer());
            gameView.setButtonText("Zakończ turę gracza");
        } else if (currentPhase instanceof EvaluationPhase && !model.isSomeoneDead()) {
            model.changePlayer();
            cycleNumber++;
            if (cycleNumber > 0 && cycleNumber % 2 == 0) roundNumber++;
            currentPhase = new BeginningPhase(this, model.getActivePlayer(), roundNumber);
            gameView.setButtonText("Rozpocznij turę gracza");
            showBoard();
        }


    }

    @Override
    public void setActiveCard(HeroCard card) {
        model.setActiveCard(card);
    }

    @Override
    public Card getActiveCard() {
        return model.getActiveCard();
    }

    @Override
    public void fight(HeroCard selectedCard) {
        System.out.println("FIGHT");
        ((HeroCard) model.getActiveCard()).setTapped(true);
        model.fight(selectedCard);

    }

    @Override
    public void getDamage() {
        ((HeroCard) model.getActiveCard()).setTapped(true);
        model.getOpponent().getDamage(((HeroCard) model.getActiveCard()).getStrength());
        gameView.setOpponentLife(model.getOpponent().getLife());

        showBoard();
        System.out.println("Damage");
    }

    @Override
    public void drawCard() {
        if (model.getActivePlayer().getDeck().getHand().size() < MAX_HAND) {
            model.getActivePlayer().getDeck().drawCard();
        }
        showBoard();
    }

    @Override
    public void playCard(CardView cardView) {
        if (cardView.getCardModel() instanceof SpecialCard || model.getActivePlayer().getActiveCards().size() < MAX_ACTIVE) {
            model.getActivePlayer().playCard(cardView.getCardModel(), model.getOpponent());
            gameView.setMana(model.getActivePlayer().getMana());
            showBoard();
        }
    }


    public void showBoard() {
        gameView.changePlayerActiveCards(makeListOfHeroCardViews(model.getActivePlayer().getActiveCards()));
        gameView.changeOpponentActiveCards(makeListOfHeroCardViews(model.getOpponent().getActiveCards()));
        gameView.changePlayerHand(makeListOfCardViews(model.getActivePlayer().getHand()));

        gameView.changeOpponentHand(makeListOfCardViews(model.getOpponent().getHand()));

        setOpponentLife();
        setActivePlayerLife();
    }

    @Override
    public void setMainPhase() {
        gameView.setMainPhase();
    }

    @Override
    public void setAttackPhase() {
        gameView.setAttackPhase();
    }

    @Override
    public void setBeginningPhase() {
        gameView.setBeginningPhase();
    }

    @Override
    public void setEvaluationPhase() {
        gameView.setEvaluationPhase();
    }

    @Override
    public void untap() {
        model.untapAll();
    }

    @Override
    public void setMana() {
        gameView.setMana(model.getActivePlayer().getMana());
    }

    @Override
    public void setActivePlayerLife() {
        gameView.setActivePlayerLife(model.getActivePlayer().getLife());
    }

    @Override
    public void setOpponentLife() {
        gameView.setOpponentLife(model.getOpponent().getLife());
    }

    @Override
    public void endGame() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.BOTTOM_CENTER);
        pane.setMinSize(1024, 600);
        Text text = new Text("Koniec gry!\n Wygrał gracz: " + model.getActivePlayer().getName());

        pane.add(text, 0, 0);
        setBackground(pane);

        Scene endScene = new Scene(pane);
        view.setScene(endScene);
    }

    private void setBackground(Pane pane) {
        BackgroundImage image = new BackgroundImage(new Image("/Game/View/Menu_bck.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(image));
    }

    private List<CardView> makeListOfHeroCardViews(List<HeroCard> listOfHeroCards) {
        List<CardView> out = new ArrayList<>();
        for (HeroCard card : listOfHeroCards) {
            CardView newCardView = new CardView(
                    card,
                    card.getName(),
                    Integer.toString(card.getCost()),
                    Integer.toString(card.getStrength()),
                    card.getText());
            out.add(newCardView);
        }
        return out;
    }

    private List<CardView> makeListOfCardViews(List<Card> listOfCards) {
        List<CardView> out = new ArrayList<>();
        String strength;
        for (Card card : listOfCards) {
            if (card instanceof HeroCard) strength = Integer.toString(((HeroCard) card).getStrength());
            else strength = "";
            CardView newCardView = new CardView(card, card.getName(), Integer.toString(card.getCost()), strength, card.getText());
            out.add(newCardView);
        }
        return out;
    }
}

