package ass2;

import javafx.scene.layout.CornerRadii;

import java.awt.*;

public class Map {
    private int[][] map;
    private int height;
    private int width;

    public Map (int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int [width][height];
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getValue(int x, int y)
    {
        return map[x][y];
    }

    public void setupMap(Coordinate coordinate)
    {
        this.map[coordinate.getX()][coordinate.getY()] = coordinate.getValue();
    }

    public void showMap(Coordinate coordinate)
    {
        for (int i = 0; i < height; i ++)
        {
            for (int j = 0; j < width; j++)
            {
                if (this.getValue(i,j) == Objects.road)
                {
                    System.out.print(" ");
                } else if (this.getValue(i,j) == Objects.player) {
                    System.out.print("&");
                } else if (this.getValue(i,j) == Objects.wall) {
                    System.out.print("*");
                } else if (this.getValue(i,j) == Objects.pit){
                    System.out.print("p");
                } else {
                    System.out.print("#");
                }
            }
            System.out.print("\n");
        }
    }

    public void setWall()
    {

        Coordinate coordinate1 = new Coordinate(1,0,Objects.OpenDoor);
        this.setupMap(coordinate1);

        for (int i = 0; i < height/2; i ++)
        {
            Coordinate coordinate = new Coordinate(i,2,Objects.wall);
            this.setupMap(coordinate);
        }

        for (int i = 4; i < height; i ++)
        {
            Coordinate coordinate = new Coordinate(4,i,Objects.wall);
            this.setupMap(coordinate);
        }

        for (int i = 6; i < height - 2; i ++)
        {
            Coordinate coordinate = new Coordinate(i,6,Objects.wall);
            this.setupMap(coordinate);
        }
    }
}
