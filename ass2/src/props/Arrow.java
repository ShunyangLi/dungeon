package props;

import ass2.*;

import javax.crypto.interfaces.PBEKey;

public class Arrow implements Props{

    private int numOfuse;
    private Coordinate position;
    private Map map;

    public Arrow(Map map)
    {
        this.map = map;
        this.setNum(0);
    }

    @Override
    public void use() {
        if (this.numOfuse != 0)
        {
            this.numOfuse -- ;
        }
    }

    @Override
    public int getNum() {
        return this.numOfuse;
    }

    @Override
    public void setNum(int num) {
        this.numOfuse = num;
    }

    @Override
    public boolean pickUp() {
        this.numOfuse ++;
        return true;
    }

    @Override
    public boolean setPositionOnMap(int x, int y) {
        Coordinate co = new Coordinate(x, y, Objects.arrow);
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
