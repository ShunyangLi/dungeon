package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HeroScene {

    protected Stage stage;
    private String title;
    private FXMLLoader fxmlLoader;

    public HeroScene(Stage stage) {
        this.stage = stage;
        this.title = "Hero";
        this.fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
    }

    public void start(int index) {
        stage.setTitle(title);
        try {
            Parent root = fxmlLoader.load();
            GameController controller = fxmlLoader.getController();
            controller.setStage(stage);
            // controller.setGame(index);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
