package unsw.goals;

import unsw.conditions.Condition;
import unsw.conditions.ExitCondition;
import unsw.entitys.Player;

import java.util.List;

public class SubGoal implements GoalCondition {

    private String type;
    private Condition condition = new ExitCondition();;

    public SubGoal(String type) {
        this.type = type;
        setCondition();

    }
    public String getType() {
        return type;
    }

    @Override
    public void addGoal(GoalCondition goalCondition) {

    }

    @Override
    public void addSub(GoalCondition goalCondition) {

    }

    @Override
    public List<GoalCondition> getGoals() {
        return null;
    }

    @Override
    public List<GoalCondition> getSubGoals() {
        return null;
    }

    public void setCondition() {
        switch (type) {
            case "exit":
                condition = condition.makeExit();
                break;
            case "enemies":
                condition = condition.makeEnemy();
                break;
            case "boulders":
                condition = condition.makeBoulder();
                break;
            case "treasure":
                condition = condition.makeTreasure();
                break;
            default:
                break;
        }
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void removeGoal(GoalCondition goalCondition) {

    }

    @Override
    public void removeSubGoal(GoalCondition goalCondition) {

    }

    @Override
    public boolean checkWin(Player player) {
        return false;
    }
}
