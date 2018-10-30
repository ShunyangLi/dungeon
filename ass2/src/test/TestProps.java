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

public class TestProps {

    private Map map = new Map();

    private Player player = new Player(map, map.getPosition(Objects.player));

    @Test
    public void testSword () {

        Sword testSword = (Sword) player.getBag().get(Objects.sword);
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

        Arrow testArrow = (Arrow) player.getBag().get(Objects.arrow);
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

        Treasure testTreasure = (Treasure) player.getBag().get(Objects.treasure);
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
        assertTrue("test useful",  testTreasure.use());

    }

    @Test
    public void testKey () {

        Key testKey = (Key) player.getBag().get(Objects.key);
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

    @Test
    public void testBomb () {

        Bomb testBomb = (Bomb) player.getBag().get(Objects.bomb);
        boolean canSetBomb = testBomb.setPositionOnMap(0, 0);
        assertFalse("can not set to (0, 0)", canSetBomb);
        canSetBomb = testBomb.setPositionOnMap(1, 1);
        assertFalse("can not set to (1, 1)", canSetBomb);
        canSetBomb = testBomb.setPositionOnMap(1, 3);
        assertTrue("can set to (1, 3)", canSetBomb);

        Coordinate currentPosition = testBomb.getPosition();
        assertEquals("get current x axis", 1, currentPosition.getX());
        assertEquals("get current y axis", 3, currentPosition.getY());
        assertEquals("get current value", bomb, currentPosition.getValue());

        boolean canPickUp = testBomb.pickUp();
        assertTrue("can pick up when player do not have a bomb", canPickUp);
        canPickUp = testBomb.pickUp();
        assertTrue("still can pick up when player do have a bomb", canPickUp);
        canPickUp = testBomb.pickUp();
        assertTrue("still can pick up when player do have two bomb", canPickUp);
        canPickUp = testBomb.pickUp();
        assertTrue("still can pick up when player do have three bomb", canPickUp);
        canPickUp = testBomb.pickUp();
        assertTrue("still can pick up when player do have four bomb", canPickUp);

        assertEquals("get number before use", 5, testBomb.getNum());
        testBomb.use();
        assertEquals("get number after used once", 4, testBomb.getNum());
        testBomb.use();
        assertEquals("get number after used second time", 3, testBomb.getNum());
        testBomb.use();
        assertEquals("get number after used third time", 2, testBomb.getNum());
        testBomb.use();
        assertEquals("get number after used fourth time", 1, testBomb.getNum());
        testBomb.use();
        assertEquals("get number after used fifth time", 0, testBomb.getNum());
        testBomb.use();
        assertEquals("bomb can not be negative", 0, testBomb.getNum());
    }
}
