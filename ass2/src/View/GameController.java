package View;

import Enemy.*;
import ShortestPath.Location;
import ass2.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import props.*;

import javax.sound.midi.Track;
import java.util.Optional;
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
//        System.out.println(index);
        this.map = new Map(index);
        if (this.map.getMap() == null) {
            winAllDialog ();
        }
    }

    public void setGame (int[][] map) {
//        System.out.println(index);
        this.map = new Map(16, 18, map);
        if (this.map.getMap() == null) {
            winAllDialog ();
        }
        updateObj();
        initialize();
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
        try {
            this.player = new Player(map,map.getPosition(Objects.player));
            this.hunter = new Hunter(map.getPosition(Objects.hunter),map,true);
            this.hunter.setMove(new TrackPlayer(this.hunter));
            this.coward = new Coward(map.getPosition(Objects.coward),map,true);
            this.coward.setMove(new TrackPlayer(this.coward));
            this.strategist = new Strategist(map.getPosition(Objects.strategist), map, true);
            this.strategist.setMove(new TrackPlayer(this.strategist));
            this.hound = new Hound(map.getPosition(Objects.hound),map,true);
            this.hound.setMove(new TrackPlayer(this.hound));
        } catch (Exception e) {
            return;
        }

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
            this.hound.autoMove();
            this.strategist.autoMove();

            if (coward.isAlive()) {
                if (hide(player, coward)) {
                    // System.out.println("Hiding !!");
                    coward.hide();
                } else {
                    coward.autoMove();
                }
            }
            initialize();
            return;
        } else if (!player.getAlive()) {
            dieDialog ();
        }else if (player.isSuccess()) {
            winDialog ();
        }

    }

    public void winAllDialog () {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Winner");
        alert.setContentText("You Passed All Level!!!!!!!!");
        ButtonType buttonTypeBack = new ButtonType("Back To Main");

        alert.getButtonTypes().setAll(buttonTypeBack);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeBack){
            alert.close();
            handleBackButton ();
        } else {
            alert.close();
        }
    }

    public void winDialog () {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Winner");
        alert.setContentText("You Win !!!!!!!!");

        ButtonType buttonNext = new ButtonType("Next Level");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonNext, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonNext){
            alert.close();
            handleNext();
        } else {
            alert.close();
        }
    }

    public void dieDialog () {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Failed");
        alert.setContentText("You die !!!!!!!!");

        ButtonType buttonNew = new ButtonType("Restart");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonNew, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonNew){
            alert.close();
            handleRestartButton ();
        } else {
            alert.close();
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

    public static boolean hide(Player player, Enemy crowd) {
        int p_x = player.getPosition().getX();
        int p_y = player.getPosition().getY();

        int h_x = crowd.getPosition().getX();
        int h_y = crowd.getPosition().getY();

        int val = Math.abs(p_x - h_x) + Math.abs(p_y - h_y);
        if (val < 5) {
            return true;
        }
        return false;
    }

}
