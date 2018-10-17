package props;

import ass2.*;


public class Sword implements Props {

    private int num;
    private int maxNum;
    private int useable;
    private Coordinate position;
    private Map map;

    public Sword (Map map) {
        this.setNum(0);
        this.map = map;
        this.setUseable(0);
        this.setMaxNum(1);
    }

    public void setUseable(int useable) {
        this.useable = useable;
    }

    public int getUseable() {
        return useable;
    }

    @Override
    public boolean use() {
        if(this.useable > 0) {
            this.useable--;
            if(this.useable == 0) {
                this.setNum(0);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean pickUp() {
        if(this.num < this.maxNum) {
            this.num++;
            this.setUseable(5);
            return true;
        } else {return false;}
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int getNum() {
        return this.num;
    }


    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }


    public int getMaxNum() {
        return this.maxNum;
    }


    @Override
    public Coordinate getPosition() {
        return this.position;
    }

    @Override
    public boolean setPositionOnMap(int x, int y) {
        Coordinate co = new Coordinate(x, y, Objects.sword);
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