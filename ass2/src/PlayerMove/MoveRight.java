package PlayerMove;
import ass2.*;

public class MoveRight implements Move {

    private Player player;
    private Map map;
    private Coordinate position;

    public MoveRight(Player player)
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
        int y = this.position.getY() + 1;

        if (inMap(x, y))
        {
            return;
        }

        // TODO 试着去写
        if (! this.player.isExit(x,y))
        {
            if (! this.player.isDie(x,y))
            {
                // 如果可以移动的话直接移动
                if ( this.player.isMoveable(x,y))
                {
                    if (this.player.getPreValue() != -1)
                    {
                        this.player.setPre();
                    }
                    this.setPosition(y);
                } else if (this.map.getValue(x,y) == Objects.boulder) {

                    if(this.player.isBoulderMove(x, y + 1))
                    {
                        if (this.map.getValue(x, y + 1) == Objects.pit)
                        {
                            // 判断是不是road，如果是的话直接push
                            Coordinate coordinate1 = new Coordinate(x, y + 1, Objects.road);
                            this.map.setupMap(coordinate1);
                            this.setPosition(y);
                        } else if (this.map.getValue(x, y - 1) == Objects.road){
                            Coordinate coordinate1 = new Coordinate(x, y + 1, Objects.boulder);
                            this.map.setupMap(coordinate1);
                            this.setPosition(y);
                        }
                    }
                }
            }
        }
    }
}
