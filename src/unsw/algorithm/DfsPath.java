package unsw.algorithm;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.entitys.*;

import java.util.*;

public class DfsPath {

    private HashMap<Integer, Location> checkedPosition;
    private Queue<Location> locations;

    public ArrayList<Location> findPath(Dungeon dungeon, Location startPosition, Location endPosition) {

        if (dungeon == null || startPosition == null || endPosition == null) return null;

        locations = new LinkedList<Location>();
        checkedPosition = new HashMap<Integer, Location>();

        // This is to check whether it have been found
        locations.add(startPosition);
        checkedPosition.put(startPosition.getKey(), null);

        while (!locations.isEmpty()) {
            // use queue to search element
            Location location = locations.poll();
            assert location != null;
            for (Location c: location.availablePosition()) {
                // check the point around
                if (checkedPosition.containsKey(c.getKey())) continue;
                if (!checkBoundary(c.getX(), c.getY(), dungeon)) continue;

                checkedPosition.put(c.getKey(), location);
                // if next is player road or exit then is ok
                Entity entity = dungeon.getEntity(c);
                if (entity == null
                        || entity instanceof Enemy
                        || entity instanceof OpenDoor) {
                    // add the location
                    locations.add(c);
                    // if the start point key is same with end
                    // then we find that
                    if (c.getKey() == endPosition.getKey()) {
                        // after find that, jus return the result
                        Location LinkedPosition = checkedPosition.get(endPosition.getKey());
                        ArrayList<Location> shortestPath = new ArrayList<Location>();

                        // compare the hash code as the unique key
                        while (LinkedPosition.getKey() != startPosition.getKey()) {
                            shortestPath.add(LinkedPosition);
                            LinkedPosition = checkedPosition.get(LinkedPosition.getKey());
                        }

                        shortestPath.add(startPosition);

                        return shortestPath;
                    }
                }

            }
        }

        // if we can find the way, then get the shortest path from start to end
        return null;
    }

    private boolean checkBoundary(int x, int y, Dungeon dungeon) {
        return x >= 0
                && y >= 0
                && x <= dungeon.getWidth()
                && y <= dungeon.getHeight();
    }
}
