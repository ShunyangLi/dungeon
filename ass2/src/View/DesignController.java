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
                StackPane stack = stackCopy(formatImage(image.getImages(map.getValue(i, j))));
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
    public void handleStart() {
        HeroScene game = new HeroScene(stage);
        game.start(map.getMap());
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        KeyCode code =  event.getCode();
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
            case DIGIT2:
                this.preValue = Objects.exit;
                break;
            case DIGIT3:
                this.preValue = Objects.boulder;
                break;
            case DIGIT4:
                this.preValue = Objects.door;
                break;
            case DIGIT5:
                this.preValue = Objects.treasure;
                break;
            case DIGIT6:
                this.preValue = Objects.key;
                break;
            case DIGIT7:
                this.preValue = Objects.bomb;
                break;
            case DIGIT8:
                this.preValue = Objects.pit;
                break;
            case DIGIT9:
                this.preValue = Objects.hunter;
                break;
            case DIGIT0:
                this.preValue = Objects.sword;
                break;
            default:
                    break;
        }
        event.consume();
        initialize();
    }


    public void DesignUp() {
        int y = this.position.getY();
        int x = this.position.getX();
        if (this.position.getX() > 0) {
            x -= 1;
        }
        setPosition (x, y);
    }

    public void DesignDown() {
        int y = this.position.getY();
        int x = this.position.getX();
        if (this.position.getX() < map.getHeight() - 1) {
            x += 1;
        }
        setPosition (x, y);
    }

    public void DesignLeft() {
        int y = this.position.getY();
        int x = this.position.getX();
        if (this.position.getY() > 0) {
            y -= 1;
        }
        setPosition (x, y);
    }

    public void DesignRight() {
        int y = this.position.getY();
        int x = this.position.getX();
        if (this.position.getY() < map.getWidth() - 1) {
            y += 1;
        }
        setPosition (x, y);
    }

    public void setPosition (int x, int y) {
        int obj = Objects.road;
        if (this.preValue != -1) {
            obj = this.preValue;
            this.preValue = -1;
        }
        this.position.setValue(obj);
        this.map.setupMap(this.position);
        this.position.setY(y);
        this.position.setX(x);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);
    }
}
