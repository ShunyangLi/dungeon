package unsw.entitys;

public class Sword extends Entity {
    private int times;
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Sword(int x, int y) {
        super(x, y);
        times = 5;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
