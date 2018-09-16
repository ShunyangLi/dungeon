package PlayerMove;

public interface Move {

    public abstract boolean inMap(int x, int y);
    public abstract void setPosition(int val);
    public abstract void move();
}
