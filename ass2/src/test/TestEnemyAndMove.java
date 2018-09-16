package test;

import Enemy.*;
import ass2.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEnemyAndMove {

    private Map map;
    private Enemy hunter;
    private Enemy hound;
    private Enemy strategist;
    private Enemy coward;
    private EnemyFactory enemyFactory;

    public void testEnemy (Enemy enemy, int object) {

        Coordinate initPos = new Coordinate(5,3, object);
        map.setupMap(initPos);
        assertTrue("enemy should be alive at beginning", enemy.alive());
        // let enemy move
        enemy.moveLeft(enemy);
        assertEquals("enemy move left", initPos.getY() - 1, enemy.getPosition(enemy).getY());
        enemy.moveLeft(enemy);
        assertEquals("enemy move left next to wall", initPos.getY() - 2, enemy.getPosition(enemy).getY());
        enemy.moveLeft(enemy);
        assertEquals("enemy can not move out of wall", initPos.getY() - 2, enemy.getPosition(enemy).getY());
        // remove wall
        Coordinate co = new Coordinate(initPos.getX(), initPos.getY() - 3, Objects.road);
        map.setupMap(co);
        enemy.moveLeft(enemy);
        assertEquals("enemy move left again after remove wall", initPos.getY() - 3, enemy.getPosition(enemy).getY());
        enemy.moveLeft(enemy);
        assertEquals("enemy can not move out map", initPos.getY() - 3, enemy.getPosition(enemy).getY());
        // other move actions
        enemy.moveRight(enemy);
        assertEquals("enemy move right", initPos.getY() - 2, enemy.getPosition(enemy).getY());
        enemy.moveRight(enemy);
        assertEquals("enemy move right again", initPos.getY() - 1, enemy.getPosition(enemy).getY());
        enemy.moveUp(enemy);
        assertEquals("enemy move up", initPos.getX() - 1, enemy.getPosition(enemy).getX());
        assertEquals("x axis should keep same when move up", initPos.getY() - 1, enemy.getPosition(enemy).getY());
        enemy.moveDown(enemy);
        assertEquals("enemy move up", initPos.getX(), enemy.getPosition(enemy).getX());
        assertEquals("x axis should keep same when move down", initPos.getY() - 1, enemy.getPosition(enemy).getY());
    }

    @Test
    public void testHunter () {
        map = new Map(16,18);
        enemyFactory = new EnemyFactory();
        Coordinate position = new Coordinate(5, 3, Objects.hunter);
        hunter = enemyFactory.getEnemy(Hunter.class.getName(), map, position);
        hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        strategist = enemyFactory.getEnemy(Strategist.class.getName(),map, map.getPosition(Objects.strategist));
        coward = enemyFactory.getEnemy(Coward.class.getName(), map, map.getPosition(Objects.coward));
        testEnemy(hunter, Objects.hunter);
    }

    @Test
    public void testHound () {
        map = new Map(16,18);
        enemyFactory = new EnemyFactory();
        Coordinate position = new Coordinate(5, 3, Objects.hound);
        hunter = enemyFactory.getEnemy(Hunter.class.getName(), map, map.getPosition(Objects.hunter));
        hound = enemyFactory.getEnemy(Hound.class.getName(), map, position);
        strategist = enemyFactory.getEnemy(Strategist.class.getName(),map, map.getPosition(Objects.strategist));
        coward = enemyFactory.getEnemy(Coward.class.getName(), map, map.getPosition(Objects.coward));
        testEnemy(hound, Objects.hound);
    }

    @Test
    public void testStrategist () {
        map = new Map(16,18);
        enemyFactory = new EnemyFactory();
        Coordinate position = new Coordinate(5, 3, Objects.hound);
        hunter = enemyFactory.getEnemy(Hunter.class.getName(), map, map.getPosition(Objects.hunter));
        hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        strategist = enemyFactory.getEnemy(Strategist.class.getName(),map, position);
        coward = enemyFactory.getEnemy(Coward.class.getName(), map, map.getPosition(Objects.coward));
        testEnemy(strategist, Objects.strategist);
    }

    @Test
    public void testCoward () {
        map = new Map(16,18);
        enemyFactory = new EnemyFactory();
        Coordinate position = new Coordinate(5, 3, Objects.hound);
        hunter = enemyFactory.getEnemy(Hunter.class.getName(), map, map.getPosition(Objects.hunter));
        hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        strategist = enemyFactory.getEnemy(Strategist.class.getName(),map, map.getPosition(Objects.strategist));
        coward = enemyFactory.getEnemy(Coward.class.getName(), map, position);
        testEnemy(coward, Objects.coward);
    }
}
