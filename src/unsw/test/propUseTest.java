package unsw.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.Action.Action;
import unsw.algorithm.Location;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.entitys.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class propUseTest {

    private Dungeon dungeon = new Dungeon(10,10);
    private Action model = new Action();
    private Player player = new Player(dungeon,1,1);

    @BeforeEach
    public void setUp() {
        // set up the Condition
        dungeon.setPlayer(player);
        player.setModel(model);
        dungeon.addEntity(player);
    }

    @AfterEach
    public void reset() {
        // reset the Condition
        dungeon = new Dungeon(10,10);
        model = new Action();
        player = new Player(dungeon,1,1);
    }


    @Test
    public void testEnemy() {
        Enemy enemy = new Enemy(1,2);
        dungeon.addEntity(enemy);
        assertTrue(player.isAlive());;
        assertFalse(player.isInvincible());
        
        player.moveDown();
        assertFalse(player.isAlive());

    }
    
//    @Test
//    public void testUseSword() {
//
//        Enemy enemy = new Enemy(1,2);
//        Sword sword = new Sword(1,1);
//
//        dungeon.addEntity(sword);
//        dungeon.addEntity(enemy);
//
//        player.pick();
//        player.setFace(Objects.face_down);
//        // if the player use sword the enemy should be killed
//        player.useSword();
//
//        List<Entity> entities = dungeon.getEntities();
//
//        assertEquals(entities.size(), 1);
//        assertNull(dungeon.getEntity(new Location(1,2)));
//
//    }

    @Test
    public void testPotion() {
        Potion potion = new Potion(1,1);
        Enemy enemy = new Enemy(1,2);

        dungeon.addEntity(potion);
        dungeon.addEntity(enemy);

        player.pick();
        player.moveDown();

        List<Entity> entities = dungeon.getEntities();
        // should be just one player because the the enemy was destroyed
        assertEquals(entities.size(), 1);

        Entity entity = dungeon.getEntity(player.getLocation());
        assertNull(entity);
    }

    @Test
    public void testKey() {
        Key key = new Key(1,1);
        // down
        Door door = new Door(1,2);
        // right
        Door door1 = new Door(2,1);

        key.setId("123");
        door.setId("123");
        door1.setId("12");

        dungeon.addEntity(key);
        dungeon.addEntity(door);
        dungeon.addEntity(door1);
        // set player face
        player.setFace(Objects.face_right);
        // pick up the key first
        player.pick();

        // test the player can not open the door
        player.openDoor();
        Entity entity = dungeon.getEntity(new Location(2,1));
        assertTrue(entity instanceof Door);
        assertEquals(entity, door1);

        player.moveRight();
        Location pl = player.getLocation();
        assertEquals(pl.getX(), 1);
        assertEquals(pl.getY(), 1);

        // test the player can open the door
        // according to the key is same so the door can be opened
        player.setFace(Objects.face_down);
        player.openDoor();

        entity = dungeon.getEntity(new Location(1,2));
        assertTrue(entity instanceof OpenDoor);

        player.moveDown();
        pl = player.getLocation();

        assertEquals(pl.getY(), 2);
        assertEquals(pl.getX(), 1);

    }

    @Test
    public void testBomb() {
        UnlitBomb unlitBomb = new UnlitBomb(1,1);
        dungeon.addEntity(unlitBomb);

        // pick up firstly
        player.pick();
        // because lit bomb need to use thread update ui, so not testable
    }

}
