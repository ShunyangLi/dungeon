package props;

import ass2.Coordinate;
import ass2.Map;

public class Hover implements Props {

    private Map map;
    private boolean buff;


    public Hover(Map map) {
        this.map = map;
        this.buff = false;
    }

    @Override
    public boolean use() {
        if (buff) {
            return false;
        } else {
            this.buff = true;
            return true;
        }
    }

    @Override
    public boolean pickUp() {
        if (this.use()) {
            return true;
        }
        return false;
    }

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public void setNum(int num) {
        return;
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
