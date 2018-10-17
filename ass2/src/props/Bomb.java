package props;

import ass2.*;


public class Bomb implements Props {

    private int num;
    private Coordinate position;
    private Map map;
    private boolean light;

    public Bomb(Map map) {
        this.map = map;
        this.setNum(0);
        this.light = false;
    }

    @Override
    public boolean use() {
        if (this.getNum() > 0) {
            this.num--;
            return true;
        }
        return false;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean isLight()
    {
        return this.light;
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean pickUp() {
        this.num ++;
        return true;
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