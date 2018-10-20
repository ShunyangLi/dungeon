package Enemy;

import ass2.Coordinate;
import ass2.Map;

public class Strategist extends Enemy {
    private EnemyMove move;
    private Coordinate position;
    private Map map;
    private boolean alive;

    public Strategist ( Coordinate position, Map map, boolean alive) {
        super(position,map,alive);
    }
}
