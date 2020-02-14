package unsw.dungeon;

import java.util.*;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import unsw.Action.Action;
import unsw.algorithm.DfsPath;
import unsw.algorithm.Location;
import unsw.entitys.*;
import unsw.images.GameImage;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements Observer {

    @FXML
    private GridPane squares;
    @FXML
    private Pane main;
    @FXML
    private VBox buttonGroup;
    @FXML
    private Button solution;
    @FXML
    private Button next;
    @FXML
    private Button previous;
    private List<ImageView> initialEntities;
    private Player player;
    private List<Enemy> enemies;
    private Dungeon dungeon;
    private GameImage gameImage;
    private DfsPath path;
    private Timer timer;
    private Stage stage;
    private Alert info = null;
    private boolean isMaze = false;
    private int level = -1;
    private boolean hasNext = false;
    private boolean hasPrevious = false;
    private boolean showInfo = true;

    /**
     * @brief because it need to return to the main page,
     *        so just pass the stage into controller
     * @param dungeon dungeon
     * @param initialEntities all the entities in dungeon
     * @param stage the main stage
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Stage stage) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.player.setGoal(dungeon.getGoal());
        this.enemies = dungeon.getEnemy();
        this.stage = stage;

         Action action = new Action();
        this.player.setModel(action);

        // action method also need add observer
        action.addObserver(this);
        this.player.addObserver(this);

        this.initialEntities = new ArrayList<>(initialEntities);
        gameImage = new GameImage();
        path = new DfsPath();

        if (dungeon.getFile().matches("^maze.*")) {
            isMaze = true;
            hasNext = true;
            hasPrevious = true;
        } else if (dungeon.getFile().matches("^boulders.*")) {
            hasNext = true;
            hasPrevious = true;
        }
    }

    @FXML
    public void initialize() {

        // move the dialog info here
        if (showInfo) info.showAndWait();

        if (enemies.size() != 0) {
            for (Enemy e: enemies) {
                e.addObserver(this);
            }
            hunterPlayer();
        }

        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        main.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                handleKeyPress(event);
            }
        });

        // if the maze model then show the solution button
        if (isMaze) solution.setVisible(true);
        if (level != -1 && level < 4 && hasNext){
            next.setVisible(true);
            next.setManaged(true);
        }
        if (level > 0 && hasPrevious){
            previous.setVisible(true);
            previous.setManaged(true);
        }

        int size = 0;
        for (Node node:buttonGroup.getChildren()) {
            if (node.isVisible() && node.isManaged()) size ++;
        }
        // resize the margin and the windows
        int height = dungeon.getHeight()*32+32;
        int groupHeight = 38*size+23*size;
        buttonGroup.setLayoutX(dungeon.getWidth()*32+10);
        stage.setWidth(dungeon.getWidth()*32+20+96);
        stage.setHeight(Math.max(height, groupHeight));

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                changeView(unsw.dungeon.Objects.face_up);
                break;
            case DOWN:
                player.moveDown();
                changeView(unsw.dungeon.Objects.face_down);
                break;
            case LEFT:
                player.moveLeft();
                changeView(unsw.dungeon.Objects.face_left);
                break;
            case RIGHT:
                player.moveRight();
                changeView(Objects.face_right);
                break;
            case P:
                player.pick();
                break;
            case L:
                player.litBomb();
                break;
            case K:
                player.useSword();
                break;
            case O:
                player.openDoor();
                break;
            case SPACE:
                player.useFire();
                break;
            default:
                break;
        }

    }

    @FXML
    public void handelRestart() {
        stage.close();

        if (enemies.size() != 0) {
            timer.cancel();
            timer.purge();
        }

        StartController startController = new StartController();
        startController.setFileName(dungeon.getFile());
        startController.setStage(new Stage());
        startController.setAlert(info);
        startController.setLevel(level);
        startController.setShowInfo(false);
        try {
            startController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handelSolution() {
        // nodes store all the circles which add into dungeon
        List<Node> nodes = new ArrayList<>();
        // check whether it include exit int the dungeon
        Entity exit = dungeon.getExit();
        if (exit == null) return;
        // use dfs find the path from player to exit
        List<Location> path = new DfsPath().findPath(dungeon,exit.getLocation(),player.getLocation());

        // if find the path, then note them to show player
        if (path != null) {
            for (Location location:path) {
                Circle circle = new Circle(16, 16, 10);
                circle.setFill(javafx.scene.paint.Color.RED);
                nodes.add(circle);
                addNode(location,squares,circle);
            }
        }

        // after one seconds, we need remove the nodes
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (Node node:nodes) {
                        squares.getChildren().remove(node);
                    }
                });
                // cancel the timer
                timer.cancel();
                timer.purge();
            }
        },2000,1000);

        // then request focus for dungeon
        squares.requestFocus();
        main.requestFocus();
    }

    @FXML
    public void handelHelp() {
        if (enemies.size() != 0) {
            timer.cancel();
            timer.purge();
        }

        Optional<ButtonType> result = info.showAndWait();
        if (enemies.size() != 0) {
            hunterPlayer();
        }
        squares.requestFocus();
        main.requestFocus();
    }

    @FXML
    public void handelHome() {

        stage.close();
        Platform.runLater( () -> {
            try {
                StartApplication startApplication = new StartApplication();
                startApplication.setShow(false);
                startApplication.start(stage);
                // cancel the timer every time
                if (enemies.size() != 0) {
                    timer.cancel();
                    timer.purge();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void handelExit() {
        Platform.exit();
        System.exit(0);

    }

    @FXML
    public void handelGoal() {

        if (enemies.size() != 0) {
            timer.cancel();
            timer.purge();
        }
        Alert goalInfo = new Alert(Alert.AlertType.CONFIRMATION);
        goalInfo.setTitle("The goal of dungeon");
        goalInfo.setContentText(dungeon.goalInfo());
        goalInfo.showAndWait();

        if (enemies.size() != 0) {
            hunterPlayer();
        }
        squares.requestFocus();
        main.requestFocus();
    }
    /**
     * @brief try to hunter the player auto
     *        it will get the path from enemy to player
     *        according to dfs, so every 700ms move a new location
     */
    public void hunterPlayer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Enemy enemy: enemies) {
                    Platform.runLater(() -> {
                        if (player.isWin()) {
                            timer.cancel();
                            timer.purge();
                        }

                        // get the path from dfs
                        List<Location> paths = path.findPath(dungeon, player.getLocation(), enemy.getLocation());

                        if (player.isInvincible()) {
                            Location runaway = enemy.runaway(dungeon);
                            paths = path.findPath(dungeon, runaway, enemy.getLocation());
                        }

                        enemy.huntPlayer(paths);
                        // if the enemy and player are in the same location
                        // check player whether invincible
                        Location en = enemy.getLocation();
                        Location pl = player.getLocation();
                        if (en.getX() == pl.getX() && en.getY() == pl.getY()) {
                            // if not invincible then die
                            if (!player.isInvincible()) {
                                timer.cancel();
                                timer.purge();
                                notification("You have been killed by enemy");
                            } else {
                                enemy.update(enemy);
                            }
                        }

                    });
                }
            }
        }, 0, 700);
    }


    /**
     * @brief show the alert information, and add button the exit or to home
     * @param arg the content of dialog
     */
    public void notification(String arg) {
        Alert gameDialog = new Alert(Alert.AlertType.NONE);
        ButtonType nextLevel = null;
        ButtonType exitButton = new ButtonType("Exit");
        ButtonType homeButton = new ButtonType("Home");
        ButtonType restartButton = new ButtonType("Restart");
        if (level != -1 && level < 4) {
            nextLevel = new ButtonType("Next Level");
        }


        gameDialog.setTitle("Dungeon");
        gameDialog.setContentText(arg);

        gameDialog.getButtonTypes().setAll(exitButton,homeButton, restartButton);
        if (nextLevel != null) gameDialog.getButtonTypes().add(nextLevel);

        Optional<ButtonType> result = gameDialog.showAndWait();

        if (result.get() == exitButton){
            gameDialog.close();
            System.exit(0);
        } else if (result.get() == homeButton) {
            gameDialog.close();
            try {
            	StartApplication scene = new StartApplication();
	            scene.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
	    } else if (result.get() == restartButton) {
	        gameDialog.close();
	        try {
	        	handelRestart();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }else if (result.get() == nextLevel) {
            try {
                nextLevel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                previousLevel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @brief check whether remove image or add or update info
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

        // if the arg instanceof entity
        // that means need to remove, add or update the ui
        if (arg instanceof Entity) {
            Entity entity = (Entity) arg;

            if (entity instanceof FireBall) {
                FireBall fireBall = (FireBall) entity;
                if (fireBall.isFinish()) {
                    squares.getChildren().remove(fireBall.getNode());
                } else {
                    if (!squares.getChildren().contains(fireBall.getNode())) {

                        // trackPosition(fireBall, fireBall.getNode());
                        squares.getChildren().add(fireBall.getNode());
                    }
                }
                return;
            }

            // the means need to change the ui to opened door
            if (entity instanceof OpenDoor) {
                entity.setNode(gameImage.getImage(String.valueOf(entity.getClass())));
                addNode(entity.getLocation(), squares, entity.getNode());
                return;
            }

            // if was litboom that means need to update ui every seconds
            if (entity instanceof LitBomb) {
                LitBomb litBomb = (LitBomb) arg;

                // the lit boom location
                Location location = litBomb.getLocation();
                // remove the last image, and not finished
                if (litBomb.getNode() != null && !litBomb.isFinish()){
                    squares.getChildren().remove(litBomb.getNode());
                }

                // if the count time is not finished
                // then change the image
                if (!litBomb.isFinish()) {
                    ImageView node = gameImage.getImage(String.valueOf(LitBomb.class) + litBomb.getCountTime());
                    litBomb.setNode(node);
                    addNode(location, squares, litBomb.getNode());
                } else {
                    // the bomb just can remove the boulder and enemy
                    // squares.getChildren().remove(litBomb.getLast());

                    // if the bomb already bombed, then remove the around entity (enemy or boulder)
                    List<Location> around = location.availablePosition();
                    around.add(location);
                    List<Node> bombs = new ArrayList<Node>();
                    for (Location l:around) {
                        ImageView imageView = gameImage.getImage(String.valueOf(LitBomb.class) + litBomb.getCountTime());
                        bombs.add(imageView);
                        addNode(l,squares,imageView);
                    }

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                for (Node node: bombs) {
                                    squares.getChildren().remove(node);
                                }
                            });
                            timer.cancel();
                            timer.purge();
                        }
                    },200,1000);


                    // around.add(location);
                    for (Location lo: around) {
                        // check whether the position ws player and check whether invincible
                        if (lo.getX() == player.getX() && lo.getY() == player.getY()) {
                            if (!player.isInvincible()) {
                                player.setAlive(false);
                                timer.cancel();
                                timer.purge();
                                notification("You have been kill by bomb");
                            }
                        } else {
                            // player.setMap(lo.getY(), lo.getX(), Objects.road);
                            // remove around ui
                            removeAround(lo);
                            // squares.add(new ImageView(new Image("/dirt_0_new.png")), lo.getY(), lo.getX());
                        }
                    }
                    // remove litbomb entity
                    litBomb.setNode(null);
                    dungeon.removeEntity(litBomb);
                }
                return;
            }

            // if was enemy then remove the enemy and stop timer
            if (entity instanceof Enemy) {
                if (enemies.size() == 0){
                    timer.cancel();
                    timer.purge();
                }
                enemies.remove(entity);
            }

            squares.getChildren().remove(entity.getNode());

        } else if (arg instanceof ImageView) {
            ImageView imageView = (ImageView) arg;
            imageView.setFitHeight(32);
            imageView.setFitWidth(32);
            squares.getChildren().remove(player.getNode());

            main.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    return;
                }
            });
            addNode(player.getLocation(),squares, imageView);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            squares.getChildren().remove(imageView);
                            squares.getChildren().add(player.getNode());
                            main.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                @Override
                                public void handle(KeyEvent event) {
                                    handleKeyPress(event);
                                }
                            });
                        });
                        timer.cancel();
                        timer.purge();

                    }
                },300,1000);

        } else if (!(o instanceof Action)) {
        	
        	notification((String) arg);
        }
    }

    /**
     * @brief remove the around space wall or enemy
     * @param location the bomb location
     */
    public void removeAround(Location location) {
        Entity entity = dungeon.getEntity(location);
        if (entity == null) return;
        if (entity instanceof Boulder || entity instanceof LitBomb) {
            squares.getChildren().remove(entity.getNode());
        } else if (entity instanceof Enemy) {
            squares.getChildren().remove(entity.getNode());
            dungeon.removeEntity(entity);
            timer.cancel();
            timer.purge();
        }

    }

    /**
     * @brief dynamic add the bomb image during the liting
     * @param location which need to add node
     * @param gridPane the GridPane
     * @param node the imageview which need to be add
     */
    public void addNode(Location location, GridPane gridPane, Node node) {
        GridPane.setRowIndex(node, location.getY());
        GridPane.setColumnIndex(node, location.getX());
        gridPane.getChildren().add(node);
    }

    public Player getPlayer() {
        return player;
    }


    public void  changeView(String dir) {
        squares.getChildren().remove(player.getNode());
        player.setNode(gameImage.getImage(dir));

        GridPane.setRowIndex(player.getNode(), player.getY());
        GridPane.setColumnIndex(player.getNode(), player.getX());

        squares.getChildren().add(player.getNode());
    }

    public void setInfo(Alert info) {
        this.info = info;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @FXML
    public void nextLevel() throws Exception {
        String filename = "";
        level += 1;
        if (dungeon.getFile().matches("^maze.*")) {
            filename = "maze"+level+".json";
        } else {
            filename = "boulders"+level+".json";
        }

        startGame(filename,level);
    }
    
    @FXML
    public void previousLevel() throws Exception {

        String filename = "";
        level -= 1;
        if (dungeon.getFile().matches("^maze.*")) {
            filename = "maze"+level+".json";
        } else {
            filename = "boulders"+level+".json";
        }

        startGame(filename, level);

    }

    public void startGame(String filename, int level) throws Exception {
        StartController startController = new StartController();
        startController.setLevel(level);
        startController.setAlert(info);
        startController.setStage(stage);
        startController.setShowInfo(false);

        startController.setFileName(filename);
        startController.start();
    }


    public void setShowInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }
}

