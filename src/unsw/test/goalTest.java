package unsw.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.Action.Action;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.entitys.*;
import unsw.goals.Goal;
import unsw.goals.GoalCondition;
import unsw.goals.SubGoal;
import static org.junit.jupiter.api.Assertions.*;


public class goalTest {

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
    public void testExitGoal() {
        // test just one goal
        GoalCondition goal = new SubGoal("exit");

        // set the goal for player
        dungeon.setGoal(goal);
        player.setGoal(goal);
        // before move it should be not win
        assertFalse(player.isWin());
        // add exit
        Exit exit = new Exit(1,2);
        dungeon.addEntity(exit);
        // player go to exit
        player.moveDown();

        assertTrue(player.isWin());
    }

    @Test
    public void testTreasureGoal() {
        // test the treasure goal
        GoalCondition goal = new SubGoal("treasure");
        dungeon.setGoal(goal);
        player.setGoal(goal);

        assertFalse(player.isWin());
        // set a treasure entity
        Treasure treasure = new Treasure(1,2);
        dungeon.addEntity(treasure);

        // move down firstly
        player.moveDown();
        assertFalse(player.isWin());
        
        // pick up the props
        player.pick();
        assertTrue(player.isWin());
    }


    @Test
    public void testEnemyPotion() {
        GoalCondition goal = new SubGoal("enemies");
        dungeon.setGoal(goal);
        player.setGoal(goal);

        // add enemy to the dungeon
        Enemy enemy = new Enemy(1,2);
        dungeon.addEntity(enemy);
        assertFalse(player.isWin());
        
        // also need to kill enemy
        Potion potion = new Potion(1,1);
        dungeon.addEntity(potion);

        // pick the potion
        player.pick();
        assertTrue(player.isInvincible());
        // kill the enemy
        player.moveDown();

        assertTrue(player.isWin());
    }

//    @Test
//    public void testEnemySword() {
//        GoalCondition goal = new SubGoal("enemies");
//        dungeon.setGoal(goal);
//        player.setGoal(goal);
//
//        // add enemy to the dungeon
//        Enemy enemy = new Enemy(1,2);
//        dungeon.addEntity(enemy);
//        assertFalse(player.isWin());
//
//        // also need to kill enemy
//        Sword sword = new Sword(1,1);
//        dungeon.addEntity(sword);
//
//        // pick the sword
//        player.pick();
//        // kill the enemy
//        player.setFace(Objects.face_down);
//        player.useSword();
//
//        assertTrue(player.isWin());
//    }

    @Test
    public void testBoulder() {
        GoalCondition goal = new SubGoal("boulders");
        dungeon.setGoal(goal);
        player.setGoal(goal);
        player.setModel(new Action());

        // add floor switch to dungeon
        Floorswitch floorswitch = new Floorswitch(1,3);
        dungeon.addEntity(floorswitch);
        assertFalse(player.isWin());

        Boulder boulder = new Boulder(1,2);
        dungeon.addEntity(boulder);
        player.moveDown();

        assertTrue(player.isWin());
    }
    
    @Test
    public void testMultipleBoulders() {
        GoalCondition goal = new SubGoal("boulders");
        dungeon.setGoal(goal);
        player.setGoal(goal);
        player.setModel(new Action());

        // add boulders and floor switch to dungeon
        Boulder boulder1 = new Boulder(1,2);
        dungeon.addEntity(boulder1);
        Boulder boulder2 = new Boulder(2,1);
        dungeon.addEntity(boulder2);        
        Floorswitch floorswitch1 = new Floorswitch(1,3);
        dungeon.addEntity(floorswitch1);
        Floorswitch floorswitch2 = new Floorswitch(3,1);
        dungeon.addEntity(floorswitch2);
        
        assertFalse(player.isWin());
        player.moveDown();
        assertFalse(player.isWin());
        player.moveUp();
        player.moveRight();
        assertTrue(player.isWin());
    }


    @Test
    // Goal condition: Enemies AND Treasure
    public void testCompositeGoal() {
        // create the goal first
        GoalCondition goal = new Goal();
        // create subgoal
        GoalCondition sub1 = new SubGoal("enemies");
        GoalCondition sub2 = new SubGoal("treasure");

        // add subgoal into goal
        goal.addGoal(sub1);
        goal.addGoal(sub2);
        dungeon.setGoal(goal);
        player.setGoal(goal);

        // add enemy
        Enemy enemy = new Enemy(1,4);
        dungeon.addEntity(enemy);

        // add treasure
        Treasure treasure = new Treasure(1,3);
        dungeon.addEntity(treasure);

        // add potion
        Potion potion = new Potion(1,2);
        dungeon.addEntity(potion);

        // pick up potion
        player.moveDown();
        player.pick();

        assertFalse(player.isWin());
        assertTrue(player.isInvincible());

        // pick treasure -- player will not win since he still has to kill the enemy
        player.moveDown();
        player.pick();
        assertFalse(player.isWin());

        // it should kill the enemy (using potion) and then it will win
        player.moveDown();

        // the win state should be true
        assertTrue(player.isWin());
    }

    @Test
    // goal condition: Exit OR Treasure
    public void testCompositeSubGoal1() {
        // create the goal first
        GoalCondition goal = new Goal();
        // create subgoal
        GoalCondition sub1 = new SubGoal("exit");
        GoalCondition sub2 = new SubGoal("treasure");

        // add subgoal into goal
        goal.addGoal(sub1);
        goal.addSub(sub2);
        dungeon.setGoal(goal);
        player.setGoal(goal);

        Exit exit = new Exit(1,2);
        dungeon.addEntity(exit);
        // move to exit should be win
        player.moveDown();
        assertTrue(player.isWin());

        // then try to move to treasure
        Treasure treasure = new Treasure(1,3);
        dungeon.addEntity(treasure);
        player.moveDown();
        // player should be win, then we need set the win is false
        player.setWin(false);
        assertFalse(player.isWin());
        // then pick up the treasure, should be win
        player.pick();
        assertTrue(player.isWin());
    }


    @Test
    // goal condition: Exit AND Treasure
    // If getting to an exit is one of a conjunction of conditions, it must be done last
    public void testCompositeSubGoal2() {
        // create the goal first
        GoalCondition goal = new Goal();
        // create subgoal
        GoalCondition sub1 = new SubGoal("exit");
        GoalCondition sub2 = new SubGoal("treasure");

        // add subgoal into goal
        goal.addGoal(sub1);
        goal.addGoal(sub2);
        dungeon.setGoal(goal);
        player.setGoal(goal);

        Exit exit = new Exit(1,2);
        dungeon.addEntity(exit);
        // move to exit should not win: hasn't collect treasure yet
        player.moveDown();
        assertFalse(player.isWin());

        // then try to move to treasure
        Treasure treasure = new Treasure(1,3);
        dungeon.addEntity(treasure);
        player.moveDown();
        player.pick();
        assertFalse(player.isWin());

        // then get to exit, should win
        player.moveUp();
        assertTrue(player.isWin());
    }

    
}
