package View;


import ShortestPath.Location;
import ShortestPath.Path;
import ass2.*;
import javafx.fxml.FXML;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import props.Key;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MazeController extends AbstractController {
    private GameImage image;
    private Map map;
    private MyMaze maze;
    private Player player;
    private Coordinate position;
    @FXML private Pane mazePane;
    @FXML private GridPane gridPane;


    public MazeController() {
        this.maze = new MyMaze(8);
        this.image = new GameImage();
        this.map = new Map(17, 17, this.maze.getGrid());
        this.player = new Player(map,map.getPosition(Objects.player));
        this.position = this.map.getPosition(Objects.player);
    }

    @FXML
    public void initialize() {
        gridPane = initGridPane();
        mazePane.getChildren().add(gridPane);
    }

    @FXML void handleBackButton () {
        StartScene startScene = new StartScene(stage);
        startScene.start();
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

    @FXML
    public void MazeSolution(){
        Path path = new Path();
        ArrayList<Location> road = path.path(this.map, this.map.getPosition(Objects.player), this.map.getPosition(Objects.exit));
        // because if the player is not move, then the enemy can kill the player, so need to add the player's position into the path

        if (road == null || road.size() == 0) {
            return;
        }
        // this is control to auto move
        // Thread.sleep(3000);

        for (int i  = road.size() - 1; i >= 0; i -- ) {
            int x = road.get(i).getX();
            int y = road.get(i).getY();
            this.map.setupMap(new Coordinate(x, y, Objects.player));
            this.position.setX(x);
            this.position.setY(y);
        }
        initialize();
    }


    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if (! player.isSuccess()){
            KeyCode code =  event.getCode();
            if (code == KeyCode.UP || code == KeyCode.W) {
                player.moveUp();
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                player.moveDown();
            } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                player.moveLeft();
            } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
                player.moveRight();
            } else {
                return;
            }
            event.consume();
            initialize();
        } else {
            System.out.println("Success");
        }
    }

}
