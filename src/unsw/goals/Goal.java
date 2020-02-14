package unsw.goals;

import unsw.conditions.Condition;
import unsw.conditions.ExitCondition;
import unsw.entitys.Player;

import java.util.ArrayList;
import java.util.List;

public class Goal implements GoalCondition {

    private List<GoalCondition> goals;
    private List<GoalCondition> subGoals;

    public Goal() {
        goals = new ArrayList<GoalCondition>();
        subGoals = new ArrayList<GoalCondition>();
    }

    @Override
    public List<GoalCondition> getGoals() {
        return goals;
    }

    @Override
    public List<GoalCondition> getSubGoals() {
        return subGoals;
    }


    public void addGoal(GoalCondition goalCondition) {
        goals.add(goalCondition);
    }

    public void addSub(GoalCondition goalCondition) {
        subGoals.add(goalCondition);
    }

    @Override
    public Condition getCondition() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    public void removeGoal(GoalCondition goalCondition) {
        goals.remove(goalCondition);
    }

    public void removeSubGoal(GoalCondition goalCondition) {
        subGoals.remove(goalCondition);
    }

    @Override
    public boolean checkWin(Player player) {
        List<GoalCondition> mainGoal = getGoals();
        if (mainGoal == null) return false;

        for (GoalCondition g:mainGoal) {
            Condition condition = g.getCondition();
            if (condition instanceof ExitCondition) {
                if (mainGoal.size() > 1) continue;
            }
            if (condition.finish(player)) {
                mainGoal.remove(g);
                if (getGoals().size() == 0) return true;
                else break;
            }
        }
        return finishSubGoal(player);
    }

    private boolean finishSubGoal(Player player) {
        List<GoalCondition> subGoals = getSubGoals();
        if (subGoals == null || subGoals.size() == 0) return false;

        for (GoalCondition g:subGoals) {
            Condition condition = g.getCondition();
            if (condition.finish(player)) {
                subGoals.remove(g);
                if (getSubGoals().size() == 0) return true;
                else break;
            }
        }
        return false;
    }


}
