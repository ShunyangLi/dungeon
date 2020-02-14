package unsw.design;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesignStart {

    public void start(Stage primaryStage) throws Exception{
        DesignController controller = new DesignController(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        primaryStage.setTitle("Design you dungeon");
        primaryStage.setScene(new Scene(root, 686, 664));
        primaryStage.show();
    }
}
