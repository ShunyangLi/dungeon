package ass2;

import java.util.ArrayList;

public class MapDatas {
    private ArrayList<MapData> mapDatas;

    public MapDatas () {
        this.mapDatas = new ArrayList<MapData>();
    }

    public int[][] getMapByIndex (int index) {
        try {
            return this.mapDatas.get(index).getData();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addMap (int[][] data) {
        MapData map = new MapData(data);
        this.mapDatas.add(map);
    }

    class MapData {
        private int[][] data;

        public MapData (int[][] data) {
            this.data = data;
        }

        public int[][] getData() {
            return this.data;
        }
    }
}


