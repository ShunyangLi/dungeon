package View;

import Enemy.*;
import ShortestPath.Location;
import ass2.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import props.*;

import javax.sound.midi.Track;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController extends AbstractController {

    private GameImage image;
    private Map map;
    private Player player;
    private Hunter hunter;
    private Coward coward;
    private Hound hound;
    private Strategist strategist;
    static Timer timer;
    private int currLevel = 0;
    @FXML private Label swordnum;
    @FXML private Label arrownum;
    @FXML private Label bombnum;
    @FXML private Label godnum;
    @FXML private Label keynum;
    @FXML private Label b1;
    @FXML private Label g1;

    @FXML private Pane mazePane;
    @FXML private GridPane gridPane;

    public GameController() {
        this.image = new GameImage();
        this.setGame(this.currLevel);
        updateObj();
    }

    @FXML
    public void initialize() {
        gridPane = null;
        gridPane = initGridPane();
        mazePane.getChildren().add(gridPane);
        updateBag();
    }


    public void setGame (int index) {
        this.map = new Map(index);
    }

    public void updateBag() {
        Sword mapsword = (Sword) this.player.getBag().get(Objects.sword);
        swordnum.setText("Usage: " + mapsword.getUseable() + "/5");
        arrownum.setText("Num: " + this.player.getBag().get(Objects.arrow).getNum());
        bombnum.setText("Num: " + this.player.getBag().get(Objects.bomb).getNum());
        godnum.setText("Num: " + this.player.getBag().get(Objects.treasure).getNum());
        keynum.setText("Num: " + this.player.getBag().get(Objects.key).getNum());
        b1.setText("Sate: " + this.player.getBag().get(Objects.invincibility).isBuff());
        g1.setText("Sate: " + this.player.getBag().get(Objects.hover).isBuff());

    }

    public void updateObj() {
        this.player = new Player(map,map.getPosition(Objects.player));
        this.hunter = new Hunter(map.getPosition(Objects.hunter),map,true);
        this.hunter.setMove(new TrackPlayer(this.hunter));
        this.coward = new Coward(map.getPosition(Objects.coward),map,true);
        this.coward.setMove(new TrackPlayer(this.coward));
        this.strategist = new Strategist(map.getPosition(Objects.strategist), map, true);
        this.hound = new Hound(map.getPosition(Objects.hound),map,true);
        this.hound.setMove(new TrackPlayer(this.hound));
    }


    @FXML
    public void handleBackButton () {
        StartScene startScene = new StartScene(stage);
        startScene.start();
    }

    @FXML
    public void handleNext() {
        mazePane.getChildren().remove(gridPane);
        setGame(++ this.currLevel);
        updateObj();
        initialize();
    }
    @FXML
    public void handleRestartButton () {
        mazePane.getChildren().remove(gridPane);
        setGame(this.currLevel);
        updateObj();
        initialize();
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
    public void handleKeyPressed(KeyEvent event) {
        if (player.getAlive() && ! player.isSuccess()) {
            checkDie();
            switch (event.getCode()) {
                case UP:
                    player.moveUp();
                    break;
                case DOWN:
                    player.moveDown();
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case RIGHT:
                    player.moveRight();
                    break;
                case L:
                    player.lightBomb();
                default:
                    break;
            }
            Bomb mapBomb = (Bomb) player.getBag().get(Objects.bomb);

            if (mapBomb.isLight()) {
                Coordinate b = map.getPosition(Objects.bomb);
                if (b != null) {
                    b.setValue(Objects.fire);
                    this.map.setupMap(b);
                    timer = new Timer();
                    MyTimer(mapBomb);
                }
            }

            event.consume();
            this.hunter.autoMove();
            this.coward.autoMove();
            this.hound.autoMove();
            initialize();
            return;
        }

    }

    public void MyTimer(Bomb bomb) {
        TimerTask task;
        task = new TimerTask() {
            @Override
            public void run() {
                Coordinate b =  map.getPosition(Objects.fire);

                int left_x = b.getX();
                int left_y = b.getY() - 1;

                int right_x = b.getX();
                int right_y = b.getY() + 1;

                int up_x = b.getX() - 1;
                int up_y = b.getY();

                int down_x = b.getX() + 1;
                int down_y = b.getY();

                int Left = map.getValue(left_x, left_y);
                int right = map.getValue(right_x, right_y);
                int up = map.getValue(up_x, up_y);
                int down = map.getValue(down_x, down_y);

                removeBlock(map, Left, new Location(left_x, left_y), player, hunter, coward, hound, strategist);
                removeBlock(map, right, new Location(right_x, right_y), player, hunter, coward, hound, strategist);
                removeBlock(map, up, new Location(up_x, up_y), player, hunter, coward, hound, strategist);
                removeBlock(map, down, new Location(down_x, down_y), player, hunter, coward, hound, strategist);

                map.setupMap(new Coordinate(b.getX(), b.getY(), Objects.road));
                bomb.setLight(false);
                timer.cancel();
                timer = null;
            }
        };
        timer.schedule(task, 1000);
    }
    public static void removeBlock(Map map, int val, Location location, Player player, Enemy hunter, Enemy coward, Enemy hound, Enemy strategist) {
        if (val == Objects.player) {
            player.setAlive(false);
        } else if (val == Objects.hunter) {
            hunter.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        } else if (val == Objects.hound) {
            hound.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        } else if (val == Objects.coward) {
            coward.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        } else if (val == Objects.strategist) {
            strategist.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        }

        return;
    }

    public void checkDie() {
        if (this.map.getPosition(Objects.hunter) == null) {
            hunter.setAlive(false);
        }

        if (this.map.getPosition(Objects.coward) == null) {
            coward.setAlive(false);
        }

//        if (this.map.getPosition(Objects.strategist) == null) {
//            hunter.setAlive(false);
//        }

        if (this.map.getPosition(Objects.hound) == null) {
            hound.setAlive(false);
        }
    }
}
