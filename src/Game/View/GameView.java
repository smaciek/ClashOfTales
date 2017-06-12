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

public class GameView {
    private int width = 1024;
    private int height = 600;

    private double minCardHeight = 190;

    private GameControllerInterface.FromView controller;

    private Scene gameScene;
    private HBox hand;
    //    private FlowPane playerActiveCards;
    private HBox playerActiveCards;
    private HBox opponentActiveCards;

    private Button drawCard;
    private Button nextPhase;

    private Text text = new Text();

    private ActivePhases activePhase = ActivePhases.BEGINING;

    //    public GameView() {
//        GridPane gridPane = new GridPane();
//
//        this.gameScene = new Scene(gridPane, width, height);
//        this.hand = new HBox();
//        this.playerActiveCards = new FlowPane();
//        this.opponentActiveCards = new HBox();
//        gridPane.add(opponentActiveCards, 0, 0);
//        gridPane.add(playerActiveCards, 0, 1);
//        gridPane.add(hand, 0, 2);
//        setPaddings();
//        addExampleData();
//    }
    public GameView() {
        BorderPane borderPane = new BorderPane();

        this.gameScene = new Scene(borderPane, width, height);
        this.hand = new HBox();
//        this.playerActiveCards = new FlowPane();
        this.playerActiveCards = new HBox();
        this.opponentActiveCards = new HBox();

        borderPane.setTop(opponentActiveCards);
        borderPane.setCenter(playerActiveCards);
        borderPane.setBottom(hand);


        setPaddings();
//        addExampleData();

        this.drawCard = new Button("Draw Card");
        this.nextPhase = new Button("Dalej");
    }

    public GameView(GameControllerInterface.FromView controller) {
        BorderPane borderPane = new BorderPane();

        this.gameScene = new Scene(borderPane, width, height);
        this.hand = new HBox();
//        this.playerActiveCards = new FlowPane();
        this.playerActiveCards = new HBox();
        this.opponentActiveCards = new HBox();

        borderPane.setTop(opponentActiveCards);
        borderPane.setCenter(playerActiveCards);
        borderPane.setBottom(hand);

        borderPane.setLeft(text);

        setMinHeight();
        setPaddings();
//        addExampleData();
        this.controller = controller;
        this.drawCard = new Button("Draw Card");
        this.nextPhase = new Button("Dalej");
        addDrawCardButton(borderPane);
        addChangePhaseButton(borderPane);
        setBackground(borderPane);
    }

    public void showText(String text) {
        this.text.setText(text);
    }

    public void setMainPhase(){
        activePhase = ActivePhases.MAIN;
    }
    public void setAttackPhase(){
        activePhase = ActivePhases.ATTACK;
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
        hand.setPadding(new Insets(1, 20, 1, 20));
        hand.setSpacing(5);
        playerActiveCards.setPadding(new Insets(1, 20, 1, 20));
        playerActiveCards.setSpacing(5);
        opponentActiveCards.setPadding(new Insets(1, 20, 1, 20));
        opponentActiveCards.setSpacing(5);
    }

    private void addExampleData() {
        CardView view1 = new CardView("Name11", "1", "Strength11", "Text11");
        CardView view12 = new CardView("Name12", "7", "Strength12", "Text12");
        CardView view21 = new CardView("Name21", "3", "Strength21", "Text21");
        CardView view22 = new CardView("Name22", "6", "Strength22", "Text22");
        CardView view23 = new CardView("Name23", "0", "Strength23", "Text23");
        CardView view31 = new CardView("Name31", "9", "Strength31", "Text31");
        CardView view32 = new CardView("Name32", "4", "Strength32", "Text32");

        hand.getChildren().add(view1.getCard());
        hand.getChildren().add(view12.getCard());
        playerActiveCards.getChildren().add(view21.getCard());
        playerActiveCards.getChildren().add(view22.getCard());
        playerActiveCards.getChildren().add(view23.getCard());
        opponentActiveCards.getChildren().add(view31.getCard());
        opponentActiveCards.getChildren().add(view32.getCard());
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
                if (activePhase.equals(ActivePhases.ATTACK) && controller.getActiveCard() != null){
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
                if (activePhase.equals(ActivePhases.ATTACK)){
                    controller.setActiveCard((HeroCard) cardView.getCardModel());
                }
            });
        }
    }

    /*TODO TU JEST ZAGRYWANIE KARTAMI
     *
     */

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

    public Scene getGameScene() {
        return gameScene;
    }
}
