package Game.Controller;

import Game.GameControllerInterface;
import Game.GameViewInterface;
import Game.Model.*;
import Game.Model.Cards.HeroCard;
import Game.Model.Cards.SpecialCard;
import Game.GameModelInterface;
import Game.Model.Phases.AttackPhase;
import Game.Model.Phases.BeginningPhase;
import Game.Model.Phases.EvaluationPhase;
import Game.Model.Phases.MainPhase;
import Game.View.CardView;
import Game.View.GameView;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;


public class GameController implements GameControllerInterface.FromModel, GameControllerInterface.FromView {
    public static int MAX_HAND = 5;
    public static int MAX_ACTIVE = 5;

    private Game game;
    private String player1Name = "Player1";
    private String player2Name = "Player2";

    private Scene gameScene;

    private GameViewInterface view;
    private GameModelInterface model;
    private GameView gameView;

    private Player currentPlayer;
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

//            exampleOppnnteCards();

//        model.getPlayer1().getDeck().drawCard();
//        model.getPlayer1().getDeck().drawCard();
//        model.getPlayer1().getDeck().drawCard();


        showBoard();

        view.setScene(gameScene);
//        view.showActiveCards(
//                makeListOfHeroCardViews(model.getPlayer1Deck().getActiveHeroes()),
//                makeListOfHeroCardViews(model.getPlayer2Deck().getActiveHeroes()));

//        while (model.isSomeoneDead()){
//            model.changePlayer();
//            model.startBeginningPhase(roundNumber);
//            model.startMainPhase(roundNumber);
//            model.startAttackPhase(roundNumber);
//            cycleNumber++;
//            if (cycleNumber>0 && cycleNumber%2 ==0)roundNumber++;
//
//            System.out.print(roundNumber);
//        }


        currentPhase = new BeginningPhase(this, model.getActivePlayer(), roundNumber);
    }

    @Override
    public void changePhase() {
//        currentPhase = phase;
//        currentPhase.startPhase();

        if (currentPhase instanceof BeginningPhase) currentPhase = new MainPhase(this, model.getActivePlayer());
        else if (currentPhase instanceof MainPhase) currentPhase = new AttackPhase(this, model.getActivePlayer());
        else if (currentPhase instanceof AttackPhase) currentPhase = new EvaluationPhase(this, model.getActivePlayer());
        else if (currentPhase instanceof EvaluationPhase && !model.isSomeoneDead()) {
            model.changePlayer();
            cycleNumber++;
            if (cycleNumber > 0 && cycleNumber % 2 == 0) roundNumber++;
            currentPhase = new BeginningPhase(this, model.getActivePlayer(), roundNumber);
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
        model.fight(selectedCard);
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
            showBoard();
        }
    }

    private void exampleOppnnteCards() {
        model.getOpponent().getDeck().drawCard();
        model.getOpponent().getDeck().drawCard();
        model.getOpponent().getDeck().drawCard();
        model.getOpponent().playCard(model.getOpponent().getHand().remove(0), model.getActivePlayer());
        model.getOpponent().playCard(model.getOpponent().getHand().remove(1), model.getActivePlayer());
        model.getOpponent().playCard(model.getOpponent().getHand().remove(2), model.getActivePlayer());
        gameView.changeOpponentActiveCards(makeListOfHeroCardViews(model.getOpponent().getActiveCards()));
    }

    public void showBoard() {
        gameView.changePlayerActiveCards(makeListOfHeroCardViews(model.getActivePlayer().getActiveCards()));
        gameView.changeOpponentActiveCards(makeListOfHeroCardViews(model.getOpponent().getActiveCards()));
        gameView.changePlayerHand(makeListOfCardViews(model.getActivePlayer().getHand()));
    }

    @Override
    public void setMainPhase() {
        gameView.setMainPhase();
    }

    @Override
    public void setAttackPhase() {
        gameView.setAttackPhase();
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

