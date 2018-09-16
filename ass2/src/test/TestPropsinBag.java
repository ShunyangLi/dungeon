package test;

import ass2.*;
import props.*;
import static ass2.Objects.sword;
import static ass2.Objects.arrow;
import static ass2.Objects.treasure;
import static ass2.Objects.bomb;
import static ass2.Objects.key;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class TestPropsinBag {

    private Map map;
    private Bag testBag;

    @Before
    public void setUp () {
        map = new Map(16,18);
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);
        testBag = new Bag(sword, arrow, bomb, treasure, key);
    }

    @Test
    public void testSword () {
        Sword testSword = testBag.getSword();
        boolean canSetSword = testSword.setPositionOnMap(0, 0);
        assertFalse("can not set to (0, 0)", canSetSword);
        canSetSword = testSword.setPositionOnMap(1, 1);
        assertFalse("can not set to (1, 1)", canSetSword);
        canSetSword = testSword.setPositionOnMap(1, 3);
        assertTrue("can set to (1, 3)", canSetSword);

        Coordinate currentPosition = testSword.getPosition();
        assertEquals("get current x axis", 1, currentPosition.getX());
        assertEquals("get current y axis", 3, currentPosition.getY());
        assertEquals("get current value", sword, currentPosition.getValue());

        assertEquals("get max number", 1, testSword.getMaxNum());

        assertEquals("get number before pick up", 0, testSword.getNum());
        assertEquals("get number before pick up", 0, testSword.getUseable());

        boolean canPickUp = testSword.pickUp();
        assertTrue("can pick up when player do not have a sword", canPickUp);
        canPickUp = testSword.pickUp();
        assertFalse("can not pick up when player do have a sword", canPickUp);
        assertEquals("get number after pick up", 5, testSword.getUseable());
        testSword.use();
        assertEquals("get number after used once", 4, testSword.getUseable());
        testSword.use();
        assertEquals("get number after used second time", 3, testSword.getUseable());
        testSword.use();
        assertEquals("get number after used third time", 2, testSword.getUseable());
        testSword.use();
        assertEquals("get number after used fourth time", 1, testSword.getUseable());
        testSword.use();
        assertEquals("get number after used last time", 0, testSword.getUseable());
        assertEquals("get number after used last time", 0, testSword.getNum());
    }

    @Test
    public void testArrow () {
        Arrow testArrow = testBag.getArrow();
        boolean canSetArrow = testArrow.setPositionOnMap(0, 0);
        assertFalse("can not set to (0, 0)", canSetArrow);
        canSetArrow = testArrow.setPositionOnMap(1, 1);
        assertFalse("can not set to (1, 1)", canSetArrow);
        canSetArrow = testArrow.setPositionOnMap(1, 3);
        assertTrue("can set to (1, 3)", canSetArrow);

        Coordinate currentPosition = testArrow.getPosition();
        assertEquals("get current x axis", 1, currentPosition.getX());
        assertEquals("get current y axis", 3, currentPosition.getY());
        assertEquals("get current value", arrow, currentPosition.getValue());

        boolean canPickUp = testArrow.pickUp();
        assertTrue("can pick up when player do not have a arrow", canPickUp);
        canPickUp = testArrow.pickUp();
        assertTrue("still can pick up when player do have a arrow", canPickUp);
        canPickUp = testArrow.pickUp();
        assertTrue("still can pick up when player do have two arrow", canPickUp);
        canPickUp = testArrow.pickUp();
        assertTrue("still can pick up when player do have three arrow", canPickUp);
        canPickUp = testArrow.pickUp();
        assertTrue("still can pick up when player do have four arrow", canPickUp);

        assertEquals("get number before use", 5, testArrow.getNum());
        testArrow.use();
        assertEquals("get number after used once", 4, testArrow.getNum());
        testArrow.use();
        assertEquals("get number after used second time", 3, testArrow.getNum());
        testArrow.use();
        assertEquals("get number after used third time", 2, testArrow.getNum());
        testArrow.use();
        assertEquals("get number after used fourth time", 1, testArrow.getNum());
        testArrow.use();
        assertEquals("get number after used fifth time", 0, testArrow.getNum());
        testArrow.use();
        assertEquals("arrow can not be negative", 0, testArrow.getNum());
    }

    @Test
    public void testTreasure () {
        Treasure testTreasure = testBag.getTreasure();
        boolean canSetTreasure = testTreasure.setPositionOnMap(0, 0);
        assertFalse("can not set to (0, 0)", canSetTreasure);
        canSetTreasure = testTreasure.setPositionOnMap(1, 1);
        assertFalse("can not set to (1, 1)", canSetTreasure);
        canSetTreasure = testTreasure.setPositionOnMap(1, 3);
        assertTrue("can set to (1, 3)", canSetTreasure);

        Coordinate currentPosition = testTreasure.getPosition();
        assertEquals("get current x axis", 1, currentPosition.getX());
        assertEquals("get current y axis", 3, currentPosition.getY());
        assertEquals("get current value", treasure, currentPosition.getValue());

        boolean canPickUp = testTreasure.pickUp();
        assertTrue("can pick up when player do not have a Treasure", canPickUp);
        canPickUp = testTreasure.pickUp();
        assertTrue("still can pick up when player do have a Treasure", canPickUp);
        canPickUp = testTreasure.pickUp();
        assertTrue("still can pick up when player do have two Treasure", canPickUp);
        canPickUp = testTreasure.pickUp();
        assertTrue("still can pick up when player do have three Treasure", canPickUp);
        canPickUp = testTreasure.pickUp();
        assertTrue("still can pick up when player do have four Treasure", canPickUp);
        canPickUp = testTreasure.pickUp();
        assertTrue("still can pick up when player do have five Treasure", canPickUp);

        assertEquals("get number before use", 6, testTreasure.getNum());
        testTreasure.use();
        assertEquals("get number after used once", 5, testTreasure.getNum());
        testTreasure.use();
        assertEquals("get number after second time", 4, testTreasure.getNum());
        testTreasure.use();
        assertEquals("get number after third time", 3, testTreasure.getNum());
        testTreasure.use();
        assertEquals("get number after fourth time", 2, testTreasure.getNum());
        testTreasure.use();
        assertEquals("get number after fifth time", 1, testTreasure.getNum());
        testTreasure.use();
        assertEquals("get number after sixth time", 0, testTreasure.getNum());
        testTreasure.use();
        assertEquals("treasure can not be negative", 0, testTreasure.getNum());
    }

    @Test
    public void testKey () {
        Key testKey = testBag.getKey();
        boolean canSetKey = testKey.setPositionOnMap(0, 0);
        assertFalse("can not set to (0, 0)", canSetKey);
        canSetKey = testKey.setPositionOnMap(1, 1);
        assertFalse("can not set to (1, 1)", canSetKey);
        canSetKey = testKey.setPositionOnMap(1, 3);
        assertTrue("can set to (1, 3)", canSetKey);

        Coordinate currentPosition = testKey.getPosition();
        assertEquals("get current x axis", 1, currentPosition.getX());
        assertEquals("get current y axis", 3, currentPosition.getY());
        assertEquals("get current value", key, currentPosition.getValue());

        assertEquals("get max number", 1, testKey.getMaxNum());

        assertEquals("get number before pick up", 0, testKey.getNum());

        boolean canPickUp = testKey.pickUp();
        assertTrue("can pick up when player do not have a Key", canPickUp);
        canPickUp = testKey.pickUp();
        assertFalse("can not pick up when player do have a Key", canPickUp);
        testKey.use();
        assertEquals("get number after used", 0, testKey.getNum());
    }

}
