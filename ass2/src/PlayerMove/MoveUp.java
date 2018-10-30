package PlayerMove;

import ass2.*;

public class MoveUp implements Move{

    private Player player;
    private Map map;
    private Coordinate position;

    public MoveUp(Player player) {
        this.player = player;
        this.map = this.player.getMap();
        this.position = this.player.getPosition();
    }

    @Override
    public boolean inMap(int x, int y) {
        if (x > this.map.getHeight() || y > this.map.getWidth() || x < 0 || y < 0) {
            return true;
        }

        return false;
    }

    @Override
    public void setPosition(int x) {
        this.position.setX(x);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);
    }


    @Override
    public void move() {
        int x = this.position.getX() - 1;
        int y = this.position.getY();

        if (inMap(x, y)) {
            return;
        }

        if (!this.player.isExit(x,y)) {
            if (!this.player.isDie(x,y)) {
                if (this.player.isMoveable(x,y)) {
                    if (this.player.getPreValue() != -1) {
                        this.player.setPre();
                    }
                    this.setPosition(x);
                } else if (this.map.getValue(x,y) == Objects.boulder) {

                    if(this.player.isBoulderMove(x - 1, y)) {
                        if (this.map.getValue(x - 1, y) == Objects.pit) {
                            Coordinate coordinate = new Coordinate(x - 1, y, Objects.road);
                            this.map.setupMap(coordinate);
                            this.setPosition(x);
                        } else if (this.map.getValue(x - 1, y) == Objects.road){
                            Coordinate coordinate = new Coordinate(x - 1, y, Objects.boulder);
                            this.map.setupMap(coordinate);
                            this.setPosition(x);
                        }
                    }
                }
            }
        }
    }

}
