package unsw.test;


import org.junit.jupiter.api.Test;
import unsw.algorithm.Location;
import unsw.dungeon.Dungeon;
import unsw.entitys.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class dungeonTest {

    Dungeon dungeon = new Dungeon(10,10);

    @Test
    public void testInit() {
        assertEquals(dungeon.getWidth(),10);
        assertEquals(dungeon.getHeight(), 10);
        assertNull(dungeon.getPlayer());

        List<Entity> entities = dungeon.getEntities();
        assertEquals(entities.size(), 0);
    }

    @Test
    public void testAddEntity() {
        Player player = new Player(dungeon, 0,0);

        dungeon.setPlayer(player);
        assertEquals(dungeon.getPlayer(), player);

        dungeon.addEntity(player);
        List<Entity> entities = dungeon.getEntities();
        assertEquals(entities.size(), 1);

        dungeon.removeEntity(player);
        entities = dungeon.getEntities();
        assertEquals(entities.size(), 0);
    }

    @Test
    public void testLocationEntity() {
        Wall wall = new Wall(1,1);
        dungeon.addEntity(wall);
        Location location = wall.getLocation();

        assertNotNull(location);

        Entity entity = dungeon.getEntity(location);
        assertNotNull(entity);
        assertTrue(entity instanceof Wall);

        assertEquals(entity, wall);
    }


    @Test
    public void testMultiSameLocation() {
        Wall wall = new Wall(1,1);
        Location location = wall.getLocation();
        dungeon.addEntity(wall);

        List<Entity> entities = dungeon.getCurrentEntity(location);

        assertNotNull(entities);
        assertEquals(entities.size(), 1);

        Boulder boulder = new Boulder(1,1);
        dungeon.addEntity(boulder);


        entities = dungeon.getCurrentEntity(location);

        assertEquals(entities.size(), 2);
    }


    @Test
    public void testGetBoulder() {
        Location location = new Location(1,1);
        Entity entity = dungeon.getBoulder(location);

        assertNull(entity);

        Boulder boulder = new Boulder(1,1);
        dungeon.addEntity(boulder);

        entity = dungeon.getBoulder(location);
        assertNotNull(entity);

        assertTrue(entity instanceof Boulder);
        assertEquals(entity, boulder);
    }

    @Test
    public void testGetSwitch() {
        Location location = new Location(1,1);
        Entity entity = dungeon.getBoulder(location);

        assertNull(entity);

        Floorswitch floorswitch = new Floorswitch(1,1);
        dungeon.addEntity(floorswitch);

        entity = dungeon.getSwitch(location);
        assertNotNull(entity);

        assertTrue(entity instanceof Floorswitch);
        assertEquals(entity, floorswitch);
    }
}
