package ass2;
import Enemy.*;
import ShortestPath.Location;
import props.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/*

 */
public class GameSystem {
    static Timer timer = new Timer();
    static int seconds = 0;

    public static void main(String[] args) {
        Map map = new Map(16,18);
        // map.setWall();
        Sword sword = new Sword(map);
        Arrow arrow = new Arrow(map);
        Bomb bomb = new Bomb(map);
        Key key = new Key(map);
        Treasure treasure = new Treasure(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure,key);

        Player player = new Player(map,bag,map.getPosition(Objects.player));

        // 利用factory格式输出相对应的enemy
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy hunter = enemyFactory.getEnemy(Hunter.class.getName(),map,map.getPosition(Objects.hunter));
        Enemy hound = enemyFactory.getEnemy(Hound.class.getName(), map, map.getPosition(Objects.hound));
        Enemy strategist = enemyFactory.getEnemy(Strategist.class.getName(),map,map.getPosition(Objects.strategist));
        Enemy coward = enemyFactory.getEnemy(Coward.class.getName(), map,map.getPosition(Objects.coward));

        hunter.moveUp(hunter);
        strategist.moveDown(strategist);

        map.showMap(player.getPosition());
        // player.showProps();
        // System.out.println("This is how to control the game!!");
        Scanner scanner = new Scanner(System.in);

        // 还要判断是不是赢了
        while (player.getAlive() && ! player.isSuccess())
        {
            if (PlayerDie(player,hunter))
            {
                System.out.println("Die!!");
                break;
            }else if (PlayerDie(player, hound)) {
                System.out.println("Die!!");
                break;
            } else if (PlayerDie(player, coward)) {
                System.out.println("Die!!");
            }

            if (hound.alive())
            {
                hound.autoMove();
            }
            if (hunter.alive())
            {
                // System.out.println(hunter.alive());
                hunter.autoMove();
            }
            if (coward.alive())
            {
                if (hide(player, coward))
                {
                    System.out.println("Hiding !!");
                    // TODO 虽然可以移动，但是bug依然很多
                    coward.hide();
                } else {
                    coward.autoMove();
                }
            }

            if (strategist.alive())
            {
                // do something
            }

            System.out.println("w: go up, s: go down, a: go left, d: go right, l: light bomb. Enter your action: ");
            String action = scanner.nextLine();
            if (action.compareTo("w") == 0)
            {
                player.moveUp();
            } else if (action.compareTo("s") == 0) {
                player.moveDown();
            } else if (action.compareTo("a") == 0) {
                player.moveLeft();
            } else if (action.compareTo("d") == 0) {
                player.moveRight();
            } else if (action.compareTo("l") == 0) {
                player.lightBomb();
            }

            if (player.getBag().getBomb().isLight())
            {
                // 这个地方就是延迟三秒，然后检查爆炸范围的人物，方圆十里不见人烟
                System.out.println("Bomb is lighting " + player.getPosition().getX() + " " + player.getPosition().getY());
                Coordinate b = map.getPosition(Objects.bomb);

                if (b != null)
                {
                    MyTimer(b,map, player, hunter, coward, hound, strategist);
                    player.getBag().getBomb().setLight(false);
                }
            }

            // System.out.println();
            player.showProps();
            map.showMap(player.getPosition());
        }

        System.out.println("game over!!");
        timer.cancel();

    }

    public static void MyTimer(Coordinate position, Map map, Player player, Enemy hunter, Enemy coward, Enemy hound, Enemy strategist) {

        TimerTask task;

        task = new TimerTask() {
            @Override
            public void run() {
                // Bomb bomb = player.getBag().getBomb();
                Coordinate b =  position;

                System.out.println(position.getX() + " " + position.getY());

                int left_x = b.getX();
                int left_y = b.getY() - 1;

                int right_x = b.getX();
                int right_y = b.getY() + 1;

                int up_x = b.getX() - 1;
                int up_y = b.getY();

                int down_x = b.getX() + 1;
                int down_y = b.getY();

                int Left = map.getValue(left_x, left_y);
                int right = map.getValue(right_x, right_y);
                int up = map.getValue(up_x, up_y);
                int down = map.getValue(down_x, down_y);

                removeBlock(map, Left, new Location(left_x, left_y), player, hunter, coward, hound, strategist);
                removeBlock(map, right, new Location(right_x, right_y), player, hunter, coward, hound, strategist);
                removeBlock(map, up, new Location(up_x, up_y), player, hunter, coward, hound, strategist);
                removeBlock(map, down, new Location(down_x, down_y), player, hunter, coward, hound, strategist);

                map.setupMap(new Coordinate(b.getX(), b.getY(), Objects.road));
            }
        };
        timer.schedule(task, 3000);
    }


    public static void removeBlock(Map map, int val, Location location, Player player, Enemy hunter, Enemy coward, Enemy hound, Enemy strategist)
    {
        if (val == Objects.player)
        {
            player.setAlive(false);
        } else if (val == Objects.hunter) {
            hunter.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        } else if (val == Objects.hound) {
            hound.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        } else if (val == Objects.coward) {
            coward.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        } else if (val == Objects.strategist) {
            strategist.setAlive(false);
            map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
        }

        return;
    }

    /**
     * @param player
     *
     */
    public static boolean hide(Player player, Enemy crowd)
    {
        int p_x = player.getPosition().getX();
        int p_y = player.getPosition().getY();

        int h_x = crowd.getPosition(crowd).getX();
        int h_y = crowd.getPosition(crowd).getY();

        int val = Math.abs(p_x - h_x) + Math.abs(p_y - h_y);
        if (val < 5)
        {
            return true;
        }
        return false;
    }

    public static boolean PlayerDie(Player player, Enemy hunter)
    {
        int p_x = player.getPosition().getX();
        int p_y = player.getPosition().getY();

        int h_x = hunter.getPosition(hunter).getX();
        int h_y = hunter.getPosition(hunter).getY();

        if (p_x - h_x == 0 && p_y - h_y == 0)
        {
            if (player.getBag().getSword().getNum() == 0 && player.getInvincibility() == 0 && player.getBag().getArrow().getNum() == 0)
            {
                player.setAlive(false);
                return true;
            } else {
                hunter.setAlive(false);
            }

        }
        return false;
    }


}
