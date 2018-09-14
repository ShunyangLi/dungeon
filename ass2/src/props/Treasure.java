package props;

import ass2.*;

public class Treasure implements Props {
    private int num;
    private Coordinate position;
    private Map map;
    public Treasure(Map map)
    {
        this.map = map;
        this.setNum(0);
    }

    @Override
    public boolean pickUp()
    {
        this.num ++;
        return true;
    }

    @Override
    public int getNum()
    {
        return this.num;
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void use() {
        this.num --;
    }

    @Override
    public Coordinate getPosition() {
        return this.position;
    }

    @Override
    public boolean setPositionOnMap(int x, int y) {
        Coordinate co = new Coordinate(x, y, Objects.treasure);
        if(validateSet(co)) {
            this.position = co;
            this.map.setupMap(co);
            return true;
        } else {return false;}
    }

    @Override
    public boolean validateSet(Coordinate coordinate) {
        if (this.map.getValue(coordinate.getX(), coordinate.getY()) == Objects.road) {
            return true;
        } else {return false;}
    }
}
