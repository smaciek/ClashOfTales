package Game.View;


import Game.Model.Card;
import Game.Model.Cards.HeroCard;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * Klasa reprezentująca graficzną reprezentację karty
 */
public class CardView {
    private BackgroundImage backgroundImage;
    private Card cardModel;
    private Text name;
    private Text strength;
    private Text cost;
    private Text text;
    private Text type;
    private AnchorPane card;

    public CardView(String name, String cost, String strength, String text) {
        this.name = new Text(name);
        this.cost = new Text(cost);
        this.strength = new Text(strength);
        this.text = new Text(text);
        createCard();
        setSize();
        setPaddings();
    }

    public CardView(Card card, String name, String cost, String strength, String text) {
        this.name = new Text(name);
        this.cost = new Text(cost);
        this.strength = new Text(strength);
        this.text = new Text(text);
        this.text.setWrappingWidth(120);
        this.cardModel = card;

        if (card instanceof HeroCard) {
            backgroundImage = new BackgroundImage(new Image("/Game/View/Template_stwor.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            this.type = new Text("C");

            if (((HeroCard) card).isTapped()) {
                this.strength.setUnderline(true);
                this.strength.setFill(Color.RED);
                this.name.setUnderline(true);
            } else {
                this.strength.setUnderline(false);
                this.name.setUnderline(false);
                this.strength.setFill(Color.BLACK);

            }
        } else {
            backgroundImage = new BackgroundImage(new Image("/Game/View/Template_czar.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            this.type = new Text("S");
        }

        createCard();
        setSize();
        setPaddings();
    }

    public Pane getCard() {
        return card;
    }

    private void setSize() {
        card.setMinSize(135, 190);
        card.setPrefSize(135, 190);
        card.setMaxSize(135, 190);
    }

    private void setPaddings() {
        card.setPadding(new Insets(0, 0, 0, 0));

    }

//    private void createCard(){
//        card = new GridPane();
//        card.add(name, 0, 0);
//        card.add(strength, 2, 0);
//        card.add(text, 0,1);
//        card.setStyle("-fx-background-color: #FFFFFF");
//    }

    private void createCard() {
        card = new AnchorPane();
//        HBox hbox = new HBox(name, strength);
//
//        ColumnConstraints  c1 = new ColumnConstraints();
//        c1.setPercentWidth(70);
//        ColumnConstraints  c2 = new ColumnConstraints();
//        c1.setPercentWidth(30);
//
//
//        card.getColumnConstraints().add(c1);
//        card.getColumnConstraints().add(c2);
//
//        card.add(name, 0, 0);
//        card.add(strength, 1, 0);
//
//        card.add(text, 0,1);
//        card.add(cost, 0, 2);
//        if (cardModel instanceof HeroCard) card.add();
//        card.add();
//
//        card.setTop(hbox);
//        card.setCenter(text);
//        card.setBottom(cost);
////        card.setStyle(
////                "-fx-background-color: greenyellow;"//+
////                //"-fx-border-width:5;"+
////                //"-fx_boder-color: black;"
////        );
//        card.setBackground(new Background(backgroundImage));
//

        AnchorPane.setTopAnchor(name, 5.0);
        AnchorPane.setLeftAnchor(name, 10.0);

        AnchorPane.setTopAnchor(strength, 9.0);
        AnchorPane.setRightAnchor(strength, 10.0);

        AnchorPane.setTopAnchor(text, 80.0);
        AnchorPane.setLeftAnchor(text, 5.0);

        AnchorPane.setLeftAnchor(cost, 45.0);
        AnchorPane.setBottomAnchor(cost, 4.0);

        AnchorPane.setRightAnchor(type, 10.0);
        AnchorPane.setBottomAnchor(type, 4.0);

        card.getChildren().add(name);
        card.getChildren().add(strength);
        card.getChildren().add(text);
        card.getChildren().add(cost);
        card.getChildren().add(type);
        card.setBackground(new Background(backgroundImage));

    }

    public Card getCardModel() {
        return cardModel;
    }
}
