package View;

import ass2.*;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import props.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends AbstractController {

    private GameImage image;
    private Map map;
    private Player player;
    private Coordinate position;
    private Sword sword;
    private Arrow arrow;
    private Bomb bomb;
    private Key key;
    private Bag bag;
    private Treasure treasure;
    @FXML private Pane mazePane;
    @FXML private GridPane gridPane;

    public GameController() {
        this.image = new GameImage();
        this.map = new Map(16,18);
        this.sword = new Sword(map);
        this.key = new Key(map);
        this.bomb = new Bomb(map);
        this.arrow = new Arrow(map);
        this.treasure = new Treasure(map);
        this.bag = new Bag(sword,arrow,bomb,treasure,key);
        this.player = new Player(map,bag,map.getPosition(Objects.player));
    }

    @FXML
    public void initialize() {
        gridPane = initGridPane();
//        gridPane.getChildren().add(image.getImages(map.getValue(0,0)));
        mazePane.getChildren().add(gridPane);
        // mazePane.addEventHandler(keyEvent);
//        mazePane.getChildren().remove(gridPane);
    }


    private GridPane initGridPane() {
        GridPane grid = new GridPane();
        // grid.setGridLinesVisible(true);
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                StackPane stack = stackCopy(imageCopy(image.getImages(map.getValue(i, j))));
                grid.add(stack,j ,i);
            }
        }
        grid.autosize();
        return grid;
    }

    private ImageView imageCopy(ImageView srcImage) {
        ImageView image = new ImageView(srcImage.getImage());
        image.setFitWidth(42);
        image.setFitHeight(42);
        return image;
    }

    private StackPane stackCopy(ImageView image) {
        StackPane stack = new StackPane();
        stack.setMaxSize(42, 42);
        stack.setMinSize(42, 42);
        stack.getChildren().add(image);
        makeDroppable(stack);
        return stack;
    }

    private void makeDroppable(StackPane stack) {
        stack.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            }
        });
        stack.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasImage()) {
                    stack.getChildren().add(imageCopy(new ImageView(db.getImage())));
                }
                event.consume();
            }
        });
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        // System.out.println(event.getCharacter());

        switch (event.getCode()) {
            case UP:
                player.moveUp();
                initialize();
                break;
            case DOWN:
                player.moveDown();
                initialize();
                break;
            case LEFT:
                player.moveLeft();
                initialize();
                break;
            case RIGHT:
                player.moveRight();
                initialize();
                break;
            default:
                break;
        }
        event.consume();
    }
}
