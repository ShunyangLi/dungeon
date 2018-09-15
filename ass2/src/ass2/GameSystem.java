package ass2;
import Enemy.*;
import props.*;

import java.util.Scanner;

// TODO 检测enemy是否死亡的方法，就是检测this.position的value是不是Enemy，如果不是，结束循环

public class GameSystem {

    public static void main(String[] args)
    {
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
        while (player.getAlive())
        {
            System.out.println("w: go up, s: go down, a: go left, d: go right. Enter your action: ");
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
            }
            // System.out.println();
            player.showProps();
            map.showMap(player.getPosition());
        }

    }

}
