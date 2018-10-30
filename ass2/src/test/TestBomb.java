package test;
import ass2.*;
import props.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBomb {

	private Map map = new Map();

    @Test
    public void testSetNum () {

    	Bomb testBomb = new Bomb(map);
    	
    	//test basic information of testBomb
    	assertEquals("test num", 0, testBomb.getNum());
    	
    	// test set
    	testBomb.setNum(4);
    	assertEquals("test num", 4, testBomb.getNum());
    }
    
    @Test
    public void testUse () {

    	Bomb testBomb = new Bomb(map);
  	
    	// test use
    	testBomb.setNum(4);
    	assertEquals("test num", 4, testBomb.getNum());
    	testBomb.use();
    	assertEquals("test num", 3, testBomb.getNum());
    }
    
    @Test
    public void testPickUp () {

    	Bomb testBomb = new Bomb(map);
  	
    	// test  pickUp
    	testBomb.setNum(4);
    	assertEquals("test num", 4, testBomb.getNum());
    	testBomb.pickUp();
    	assertEquals("test num", 5, testBomb.getNum());
    }

    @Test
    public void testSetPositionOnMap () {

    	Bomb testBomb = new Bomb(map);
    	
        assertEquals("test get value 1", 0, map.getValue(4, 4));
        	
    	// test setPositionOnMap
    	testBomb.setPositionOnMap(4, 4);
    	assertEquals("test get value 1", Objects.bomb, map.getValue(4, 4));
    }

}
