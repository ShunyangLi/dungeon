package ShortestPath;

import ass2.*;

import java.util.ArrayList;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x * 100 + y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public ArrayList<Location> getAroundPosition()
    {
        ArrayList<Location> around = new ArrayList<Location>();

        around.add(new Location(x - 1, y));
        around.add(new Location(x + 1, y));
        around.add(new Location(x, y - 1));
        around.add(new Location(x, y + 1));

        return around;
    }
}
