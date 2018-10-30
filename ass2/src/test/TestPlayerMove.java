package test;

import ass2.*;
import Enemy.*;
import PlayerMove.*;
import org.junit.Ignore;
import props.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestPlayerMove {

    private Map map = new Map();

    private Player player = new Player(map, map.getPosition(Objects.player));

    @Test
    public void testSetUp () {
        assertEquals("default position (1, 1)", 1, player.getPosition().getX());
        assertEquals("default position (1, 1)", 1, player.getPosition().getY());
        assertEquals("default value", 14, player.getPosition().getValue());
        assertTrue("player should alive at beginning", player.getAlive());
        assertFalse("player should not win at beginning", player.isSuccess());
        assertEquals("player should have no sword in bag", 0, player.getBag().get(Objects.sword).getNum());
        assertEquals("player should have no arrow in bag", 0, player.getBag().get(Objects.arrow).getNum());
        assertEquals("player should have no treasure in bag", 0, player.getBag().get(Objects.treasure).getNum());
        assertEquals("player should have no key in bag", 0, player.getBag().get(Objects.key).getNum());
        assertEquals("player should have no bomb in bag", 0, player.getBag().get(Objects.bomb).getNum());
    }

    @Test
    public void testMove () {
        player.moveDown();
        assertEquals("player move to (2, 1)", 2, player.getPosition().getX());
        assertEquals("player move to (2, 1)", 1, player.getPosition().getY());
        player.moveUp();
        assertEquals("player back move to (1, 1)", 1, player.getPosition().getX());
        assertEquals("player back move to (1, 1)", 1, player.getPosition().getY());
        player.moveLeft();
        assertEquals("player can not move to (1, 0)", 1, player.getPosition().getX());
        assertEquals("player can not move to (1, 0)", 1, player.getPosition().getY());
        player.moveRight();
        assertEquals("player move to (1, 2)", 1, player.getPosition().getX());
        assertEquals("player move to (1, 2)", 2, player.getPosition().getY());
        player.moveLeft();
        assertEquals("player back move to (1, 1)", 1, player.getPosition().getX());
        assertEquals("player back move to (1, 1)", 1, player.getPosition().getY());
        player.moveRight();
        player.moveDown();
        assertEquals("player boulder move to (2, 2)", 2, player.getPosition().getX());
        assertEquals("player boulder move to (2, 2)", 2, player.getPosition().getY());
        assertEquals("boulder should moved to (3, 2)", 3, map.getValue(3, 2));
        Coordinate co1 = new Coordinate(1, 1, Objects.sword);
        Coordinate co2 = new Coordinate(1, 2, Objects.sword);
        Coordinate co3 = new Coordinate(2, 1, Objects.arrow);
        map.setupMap(co1); map.setupMap(co2); map.setupMap(co3);
        assertEquals("(1, 1) should be sword", Objects.sword, map.getValue(1, 1));
        assertEquals("(1, 2) should be sword", Objects.sword, map.getValue(1, 2));
        assertEquals("(2, 1) should be arrow", Objects.arrow, map.getValue(2, 1));
        player.moveLeft();
        assertEquals("player should have one arrow", 1, player.getBag().get(Objects.arrow).getNum());
        player.moveUp();
        assertEquals("player should have one sword", 1, player.getBag().get(Objects.sword).getNum());
        player.moveRight();
        assertEquals("player should not have more than one sword", 1, player.getBag().get(Objects.sword).getNum());
        assertEquals("player back stay at (1, 1)", 1, player.getPosition().getX());
        assertEquals("player back stay at (1, 1)", 1, player.getPosition().getY());
    }

    @Test
    public void testIsDie () {
        Coordinate co1 = new Coordinate(2, 1, Objects.arrow);
        Coordinate co2 = new Coordinate(3, 1, Objects.hunter);
        Coordinate co3 = new Coordinate(4, 1, Objects.hunter);
        map.setupMap(co1); map.setupMap(co2); map.setupMap(co3);
        player.moveDown();
        assertEquals("player should have one arrow", 1, player.getBag().get(Objects.arrow).getNum());
        player.moveDown();
        assertEquals("player move to (3, 1) and kill hunter", 3, player.getPosition().getX());
        assertEquals("player move to (3, 1) and kill hunter", 1, player.getPosition().getY());
        assertEquals("player should have no arrow", 0, player.getBag().get(Objects.arrow).getNum());
        player.moveDown();
        assertEquals("player stay to (3, 1) and killed hunter", 3, player.getPosition().getX());
        assertEquals("player stay to (3, 1) and killed hunter", 1, player.getPosition().getY());
        assertFalse("player should be killed", player.getAlive());
        assertEquals("player should have no arrow", 0, player.getBag().get(Objects.arrow).getNum());
    }

    @Test
    public void testPickUpAndUse () {
        // first set one sword on the map, and test that
        Coordinate next = player.getPosition();
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.sword);
        map.setupMap(next);

        // from this, to check whether can get one sword
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveDown();
        Sword tmpSword = (Sword)player.getBag().get(Objects.sword);
        assertEquals("The player shou have one sword", 1, tmpSword.getNum());
        assertEquals("The sowrd should can be used five times", 5,tmpSword.getUseable());

        // test use sword
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.hunter);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertEquals("The player should use once sword", 1, tmpSword.getNum());
        assertEquals("The useable of sword should be 4", 4, tmpSword.getUseable());

        // next setup is put another sword to check player can not puck up again, and should not moveable
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.sword);
        map.setupMap(next);
        assertFalse(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player shou have one sword", 1, tmpSword.getNum());
        assertEquals("The sowrd should can be used five times", 4, tmpSword.getUseable());

        // it should be same on different direction
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.sword);
        map.setupMap(next);
        assertFalse(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player shou have one sword", 1, tmpSword.getNum());
        assertEquals("The sowrd should can be used five times", 4, tmpSword.getUseable());

        // it should can move without anything
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.road);
        map.setupMap(next);
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();
        assertEquals("The player shou have one sword", 1, tmpSword.getNum());
        assertEquals("The sowrd should can be used four times", 4, tmpSword.getUseable());

    }

    @Test
    public void testUseArrow()
    {
        Coordinate next = player.getPosition();
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.arrow);
        map.setupMap(next);
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();
        assertEquals("The player should pick up one arrow", 1, player.getBag().get(Objects.arrow).getNum());

        // test hunter
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.hunter);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player should kill hunter", 0, player.getBag().get(Objects.arrow).getNum());

        // test pick one more
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.arrow);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player should use once arrow", 1, player.getBag().get(Objects.arrow).getNum());

        // pick up another
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.arrow);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player should use once arrow", 2, player.getBag().get(Objects.arrow).getNum());

        // test use one
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.hunter);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player should kill hunter", 1, player.getBag().get(Objects.arrow).getNum());

        // test use all of them
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.hunter);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        assertEquals("The player should kill hunter", 0, player.getBag().get(Objects.arrow).getNum());


        // test die withe out arrow
        next.setX(player.getPosition().getX() + 1);
        next.setValue(Objects.hunter);
        map.setupMap(next);
        assertTrue(player.isDie(next.getX(), next.getY()));

    }

    @Test
    public void testKey()
    {
        // next is get key, and the key should be just one
        Coordinate next = player.getPosition();
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.key);
        map.setupMap(next);
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();
        assertEquals("The player should pick up one key", 1, player.getBag().get(Objects.key).getNum());


        // test open door
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.door);
        map.setupMap(next);
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        assertTrue(player.getPreValue() == Objects.OpenDoor);
        assertEquals("The player shoud use one key", 0, player.getBag().get(Objects.key).getNum());

    }

    @Test
    public void testHover()
    {
        // without pit
        Coordinate next = player.getPosition();
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.hover);
        map.setupMap(next);
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();


        // test if there is a pit
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.pit);
        map.setupMap(next);
        assertFalse(player.isDie(next.getX(), next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();
    }

    @Test
    public void testInvincibility()
    {
        // test get invi
        Coordinate next = player.getPosition();
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.invincibility);
        map.setupMap(next);
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();

        // test if have enemy
        next.setY(player.getPosition().getY() + 1);
        next.setValue(Objects.hunter);
        map.setupMap(next);
//        assertFalse(player.isDie(next.getX(),next.getY()));
        assertTrue(player.isMoveable(next.getX(), next.getY()));
        player.moveRight();


    }

}
