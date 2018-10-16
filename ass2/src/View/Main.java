package View;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setHeight(700);
        // set the stage width to be 600
        primaryStage.setWidth(900);

        HeroScene heroScene = new HeroScene(primaryStage);
        heroScene.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}