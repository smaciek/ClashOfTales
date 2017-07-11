package Game.View;


import Game.GameControllerInterface;
import Game.Model.ActivePhases;
import Game.Model.Cards.HeroCard;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Klasa reprezentująca widok w trakcie gry.
 */

public class GameView {
    private int width = 1024;
    private int height = 600;

    private double minCardHeight = 190;
    private double cardWidth = 135;

    private GameControllerInterface.FromView controller;

    private Scene gameScene;
    private HBox hand;
    private HBox playerActiveCards;
    private HBox opponentActiveCards;


    private VBox opponentHandList;


    private Button drawCard;
    private Button nextPhase;

    private Text text = new Text();

    private Text opponentLife;
    private Text activePlayerLife;
    private Text activePlayerMana;

    private ActivePhases activePhase = ActivePhases.BEGINING;


    public GameView(GameControllerInterface.FromView controller) {

        BorderPane borderPane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();

        this.gameScene = new Scene(anchorPane, width, height);
        this.hand = new HBox();

        this.playerActiveCards = new HBox();
        this.opponentActiveCards = new HBox();


        this.opponentHandList = new VBox();

        borderPane.setTop(opponentActiveCards);
        borderPane.setCenter(playerActiveCards);
        borderPane.setBottom(hand);

        borderPane.setLeft(text);

        setMinHeight();
        setPaddings();

        this.controller = controller;
        this.drawCard = new Button("Draw Card");
        this.nextPhase = new Button("Rozpocznij turę gracza");
        addDrawCardButton(borderPane);
        addChangePhaseButton(borderPane);
        setBackground(borderPane);
        setBackground(anchorPane);


        setAnchorPane(opponentActiveCards, 10, -1, 5, -1);
        setAnchorPane(playerActiveCards, 205, -1, 5, -1);
        setAnchorPane(hand, 400, -1, 140, -1);

        setAnchorPane(nextPhase, -1, 100, -1, 20);
        setAnchorPane(drawCard, -1, 80, -1, 20);


        anchorPane.getChildren().add(hand);
        anchorPane.getChildren().add(opponentActiveCards);
        anchorPane.getChildren().add(playerActiveCards);
        anchorPane.getChildren().add(nextPhase);
        anchorPane.getChildren().add(drawCard);


        preareOpponentHand();
        setOpponentHandVisible(true);

        anchorPane.getChildren().add(opponentHandList);


        Pane opponentIcon = createOpponentIcon();
        setAnchorPane(opponentIcon, 10, -1, -1, 20);
        anchorPane.getChildren().add(opponentIcon);

        initializeTexts(anchorPane);
    }

    private void setOpponentHandVisible(boolean isVisible) {
        if (isVisible) {
            opponentHandList.setStyle("-fx-background-color: WHITE");
        }
    }

    private void preareOpponentHand() {
        setAnchorPane(opponentHandList, 20, -1, -1, cardWidth + 10);
    }

    private void initializeTexts(AnchorPane anchorPane) {
        opponentLife = new Text("Życie");
        activePlayerLife = new Text("Życie");
        activePlayerMana = new Text("Mana");

        setAnchorPane(opponentLife, 125, -1, -1, 34);
        setAnchorPane(activePlayerLife, -1, 120, 34, -1);
        setAnchorPane(activePlayerMana, -1, 50, 34, -1);

        anchorPane.getChildren().add(opponentLife);
        anchorPane.getChildren().add(activePlayerLife);
        anchorPane.getChildren().add(activePlayerMana);
    }

    public void setMana(int mana) {
        activePlayerMana.setText(Integer.toString(mana));
    }


    public void setActivePlayerLife(int life) {
        activePlayerLife.setText(Integer.toString(life));
    }

    public void setOpponentLife(int life) {
        opponentLife.setText(Integer.toString(life));
    }

    private Pane createOpponentIcon() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(94, 94);

