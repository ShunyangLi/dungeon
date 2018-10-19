package ass2;

import javafx.scene.layout.CornerRadii;

import java.awt.*;

public class Map {

    /**
     * @brief just init the map, and get one map which can play
     */
    private int[][] map = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,14,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,3,1,0,0,1,1,1,1,0,0,1,1,1,1,0,1},
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
            {1,0,0,0,0,0,0,0,0,0,0,0,17,0,0,11,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private int height;
    private int width;

    /**
     *
     * @param height the height of the map
     * @param width the width of the map
     * @brief according to the controller, it can be any size of double array
     *          this init is without new double array, and use the origin map value
     */
    public Map (int height, int width) {
        this.height = height;
        this.width = width;
        // this.map = new int [width][height];
    }

    /**
     *
     * @param height the height of the map
     * @param width the width of the map
     * @param map map is according to the controller generate a new map, so the map can reset the size and value
     */
    public Map (int height, int width, int[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
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
        return this.map[x][y];
    }

    public void setupMap(Coordinate coordinate)
    {
        this.map[coordinate.getX()][coordinate.getY()] = coordinate.getValue();
    }

    /**
     *
     * @param val which is the Objects, and get the Objects position
     * @return  coordinate is the position and Onjects
     */
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

}
