package unsw.conditions;

import unsw.dungeon.Dungeon;
import unsw.entitys.Enemy;
import unsw.entitys.Entity;
import unsw.entitys.Player;

public class EnemyCondition implements Condition {

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
     * @brief check whether has enemy in the dungeon
     * @param player
     * @return
     */
    @Override
    public boolean finish(Player player) {
        Dungeon dungeon = player.getDungeon();
        for (Entity entity:dungeon.getEntities()) {
            if (entity instanceof Enemy) {
                return false;
            }
        }
        return true;
    }
}
