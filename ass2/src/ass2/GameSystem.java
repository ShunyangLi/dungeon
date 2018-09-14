package ass2;
import props.*;

import java.util.Scanner;

public class GameSystem {

    public static void main(String[] args)
    {
        Map map = new Map(20,20);
        map.setWall();


        // 新的大宝剑测试
        Sword sword = new Sword(map);
//        sword.setPositionOnMap(1,0);
//
//        // 没啥屁用
        Bomb bomb = new Bomb(map);
        Arrow arrow = new Arrow(map);
        Treasure treasure = new Treasure(map);
        Key key = new Key(map);


        // 设置背包呢
        Bag bag = new Bag(sword,arrow,bomb,treasure, key);

        // 设置player，等待测试
        Player player = new Player(map, bag);
//        Sword sword1 = new Sword(map);
//        sword1.setPositionOnMap(2,0);
        map.showMap(player.getPosition());

        System.out.println("w: go up, s: go down, a: go left, d: go right. Enter your action: ");
        Scanner scanner = new Scanner(System.in);
        // 还要判断是不是赢了
        while (player.getAlive())
        {
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
            map.showMap(player.getPosition());
        }




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
