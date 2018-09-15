package PlayerMove;

import ass2.*;

public class MoveLeft implements Move {

    private Player player;
    private Map map;
    private Coordinate position;

    public MoveLeft(Player player)
    {
        this.player = player;
        this.map = this.player.getMap();
        this.position = this.player.getPosition();
    }

    public boolean inMap(int x, int y)
    {
        if (x > this.map.getHeight() || y > this.map.getWidth() || x < 0 || y < 0)
        {
            return true;
        }

        return false;
    }

    @Override
    public void setPosition(int val) {
        this.position.setY(val);
        this.position.setValue(Objects.player);
        this.map.setupMap((this.position));
    }

    @Override
    public void move() {
        int x = this.position.getX();
        int y = this.position.getY() - 1;

        if (inMap(x, y))
        {
            return;
        }

        // TODO player move left
        if (! this.player.isExit(x,y))
        {
            if (! this.player.isDie(x,y))
            {
                if (this.player.isMoveable(x,y))
                {
                    if (this.player.getPreValue() != -1)
                    {
                        this.player.setPre();
                    }

                    this.setPosition(y);
                } else if (this.map.getValue(x,y) == Objects.boulder) {

                    if(this.player.isBoulderMove(x, y - 1))
                    {

                        // want to add thi
                        if (map.getValue(x, y - 1) == Objects.pit)
                        {
                            Coordinate coordinate = new Coordinate(x, y - 1, Objects.road);
                            this.map.setupMap(coordinate);
                            this.setPosition(y);
                        } else if (this.map.getValue(x, y - 1) == Objects.road){
                            Coordinate coordinate = new Coordinate(x, y - 1, Objects.boulder);
                            this.map.setupMap(coordinate);
                            this.setPosition(y);
                        }
                    }
                }
            }
        }
    }
}
