package View;

import ass2.GameImage;
import ass2.Map;
import ass2.MapDatas;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DesignController extends AbstractController {
    @FXML private Pane mazePane;
    @FXML private GridPane gridPane;
    private GameImage image;
    private Map map;

    public DesignController() {
        this.map = new Map(99);
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


}
