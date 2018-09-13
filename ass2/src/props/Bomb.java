package props;

import ass2.*;


public class Bomb implements Props {

    private int num;
    private int maxNum;
    private Coordinate position;
    private Map map;

    // -1 代表着没有限制
    public Bomb(Map map) {
        this.map = map;
        this.setMaxNum(-1);
        this.setNum(0);
    }

    @Override
    public void use() {
        if (this.getNum() != 0) {
            this.num--;
        }
    }

    @Override
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean pickUp()
    {
        if (this.getMaxNum() == -1)
        {
            this.num ++;
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
        }
        return true;
    }

    @Override
    public int getMaxNum() {
        return this.maxNum;
    }

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public Coordinate getPosition() {
        return this.position;
    }

    @Override
    public boolean setPositionOnMap(int x, int y) {
        Coordinate co = new Coordinate(x, y, Objects.bomb);
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