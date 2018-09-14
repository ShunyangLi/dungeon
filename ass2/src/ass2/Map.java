package ass2;

import javafx.scene.layout.CornerRadii;

import java.awt.*;

public class Map {

    private int[][] map = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,14,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,1,0,0,1,1,1,1,0,0,1,1,1,1,0,1},
            {1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,16,0,8,0,0,0,1},
            {1,0,0,15,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,1,1,0,0,0,1,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,1},
            {1,9,9,1,1,1,1,0,1,1,1,0,0,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,1},
            {1,0,0,1,0,1,1,6,0,0,0,12,0,0,0,0,0,1},
            {1,0,0,1,0,0,1,0,1,1,1,0,1,0,0,0,0,1},
            {1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,1},
            {1,0,18,1,1,1,0,0,0,0,0,0,1,1,1,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,17,0,0,0,11,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private int height;
    private int width;
    // TODO 可以用这个char [] 进行相对应的value的映射，更好的显示出来
    private char[] obj = {'r','#','e','b','f','d','k','p','O','p','B','s','a','t','I','h','P','H','S','D','C'};

    public Map (int height, int width) {
        this.height = height;
        this.width = width;
        // this.map = new int [width][height];
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
                } else {
                    System.out.print(obj[this.getValue(i,j)]);
                }
            }
            System.out.print("\n");
        }
    }

    public Coordinate getPosition(int val)
    {
        for (int i  = 0; i < height; i ++)
        {
            for (int j = 0; j < width; j ++)
            {
                if (this.getValue(i,j) == val)
                {
                    Coordinate coordinate = new Coordinate(i,j,val);
                    return coordinate;
                }
            }
        }

        return null;
    }

// 可以退休了
//    public void setWall()
//    {
//
//        Coordinate coordinate1 = new Coordinate(1,0,Objects.boulder);
//        this.setupMap(coordinate1);
//        Coordinate coordinate2 = new Coordinate(2,0,Objects.pit);
//        this.setupMap(coordinate2);
//
//        for (int i = 0; i < height/2; i ++)
//        {
//            Coordinate coordinate = new Coordinate(i,2,Objects.wall);
//            this.setupMap(coordinate);
//        }
//
//        for (int i = 4; i < height; i ++)
//        {
//            Coordinate coordinate = new Coordinate(4,i,Objects.wall);
//            this.setupMap(coordinate);
//        }
//
//        for (int i = 6; i < height - 2; i ++)
//        {
//            Coordinate coordinate = new Coordinate(i,6,Objects.wall);
//            this.setupMap(coordinate);
//        }
//
//    }
}
