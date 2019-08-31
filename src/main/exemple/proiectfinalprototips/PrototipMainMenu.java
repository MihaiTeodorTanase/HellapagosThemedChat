package proiectfinalprototips;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class PrototipMainMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hellapagos Game");

        Group root = new Group();
        Scene mainMenuScene = new Scene(root);
        primaryStage.setScene(mainMenuScene);
        Canvas canvas = new Canvas(1280,720);
        root.getChildren().add(canvas);

        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        final Image background = new Image("hellapagosbackground.png");
        final Image startbutton= new Image("hellapagosbackground.png");
        final Image optionsbutton= new Image("hellapagosbackground.png");
        graphicsContext.drawImage(background,0,0);

        primaryStage.show();
    }

}
