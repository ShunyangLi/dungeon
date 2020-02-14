package unsw.algorithm;

import java.util.ArrayList;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // as the primary key
    public int getKey() {
        return x * 4000 + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @brief go up, down, left, right
     * @return the list of the player can go
     */
    public ArrayList<Location> availablePosition() {
        ArrayList<Location> available = new ArrayList<Location>();
        Location location = new Location(x + 1, y);
        available.add(location);
        location = new Location(x, y - 1);
        available.add(location);
        location = new Location(x - 1, y);
        available.add(location);
        location = new Location(x, y + 1);
        available.add(location);
        return available;
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }


    public boolean samePosition(Location obj) {
        return getX() == obj.getX() && getY() == obj.getX();
    }
}
