package ass2;
import props.*;

public class GameSystem {

    public static void main(String[] args)
    {
        Map map = new Map(20,20);
        map.setWall();

        // 新的大宝剑测试
        Sword sword = new Sword(map);
        sword.setPositionOnMap(1,0);

        // 没啥屁用
        Bomb bomb = new Bomb(map);
        Arrow arrow = new Arrow(map);
        Treasure treasure = new Treasure(map);
        Key key = new Key(map);

        // 设置背包呢
        Bag bag = new Bag(sword,arrow,bomb,treasure, key);

        // 设置player，等待测试
        Player player = new Player(map, bag);
        Sword sword1 = new Sword(map);
        sword1.setPositionOnMap(2,0);
        map.showMap(player.getPosition());
        player.moveDown();
        System.out.println();

        player.moveDown();
        player.moveDown();
        map.showMap(player.getPosition());
    }

}
