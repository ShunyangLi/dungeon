package View;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AbstractController {
    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    ImageView formatImage(ImageView srcImage) {
        ImageView image = new ImageView(srcImage.getImage());
        image.setFitWidth(40);
        image.setFitHeight(40);
        return image;
    }

    StackPane stackCopy(ImageView image) {
        StackPane stack = new StackPane();
        stack.setMaxSize(40, 40);
        stack.setMinSize(40, 40);
        stack.getChildren().add(image);
        makeDroppable(stack);
        return stack;
    }

    // TODO this part get from tutorial code
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
                    stack.getChildren().add(formatImage(new ImageView(db.getImage())));
                }
                event.consume();
            }
        });
    }
}
