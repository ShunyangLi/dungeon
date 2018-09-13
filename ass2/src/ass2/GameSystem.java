package ass2;
import props.*;

public class GameSystem {

    public static void main(String[] args)
    {
        Map map = new Map(20,20);
        map.setWall();
        Sword sword = new Sword(map);
        sword.setPositionOnMap(1,0);
        Bomb bomb = new Bomb(map);
        Arrow arrow = new Arrow(map);
        Treasure treasure = new Treasure(map);
        Key key = new Key(map);

        Bag bag = new Bag(sword,arrow,bomb,treasure, key);

        Player player = new Player(map, bag);

        map.showMap(player.getPosition());

        player.moveDown();
        System.out.println();
        Sword sword1 = new Sword(map);
        sword.setPositionOnMap(2,0);
        player.moveDown();
        player.moveDown();
        map.showMap(player.getPosition());
    }

}
