package props;
import ass2.Coordinate;
import ass2.Map;

public interface Props {

    boolean use();
    boolean pickUp();

    int getNum();
    void setNum(int num);
    boolean validateSet(Coordinate coordinate);
    boolean setPositionOnMap(int x, int y);
    Coordinate getPosition();

    void setMap(Map map);

    boolean isBuff();
}