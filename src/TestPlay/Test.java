package TestPlay;

public class Test {


    public static void main(String[] args)
    {
        Player player = new Player();
        Map map = new Map(10,10);

        map.setMap();
        map.showMap(player.getPosition());

        System.out.println();

        for (int i = 0;i < 9; i ++)
        {
            player.moveRight(map.getWidth());
            map.showMap(player.getPosition());
            System.out.println();
        }


        for (int i = 0; i < 5; i++)
        {
            player.moveDown(map.getHeight());
            map.showMap(player.getPosition());
            System.out.println();
        }


        for (int i = 0; i < 5; i ++)
        {
            player.moveLeft();
            map.showMap(player.getPosition());
            System.out.println();
        }

        for (int i = 0; i < 5; i ++)
        {
            player.moveUp();
            map.showMap(player.getPosition());
            System.out.println();
        }





    }
}
