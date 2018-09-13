package ass2;

public interface Enemy {
    public abstract boolean vildateMove(Coordinate coordinate);
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract Coordinate getPosition();

}
