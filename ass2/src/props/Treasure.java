package props;

import ass2.*;

public class Treasure {
    private int num;
    private Coordinate position;
    private Map map;
    public Treasure(Map map)
    {
        this.map = map;
        this.setNum(0);
    }

    public boolean pickUp()
    {
        this.num ++;
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        return true;
    }

    public int getNum()
    {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public Coordinate getPosition() {
        return this.position;
    }


    public boolean setPositionOnMap(int x, int y) {
        Coordinate co = new Coordinate(x, y, Objects.treasure);
        if(validateSet(co)) {
            this.position = co;
            this.map.setupMap(co);
            return true;
        } else {return false;}
    }


    public boolean validateSet(Coordinate coordinate) {
        if (this.map.getValue(coordinate.getX(), coordinate.getY()) == Objects.road) {
            return true;
        } else {return false;}
    }
}
