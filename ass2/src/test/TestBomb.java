package test;
import ass2.*;
import props.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBomb {

    @Test
    public void testsetNum () {
    	Map map = new Map(16,18);
    	Bomb testBomb = new Bomb(map);
    	
    	//test basic information of testBomb
    	assertEquals("test num", 0, testBomb.getNum());
    	
    	// test set
    	testBomb.setNum(4);
    	assertEquals("test num", 4, testBomb.getNum());
    }
    
    @Test
    public void testuse () {
    	Map map = new Map(16,18);
    	Bomb testBomb = new Bomb(map);
  	
    	// test use
    	testBomb.setNum(4);
    	assertEquals("test num", 4, testBomb.getNum());
    	testBomb.use();
    	assertEquals("test num", 3, testBomb.getNum());
    }
    
    @Test
    public void testpickUp () {
    	Map map = new Map(16,18);
    	Bomb testBomb = new Bomb(map);
  	
    	// test  pickUp
    	testBomb.setNum(4);
    	assertEquals("test num", 4, testBomb.getNum());
    	testBomb.pickUp();
    	assertEquals("test num", 5, testBomb.getNum());
    }

    @Test
    public void testsetPositionOnMap () {
    	Map map = new Map(16,18);
    	Bomb testBomb = new Bomb(map);
    	
        assertEquals("test get value 1", 0, map.getValue(4, 4));
        	
    	// test setPositionOnMap
    	testBomb.setPositionOnMap(4, 4);
    	assertEquals("test get value 1", Objects.bomb, map.getValue(4, 4));
    }

}
