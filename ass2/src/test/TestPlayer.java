package test;
import ass2.*;
import props.*;
import Enemy.*;
import PlayerMove.*;
import org.junit.Test;
import static org.junit.Assert.*;



public class TestPlayer {

    @Test
    public void testmoveDown () {
    	
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));

        assertEquals("test this.x", 1, player.getPosition().getX());
        assertEquals("test this.y", 1, player.getPosition().getY());
        
        //moveDown
        player.moveDown();
        assertEquals("test this.x", 2, player.getPosition().getX());
        assertEquals("test this.y", 1, player.getPosition().getY());
        
    }
    
    @Test
    public void testmoveUp () {
    	
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));

        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y",1, player.getPosition( ).getY());
        
        //moveUp
        player.moveUp( );
        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
    }
    
    @Test
    public void testmoveLeft () {
    	
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));

        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
        //moveLeft
        player.moveLeft( );
        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
    }
    
    @Test
    public void testmoveRight () {
    	
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));

        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 1, player.getPosition( ).getY());
        
        //moveRight
        player.moveRight( );
        assertEquals("test this.x", 1, player.getPosition( ).getX());
        assertEquals("test this.y", 2, player.getPosition( ).getY());
    }
    
    @Test
    public void testisMoveable () {
    	
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));
        

        assertEquals("test this.x", false, player.isMoveable(2, 3));
        assertEquals("test this.x", true, player.isMoveable(2, 4));  
            
    }
    
    @Test
    public void testisBoulderMove () {
    	
    	Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        // test player
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));
        

        assertEquals("test this.x", false, player.isBoulderMove(2, 3));
        assertEquals("test this.x", true, player.isBoulderMove(8, 2)); 
            
    }
    
    

   
}
