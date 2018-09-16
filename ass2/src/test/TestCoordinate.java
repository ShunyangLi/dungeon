package test;

import ass2.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCoordinate {

    @Test
    public void testCoordinate() {
        // test initialize
        Coordinate testCo = new Coordinate(1, 1, 1);
        assertEquals("test get x axis", 1, testCo.getX());
        assertEquals("test get y axis", 1, testCo.getY());
        assertEquals("test get value", 1, testCo.getValue());

        testCo.setValue(2);
        testCo.setX(2);
        testCo.setY(2);
        // test set
        assertEquals("test get x axis after set", 2, testCo.getX());
        assertEquals("test get y axis after set", 2, testCo.getY());
        assertEquals("test get value after set", 2, testCo.getValue());
    }
}
