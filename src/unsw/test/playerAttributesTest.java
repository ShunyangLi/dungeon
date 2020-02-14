package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.Action.Action;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.entitys.Player;
import unsw.entitys.Wall;


import static org.junit.jupiter.api.Assertions.*;

public class playerAttributesTest {


    @Test
    public void testMove() {
        Player player = new Player(null,6,6);
        player.x().set(5);
        player.y().set(6);

        assertEquals(5, player.getX());
        assertEquals(6, player.getY());


        player.x().set(8);
        player.y().set(7);

        assertEquals(8, player.getX());
        assertEquals(7, player.getY());
    }

    @Test
    public void testStates() {
        Player player = new Player(null,0,0);
        assertTrue(player.isAlive());
        assertFalse(player.isWin());
        assertFalse(player.isInvincible());
    }


    @Test
    public void testBag() {
        Player player = new Player(null, 0,0);

        assertTrue(player.getBag() != null);
        assertNull(player.getEntityByKey(Objects.sword));
        assertNull(player.getEntityByKey(Objects.key));
        assertNull(player.getEntityByKey(Objects.unlit_bomb));

    }

    @Test
    public void testInvalidMove() {
        Dungeon dungeon = new Dungeon(10,10);
        Action model = new Action();
        Player player = new Player(dungeon,1,1);
        dungeon.setPlayer(player);
        player.setModel(model);
        Wall wall = new Wall(1,0);

        dungeon.addEntity(wall);
        dungeon.addEntity(player);

        player.moveUp();

        assertEquals(player.getX(),1);
        assertEquals(player.getY(), 1);

        player.moveLeft();

        assertEquals(player.getX(),0);
        assertEquals(player.getY(), 1);

        player.moveRight();

        assertEquals(player.getX(),1);
        assertEquals(player.getY(),1);

        player.moveDown();
        assertEquals(player.getX(),1);
        assertEquals(player.getY(), 2);
    }

}
