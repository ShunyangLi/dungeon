package ass2;

import javafx.scene.image.ImageView;

public class GameImage {

    private ImageView[] imageViews;

    /**
     * @biref this part is according to the Objects number and store that image in the list
     *  when show on the map, we just need to get value from that
     */
    public GameImage() {
        imageViews = new ImageView[] {
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/tile.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/brick_brown_0.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/exit.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/boulder.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/pressure_plate.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/closed_door.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/gold_pile.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/key.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/bomb_unlit.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/shaft.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/greatsword_1_new.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/arrow.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/brilliant_blue_new.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/bubbly.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/human_new.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/deep_elf_master_archer.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/deep_elf_conjurer.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/hound.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/gnome.png"))),
                new ImageView(String.valueOf(getClass().getClassLoader().getResource("image/open_door.png"))),
        };
    }

    /**
     *
     * @param i the index of this list, and the i which come from the map Objects
     * @return return the image to the controller
     */
    public ImageView getImages(int i) {
        return imageViews[i];
    }
}
