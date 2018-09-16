package Enemy;

import ShortestPath.Location;
import ShortestPath.Path;
import ass2.*;

import java.util.ArrayList;

public class Hound extends Enemy {
    private Coordinate position;
    private Map map;
    private boolean alive;


    public Hound(Map map, Coordinate position)
    {
        this.position = position;
        this.map = map;
        this.map.setupMap(this.position);
        this.alive = true;
    }

    @Override
    public boolean autoMove() {
        Path path = new Path();
        ArrayList<Location> road = path.path(this.map, this.map.getPosition(Objects.hound), this.map.getPosition(Objects.player));

        if (road == null || road.size() == 0)
        {
            return false;
        }

        road.add(0,new Location(this.map.getPosition(Objects.player).getX(),this.map.getPosition(Objects.player).getX()));

        for (int i  = road.size() - 1; i >= 0; i -- )
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);

            // System.out.println("x " + road.get(i).getX());

            int x = road.get(i).getX();
            int y = road.get(i).getY();

            if (this.map.getValue(x,y) == Objects.player)
            {
                return false;
            }
            this.map.setupMap(new Coordinate(x, y, Objects.hound));
            this.position.setX(x);
            this.position.setY(y);
            this.position.setValue(Objects.hound);
            break;
        }
        return true;
    }

    @Override
    public void moveDown(Enemy enemy) {
        super.moveDown(this);
    }

    @Override
    public void moveUp(Enemy enemy) {
        super.moveUp(this);
    }

    @Override
    public void moveRight(Enemy enemy) {
        super.moveRight(this);
    }

    @Override
    public void moveLeft(Enemy enemy) {
        super.moveLeft(this);
    }

    @Override
    public Coordinate getPosition(Enemy enemy) {
        return this.position;
    }

    @Override
    public Map getMap(Enemy enemy) {
        return this.map;
    }

    @Override
    public int getVal(Enemy enemy) {
        return Objects.hound;
    }
    @Override
    public void setAlive(boolean s) {
        this.alive = s;
    }

    @Override
    public boolean alive() {
        return this.alive;
    }

    @Override
    public void hide() {

    }
}

