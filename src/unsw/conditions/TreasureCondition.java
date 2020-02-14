package unsw.conditions;

import unsw.dungeon.Objects;
import unsw.entitys.Enemy;
import unsw.entitys.Entity;
import unsw.entitys.Player;

import java.util.List;

public class TreasureCondition implements Condition {

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
     * @brief check whether has treasure in the bag
     * @param player
     * @return
     */
    @Override
    public boolean finish(Player player) {
        List<Entity> props = player.getEntityByKey(Objects.treasure);
        return props != null;
    }
}
