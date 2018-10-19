package Enemy;

import ShortestPath.Location;
import ShortestPath.Path;
import ass2.Coordinate;
import ass2.Map;
import ass2.Objects;

import java.util.ArrayList;

public class TrackPlayer implements EnemyMove {

    private Enemy enemy;
    private Map map;
    private Coordinate position;

    public TrackPlayer(Enemy enemy) {
        this.enemy = enemy;
        this.map = this.enemy.getMap();
        this.position = this.enemy.getPosition();
    }

    /**
     * @param object which is the start position
     * @brief the function is according to the bfs to get the shortest path to the player, and then track the player
     */
    @Override
    public void autoMove(int object) {

        // firstly, use the dfs to get the colest path to the player
        Path path = new Path();
        ArrayList<Location> road = path.path(this.map, this.map.getPosition(object), this.map.getPosition(Objects.player));
        // because if the player is not move, then the enemy can kill the player, so need to add the player's position into the path

        if (road == null || road.size() == 0) {
            return;
        }
        road.add(0,new Location(this.map.getPosition(Objects.player).getX(),this.map.getPosition(Objects.player).getX()));
        // this is control to auto move
        for (int i  = road.size() - 1; i >= 0; i -- ) {
            // just set the position
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            int x = road.get(i).getX();
            int y = road.get(i).getY();
            this.map.setupMap(new Coordinate(x, y, object));
            this.position.setX(x);
            this.position.setY(y);
            this.position.setValue(object);
            break;
        }
    }
}
