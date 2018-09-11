package TestPlay;

import java.awt.*;

public class Player implements Movement {
//    public static void main(String []argv)
//    {
//        Map map = new Map(10,10);
//        map.setMap();
//
//        Coordinate coordinate = new Coordinate(0,0,1);
//        map.showMap(coordinate);
//    }

    private Coordinate position;
    private String name;

    public Player () {
        position = new Coordinate(0,0,0);
        this.position.setX(0);
        this.position.setY(0);
        this.name = "Player1";
    }

    public void setPosition (int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public void moveUp () {
        if (this.position.getX() > 0)
        {
            this.position.setX(this.position.getX() - 1);
            // this.position.setX(this.position.getX());
        }
        return;
    }
    public void moveDown (int hight) {

        if (this.position.getX() < hight)
        {
            this.position.setX(this.position.getX() + 1);
        }
        return;
    }
    public void moveLeft (){

        if (this.position.getY() > 0)
        {
            this.position.setY(this.position.getY() - 1);
        }
        return;
    }
    public void moveRight (int width){

        if (this.position.getY() < width - 1)
        {
            this.position.setY(this.position.getY() + 1);
        }
        return;
    }


}