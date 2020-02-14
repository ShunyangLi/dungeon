/**
 *
 */
package unsw.dungeon;

import unsw.algorithm.Location;
import unsw.entitys.*;
import unsw.goals.Goal;
import unsw.goals.GoalCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private List<Enemy> enemy;
    private GoalCondition goal;
    private String file;

    /**
     * @brief init with param
     * @param width map width
     * @param height map height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        enemy = new ArrayList<Enemy>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEnemy(Enemy enemy) {
        this.enemy.add(enemy);
    }

    public List<Enemy> getEnemy() {
        return enemy;
    }
    
    public void setGoal(GoalCondition goal) {
        this.goal = goal;
    }
    

    public GoalCondition getGoal() {
        return goal;
    }

    // get the entity which not instanceof player in player's position
    public Entity getEntity(Location location) {
        Entity entity = null;
        for (Entity en: getEntities()) {
            if (en.getX() == location.getX() && en.getY() == location.getY()) {
                if (!(en instanceof Player)) {
                    entity = en;
                    break;
                }
            }
        }
        return entity;
    }


    // get boulders according to the location
    public Entity getBoulder(Location location) {
        Entity entity = null;
        for (Entity en: getEntities()) {
            if (en.getX() == location.getX() && en.getY() == location.getY()) {
                if (en instanceof Boulder) {
                    entity = en;
                    break;
                }
            }
        }
        return entity;
    }

    // get switch according to the location
    public Entity getSwitch(Location location) {
        Entity entity = null;
        for (Entity en: getEntities()) {
            if (en.getX() == location.getX() && en.getY() == location.getY()) {
                if (en instanceof Floorswitch) {
                    entity = en;
                    break;
                }
            }
        }
        return entity;
    }

    // get all the entities in the location
    public List<Entity> getCurrentEntity(Location location) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Entity en: getEntities()) {
            if (en.getX() == location.getX() && en.getY() == location.getY()) {
                if (!(en instanceof Player)) {
                    entities.add(en);
                }
            }
        }
        return entities;
    }


    // remove the entity
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    //  get the exit of the dungeon
    public Entity getExit() {
        for (Entity entity:entities) {
            if (entity instanceof Exit) {
                return entity;
            }
        }

        return null;
    }

    /**
     * @brief convert the goals into string and display on the stage
     * @return string
     */
    public String goalInfo() {
        StringBuilder res = new StringBuilder();

        if (goal.getGoals() == null) {
            res = new StringBuilder(convertGoalString(goal.getType()));
            return res.toString();
        } else {
            res.append("You main goals are: \n");
            for (GoalCondition g:goal.getGoals()) {
                res.append(convertGoalString(g.getType()));
            }

            // check the subgoals
            if (goal.getSubGoals() != null && goal.getSubGoals().size() > 0) {
                res.append("\nAnd subgoals are: \n");
                for (GoalCondition g:goal.getSubGoals()) {
                    System.out.println(g.getType());
                    res.append(convertGoalString(g.getType()));
                }
            }
        }
        return res.toString();
    }


    /**
     * @brief convert the different type of goal into string
     * @param type the type of goal
     * @return
     */
    public String convertGoalString(String type) {
        String res = "";
        switch (type) {
            case "exit":
                res = "arrive to exit then win";
                break;
            case "enemies":
                res = "kill enemies";
                break;
            case "boulders":
                res = "Push all boulders on switches then win";
                break;
            case "treasure":
                res = "collect treasure";
                break;
            default:
                break;
        }
        return res+"\n";
    }


    public void setFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
