package PlayerMove;

import ass2.*;

public class MoveDown implements Move {

    private Player player;
    private Map map;
    private Coordinate position;

    public MoveDown(Player player) {
        this.player = player;
        this.map = this.player.getMap();
        this.position = this.player.getPosition();
    }

    /**
     *
     * @param x x is the x coordinate in the map
     * @param y y is the x coordinate in the map
     * @return return boolean
     * this function is detect whether exit the map algin
     */
    @Override
    public boolean inMap(int x, int y) {
        if (x > this.map.getHeight() || y > this.map.getWidth() || x < 0 || y < 0) {
            return true;
        }

        return false;
    }

    /**
     *
     * @param val which is Objects, and make current position as the objects
     */
    @Override
    public void setPosition(int val) {
        this.position.setX(val);
        this.position.setValue(Objects.player);
        this.map.setupMap((this.position));
    }

    /**
     * thi function will control move, and make the player around in the map
     */
    @Override
    public void move() {

        int x = this.position.getX() + 1;
        int y = this.position.getY();

        if (inMap(x, y)) {
            return;
        }

        if (! this.player.isExit(x,y)) {
            if (! this.player.isDie(x,y)) {
                // if can move, then just move
                if (this.player.isMoveable(x,y)) {
                    if (this.player.getPreValue() != -1) {
                        this.player.setPre();
                    }
                    // set position
                    this.setPosition(x);
                } else if (this.map.getValue(x,y) == Objects.boulder) {
                    // if found blouder, then can push the boulder
                    if(this.player.isBoulderMove(x + 1, y)) {
                        if (this.map.getValue(x + 1, y) == Objects.pit) {
                            Coordinate coordinate = new Coordinate(x + 1, y, Objects.road);
                            this.map.setupMap(coordinate);
                            this.setPosition(x);
                        } else if (this.map.getValue(x + 1, y) == Objects.road){
                            Coordinate coordinate = new Coordinate(x + 1, y, Objects.boulder);
                            this.map.setupMap(coordinate);
                            this.setPosition(x);
                        }
                    }
                }
            }
        }
    }
}
