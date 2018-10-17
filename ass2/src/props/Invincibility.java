package props;

import ass2.Coordinate;
import ass2.Map;

public class Invincibility implements Props {

    private Map map;
    public Invincibility(Map map) {
        this.map = map;
    }

    @Override
    public void use() {

    }

    @Override
    public boolean pickUp() {
        return false;
    }

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public void setNum(int num) {

    }

    @Override
    public boolean validateSet(Coordinate coordinate) {
        return false;
    }

    @Override
    public boolean setPositionOnMap(int x, int y) {
        return false;
    }

    @Override
    public Coordinate getPosition() {
        return null;
    }
}
