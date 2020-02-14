package unsw.entitys;

import javafx.scene.Node;

public class LitBomb extends Entity {
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    private int countTime;
    private boolean finish = false;

    public LitBomb(int x, int y) {
        super(x, y);
    }

    public void setCountTime(int countTime) {
        this.countTime = countTime;
    }

    public int getCountTime() {
        return countTime;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
