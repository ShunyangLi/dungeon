package ass2;
import javafx.scene.layout.CornerRadii;
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


    public boolean vildateMove(Coordinate coordinate)
    {
        boolean flag = false;
        if (this.map.getValue(coordinate.getX(),coordinate.getY()) == Objects.road)
        {
            flag = true;
        }

        if(this.map.getValue(coordinate.getX(),coordinate.getY()) == Objects.pit)
        {
            this.setAlive(false);
            System.out.println("The player is died, game over!!");
        }

        if (this.map.getValue(coordinate.getX(),coordinate.getY()) == Objects.sword)
        {
            if (this.bag.getSword().getMaxNum() > this.bag.getSword().getNum())
            {
                this.bag.getSword().pickUp();
                System.out.println("大宝剑归位！！");
            } else {
                System.out.println("You already get one 大宝剑");

            }
            flag = true;
        }


        return flag;
    }


    public void moveUp()
    {
        Coordinate coordinate = new Coordinate(this.position.getX() - 1 ,this.position.getY(), Objects.player);

        if (vildateMove(coordinate))
        {

            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.position.setX(this.position.getX() - 1);
            this.position.setValue(Objects.player);
            this.map.setupMap(this.position);
        }

    }

    public void moveDown()
    {
        Coordinate coordinate = new Coordinate(this.position.getX() + 1, this.position.getY(), Objects.player);
        if (vildateMove(coordinate))
        {

            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.position.setX(this.position.getX() + 1);
            this.position.setValue(Objects.player);
            this.map.setupMap((this.position));
        }
    }

    public void moveLeft()
    {
        Coordinate coordinate = new Coordinate(this.position.getX(), this.position.getY() - 1,Objects.player);
        if (vildateMove(coordinate))
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.position.setY(this.position.getY() - 1);
            this.position.setValue(Objects.player);
            this.map.setupMap(this.position);
        }
    }

    public void moveRight()
    {
        Coordinate coordinate = new Coordinate(this.position.getX(),this.position.getY() + 1,this.position.getValue());
        if (vildateMove(coordinate))
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.position.setY(this.position.getY() + 1);
            this.position.setValue(Objects.player);
            this.map.setupMap(this.position);
        }
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
