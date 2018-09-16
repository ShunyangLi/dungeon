package Enemy;

import ShortestPath.Location;
import ShortestPath.Path;
import ass2.*;

import java.util.ArrayList;

public class Coward extends Enemy {
    private Coordinate position;
    private Map map;
    private boolean alive;

    public Coward(Map map, Coordinate position)
    {
        this.position = position;
        this.map = map;
        this.map.setupMap(this.position);
        this.alive = true;
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
    public boolean autoMove() {
        Path path = new Path();
        ArrayList<Location> road = path.path(this.map, this.map.getPosition(Objects.coward), this.map.getPosition(Objects.player));

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
            this.map.setupMap(new Coordinate(x, y, Objects.coward));
            this.position.setX(x);
            this.position.setY(y);
            this.position.setValue(Objects.coward);
            break;
        }

        return true;
    }

    // TODO 这个地方需要改，要不然会gg
    // 需要考虑一下移动的方向，要不然会gg的， 会报错的
    @Override
    public void hide() {
        Path path = new Path();
        Coordinate temp = new Coordinate(1 , 2, -1);

        ArrayList<Location> road = path.path(this.map, this.map.getPosition(Objects.coward), this.map.getPosition(Objects.exit));

        // System.out.println(road.size());
        if (road == null || road.size() == 0)
        {
            return;
        }

        // road.add(0,new Location(this.map.getPosition(Objects.player).getX(),this.map.getPosition(Objects.player).getX()));

        for (int i  = road.size() - 1; i >= 0; i -- )
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);

            // System.out.println("x " + road.get(i).getX());

            int x = road.get(i).getX();
            int y = road.get(i).getY();

            if (this.map.getValue(x,y) == Objects.player)
            {
                return;
            }
            this.map.setupMap(new Coordinate(x, y, Objects.coward));
            this.position.setX(x);
            this.position.setY(y);
            this.position.setValue(Objects.coward);
            break;
        }
    }

    @Override
    public int getVal(Enemy enemy) {
        return Objects.coward;
    }

    @Override
    public void setAlive(boolean s) {
        this.alive = s;
    }

    @Override
    public boolean alive() {
        return this.alive;
    }
}
