package TestPlay;

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

    public void setMap()
    {
        for (int i = 0; i < height; i ++)
        {
            for (int j = 0; j < width; j++)
            {
                this.map[i][j] = 0;
            }
        }
    }

    public void showMap(Coordinate coordinate)
    {
        for (int i = 0; i < height; i ++)
        {
            for (int j = 0; j < width; j++)
            {
                if (i == coordinate.getX() && j == coordinate.getY())
                {
                    System.out.print("*");
                } else {
                    System.out.print(this.map[i][j]);
                }
            }
            System.out.print("\n");
        }
    }
}
