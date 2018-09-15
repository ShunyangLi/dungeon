package PlayerMove;

import ass2.*;

public class MoveDown implements Move {

    private Player player;
    private Map map;
    private Coordinate position;

    public MoveDown(Player player)
    {
        this.player = player;
        this.map = this.player.getMap();
        this.position = this.player.getPosition();
    }

    @Override
    public void setPosition(int val) {
        this.position.setX(val);
        this.position.setValue(Objects.player);
        this.map.setupMap((this.position));
    }

    @Override
    public void move() {

        int x = this.position.getX() + 1;
        int y = this.position.getY();

        if (! this.player.isExit(x,y))
        {
            if (! this.player.isDie(x,y))
            {
                // 如果可以移动的话直接移动
                if (this.player.isMoveable(x,y))
                {
                    if (this.player.getPreValue() != -1)
                    {
                        this.player.setPre();
                    }

                    this.setPosition(x);
                } else if (this.map.getValue(x,y) == Objects.boulder) {
                    if(this.player.isBoulderMove(x + 1, y))
                    {
                        if (this.map.getValue(x + 1, y) == Objects.pit)
                        {
                            Coordinate coordinate = new Coordinate(x + 1, y, Objects.road);
                            this.map.setupMap(coordinate);
                            this.setPosition(x);
                        } else if (this.map.getValue(x + 1, y) == Objects.road){
                            Coordinate coordinate = new Coordinate(x + 1, y, Objects.boulder);
                            this.map.setupMap(coordinate);
                            this.setPosition(x);
                        }
                    }
                }
            }
        }
    }
}
