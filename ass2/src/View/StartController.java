package View;

import javafx.fxml.FXML;

public class StartController extends AbstractController {

    @FXML
    public void handleGameSceneButton () {
        HeroScene scene = new HeroScene(stage);
        scene.start(2);
    }

    @FXML
    public void handleMazeSceneButton () {
        MazeScene scene = new MazeScene(stage);
        scene.start();
    }

    @FXML
    public void handleDesignSceneButton () {
    }

    @FXML
    public void handleExitButton () {
        stage.close();
    }
}
