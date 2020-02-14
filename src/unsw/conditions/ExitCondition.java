package unsw.conditions;

import unsw.dungeon.Dungeon;
import unsw.entitys.Enemy;
import unsw.entitys.Entity;
import unsw.entitys.Exit;
import unsw.entitys.Player;

public class ExitCondition implements Condition {

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
     * @brief check whether in the exit position
     * @param player
     * @return
     */
    @Override
    public boolean finish(Player player) {

        Dungeon dungeon = player.getDungeon();

        Entity entity = dungeon.getEntity(player.getLocation());

        return entity instanceof Exit;
    }
}
