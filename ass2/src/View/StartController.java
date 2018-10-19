package View;

import javafx.fxml.FXML;

public class StartController extends AbstractController {

    @FXML
    public void handleGameSceneButton () {
        HeroScene scene = new HeroScene(stage);
        scene.start();
    }

    @FXML
    public void handleExitButton () {
        stage.close();
    }
}
