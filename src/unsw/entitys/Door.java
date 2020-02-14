package unsw.entitys;

public class Door extends Entity {
    private String id;
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Door(int x, int y) {
        super(x, y);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
