package unsw.GameDialog;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dialog {

    public Alert gameIntr() {
        return homeDialog("Game instructions", "" +
                "1. Maze model [simple]: " +
                "GOAL: The player must find their way from the starting point to the exit.\n\n" +
                "2. Boulder Model [advanced]: " +
                "GOAL: The player need to push boulders onto all floor switches.\n\n" +
                "3. Advanced Model [difficult]: " +
                "GOAL: The player wins by comleting a combination of the following goals:\n" +
                "Push boulder | Fight Enemy | Collect treasure | Exit \n\n"  +
                "4. Design Model: The player can design their own map and choose their own goals.\n\n");
    }


    public Alert mazeInfo() {
        return showDialog("Maze model instructions", "" +
        		"GOAL: The player wins by arriving at the exit.\n\n" +
                "Wall blocks the movement of the player.\n\n " +
                "> Restart button: restart the game\n\n" +
                "> Home button: go to home page\n\n" +
                "> Help button: show instructions\n\n" +
                "> Exit button: exit the game\n\n");
    }

    public Alert boulderInfo() {
        return showDialog("Boulder model instructions", "" +
        		"GOAL: The player wins by pushing all boulders onto switches.\n\n" +
                "Wall blocks the movement of the player and boulders. " +
                "The player is only strong enough to push one boulder at a time into adjacent squares.\n\n" +
                "Pushing a boulder onto a floor switch triggers it.\n" +
                "Pushing a boulder off the floor switch untriggers it.\n\n" +
                "> Restart button: restart the game\n\n" +
                "> Home button: go to home page\n\n" +
                "> Help button: show instructions\n\n" +
                "> Exit button: exit the game\n\n");
    }

    public Alert advancedInfo() {
        return showDialog("Advanced model instructions", "" +
                "GOAL: The player wins by comleting a combination of the following goals:\n" +
                "Push boulder | Fight Enemy | Collect treasure | Exit \n\n"  +
                "1. Wall blocks the movement of the player, enemies, boulders and arrows.\n\n" +
                "2. Treasure: can be collected by the player.\n\n" +
                "3. Key: only one key at a time can be carried, and only one door has a lock that fits the key. " +
                "If the player holds the key, they can open the door by moving through it.\n\n" +
                "4. Bomb: collected bomb can be lit and dropped to kill enemies or destroy boulders.\n\n" +
                "5. Enemy: Constantly moves toward the player, stopping if it cannot move any closer, " +
                "the player dies upon collision with an enemy.\n\n" +
                "6. Sword: can be picked up the player and used to kill enemies. " +
                "Only one sword can be carried at once. " +
                "Each sword is only capable of 5 hits and disappears after that. One hit of the sword is sufficient to destroy any enemy.\n\n" +
                "7. Potion: If the player picks this up they become invincible to all bombs and enemies. " +
                "Colliding with an enemy should result in their immediate destruction. " +
                "Because of this, all enemies will run away from the player when they are invincible. " +
                "The effect of the potion only lasts a limited time.\n\n"
        );
    }


    public Alert designInfo() {
        return showDialog("Design model instructions", "" +
                "1. Drag the entity into the map then you can add it to map.\n\n" +
                "2. If you choose the goal, you must include the entity in the dungeon.\n\n" +
                "3. If you make a mistake when drag, you can drag the mistake into crush bin.\n\n" +
                "4. Dungeon must include player!\n\n");
    }


    public Alert showDialog(String title, String arg) {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
        info.setTitle(title);
        info.setHeaderText("");
        info.setContentText(arg);
        info.setResizable(true);

        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();
        gridPane.add(getImage("/up.png"), 1,0);
        gridPane.add(getImage("/left.png"),0,1);
        gridPane.add(getImage("/down.png"),1,1);
        gridPane.add(getImage("/right.png"),2,1);
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(new Text("Control player's directions\n\n"));

        gridPane = new GridPane();
        gridPane.add(getImage("/P.png"),0,0);
        gridPane.add(new Text("    Pick up props"),1,0);

        gridPane.add(getImage("/L.png"),0,1);
        gridPane.add(new Text("    Lit bomb"),1,1);

        gridPane.add(getImage("/blank.png"),0,2);
        gridPane.add(new Text("    Fire fireball"),1,2);
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(new Text("\n"));
        TextArea textArea = new TextArea(arg);
        textArea.setPrefWidth(400);
        textArea.setWrapText(true);
        textArea.setPadding(new Insets(5,5,5,5));
        vBox.getChildren().add(textArea);
        info.getDialogPane().setContent(vBox);

        Image APPLICATION_ICON = new Image("/human_new.png");
        Stage dialogStage = (Stage) info.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);

        return info;
    }

    public Alert homeDialog(String title, String arg) {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
        info.setTitle(title);
        info.setHeaderText("");
        info.setContentText(arg);
        info.setResizable(true);

        Image APPLICATION_ICON = new Image("/human_new.png");
        Stage dialogStage = (Stage) info.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);

        return info;
    }

    private ImageView getImage(String url) {
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);
        return imageView;
    }
}
