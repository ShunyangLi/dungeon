package Enemy;

import ass2.Coordinate;
import ass2.Map;

public abstract class Enemy {

    private EnemyMove move;
    private Coordinate position;
    private Map map;
    private boolean alive;

    /**
     *
     * @param position to get the correct position of enemy
     * @param map pass the map into the the enemy, which is help the enemy find shortest path
     * @param alive when init, set alive
     */

    public Enemy (Coordinate position, Map map, boolean alive) {
        this.move = null;
        this.position = position;
        this.map = map;
        this.alive = alive;
    }

    /**
     * @brief auto move, when use dfs find the shortest path, then the enemy auto move to close the player
     */
    public void autoMove() {
        // System.out.println(this.move);
        System.out.println("Move");
    }

    /**
     *
     * @return return the enemy's position
     */
    public final Coordinate getPosition() {
        return this.position;
    }

    /**
     *
     * @return return the map
     */

    public final Map getMap() {
        return this.map;
    }

    /**
     *
     * @param alive which is the player status
     */
    public final void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     *
     * @param map set the map
     */

    public final void setMap(Map map) {
        this.map = map;
    }

    /**
     *
     * @param move which is enemyMove, this include track and move around, so it's a little different
     *             when init that, then we init a EnemyMove class
     */
    public final void setMove(EnemyMove move) {
        this.move = move;
    }

    /**
     *
     * @param position which is the enemy's current position
     */

    public final void setPosition(Coordinate position) {
        this.position = position;
    }

    public final boolean isAlive() {
        return this.alive;
    }

    public final EnemyMove getMove() {
        return this.move;
    }
}
