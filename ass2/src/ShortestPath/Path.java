package ShortestPath;

import ass2.*;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Path {

    public ArrayList<Location> path(Map map, Coordinate cooStart, Coordinate cooEnd)
    {
        Queue<Location> coordinateQueue = new LinkedList<Location>();
        HashMap<Integer, Location> curr = new HashMap<Integer, Location>();
        ArrayList<Location> path = new ArrayList<Location>();

        Location start = new Location(cooStart.getX(), cooStart.getY());
        Location end = new Location(cooEnd.getX(), cooEnd.getY());

        // This is to check whether it have been found
        boolean flag = false;
        coordinateQueue.add(start);
        curr.put(start.hashCode(), null);
        // System.out.println("X: " + end.getX() + " Y: " + end.getY());

        while (! coordinateQueue.isEmpty() && !flag)
        {
            // 把第一个值取出来
            Location coordinate = coordinateQueue.poll();

            for (Location c: coordinate.getAroundPosition())
            {
                // System.out.println("X: " + c.getX() + " Y: " + c.getY());

                if (curr.containsKey(c.hashCode()) || moveable(c.getX(), c.getY(), map)) continue;

                // System.out.println("X: " + c.getX() + " Y: " + c.getY());
                curr.put(c.hashCode(), coordinate);

                if (invildateMove(map.getValue(c.getX(), c.getY())))
                {
                    coordinateQueue.add(c);

                    if (c.hashCode() == end.hashCode())
                    {
                        flag = true;
                        break;
                    }
                }

            }
        }

        if (!flag)
        {
            return null;
        }



        Location tempEnd = curr.get(end.hashCode());

        // System.out.println("here " + curr.get(end.hashCode()));

        while (tempEnd.hashCode() != start.hashCode())
        {
            path.add(tempEnd);
            tempEnd = curr.get(tempEnd.hashCode());
        }
        return path;
    }

    public boolean invildateMove(int val)
    {
        if (val == Objects.player || val == Objects.road || val == Objects.exit)
        {
            return true;
        }

        return false;
    }

    public boolean moveable(int x, int y, Map map)
    {
        // boolean flag = false;

        if (x < 0 || y < 0 || x > map.getHeight() || y > map.getWidth())
        {
            return true;
        }

        return false;
    }
}
