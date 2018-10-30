package test;

import ass2.Map;
import ass2.Objects;
import ass2.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestPlayerBag {

    private Map map = new Map();

    private Player player = new Player(map, map.getPosition(Objects.player));

    @Test
    public void testSwordNum () {
        
        // test basic information of sword
        assertEquals("test sword", 0, player.getBag().get(Objects.sword).getNum());

        // test set
        player.getBag().get(Objects.sword).setNum(4);
        assertEquals("test sword after set", 4, player.getBag().get(Objects.sword).getNum());
    }

    @Test
    public void testArrowNum () {

        // test basic information of sword
        assertEquals("test sword", 0, player.getBag().get(Objects.arrow).getNum());

        // test set
        player.getBag().get(Objects.arrow).setNum(4);
        assertEquals("test sword after set", 4, player.getBag().get(Objects.arrow).getNum());
    }
    
    
    @Test
    public void testBombNum () {

        // test basic information of bomb
        assertEquals("test sword", 0, player.getBag().get(Objects.bomb).getNum());

        // test set
        player.getBag().get(Objects.bomb).setNum(4);
        assertEquals("test sword after set", 4, player.getBag().get(Objects.bomb).getNum());
    }
    
    @Test
    public void testKeyNum () {

        // test basic information of key
        assertEquals("test sword", 0, player.getBag().get(Objects.key).getNum());

        // test set
        player.getBag().get(Objects.key).setNum(4);
        assertEquals("test sword after set", 4, player.getBag().get(Objects.key).getNum());
    }

}

