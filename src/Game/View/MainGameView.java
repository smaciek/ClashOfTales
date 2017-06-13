package Game.View;

import Game.Controller.GameController;
import Game.GameControllerInterface;
import Game.GameViewInterface;
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


/**
 * Klasa główna, reprezentująca widok menu po uruchomieniu gry.
 */
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


    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void init() throws Exception {
        initGame();
        initializeView();
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

    public void setStartScene() {
        mainStage.setScene(startScene);

    }


    private void setBackground(Pane pane) {
        BackgroundImage image = new BackgroundImage(new Image("/Game/View/Menu_bck.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(image));
    }

    private void initGame() {
        controller = new GameController();

    }

    private void initializeView() {
        start = new Button("Start");
        start.setOnAction(event -> {
            if (event.getSource() == start) {
                controller.startGame(this);
            }
        });
        start.setMinWidth(130);
        end = new Button("Wyjdź");
        end.setOnAction(event -> {
            mainStage.close();

        });
        end.setMinWidth(130);
    }


    private GridPane makeStartGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));


        gridPane.add(start, 0, 1);
        gridPane.add(end, 0, 2);

        setBackground(gridPane);
        return gridPane;
    }

    @Override
    public void setScene(Scene scene) {
        mainStage.setScene(scene);
    }
}
