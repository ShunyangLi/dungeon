package ass2;
import props.*;
import java.util.List;


public class Player {

    private Coordinate position;
    private boolean alive;
    private boolean hover;
    private int invincibility;
    private Map map;
    private Bag bag;

    public Player(Map map, Bag bag)
    {
        this.position = new Coordinate(0,0,Objects.player);
        this.map = map;
        this.map.setupMap(this.position);
        this.alive = true;
        this.bag = bag;
        this.hover = false;
        this.invincibility = 0;
    }

    // 如果放下炸弹的话，等三秒以后把周围四个格子改成fire， if fire then die
    // 少一个检测炸弹的范围，如果这个人在炸弹的范围内，直接gg
    public boolean isDie(int x, int y)
    {
        Coordinate coordinate = new Coordinate(x,y,-1);
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
                coordinate.setValue(Objects.road);
                this.map.setupMap(coordinate);
                this.map.setupMap(this.position);
                return false;

            } else if (this.bag.getSword().getNum() != 0) {
                // 如果没有无敌药水，检测是不是有sword，如果有sword，直接杀死敌人，把当前的位置设置成road
                this.bag.getSword().use();
                coordinate.setValue(Objects.road);
                this.map.setupMap(coordinate);
                return false;
            } else if (this.bag.getArrow().getNum() != 0) {
                // 如果没有sword，检测arrow，如果有arrow，直接杀死敌人，然后设置当前位置road
                this.bag.getArrow().use();
                coordinate.setValue(Objects.road);
                this.map.setupMap(coordinate);
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
    public boolean isMoveable(int x, int y)
    {
//        boolean flag = false;
        int value = this.map.getValue(x,y);

        // 如果是墙的话不能移动
        if (value == Objects.wall)
        {
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
            // TODO 大bug
            return true;
        } else if (value == Objects.OpenDoor) {
            // 如果是open door需要fix
            // TODO 大bug
            return true;
        } else if (value == Objects.door && this.bag.getKey().getNum() != 0) {
            // TODO
            this.bag.getKey().use();
            this.position.setValue(Objects.OpenDoor);
            this.map.setupMap(this.position);
            return true;
        }
        return false;
    }

    public boolean isExit(int x, int y)
    {
        int value = this.map.getValue(x, y);
        if (value == Objects.exit)
        {
            System.out.println("SUCCESS!!");
            return true;
        }
        return false;
    }


    // 暂定在move里面设置下一个坐标的value
    // moveable 只设置物品的坐标

    // 需要设置face的朝向， maybe implement later

    public void moveUp()
    {
        Coordinate coordinate = new Coordinate(this.position.getX() - 1 ,this.position.getY(), Objects.player);

        // TODO
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position.setX(this.position.getX() - 1);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);


    }

    public void moveDown()
    {
        // Coordinate coordinate = new Coordinate(this.position.getX() + 1, this.position.getY(), Objects.player);
        int x = this.position.getX() + 1;
        int y = this.position.getY();
        // TODO 试着去写
        if (! isExit(x,y))
        {
            if (! isDie(x,y))
            {
                if (isMoveable(x,y))
                {
                    this.position.setX(this.position.getX() + 1);
                    this.position.setValue(Objects.player);
                    this.map.setupMap((this.position));
                }
            }
        }
//        this.position.setValue(Objects.road);
//        this.map.setupMap(this.position);
//        this.position.setX(this.position.getX() + 1);
//        this.position.setValue(Objects.player);
//        this.map.setupMap((this.position));

    }

    public void moveLeft()
    {
        Coordinate coordinate = new Coordinate(this.position.getX(), this.position.getY() - 1,Objects.player);

        // TODO
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position.setY(this.position.getY() - 1);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);

    }

    public void moveRight()
    {
        Coordinate coordinate = new Coordinate(this.position.getX(),this.position.getY() + 1,this.position.getValue());

        // TODO
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position.setY(this.position.getY() + 1);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);

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

}
