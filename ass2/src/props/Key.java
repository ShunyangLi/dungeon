package props;

import ass2.*;

public class Key implements Props {

    private int num;
    private Coordinate position;
    private Map map;

    public Key(Map map)
    {
        this.map = map;
        this.setNum(0);
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public boolean pickUp() {

        if (this.getNum() != 0)
        {
            this.num ++;
            return true;
        }

        return false;
    }

    @Override
    public void use() {
        if (this.getNum() != 0)
        {
            this.num --;
        }
    }

    @Override
    public boolean setPositionOnMap(int x, int y) {
        Coordinate co = new Coordinate(x, y, Objects.key);
        if(validateSet(co)) {
            this.position = co;
            this.map.setupMap(co);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateSet(Coordinate coordinate) {

        if (this.map.getValue(coordinate.getX(), coordinate.getY()) == Objects.road) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Coordinate getPosition() {
        return this.position;
    }
}
