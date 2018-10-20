package Enemy;

import ass2.Coordinate;
import ass2.Map;
import ass2.Objects;

public class Hunter extends Enemy {
    private EnemyMove move;
    private Coordinate position;
    private Map map;
    private boolean alive;

    public Hunter (Coordinate position, Map map, boolean alive) {
        super(position,map,alive);
    }

    /**
     * @brief because when we use dfs, we need to get the enemy coordinate and player coordinate as
     *          start position and end position
     */
    @Override
    public void autoMove() {
        if (this.isAlive()){
            this.getMove().autoMove(Objects.hunter, 0);
        }

    }
}
