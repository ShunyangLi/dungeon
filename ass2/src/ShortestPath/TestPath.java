package ShortestPath;

import ass2.*;

import java.util.ArrayList;

public class TestPath {

    public static void main(String[] args)
    {
        Map map = new Map(16,18);
        Path path = new Path();
        ArrayList<Location> shortPath = path.path(map, map.getPosition(Objects.coward), map.getPosition(Objects.exit));

        System.out.println(shortPath);
        for(Location co: shortPath)
        {
            System.out.println("X: " + co.getX() + " Y: " + co.getY());
        }

    }
}
