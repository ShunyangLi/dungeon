package ass2;

public interface NPC {
    public abstract boolean vildateMove(Coordinate coordinate);
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRigt();
    public abstract Coordinate getPosition();

}
