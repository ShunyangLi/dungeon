package unsw.entitys;

import unsw.Action.Action;
import unsw.algorithm.Location;
import unsw.conditions.Condition;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.goals.GoalCondition;
import java.util.*;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private boolean isAlive;
    private boolean isWin;
    private boolean isInvincible;
    private HashMap<String, List<Entity>> bag;
    private Dungeon dungeon;
    // the model of player choose
    private Action model;
    private GoalCondition goal;
    private String face;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.isAlive = true;
        this.isWin = false;
        this.isInvincible = false;
        this.bag = new HashMap<String, List<Entity>>();
    }

    public void moveUp() {
        Location next = new Location(getX(), getY()-1);
        Location next_next = new Location(getX(), getY()-2);
        face = Objects.face_up;

        if (model.isInvalidMove(next,next_next,this)) {
            y().set(getY()-1);
            if (checkWin()) setWin(true);
        }
    }

    public void moveDown() {
        Location next = new Location(getX(), getY()+1);
        Location next_next = new Location(getX(), getY()+2);
        face = Objects.face_down;
        if (model.isInvalidMove(next,next_next,this)) {
            y().set(getY()+1);
            if (checkWin()) setWin(true);
        }

    }

    public void moveLeft() {
        Location next = new Location(getX()-1, getY());
        Location next_next = new Location(getX()-2, getY());
        face = Objects.face_left;
        if (model.isInvalidMove(next,next_next,this)) {
            x().set(getX()-1);
            if (checkWin()) setWin(true);
        }

    }

    public void moveRight() {
        Location next = new Location(getX()+1, getY());
        Location next_next = new Location(getX()+2, getY());
        face = Objects.face_right;
        if (model.isInvalidMove(next,next_next,this)) {
            x().set(getX()+1);
            if (checkWin()) setWin(true);
        }
    }

    public void pick() {
        Location curr = new Location(getX(), getY());
        model.pickUp(curr, this);
    }

    public void litBomb() {
        Location curr = new Location(getX(), getY());
        model.litBomb(curr, this);
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public HashMap<String, List<Entity>> getBag() {
        return bag;
    }

    public void setGoal(GoalCondition goal) {
        this.goal = goal;
    }

    public GoalCondition getGoal() {
		return goal;
	}

	/**
     * @brief to check whether win
     * @return
     */
    public boolean isWin() {
        return isWin;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setWin(boolean win) {
        isWin = win;
        if (win) {
            setChanged();
            notifyObservers("You complete the dungeon");
        }
    }

    public void openDoor() {
        model.openDoor(face_next(), this);
    }


    public List<Entity> getEntityByKey(String obj) {
        return bag.get(obj);
    }

    public void setProps(String obj, List<Entity> props) {
        bag.put(obj, props);
    }

    public void useSword() {
        model.useSword(face_next(), this);
        if (checkWin()) setWin(true);
    }

    public void setModel(Action model) {
        this.model = model;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    /**
     * check whether can pick up the props in the dungeon
     * @param entity at that location which need to pick up
     * @return whether can pick up the props
     */
    public boolean pickProps(Entity entity) {
        if (entity instanceof Potion) {
            // after pick up just use it
            setInvincible(true);
            setPotionCount();
            return true;
        }

        // if entity not was sword, key or fireball, it can be picked up more than on
        if (!(entity instanceof Sword)
                && !(entity instanceof Key)
                && !(entity instanceof FireBall)) {

            List<Entity> temp = bag.get(String.valueOf(entity.getClass()));
            if (temp == null) temp = new ArrayList<Entity>();
            temp.add(entity);
            bag.put(String.valueOf(entity.getClass()), temp);

        } else {
            // otherwise just can pick up one
            List<Entity> temp = bag.get(String.valueOf(entity.getClass()));
            if (temp == null){
                temp = new ArrayList<Entity>();
            } else{
                return false;
            }
            temp.add(entity);
            bag.put(String.valueOf(entity.getClass()), temp);
        }

        if (checkWin()) setWin(true);
        return true;
    }

    /**
     * @brief get the next location of face direction
     * @return the location
     */
    public Location face_next() {
        Location location = null;

        switch (face) {
            case Objects.face_up:
                location = new Location(getX(), getY()-1);
                break;
            case Objects.face_down:
                location = new Location(getX(), getY()+1);
                break;
            case Objects.face_left:
                location = new Location(getX()-1, getY());
                break;
            case Objects.face_right:
                location = new Location(getX()+1, getY());
                break;
            default:
                break;
        }

        return location;
    }


    /**
     * @brief after 7 seconds the postion is useless
     */
    public void setPotionCount() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int countTime = 0;
            @Override
            public void run() {
                // for time count
                setInvincible(false);
                bag.put(Objects.potion, null);
                timer.cancel();
                timer.purge();

            }
        }, 7000, 1000);
    }

    /**
     * @brief check whether win
     * @return if win then true
     */
    public boolean checkWin() {
        // that means we just have one goal
        if (goal == null) return false;
        if (goal.getGoals() == null) {
            Condition condition = goal.getCondition();
            return condition.finish(this);
        } else {
            return goal.checkWin(this);
        }
    }

    public String getFace() {
        return face;
    }

    public void useFire() {
        model.useFire(this);
    }
}
