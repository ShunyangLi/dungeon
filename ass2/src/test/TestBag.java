package test;
import ass2.*;
import ass2.Bag;
import props.Arrow;
import props.Bomb;
import props.Key;
import props.Sword;
import props.Treasure;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestBag {

    @Test
    public void testgetSword () {
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);
        
        Bag bag = new Bag(sword,arrow,bomb,treasure,key);
        
      // test basic information of sword        
        assertEquals("test sword", 0, bag.getSword().getNum());

        // test set
        sword.setNum(4);
        Bag bag1 = new Bag(sword,arrow,bomb,treasure,key);
        assertEquals("test sword after set", 4, bag1.getSword().getNum());
    }

    @Test
    public void testgetArrow () {
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);
        
        Bag bag = new Bag(sword,arrow,bomb,treasure,key);
        
      // test basic information of sword        
        assertEquals("test arrow", 0, bag.getArrow().getNum());

        // test set
        arrow.setNum(4);
        Bag bag1 = new Bag(sword,arrow,bomb,treasure,key);
        assertEquals("test sword after set", 4, bag1.getArrow().getNum());
    }
    
    
    @Test
    public void testgetBomb () {
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);
        
        Bag bag = new Bag(sword,arrow,bomb,treasure,key);
        
      // test basic information of sword        
        assertEquals("test bomb", 0, bag.getBomb().getNum());

        // test set
        bomb.setNum(4);
        Bag bag1 = new Bag(sword,arrow,bomb,treasure,key);
        assertEquals("test bomb after set", 4, bag1.getBomb().getNum());
    }
    
    @Test
    public void testgetKey () {
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);
        
        Bag bag = new Bag(sword,arrow,bomb,treasure,key);
        
      // test basic information of sword        
        assertEquals("test key", 0, bag.getKey().getNum());

        // test set
        key.setNum(4);
        Bag bag1 = new Bag(sword,arrow,bomb,treasure,key);
        assertEquals("test key after set", 4, bag1.getKey().getNum());
    }

}

