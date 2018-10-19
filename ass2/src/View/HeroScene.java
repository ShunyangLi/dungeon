package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HeroScene extends AbstractScene {
    public HeroScene(Stage stage) {
        super(stage, "Hero", "game.fxml");
    }
}
