package ass2;

import javafx.scene.layout.CornerRadii;

import java.awt.*;

public class Map {

    private int[][] map;
    private MapDatas mapDatas = new MapDatas();
    private int height;
    private int width;

    /**
     * @brief just init the map, and get one map which can play
     */
    public Map () {
        this.height = 16;
        this.width = 18;
        this.map = mapDatas.getMapByIndex(0);
    }

    /**
     * @param index the index of the map data
     * @brief just init the map, and get one map which can play
     */
    public Map (int index) {
        this.height = 16;
        this.width = 18;
        this.map = mapDatas.getMapByIndex(index);
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

    public int getValue(int x, int y) {
        try {
            return this.map[x][y];
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public void setupMap(Coordinate coordinate) {
        try {
            this.map[coordinate.getX()][coordinate.getY()] = coordinate.getValue();
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     *
     * @param val which is the Objects, and get the Objects position
     * @return  coordinate is the position and Objects
     */
    public Coordinate getPosition(int val) {
        for (int i  = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                if (this.getValue(i,j) == val) {
                    Coordinate coordinate = new Coordinate(i,j,val);
                    return coordinate;
                }
            }
        }
        return null;
    }

    public int[][] getMap() {
        return map;
    }
}
