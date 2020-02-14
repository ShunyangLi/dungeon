package unsw.conditions;

import unsw.entitys.Player;

public interface Condition {
    public Condition makeExit();
    public Condition makeEnemy();
    public Condition makeBoulder();
    public Condition makeTreasure();
    public boolean finish(Player player);
}