        BackgroundImage image = new BackgroundImage(new Image("/Game/View/player.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        gridPane.setBackground(new Background(image));

        gridPane.setOnMouseClicked(event -> {
            if (activePhase.equals(ActivePhases.ATTACK) && controller.getActiveCard() != null && !((HeroCard) controller.getActiveCard()).isTapped()) {
                controller.getDamage();
            }
        });
        return gridPane;
    }

    private void setAnchorPane(Node node, double top, double bottom, double left, double right) {
        if (top > 0) AnchorPane.setTopAnchor(node, top);
        if (bottom > 0) AnchorPane.setBottomAnchor(node, bottom);
        if (left > 0) AnchorPane.setLeftAnchor(node, left);
        if (right > 0) AnchorPane.setRightAnchor(node, right);
    }


    public void showText(String text) {
        this.text.setText(text);
    }

    public void setMainPhase() {
        activePhase = ActivePhases.MAIN;
    }

    public void setAttackPhase() {
        activePhase = ActivePhases.ATTACK;
    }

    public void setBeginningPhase() {
        activePhase = ActivePhases.BEGINING;
    }

    public void setEvaluationPhase() {
        activePhase = ActivePhases.EVALUATION;
    }

    private void setMinHeight() {
        hand.setMinHeight(minCardHeight);
        opponentActiveCards.setMinHeight(minCardHeight);
        playerActiveCards.setMinHeight(minCardHeight);

    }

    private void setBackground(Pane pane) {
        BackgroundImage image = new BackgroundImage(new Image("/Game/View/Bck.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(image));
    }

    private void setPaddings() {
        int spacing = 5;//bylo 5
        int sidePadding = 20;
        hand.setPadding(new Insets(1, sidePadding, 1, sidePadding));
        hand.setSpacing(spacing);
        playerActiveCards.setPadding(new Insets(1, sidePadding, 1, sidePadding));
        playerActiveCards.setSpacing(spacing);
        opponentActiveCards.setPadding(new Insets(1, sidePadding, 1, sidePadding));
        opponentActiveCards.setSpacing(spacing);

        opponentHandList.setPadding(new Insets(5, 5, 5, 5));
        opponentHandList.setSpacing(spacing);
    }


    /*TODO
    Usunąć przycisk ciągnięcia karty
     */
    private void addDrawCardButton(BorderPane borderPane) {
        this.drawCard.setOnAction(e -> {
            controller.drawCard();
        });
        borderPane.setRight(drawCard);
    }

    private void addChangePhaseButton(BorderPane borderPane) {
        this.nextPhase.setOnAction(event -> {
            controller.changePhase();
        });
        borderPane.setLeft(nextPhase);
    }

    public void changeOpponentActiveCards(List<CardView> activeCards) {
        opponentActiveCards.getChildren().clear();
        for (CardView cardView : activeCards) {
            opponentActiveCards.getChildren().add(cardView.getCard());
            cardView.getCard().setOnMouseClicked(event -> {
                if (activePhase.equals(ActivePhases.ATTACK) && controller.getActiveCard() != null) {
                    controller.fight((HeroCard) cardView.getCardModel());
                }
            });
        }
    }

    public void changePlayerActiveCards(List<CardView> activeCards) {
        playerActiveCards.getChildren().clear();
        for (CardView cardView : activeCards) {

            playerActiveCards.getChildren().add(cardView.getCard());
            cardView.getCard().setOnMouseClicked(event -> {
                if (activePhase.equals(ActivePhases.ATTACK) && !((HeroCard) cardView.getCardModel()).isTapped()) {
                    controller.setActiveCard((HeroCard) cardView.getCardModel());
                }
            });
        }
    }


    public void changePlayerHand(List<CardView> newHand) {
        hand.getChildren().clear();
        for (CardView cardView : newHand) {

            hand.getChildren().add(cardView.getCard());
            cardView.getCard().setOnMouseClicked(event -> {
                if (activePhase.equals(ActivePhases.MAIN)) {
                    controller.playCard(cardView);
                }
            });
        }
    }


    public void changeOpponentHand(List<CardView> newHand) {
        opponentHandList.getChildren().clear();
        Text text = new Text("Ręka przeciwnika:");
        text.setUnderline(true);
        opponentHandList.getChildren().add(text);

        for (CardView cardView : newHand) {
            Text cardName = new Text(cardView.getCardModel().getName());
            opponentHandList.getChildren().add(cardName);
        }

    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void setButtonText(String s) {
        this.nextPhase.setText(s);

    }
}
