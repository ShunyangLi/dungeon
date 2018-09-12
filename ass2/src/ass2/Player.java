package ass2;

import java.util.List;

public class Player implements NPC{

    private Coordinate position;
    private Map map;
    private String name;
    private boolean alive;
    private List bag;

    public Player(Map map)
    {
        this.position = new Coordinate(0,0,Objects.player);
        this.map = map;
        this.map.setupMap(this.position);
        this.name = "Dun";
        this.alive = true;
    }

    @Override
    public boolean vildateMove(Coordinate coordinate)
    {
        boolean flag = false;
        if (this.map.getValue(coordinate.getX(),coordinate.getY()) == Objects.road)
        {
            flag = true;
        }

        return flag;
    }

    @Override
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

    @Override
    public void moveDown()
    {
        Coordinate coordinate = new Coordinate(this.position.getX() + 1, this.position.getY(),Objects.player);
        if (vildateMove(coordinate))
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.position.setX(this.position.getX() + 1);
            this.position.setValue(Objects.player);
            this.map.setupMap((this.position));
        }
    }

    @Override
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

    @Override
    public void moveRigt()
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

    @Override
    public Coordinate getPosition() {
        return this.position;
    }
}
