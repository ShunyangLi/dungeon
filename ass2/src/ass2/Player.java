package ass2;


import PlayerMove.MoveDown;
import PlayerMove.MoveLeft;
import PlayerMove.MoveRight;
import PlayerMove.MoveUp;
import javafx.scene.layout.CornerRadii;
import props.*;

import java.util.HashMap;

public class Player {

    private Coordinate position;
    private boolean alive;
    private Map map;
    private int preValue = -1;
    private int flag = -1;
    private boolean success;

    // this part is gonna to refactor, firstly, i am gonna use hashmap to as bag
    private HashMap<Integer, Props> bag;

    /**
     *
     * @param map which is the game map
     * @param bag is gonna disappear, i am gonna use hashmap to fix that, could be more easier
     * @param position  which is the player's current position
     */
    public Player(Map map, Bag bag, Coordinate position) {
        this.position = position;
        this.map = map;
        this.map.setupMap(this.position);
        this.alive = true;
        this.bag.put(Objects.arrow, new Arrow(map));
        this.bag.put(Objects.bomb, new Bomb(map));
        this.bag.put(Objects.sword, new Sword(map));
        this.bag.put(Objects.treasure, new Treasure(map));
        this.bag.put(Objects.hover, new Hover(map));
        this.bag.put(Objects.invincibility, new Invincibility(map));


        this.success = false;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     *
     * gonna cancel this part
     */
    public boolean isDie(int x, int y) {
        int objects = this.map.getValue(x,y);
        int[] priority = {Objects.arrow,Objects.bomb,Objects.sword,Objects.invincibility};
        if (new Objects().isEnemy(objects)) {
            for (int i = 0; i < 3; i ++) {
                if (this.bag.get(priority[i]).use()) {
                    this.position.setValue(Objects.road);
                    this.map.setupMap(this.position);
                    return true;
                }
            }
        } else if (objects == Objects.pit && this.bag.get(Objects.hover).buff) {
            this.preValue = Objects.pit;
            return true;
        }

        this.setAlive(false);
        return false;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isMoveable(int x, int y) {
        int objects = this.map.getValue(x,y);
        boolean state = false;
        if (new Objects().isProps(objects)) {
            if (this.bag.get(objects).pickUp()) {
                state = true;
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
            }
        } else if (objects == Objects.door
                && this.bag.get(Objects.key).getNum() > 0) {
            this.bag.get(Objects.key).use();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.preValue = Objects.OpenDoor;
        }
        return state;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isExit(int x, int y) {
        int value = this.map.getValue(x, y);
        if (value == Objects.exit) {
            System.out.println("SUCCESS!!");
            this.success = true;
            return true;
        }
        return false;
    }


    /**
     *
     */
    public void setPre() {
        if (this.preValue != -1 && this.flag != -1) {
            this.position.setValue(this.preValue);
            this.map.setupMap(this.position);
            this.preValue = -1;
            this.flag = -1;
        } else if (this.flag == -1){
            this.flag ++;
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isBoulderMove(int x, int y) {
        if (this.map.getValue(x,y) == Objects.road) {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (this.map.getValue(x,y) == Objects.pit) {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        }

        return false;
    }


    // move up
    public void moveUp() {
        new MoveUp(this).move();
    }

    // move down
    public void moveDown() {
        new MoveDown(this).move();
    }

    public void moveLeft() {
        new MoveLeft(this).move();
    }

    public void moveRight() {
        new MoveRight(this).move();
    }

//    public void showProps() {
//        System.out.println("You have " + this.getBag().getSword().getNum() + " Sword!" + " and you can use " + this.bag.getSword().getUseable());
//        System.out.println("You have " + this.getBag().getArrow().getNum() + " Arrow!");
//        System.out.println("You have " + this.getBag().getBomb().getNum() + " Bomb!");
//        System.out.println("You have " + this.getBag().getTreasure().getNum() + " Treasure!");
//        System.out.println("You have " + this.getBag().getKey().getNum() + " Key");
//        System.out.println("Hover " + this.getHover());
//        System.out.println("Invincibilit " + this.getInvincibility());
//    }

    public void lightBomb() {
        if (this.bag.get(Objects.bomb).use()) {
            this.preValue = Objects.bomb;
            this.flag = 0;
        }
        // this.bag.getBomb().setPositionOnMap(this.position.getX(), this.position.getY());
        // this.map.setupMap(new Coordinate(this.position.getX(), this.position.getY(), Objects.bomb));
    }

    public Map getMap() {
        return this.map;
    }

    public int getPreValue() {
        return this.preValue;
    }

    public int getFlag() {
        return this.flag;
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public HashMap<Integer, Props> getBag() {
        return bag;
    }

    public void setAlive(boolean status)
    {
        this.alive = status;
    }

    public boolean getAlive()
    {
        return this.alive;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
        this.map.setupMap(this.position);
    }

    // only use for game design and test
    public void reSetPosition(Coordinate position) {
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position = position;
        this.map.setupMap(this.position);
    }
}
