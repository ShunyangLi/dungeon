package test;
import ass2.*;
import props.*;
import Enemy.*;
import PlayerMove.*;
import org.junit.Test;

import javax.swing.text.Position;

import static org.junit.Assert.*;



public class TestPlayer {

    private Map map = new Map();

    private Player player = new Player(map, map.getPosition(Objects.player));

    @Test
    public void testMoveDown () {

        assertEquals("test this.x", 1, player.getPosition().getX());
        assertEquals("test this.y", 1, player.getPosition().getY());
        
        //moveDown
        player.moveDown();
        assertEquals("test this.x", 2, player.getPosition().getX());
        assertEquals("test this.y", 1, player.getPosition().getY());
        
    }
    
    @Test
    public void testMoveUp () {

        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y",1, player.getPosition( ).getY());
        
        //moveUp
        player.moveUp( );
        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
    }
    
    @Test
    public void testMoveLeft () {

        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
        //moveLeft
        player.moveLeft( );
        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
    }
    
    @Test
    public void testMoveRight () {

        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
        //moveRight
        player.moveRight( );
        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 2, player.getPosition( ).getY());
    }
    
    @Test
    public void testMoveable () {

        assertEquals("test this.x", false, player.isMoveable(2, 3));
        assertEquals("test this.x", true, player.isMoveable(2, 4));  
            
    }
    
    @Test
    public void testisBoulderMove () {

        assertEquals("test this.x", false, player.isBoulderMove(2, 3));
        assertEquals("test this.x", true, player.isBoulderMove(8, 2)); 
            
    }
    
    

   
}
