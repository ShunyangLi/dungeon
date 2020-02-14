package unsw.entitys;

public class Key extends Entity {

    private String id;
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Key(int x, int y) {
        super(x, y);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
