package ass2;

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

    public int getValue(Coordinate coordinate)
    {
        return map[coordinate.getX()][coordinate.getY()];
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
                System.out.print(this.map[i][j]);
            }
            System.out.print("\n");
        }
    }
}
