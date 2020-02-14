package unsw.goals;

import unsw.conditions.Condition;
import unsw.entitys.Player;

import java.util.List;

public interface GoalCondition {
    public List<GoalCondition> getGoals();
    public List<GoalCondition> getSubGoals();
    public String getType();
    public void addGoal(GoalCondition goalCondition);
    public void addSub(GoalCondition goalCondition);
    public Condition getCondition();
    public void removeGoal(GoalCondition goalCondition);
    public void removeSubGoal(GoalCondition goalCondition);
    public boolean checkWin(Player player);
}
