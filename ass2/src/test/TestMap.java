package test;

import ass2.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMap {

    @Test
    public void testMapSetUp () {
        Map testMap = new Map(16,18);
        // test basic information of map
        assertEquals("test height", 16, testMap.getHeight());
        assertEquals("test width", 18, testMap.getWidth());
        // test set
        testMap.setHeight(10);
        testMap.setWidth(10);
        assertEquals("test height after set", 10, testMap.getHeight());
        assertEquals("test width after set", 10, testMap.getWidth());
    }

    @Test
    public void testSetUpMap () {
        // try get map value
        Map testMap = new Map(16,18);
        assertEquals("test get value 1", 1, testMap.getValue(0, 0));
        assertEquals("test get value 2", 14, testMap.getValue(1, 1));
        assertEquals("test get value 3", 3, testMap.getValue(2, 2));
        assertEquals("test get value 4", 1, testMap.getValue(3, 3));
        assertEquals("test get value 5", 0, testMap.getValue(4, 4));
        assertEquals("test get value 6", 0, testMap.getValue(5, 5));
        assertEquals("test get value 7", 1, testMap.getValue(6, 6));
        assertEquals("test get value 8", 0, testMap.getValue(7, 7));

        Coordinate testCo = new Coordinate(1,1,0);
        testMap.setupMap(testCo);
        assertEquals("test get value 2 after set", 0, testMap.getValue(1, 1));
    }

    @Test
    public void testGetPosition() {
        Map testMap = new Map(16,18);
        Coordinate testCo = testMap.getPosition(14);
        assertEquals("test get position x axis", 1, testCo.getX());
        assertEquals("test get position y axis", 1, testCo.getY());
        assertEquals("test get position value", 14, testCo.getValue());
    }

}
