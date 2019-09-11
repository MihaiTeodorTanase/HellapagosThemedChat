package game;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hellapagos Game");
        OverallController.playMusic();
        OverallController.loadMainMenu(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
