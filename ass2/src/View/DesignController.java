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
    private Coordinate position;
    private int preValue = -1;

    public DesignController() {
        this.map = new Map(99);
        this.player = new Player(map, map.getPosition(Objects.player));
        this.image = new GameImage();
        this.position = this.player.getPosition();
        System.out.println(map.getPosition(Objects.player));
        System.out.println(map.getValue(0,0));
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
        System.out.println(code);
        switch (code) {
            case UP:
                DesignUp();
                break;
            case DOWN:
                DesignDown();
                break;
            case LEFT:
                DesignLeft();
                break;
            case RIGHT:
                DesignRight();
                break;
            case DIGIT1:
                this.preValue = Objects.wall;
                break;
            default:
                    break;
        }
        event.consume();
        initialize();
    }


    public void DesignUp() {
        int x = this.position.getX() - 1;
        int y = this.position.getY();
        int obj = Objects.road;

        if (this.preValue != -1) {
            obj = this.preValue;
            this.preValue = -1;
        }
        this.position.setValue(obj);
        this.map.setupMap(this.position);
        this.position.setX(x);
        this.position.setY(y);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);

    }

    public void DesignDown() {
        int x = this.position.getX() + 1;
        int y = this.position.getY();
        int obj = Objects.road;

        if (this.preValue != -1) {
            obj = this.preValue;
            this.preValue = -1;
        }
        this.position.setValue(obj);

        this.map.setupMap(this.position);
        this.position.setX(x);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);
    }
    public void DesignLeft() {
        int y = this.position.getY() - 1;
        int obj = Objects.road;

        if (this.preValue != -1) {
            obj = this.preValue;
            this.preValue = -1;
        }
        this.position.setValue(obj);

        this.map.setupMap(this.position);
        this.position.setY(y);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);
    }

    public void DesignRight() {
        int y = this.position.getY() + 1;
        int obj = Objects.road;

        if (this.preValue != -1) {
            obj = this.preValue;
            this.preValue = -1;
        }
        this.position.setValue(obj);
        this.map.setupMap(this.position);
        this.position.setY(y);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);
    }

}
