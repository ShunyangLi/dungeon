package unsw.conditions;

import unsw.algorithm.Location;
import unsw.dungeon.Dungeon;
import unsw.entitys.Boulder;
import unsw.entitys.Entity;
import unsw.entitys.Floorswitch;
import unsw.entitys.Player;

import java.util.ArrayList;
import java.util.List;

public class BoulderCondition implements Condition {

    @Override
    public Condition makeExit() {
        return new ExitCondition();
    }

    @Override
    public Condition makeEnemy() {
        return new EnemyCondition();
    }

    @Override
    public Condition makeBoulder() {
        return new BoulderCondition();
    }

    @Override
    public Condition makeTreasure() {
        return new TreasureCondition();
    }

    /**
     * @brief it will check whether all the boulder on the switches
     * @param player the player
     * @return whether finish the game
     */
    @Override
    public boolean finish(Player player) {
        Dungeon dungeon = player.getDungeon();
        List<Entity> boulders = new ArrayList<Entity>();
        List<Entity> switches = new ArrayList<Entity>();

        // check all the boulder and switches
        for (Entity entity: dungeon.getEntities()) {
            if (entity instanceof Boulder) {
                boulders.add(entity);
            } else if (entity instanceof Floorswitch) {
                switches.add(entity);
            }
        }

        // check all the boulders
        for (Entity entity:boulders) {
            // check whether all boulder on switches
            if (!checkContain(entity.getLocation(), switches)) return false;
        }

        return true;
    }

    /**
     * @brief check whether contain all the entity
     * @param location the boulder's location
     * @param entities all the entities in the map
     * @return check contain entities
     */
    public boolean checkContain(Location location, List<Entity> entities) {
        for (Entity entity: entities) {
            Location curr = entity.getLocation();
            if (curr.getX() == location.getX() && curr.getY() == location.getY()) return true;
        }

        return false;
    }
}
