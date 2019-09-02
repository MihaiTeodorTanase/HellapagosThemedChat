package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(final Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hellapagos Game");
        OverallController.loadMainMenu(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
