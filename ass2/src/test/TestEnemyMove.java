package test;

import Enemy.*;
import ass2.*;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class TestEnemyMove {

    private int[][] map_zero = {
            {1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1},
            {1,0,0,14,0,0,1},
            {1,0,0,0,0,0,1},
            {1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1}

    };

    private Map map = new Map(7,7, map_zero);

    private Player player = new Player(map, map.getPosition(Objects.player));

    @Test
    public void testMoveUp () {

        int x = player.getPosition().getX();
        int y = player.getPosition().getY() - 2;

        Coordinate hunterUp = new Coordinate(x, y, Objects.hunter);
        map.setupMap(hunterUp);
        moveUp(new Hunter(hunterUp, map, true));

        Coordinate cowardUp = new Coordinate(x, y, Objects.coward);
        map.setupMap(cowardUp);
        moveUp(new Coward(cowardUp, map, true));

        Coordinate houndUp = new Coordinate(x, y, Objects.hound);
        map.setupMap(houndUp);
        moveUp(new Hound(houndUp, map, true));
    }

    @Test
    public void testMoveDown () {

        int x = player.getPosition().getX();
        int y = player.getPosition().getY() + 2;

        Coordinate hunterDown = new Coordinate(x, y, Objects.hunter);
        map.setupMap(hunterDown);
        moveDown(new Hunter(hunterDown, map, true));

        Coordinate cowardDown = new Coordinate(x, y, Objects.coward);
        map.setupMap(cowardDown);
        moveDown(new Coward(cowardDown, map, true));

        Coordinate houndDown = new Coordinate(x, y, Objects.hound);
        map.setupMap(houndDown);
        moveDown(new Hound(houndDown, map, true));
    }

    @Test
    public void testMoveLeft () {

        int x = player.getPosition().getX() - 2;
        int y = player.getPosition().getX();

        Coordinate hunterLeft = new Coordinate(x, y, Objects.hunter);
        map.setupMap(hunterLeft);
        moveLeft(new Hunter(hunterLeft, map, true));

        Coordinate cowardLeft = new Coordinate(x, y, Objects.coward);
        map.setupMap(cowardLeft);
        moveLeft(new Coward(cowardLeft, map, true));

        Coordinate houndLeft = new Coordinate(x, y, Objects.hound);
        map.setupMap(houndLeft);
        moveLeft(new Hound(houndLeft, map, true));
    }

    @Test
    public void testMoveRight () {

        int x = player.getPosition().getX() + 2;
        int y = player.getPosition().getX();

        Coordinate hunterRight = new Coordinate(x, y, Objects.hunter);
        map.setupMap(hunterRight);
        moveRight(new Hunter(hunterRight, map, true));

        Coordinate cowardRight = new Coordinate(x, y, Objects.coward);
        map.setupMap(cowardRight);
        moveRight(new Coward(cowardRight, map, true));

        Coordinate houndRight = new Coordinate(x, y, Objects.hound);
        map.setupMap(houndRight);
        moveRight(new Hound(houndRight, map, true));

    }


    public void moveUp (Enemy enemy) {

        enemy.setMove(new TrackPlayer(enemy));

        // let enemy move
        enemy.autoMove();
        assertEquals("enemy move up", player.getPosition().getY() - 1, enemy.getPosition().getY());
        map.setupMap(new Coordinate(enemy.getPosition().getX(), enemy.getPosition().getY(), 0));
    }

    public void moveDown (Enemy enemy) {

        enemy.setMove(new TrackPlayer(enemy));

        // let enemy move
        enemy.autoMove();
        assertEquals("enemy move down", player.getPosition().getY() + 1, enemy.getPosition().getY());
        map.setupMap(new Coordinate(enemy.getPosition().getX(), enemy.getPosition().getY(), 0));
    }

    public void moveLeft (Enemy enemy) {

        enemy.setMove(new TrackPlayer(enemy));

        // let enemy move
        enemy.autoMove();
        assertEquals("enemy move left", player.getPosition().getX() - 1, enemy.getPosition().getX());
        map.setupMap(new Coordinate(enemy.getPosition().getX(), enemy.getPosition().getY(), 0));
    }

    public void moveRight (Enemy enemy) {

        enemy.setMove(new TrackPlayer(enemy));

        // let enemy move
        enemy.autoMove();
        assertEquals("enemy move right", player.getPosition().getX() + 1, enemy.getPosition().getX());
        map.setupMap(new Coordinate(enemy.getPosition().getX(), enemy.getPosition().getY(), 0));
    }
}
