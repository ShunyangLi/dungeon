package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.entitys.*;
import unsw.goals.Goal;
import unsw.goals.GoalCondition;
import unsw.goals.SubGoal;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private String openID = UUID.randomUUID().toString();
    private List<String> keyID;
    private List<String> doorID;
    private String filename;


    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        keyID = new ArrayList<String>();
        doorID = new ArrayList<String>();
        this.filename = filename;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);
        dungeon.setFile(filename);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        GoalCondition goal = new Goal();

        // we get two Condition, "and" or just goal
        JSONObject jsonObject = json.getJSONObject("goal-condition");
        // if no and, that means just one goal
        // so just send the goal
        if (jsonObject.getString("goal").compareTo("AND") != 0) {
            goal = new SubGoal(jsonObject.getString("goal"));
        } else {
            // if the Condition is and
            JSONArray goals = jsonObject.getJSONArray("subgoals");
            for (int i = 0; i < goals.length(); i ++) {
                String con = goals.getJSONObject(i).getString("goal");
                if (con.compareTo("OR") != 0) {
                    SubGoal subGoal = new SubGoal(con);
                    goal.addGoal(subGoal);
                } else {
                    JSONArray subs = goals.getJSONObject(i).getJSONArray("subgoals");
                    for (int j  = 0; j < subs.length(); j++) {
                        JSONObject jsonObject1 = subs.getJSONObject(j);
                        SubGoal subGoal = new SubGoal(jsonObject1.getString("goal"));
                        goal.addSub(subGoal);
                    }
                }

            }
        }
        dungeon.setGoal(goal);
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                onLoad(wall);
                entity = wall;
                break;
            case "exit":
                Exit exit = new Exit(x, y);
                onLoad(exit);
                entity = exit;
                break;
            case "switch":
                Floorswitch floorswitch = new Floorswitch(x,y);
                onLoad(floorswitch);
                entity = floorswitch;
                break;
            case "boulder":
                Boulder boulder = new Boulder(x,y);
                onLoad(boulder);
                entity = boulder;
                // map.set_object(x,y,Objects.boulder);
                break;
            case "treasure":
                Treasure treasure = new Treasure(x,y);
                onLoad(treasure);
                entity = treasure;
                break;
            case "invincibility":
                Potion potion = new Potion(x,y);
                onLoad(potion);
                entity = potion;
                break;
            case "sword":
                Sword sword = new Sword(x,y);
                onLoad(sword);
                sword.setTimes(5);
                entity = sword;
                break;
            case "bomb":
                UnlitBomb bomb = new UnlitBomb(x,y);
                onLoad(bomb);
                entity = bomb;
                break;
            case "enemy":
                Enemy enemy = new Enemy(x,y);
                onLoad(enemy);
                entity = enemy;
                dungeon.addEnemy(enemy);
                break;
            case "key":
                Key key = new Key(x,y);
                onLoad(key);
                entity = key;

                if (doorID.size() > 0) {
                    String k = doorID.get(0);
                    key.setId(k);
                    doorID.remove(k);
                } else {
                    key.setId(openID);
                    keyID.add(openID);
                    openID = UUID.randomUUID().toString();
                }
                break;
            case "door":
                Door door = new Door(x,y);
                onLoad(door);
                entity = door;

                if (keyID.size() > 0) {
                    String k = keyID.get(0);
                    door.setId(k);
                    keyID.remove(k);
                } else {
                    door.setId(openID);
                    doorID.add(openID);
                    openID = UUID.randomUUID().toString();
                }
                break;
            case "fire":
                FireBall fireBall = new FireBall(x,y);
                fireBall.setFinish(true);
                onLoad(fireBall);
                entity = fireBall;
                break;
            // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity entity);

    // TODO Create additional abstract methods for the other entities

}