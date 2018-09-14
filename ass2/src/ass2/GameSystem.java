package ass2;
import props.*;

import java.util.Scanner;

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

        Hunter hunter = new Hunter(map,map.getPosition(Objects.hunter));
        Hound hound = new Hound(map, map.getPosition(Objects.hound));
        Strategist strategist = new Strategist(map,map.getPosition(Objects.strategist));
        Coward coward = new Coward(map,map.getPosition(Objects.coward));


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


        // 新的大宝剑测试
//        Sword sword = new Sword(map);
////        sword.setPositionOnMap(1,0);
////
////        // 没啥屁用
//        Bomb bomb = new Bomb(map);
//        Arrow arrow = new Arrow(map);
//        Treasure treasure = new Treasure(map);
//        Key key = new Key(map);
//
//
//        // 设置背包呢
//        Bag bag = new Bag(sword,arrow,bomb,treasure, key);
//
//        // 设置player，等待测试
//        Player player = new Player(map, bag);
////        Sword sword1 = new Sword(map);
////        sword1.setPositionOnMap(2,0);
//        map.showMap(player.getPosition());


//        player.moveDown();
////        System.out.println();
//        map.showMap(player.getPosition());
//        System.out.println("Go right!");
//        player.moveRight();
//        map.showMap(player.getPosition());
//        player.moveDown();
//        map.showMap(player.getPosition());
//        player.moveDown();
//        map.showMap(player.getPosition());
//        player.moveDown();
//        map.showMap(player.getPosition());
    }

}
