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
//    private boolean hover;
//    private int invincibility;
    private Map map;
    // private Bag bag;
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
        // this.bag = bag;
//        this.hover = false;
//        this.invincibility = 0;
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
        int value = this.map.getValue(x,y);
        // 如果是pit的话直接死亡, setAlibe = false
        if (value == Objects.pit && ! this.hover)
        {
            System.out.println("Sorry you fall into pit, you die!!");
            this.setAlive(false);
            return true;
        }
        // 检测，如果碰到敌人
        if (value == Objects.hunter || value == Objects.strategist
                || value == Objects.hound || value == Objects.coward)
        {
            // 如果没有武器也没有无敌药水的话直接死亡
            if (this.bag.getSword().getNum() == 0 && this.bag.getArrow().getNum() == 0 && this.getInvincibility() == 0)
            {
                System.out.println("You have been killed!!");
                this.setAlive(false);
                return true;
            }

            // 如果有无敌药水的话， 敌人直接被杀死，下一个位置被改成road
            if (this.getInvincibility() != 0)
            {
                this.setInvincibility(this.getInvincibility() - 1);
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                Coordinate up = new Coordinate(x,y,Objects.road);
                this.map.setupMap(up);
                return false;

            } else if (this.bag.getSword().getNum() != 0) {
                // 如果没有无敌药水，检测是不是有sword，如果有sword，直接杀死敌人，把当前的位置设置成road
                this.bag.getSword().use();
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                Coordinate up = new Coordinate(x,y,Objects.road);
                this.map.setupMap(up);
                return false;
            } else if (this.bag.getArrow().getNum() != 0) {
                // 如果没有sword，检测arrow，如果有arrow，直接杀死敌人，然后设置当前位置road
                this.bag.getArrow().use();
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                Coordinate up = new Coordinate(x,y,Objects.road);
                this.map.setupMap(up);
                return false;

            } else {
                // 如果什么都没有，直接gg
                System.out.println("You have been killed!!");
                return true;
            }
        }
        return false;
    }


    // 检测能不能移动，以及在移动中捡的东西
    // 写出来一个pre用来储存open door或者pit， 如果pre == 这两个值，然后把当前的值替换为pre
    // TODO 还没检查嗑无敌药水的模式，因为不知道咋弄
    public boolean isMoveable(int x, int y)
    {
//        boolean flag = false;
        int value = this.map.getValue(x,y);
        // 如果是墙的话不能移动
        if (value == Objects.wall) {
            return false;
        } else if (value == Objects.sword) {
            // 如果是sword的话，先检测是不是已经有了，如果咩有，直接捡起来，否则捡不起来，也过不去
            if (! this.bag.getSword().pickUp())
            {
                System.out.println("You already get one sword");
                return false;
            } else {
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                return true;
            }
        } else if (value == Objects.arrow) {
            // 如果为arrow的话直接捡起来
            this.bag.getArrow().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.treasure) {
            this.bag.getTreasure().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.bomb) {
            this.bag.getBomb().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.key) {
            this.bag.getKey().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.pit && this.hover) {
            // pre是为了player走过之后把这个地方改掉
            this.preValue = Objects.pit;
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.OpenDoor) {
            // pre是为了player走过之后把这个地方改掉
            this.preValue = Objects.OpenDoor;
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.door && this.bag.getKey().getNum() > 0) {
            // 当这个门打开以后这个就是OpenDoor， 然后设置pre的value
            this.preValue = Objects.OpenDoor;
            this.bag.getKey().use();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.hover) {
            // 当是飞行药水时，直接设置
            this.setHover(true);
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.road) {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.invincibility) {
            this.setInvincibility(3);
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        }
        return false;
    }

    // 看一下是不是exit
    public boolean isExit(int x, int y)
    {
        int value = this.map.getValue(x, y);
        if (value == Objects.exit)
        {
            System.out.println("SUCCESS!!");
            this.success = true;
            return true;
        }
        return false;
    }


    // 把人物覆盖的object显示出来
    public void setPre()
    {
        if (this.preValue != -1 && this.flag != -1)
        {
            this.position.setValue(this.preValue);
            this.map.setupMap(this.position);
            this.preValue = -1;
            this.flag = -1;
        } else if (this.flag == -1){
            this.flag ++;
        }
    }

    // 判断是不是road 或者 pit
    // 如果可以移动的话把当前的位置替换为road
    public boolean isBoulderMove(int x, int y)
    {
        if (this.map.getValue(x,y) == Objects.road)
        {
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
    public void moveUp()
    {
        new MoveUp(this).move();
    }

    // move down
    public void moveDown()
    {
        new MoveDown(this).move();
    }

    public void moveLeft()
    {
        new MoveLeft(this).move();
    }

    public void moveRight()
    {
        new MoveRight(this).move();
    }

    public void showProps()
    {
        System.out.println("You have " + this.getBag().getSword().getNum() + " Sword!" + " and you can use " + this.bag.getSword().getUseable());
        System.out.println("You have " + this.getBag().getArrow().getNum() + " Arrow!");
        System.out.println("You have " + this.getBag().getBomb().getNum() + " Bomb!");
        System.out.println("You have " + this.getBag().getTreasure().getNum() + " Treasure!");
        System.out.println("You have " + this.getBag().getKey().getNum() + " Key");
        System.out.println("Hover " + this.getHover());
        System.out.println("Invincibilit " + this.getInvincibility());
    }

    public void lightBomb()
    {
        if (this.bag.getBomb().getNum() > 0)
        {
            this.bag.getBomb().use();
            this.bag.getBomb().setLight(true);
        }
        this.preValue = Objects.bomb;
        this.flag = 0;

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

    public Bag getBag() {
        return this.bag;
    }

    public int getInvincibility() {
        return this.invincibility;
    }

    public boolean getHover()
    {
        return this.hover;
    }

    public void setHover(boolean status)
    {
        this.hover = status;
    }

    public void setInvincibility(int invincibility) {
        this.invincibility = invincibility;
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
