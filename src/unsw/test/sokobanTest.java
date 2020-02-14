package unsw.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.Action.Action;
import unsw.algorithm.Location;
import unsw.dungeon.Dungeon;
import unsw.entitys.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class sokobanTest {

    private Dungeon dungeon = new Dungeon(10,10);
    private Action action = new Action();
    private Player player = new Player(dungeon,1,1);

    @BeforeEach
    public void setUp() {
        // set up the Condition
        dungeon.setPlayer(player);
        player.setModel(action);
        dungeon.addEntity(player);
    }

    @AfterEach
    public void reset() {
        // reset the Condition
        dungeon = new Dungeon(10,10);
        action = new Action();
        player = new Player(dungeon,1,1);
    }

    @Test
    public void testBoulderMove() {
        // down
        Boulder boulder = new Boulder(1,2);
        dungeon.addEntity(boulder);
        player.moveDown();

        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);

        assertEquals(boulder.getX(),1);
        assertEquals(boulder.getY(),3);
    }

    @Test
    public void testNotMovie() {
        Boulder boulder = new Boulder(1,2);
        Wall wall = new Wall(1,3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(wall);

        player.moveDown();

        assertEquals(player.getX(),1);
        assertEquals(player.getY(),1);

        assertEquals(boulder.getX(),1);
        assertEquals(boulder.getY(), 2);

        assertEquals(wall.getX(), 1);
        assertEquals(wall.getY(), 3);
    }
    @Test
    public void testSwitches() {
        Floorswitch floorswitch = new Floorswitch(1,3);
        dungeon.addEntity(floorswitch);


        Boulder boulder = new Boulder(1,2);
        dungeon.addEntity(boulder);


        player.moveDown();

        assertEquals(boulder.getX(),1);
        assertEquals(boulder.getY(),3);

        List<Entity> entities = dungeon.getCurrentEntity(new Location(1,2));

        // which not include player
        assertEquals(entities.size(), 0);

        Entity entity = dungeon.getBoulder(new Location(1,3));
        Entity entity1 = dungeon.getSwitch(new Location(1,3));

        assertNotNull(entity);
        assertNotNull(entity1);

        assertEquals(entity, boulder);
        assertEquals(entity1, floorswitch);
    }
}
