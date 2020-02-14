package unsw.dungeon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.GameDialog.Dialog;

public class StartApplication extends Application {

    private boolean isShow = true;
    @Override
    public void start(Stage primaryStage) throws Exception {
        StartController startController = new StartController();
        startController.setStage(primaryStage);
        // startController.setShowInfo(isShow);
        primaryStage.setTitle("Home page");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        loader.setController(startController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

        if (isShow) new Dialog().gameIntr().show();
    }
    public void setShow(boolean show) {
        isShow = show;
    }

    public static void main(String[] args) {
        launch(args);
    }
}