package unsw.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.Action.Action;
import unsw.dungeon.Dungeon;
import unsw.entitys.*;

import static org.junit.jupiter.api.Assertions.*;


public class mazeTest {
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
    public void testMoveBlocked() {
        Wall wall = new Wall(1,2);
        dungeon.addEntity(wall);
        Wall wall2 = new Wall(3,1);
        dungeon.addEntity(wall2);

        assertEquals(player.getX(),1);
        assertEquals(player.getY(),1); 
        
        // test move player in the top left corner
        player.moveUp();
        player.moveUp();
        player.moveLeft();
        assertEquals(player.getX(),0);
        assertEquals(player.getY(),0);
        
        // test move if there was a wall
        player.moveDown();
        assertEquals(player.getX(),0);
        assertEquals(player.getY(),1);     
        
        player.moveRight();
        player.moveRight();
        assertEquals(player.getX(),2);
        assertEquals(player.getY(),1);

    }
    
    @Test
    public void testMoveFree() {
        // test move freely in all direction
        player.moveDown();
        assertEquals(player.getX(),1);
        assertEquals(player.getY(),2);
        player.moveRight();
        assertEquals(player.getX(),2);
        assertEquals(player.getY(),2);
        player.moveUp();
        assertEquals(player.getX(),2);
        assertEquals(player.getY(),1);
        player.moveLeft();
        assertEquals(player.getX(),1);
        assertEquals(player.getY(),1);

    }


}
