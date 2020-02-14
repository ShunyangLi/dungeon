package unsw.design;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import unsw.GameDialog.Dialog;
import unsw.algorithm.Location;
import unsw.dungeon.StartApplication;
import unsw.dungeon.StartController;
import unsw.entitys.*;
import unsw.images.GameImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DesignController {

    @FXML
    private GridPane target;
    @FXML
    private GridPane source;
    @FXML
    private GridPane bin;
    @FXML
    private Button addGoal;
    @FXML
    private Text goaldetail;
    @FXML
    private ChoiceBox goals;
    @FXML
    private Button start;

    private GameImage image;
    private String curr = null;
    private Node deleteNode = null;
    private HashMap<Node, HashMap<String, Location>> objs;
    private HashMap<String, String> availableNodes;
    private List<String> designGoals;
    private Stage stage;


    /**
     * @brief init the design model with some available Node and goals list
     * @param stage the main stage which display
     */
    public DesignController(Stage stage) {
        image = new GameImage();
        objs = new HashMap<Node, HashMap<String, Location>>();
        availableNodes = new HashMap<String, String>();
        designGoals = new ArrayList<String>();
        this.stage = stage;

        availableNodes.put(String.valueOf(Player.class), "player");
        availableNodes.put(String.valueOf(Enemy.class), "enemy");
        availableNodes.put(String.valueOf(Boulder.class), "boulder");
        availableNodes.put(String.valueOf(Door.class), "door");
        availableNodes.put(String.valueOf(Exit.class), "exit");
        availableNodes.put(String.valueOf(Key.class), "key");
        availableNodes.put(String.valueOf(UnlitBomb.class), "bomb");
        availableNodes.put(String.valueOf(Potion.class), "invincibility");
        availableNodes.put(String.valueOf(Wall.class), "wall");
        availableNodes.put(String.valueOf(Sword.class), "sword");
        availableNodes.put(String.valueOf(Treasure.class), "treasure");
        availableNodes.put(String.valueOf(FireBall.class), "fire");

        new Dialog().designInfo().showAndWait();
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                ImageView imageView = new ImageView(ground);

                imageView.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
                    @Override
                    public void handle(MouseDragEvent event) {
                        // after release we need to add the image into that
                        addNode((Node) event.getSource());
                    }
                });

                target.add(imageView, x, y);
            }
        }

        // add source image
        int row  = 0;
        int col = 0;
        for (String key:availableNodes.keySet()) {
            ImageView imageView = image.getImage(key);

            GridPane.setRowIndex(imageView, row);
            GridPane.setColumnIndex(imageView, col);
            source.getChildren().add(imageView);

            // this one this just when you drag the image
            imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    imageView.startFullDrag();
                    curr = key;
                }
            });

            // every 4 item to change one line
            if (col == 4) {
                col = 0;
                row ++;
            }
            col++;

        }


        // add bin image
        ImageView binIm = new ImageView(new Image("/bin.png"));
        binIm.setFitWidth(125);
        binIm.setFitHeight(135);

        // if player drag the node from target then delete that
        binIm.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if (deleteNode != null) {
                    // add delete sound
                    Media media = new Media(new File("src/unsw/design/sound.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    target.getChildren().remove(deleteNode);
                    // remove the node from objs
                    objs.remove(deleteNode);
                    deleteNode = null;
                }
            }
        });

        bin.add(binIm, 0,0);


        // add goal choice
        goals.setItems(FXCollections.observableArrayList(
           "exit", "enemies","boulders", "treasure"
        ));

        // set add onclick
        addGoal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                designGoals.add(goals.getValue().toString());
                goaldetail.setText(goaldetail.getText().toString() + "\n" +
                        goals.getValue().toString());
            }
        });

        // add start handle
        stage.setWidth(686);
        stage.setHeight(684);
    }


    /**
     * @brief add node to the target
     * @param node the entity
     */
    public void addNode(Node node) {
        int x = 0, y = 0;
        List<Node> children = target.getChildren();
        // get the location
        for (Node n:children) {
            if (n == node) {
                x = target.getColumnIndex(n);
                y =  target.getRowIndex(n);
            }
        }
        if (curr == null) return;
        // get the image from GameImage object
        ImageView nodeAdd = image.getImage(curr);
        GridPane.setRowIndex(nodeAdd, y);
        GridPane.setColumnIndex(nodeAdd, x);

        // set the node can be delete
        nodeAdd.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nodeAdd.startFullDrag();
                deleteNode = nodeAdd;
            }
        });
        target.getChildren().add(nodeAdd);
        HashMap<String, Location> values = new HashMap<String, Location>();
        values.put(curr, new Location(x,y));
        objs.put(nodeAdd, values);
        curr = null;
    }

    @FXML
    public void handelStart() {
        // check the entity numbers
        if (objs.size() == 0) {
            notification("Nothing on the map");
            return;
        }

        // check the goals
        if (designGoals.size() == 0) {
            notification("You should choose at least one goal");
            return;
        }

        // if add the goal then check whether add the entity
        for (String str:designGoals) {
            if (!checkGoal(str)) {
                notification("If you choose goal: " + str + " you should add " + str + " in the map");
                return;
            }
        }

        // check whether got a player
        if (!(checkPlayer())) {
            notification("Please add a player into map");
            return;
        }

        JSONObject result = new JSONObject();

        result.put("width", 15);
        result.put("height", 15);
        result.put("entities", makeEntites());
        // put condition
        if (designGoals.size() == 1) {
            result.put("goal-condition", makeGoal());
        } else {
            result.put("goal-condition", makeGoals());
        }
        // result.put("goal-condition", "");

        writeJson(result.toString(2));

        // start the game
        StartController startController = new StartController();
        startController.setFileName("design.json");
        startController.setStage(stage);
        startController.setShowInfo(true);
        startController.setAlert(new Dialog().advancedInfo());
        try {
            startController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handelExit() {
        System.exit(0);
    }

    @FXML
    public void handelHome() {
        try {
            StartApplication scene = new StartApplication();
            scene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notification(String arg) {
        Alert gameDialog = new Alert(Alert.AlertType.WARNING);

        gameDialog.setTitle("Design");
        gameDialog.setContentText(arg);
        gameDialog.show();
    }

    /**
     * @brief it will check whether the player add a goal
     * @param g the goal's name
     * @return if has at least one goal then return true
     *          otherwise false
     */
    public boolean checkGoal(String g) {
        String detectKey = null;

        switch (g) {
            case "exit":
                detectKey = "exit";
                break;
            case "enemies":
                detectKey = "enemy";
                break;
            case "boulders":
                detectKey = "boulder";
                break;
            case "treasure":
                detectKey = "treasure";
                break;
            default:
                break;
        }

        // check the entity from the whole entity
        for (Node key:objs.keySet()) {
            HashMap<String, Location> hashMap = objs.get(key);
            for (String str: hashMap.keySet()) {
                String k = availableNodes.get(str);
                if (k.equals(detectKey)) return true;
            }
        }

        return false;
    }


    /**
     * @brief check whether got a player
     * @return true if got player, otherwise false
     */
    public boolean checkPlayer() {
        int count = 0;
        for (Node key:objs.keySet()) {
            HashMap<String, Location> hashMap = objs.get(key);
            for (String str: hashMap.keySet()) {
                String k = availableNodes.get(str);
                if (k.equals("player")) count++;
            }
        }

        return count == 1;

    }

    /**
     * @brief add the entity according to the HashMap
     *        add location and the name of entity
     * @return jsonarray
     */
    public JSONArray makeEntites() {
        JSONArray jsonArray = new JSONArray();

        for (Node key:objs.keySet()) {
            HashMap<String, Location> hashMap = objs.get(key);
            for (String s:hashMap.keySet()) {
                Location location = hashMap.get(s);
                String k = availableNodes.get(s);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("x",location.getX());
                jsonObject.put("y",location.getY());
                jsonObject.put("type",k);
                jsonArray.put(jsonObject);
            }
        }

        return jsonArray;
    }

    /**
     * @brief if got more than one goal, then use like array
     * @return jsonObject
     */
    public JSONObject makeGoals() {
        JSONObject res = new JSONObject();
        res.put("goal","AND");

        JSONArray jsonArray = new JSONArray();

        for (String key:designGoals) {
            JSONObject g = new JSONObject();
            g.put("goal", key);
            jsonArray.put(g);
        }

        res.put("subgoals", jsonArray);

        return res;
    }

    /**
     * @brief just one goal
     * @return jsonObject
     */
    public JSONObject makeGoal() {
        String goal = designGoals.get(0);

        JSONObject res = new JSONObject();
        res.put("goal", goal);

        return res;
    }


    /**
     * @brief write all the entity, goal, height, width into design.json file
     * @param str
     */
    public void writeJson(String str) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("dungeons/design.json", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.print(str);
        writer.close();
    }
}
