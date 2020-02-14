package unsw.entitys;

public class FireBall extends Entity {

    private boolean finish;

    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public FireBall(int x, int y) {
        super(x, y);
    }


    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isFinish() {
        return finish;
    }
}
