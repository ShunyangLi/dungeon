package unsw.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.Action.Action;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.entitys.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class pickTest {
    private Dungeon dungeon = new Dungeon(10,10);
    private Action model = new Action();
    private Player player = new Player(dungeon,1,1);


    @BeforeEach
    public void setUp() {
        dungeon.setPlayer(player);
        player.setModel(model);
        dungeon.addEntity(player);
    }

    @Test
    public void testPickSword() {

        // test pick up sword
        Sword sword = new Sword(1,1);
        dungeon.addEntity(sword);

        player.pick();

        List<Entity> entities = dungeon.getEntities();
        assertEquals(entities.size(), 1);

        List<Entity> sw = player.getEntityByKey(Objects.sword);

        assertEquals(sw.size(), 1);

        assertTrue(sw.get(0) instanceof Sword);
        Sword sword1 = (Sword) sw.get(0);

        assertEquals(sword1.getTimes(), 5);


        // test the number of sword picked, should be only one sword
        // if the player want to pick up another one is not possible
        Sword sword2 = new Sword(1,1);
        dungeon.addEntity(sword2);
        player.pick();
        entities = player.getEntityByKey(Objects.sword);
        assertEquals(entities.size(), 1);

        dungeon.removeEntity(sword2);
    }

    @Test
    public void testPickKey() {

        Key key = new Key(1,1);
        dungeon.addEntity(key);
        player.pick();

        List<Entity> entities = player.getEntityByKey(Objects.key);

        assertEquals(entities.size(), 1);

        entities = dungeon.getEntities();
        assertEquals(entities.size(), 1);

        Key key1 = new Key(1,1);
        dungeon.addEntity(key1);
        player.pick();
        // test player just get one key
        entities = player.getEntityByKey(Objects.key);
        assertEquals(entities.size(), 1);

        dungeon.removeEntity(key1);

        // test the size of dungeon size include player should be 1
        entities = dungeon.getEntities();
        assertEquals(entities.size(), 1);
    }


    @Test
    public void testPickBomb() {

        // test bomb it can be collect more than one
        UnlitBomb unlitBomb = new UnlitBomb(1,1);
        dungeon.addEntity(unlitBomb);

        player.pick();
        List<Entity> entities = player.getEntityByKey(Objects.unlit_bomb);
        assertEquals(entities.size(), 1);

        assertEquals(dungeon.getEntities().size(), 1);

        UnlitBomb unlitBomb1 = new UnlitBomb(1,1);
        dungeon.addEntity(unlitBomb1);
        player.pick();

        // test player can pick up more than one bomb
        entities = player.getEntityByKey(Objects.unlit_bomb);
        assertEquals(entities.size(), 2);

        assertEquals(dungeon.getEntities().size(), 1);
    }

    @Test
    public void testPickTreasure() {

        // test pick up Treasure should be more than one
        Treasure treasure = new Treasure(1,1);
        dungeon.addEntity(treasure);
        player.pick();

        List<Entity> entities = player.getEntityByKey(Objects.treasure);
        assertEquals(entities.size(), 1);

        Treasure treasure1 = new Treasure(1,1);
        dungeon.addEntity(treasure1);
        entities = dungeon.getEntities();
        assertEquals(entities.size(), 2);
        
        player.pick();
        entities = player.getEntityByKey(Objects.treasure);
        assertEquals(entities.size(), 2);

        entities = dungeon.getEntities();
        assertEquals(entities.size(), 1);

    }

    @Test
    public void testPickPotion() {
        // test pick up potion
        Potion potion = new Potion(1,1);
        dungeon.addEntity(potion);
        List<Entity> entities = dungeon.getEntities();
        assertEquals(entities.size(), 2);
        player.pick();

        assertTrue(player.isInvincible());
        entities = dungeon.getEntities();
        assertEquals(entities.size(), 1);
        
    }

}
