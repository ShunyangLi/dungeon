package unsw.dungeon;

import javafx.scene.control.Alert;
import unsw.design.DesignStart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import unsw.GameDialog.Dialog;

public class StartController {

    private String fileName = "";
    private Stage stage;
    @FXML
    private AnchorPane main;
    private Alert alert;
    private int level = -1;
    private boolean showInfo = true;


    @FXML
    public void mazeButton () throws Exception {
        // if player choose maze model
        fileName = "maze0.json";
        alert = new Dialog().mazeInfo();
        level = 0;
        start();
    }

    @FXML
    public void sokobanButton () throws Exception {
        // if player choose sokoban model
        fileName = "boulders0.json";
        alert = new Dialog().boulderInfo();
        level = 0;
        start();
    }

    @FXML
    public void advancedButton () throws Exception {
        // if player choose advanced model
        fileName = "advanced1.json";
        alert = new Dialog().advancedInfo();
        start();
    }

    @FXML
    public void designButton() {
        DesignStart designStart = new DesignStart();
        alert = new Dialog().designInfo();
        try {
            designStart.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void helpButton() {
        new Dialog().gameIntr().show();
    }

    @FXML
    public void initialize() {
        BackgroundImage myBI= new BackgroundImage(new Image("/background.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        main.setBackground(new Background(myBI));

        stage.setWidth(672);
        stage.setHeight(500);
    }

    public void start() throws Exception {
        stage.setTitle("Dungeon");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(fileName, stage);
        DungeonController controller = dungeonLoader.loadController();
        controller.setInfo(alert);
        controller.setShowInfo(showInfo);
        // make level
        if (level != -1) controller.setLevel(level);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    public void setFileName(String string) {
        this.fileName = string;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public void setShowInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }
}
