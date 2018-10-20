package View;

import ass2.*;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DesignController extends AbstractController {
    @FXML private Pane mazePane;
    @FXML private GridPane gridPane;
    private GameImage image;
    private Map map;
    private Player player;

    public DesignController() {
        this.map = new Map(99);
        this.player = new Player(map, map.getPosition(Objects.player));
    }

    @FXML
    public void initialize() {
        gridPane = null;
        gridPane = initGridPane();
        mazePane.getChildren().add(gridPane);
    }

    private GridPane initGridPane() {
        GridPane grid = new GridPane();
        // grid.setGridLinesVisible(true);
        for (int i = 0; i < this.map.getHeight(); i++) {
            for (int j = 0; j < this.map.getWidth(); j++) {
                StackPane stack = stackCopy(imageCopy(image.getImages(map.getValue(i, j))));
                grid.add(stack,j ,i);
            }
        }
        grid.autosize();
        return grid;
    }

    @FXML
    public void handleBackButton () {
        StartScene startScene = new StartScene(stage);
        startScene.start();
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {

        KeyCode code =  event.getCode();

        if (code == KeyCode.UP) {
            player.moveUp();
        } else if (code == KeyCode.DOWN) {
            player.moveDown();
        } else if (code == KeyCode.LEFT ) {
            player.moveLeft();
        } else if (code == KeyCode.RIGHT ) {
            player.moveRight();
        } else {
            return;
        }
        event.consume();
        initialize();
    }

}
