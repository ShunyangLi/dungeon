package ass2;

public class GameSystem {

    public static void main(String[] args)
    {
        Map map = new Map(20,20);
        map.setWall();
        Player player = new Player(map);

        map.showMap(player.getPosition());
        player.moveDown();
        System.out.println();
        map.showMap(player.getPosition());
        System.out.println();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveRigt();
        player.moveRigt();
        player.moveRigt();
        player.moveUp();
        player.moveLeft();
        map.showMap(player.getPosition());
    }

}
