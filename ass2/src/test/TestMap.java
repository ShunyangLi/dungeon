package test;

import ass2.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMap {

    private Map map = new Map();

    @Test
    public void testMapSetUp () {

        // test basic information of map
        assertEquals("test height", 16, map.getHeight());
        assertEquals("test width", 18, map.getWidth());
        // test set
        map.setHeight(10);
        map.setWidth(10);
        assertEquals("test height after set", 10, map.getHeight());
        assertEquals("test width after set", 10, map.getWidth());
    }

    @Test
    public void testSetUpMap () {
        // try get map value
        assertEquals("test get value 1", 1, map.getValue(0, 0));
        assertEquals("test get value 2", 14, map.getValue(1, 1));
        assertEquals("test get value 3", 3, map.getValue(2, 2));
        assertEquals("test get value 4", 1, map.getValue(3, 3));
        assertEquals("test get value 5", 0, map.getValue(4, 4));
        assertEquals("test get value 6", 0, map.getValue(5, 5));
        assertEquals("test get value 7", 1, map.getValue(6, 6));
        assertEquals("test get value 8", 0, map.getValue(7, 7));

        Coordinate testCo = new Coordinate(1,1,0);
        map.setupMap(testCo);
        assertEquals("test get value 2 after set", 0, map.getValue(1, 1));
    }

    @Test
    public void testGetPosition() {

        Coordinate testCo = map.getPosition(14);
        assertEquals("test get position x axis", 1, testCo.getX());
        assertEquals("test get position y axis", 1, testCo.getY());
        assertEquals("test get position value", 14, testCo.getValue());
    }

}
