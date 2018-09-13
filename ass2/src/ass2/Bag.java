package ass2;
import props.*;

public class Bag {

    private Sword sword;
    private Arrow arrow;
    private Bomb bomb;
    private Treasure treasure;
    private Key key;

    public Bag (Sword sword, Arrow arrow, Bomb bomb, Treasure treasure, Key key) {
        this.sword = sword;
        this.arrow = arrow;
        this.bomb = bomb;
        this.treasure = treasure;
        this.key = key;
    }

    public Sword getSword() {
        return sword;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public Key getKey() {
        return this.key;
    }
}