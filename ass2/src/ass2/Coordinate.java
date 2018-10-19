package ass2;

public class Coordinate {
    // x, y is the coordinate, and the value is the objects
    private int x;
    private int y;
    private int value;

    /**
     *
     * @param x x is the coordinate in the map
     * @param y y is the coordinate in the map
     * @param value value is the Objects of different things
     */
    public Coordinate (int x, int y, int value) {
        this.setX(x);
        this.setY(y);
        this.setValue(value);
    }

    /**
     * @biref this is set the coordinate
     * @param x set the x value in current position
     */
    public void setX (int x) {
        this.x = x;
    }

    /**
     * @biref this is set the coordinate
     * @param y set the x value in current position
     */
    public void setY (int y) {
        this.y = y;
    }

    /**
     * @biref this is set the coordinate
     * @param value set the x value in current position
     */
    public void setValue(int value) {
        this.value = value;
    }

    public int getX () {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     *
     * @return the value of current Objects
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}