package props;

import ass2.*;

public class Key implements Props {

    private int num;
    private int maxNum;
    private Coordinate position;
    private Map map;

    public Key(Map map) {
        this.map = map;
        this.setNum(0);
        this.setMaxNum(1);
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
        if(this.num < this.maxNum) {
            this.num++;
            return true;
        } else {return false;}
    }

    @Override
    public boolean use() {
        if (this.num > 0) {
            this.num --;
            return true;
        }
        return false;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }


    public int getMaxNum() {
        return this.maxNum;
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