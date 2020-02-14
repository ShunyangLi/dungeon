package unsw.entitys;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.algorithm.Location;

import java.util.Observable;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity extends Observable {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private ImageView imageView;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public void setNode(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getNode() {
        return imageView;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public Location getLocation() {
        return new Location(getX(), getY());
    }
}
