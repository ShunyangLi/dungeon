package props;
import ass2.Coordinate;

public interface Props {

    void use();
    boolean pickUp();

    int getNum();
    void setNum(int num);
    void setMaxNum(int maxNum);
    int getMaxNum();

    boolean validateSet(Coordinate coordinate);
    boolean setPositionOnMap(int x, int y);
    Coordinate getPosition();
}