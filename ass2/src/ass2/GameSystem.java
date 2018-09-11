package ass2;

public class GameSystem {

    public static void main(String[] args)
    {
        Map map = new Map(10,10);
        Player player = new Player(map);

        map.showMap(player.getPosition());
        player.moveDown();
        System.out.println();
        map.showMap(player.getPosition());
    }
}
