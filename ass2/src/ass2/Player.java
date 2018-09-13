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
    public boolean isDie(int x, int y)
    {

        boolean flag = false;
        int value = this.map.getValue(x,y);

        if (value == Objects.pit)
        {
            return true;
        }
        // 在检测的时候后否直接把敌人杀死，并且改成road
        if (value == Objects.hunter || value == Objects.strategist
                || value == Objects.hound || value == Objects.coward)
        {
            if (this.bag.getSword().getNum() == 0 && this.bag.getArrow().getNum() == 0 && this.getInvincibility() == 0)
            {
                System.out.println("You have been killed!!");
                return true;
            }

            if (this.getInvincibility() != 0)
            {
                this.position.setValue(Objects.player);
                this.map.setupMap(this.position);
                flag = false;
            } else if (this.bag.getSword().getNum() != 0) {
                this.bag.getSword().use();
                this.position.setValue(Objects.player);
                this.map.setupMap(this.position);
                flag = false;
            } else if (this.bag.getArrow().getNum() != 0) {
                this.bag.getArrow().use();
                this.position.setValue(Objects.player);
                this.map.setupMap(this.position);
                flag = false;
            } else {
                System.out.println("You have been killed!!");
                flag = true;
            }
//            // if sowrd && arrow && not invincibity == 0 then die
//            if (this.bag.getSword().getNum() == 0)
//            {
//                System.out.println("You have been killed by enemey!!");
//                return true;
//            } else {
//                // else if invincibity, then do not use weapon, else choose a weapon
//                return false;
//            }
        }
        return flag;
    }

    public boolean isMoveable(int x, int y)
    {
//        boolean flag = false;
        int value = this.map.getValue(x,y);

        // if this is a door (not open) and wall, then return false
        if (value == Objects.wall)
        {
            return false;
        } else if (value == Objects.sword) {
            // if this is a sword if not have, then pickup else not moveable

            if (! this.bag.getSword().pickUp())
            {
                System.out.println("You already get one sword");
                return false;
            } else {
                return true;
            }
        } else if (value == Objects.arrow) {

            this.bag.getArrow().pickUp();
            return true;
        } else if (value == Objects.treasure) {
            this.bag.getTreasure().pickUp();
            return true;
        } else if (value == Objects.bomb) {
            this.bag.getBomb().pickUp();
        } else if (value == Objects.key) {
            // TODO need to be fiexd;
            return true;
        } else if (value == Objects.pit && this.hover) {
            return true;
        } else if (value == Objects.OpenDoor) {
            return true;
        } else if (value == Objects.door && this.bag.getKey().getNum() != 0) {
            // TODO
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
        Coordinate coordinate = new Coordinate(this.position.getX() + 1, this.position.getY(), Objects.player);

        // TODO
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position.setX(this.position.getX() + 1);
        this.position.setValue(Objects.player);
        this.map.setupMap((this.position));

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
