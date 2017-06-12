package Game.View;

import Game.Controller.GameController;
import Game.GameControllerInterface;
import Game.GameViewInterface;
import Game.Model.Card;
import Game.Model.Cards.HeroCard;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class MainGameView extends Application implements GameViewInterface {

    private double width = 1024;
    private double height = 600;

    private GameControllerInterface.FromView controller;

    private Button start;
    private Button end;
    private Text title;
    private Stage mainStage;


    private Text gameText;

    private Scene startScene;
    private Scene gameScene;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void init() throws Exception {
        initGame();
        initializeView();
        initializeGameView();
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        primaryStage.setTitle("Clash of Tales");



        this.startScene = new Scene(makeStartGridPane(), width, height);

        primaryStage.setScene(startScene);

        primaryStage.show();
    }

    @Override
    public void showActiveCards(List<CardView> player1ActiveCards, List<CardView> player2ActiveCards) {

    }



    private void initGame(){
        controller = new GameController();
        //controller.startGame(this);

    }

    private void initializeView(){
        start = new Button("Start");
        start.setOnAction(event -> {
            if(event.getSource()==start){
                //mainStage.setScene(gameScene);
                controller.startGame(this);
            }});
        start.setMinWidth(130);
        end = new Button("Wyjdź");
        end.setMinWidth(130);
        title = new Text("Clash of Tales");
    }

    private void initializeGameView(){
        GameView gameView = new GameView();
        gameScene = gameView.getGameScene();
    }

    @Override
    public void setScene(Scene scene) {
        mainStage.setScene(scene);
    }

    @Override
    public void showText(String text) {

    }

    //    private GridPane makeGamePane(){
//        GridPane gridPane = new GridPane();
//        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//
//        gameText = new Text("Game Scene");
//        gridPane.add(gameText, 0, 0);
//
//        return gridPane;
//    }

    private GridPane makeStartGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        gridPane.add(title, 0, 0);
        gridPane.add(start,0,1);
        gridPane.add(end,0,2);

        //gridPane.setBackground(new Background(new BackgroundImage(new Image("/Game/View/Template_czar.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        return gridPane;
    }
}
