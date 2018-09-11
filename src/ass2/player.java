package ass2;

import java.util.List;

public class player {

    private Coordinate position;
    private Map map;
    private String name;
    private boolean alive;
    private List bag;

    public player(Map map)
    {
        this.position = new Coordinate(0,0,Objects.player);
        this.map = map;
        this.name = "Dun";
        this.alive = true;
    }

    public boolean vildateMove(Coordinate coordinate)
    {
        boolean flag = false;
        if (this.map.getValue(coordinate) == Objects.road)
        {
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
            this.position.setX(this.position.getX() + 1);
            this.position.setValue(Objects.player);
            this.map.setupMap(this.position);
        }

    }

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

    public void moveLeft()
    {
        Coordinate coordinate = new Coordinate(this.position.getY() - 1, this.position.getY(),Objects.player);
        if (vildateMove(coordinate))
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            this.position.setY(this.position.getY() - 1);
            this.position.setValue(Objects.player);
            this.map.setupMap(this.position);
        }
    }

    public void moveRigt()
    {
        Coordinate coordinate = new Coordinate(this.position.getY() + 1, this.position.getY(),this.position.getValue());
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
}
