package unsw.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.Objects;
import unsw.entitys.*;

import java.util.HashMap;

public class GameImage {
    private HashMap<String, Image> imageViews;

    public GameImage() {
        imageViews = new HashMap<String, Image>();
        loadImage();
    }

    private void loadImage() {
        imageViews.put(String.valueOf(Player.class), new Image("/human_new.png"));
        imageViews.put(String.valueOf(Wall.class), new Image("/brick_brown_0.png"));
        imageViews.put(String.valueOf(Exit.class), new Image("/exit.png"));
        imageViews.put(String.valueOf(Treasure.class), new Image("/gold_pile.png"));
        imageViews.put(String.valueOf(Door.class), new Image("/closed_door.png"));
        imageViews.put(String.valueOf(Key.class), new Image("/key.png"));
        imageViews.put(String.valueOf(Boulder.class), new Image("/boulder.png"));
        imageViews.put(String.valueOf(Floorswitch.class), new Image("/pressure_plate.png"));
        imageViews.put(String.valueOf(UnlitBomb.class), new Image("/bomb_unlit.png"));
        imageViews.put(LitBomb.class+"1", new Image("/bomb_lit_1.png"));
        imageViews.put(LitBomb.class+"2", new Image("/bomb_lit_2.png"));
        imageViews.put(LitBomb.class+"3", new Image("/bomb_lit_3.png"));
        imageViews.put(LitBomb.class+"4", new Image("/bomb_lit_4.png"));
        imageViews.put(String.valueOf(Enemy.class), new Image("/deep_elf_master_archer.png"));
        imageViews.put(String.valueOf(Sword.class), new Image("/greatsword_1_new.png"));
        imageViews.put(String.valueOf(Potion.class), new Image("/brilliant_blue_new.png"));
        imageViews.put(String.valueOf(OpenDoor.class), new Image("/open_door.png"));


        imageViews.put(Objects.face_down, new Image("/face_down.png"));
        imageViews.put(Objects.face_up, new Image("/face_back.png"));
        imageViews.put(Objects.face_left, new Image("/face_left.png"));
        imageViews.put(Objects.face_right, new Image("/face_right.png"));


        imageViews.put(String.valueOf(FireBall.class), new Image("/fireball.png"));
    }

    public HashMap<String, Image> getImageViews() {
        return imageViews;
    }

    public ImageView getImage(String key) {
        return new ImageView(imageViews.get(key));
    }

}
