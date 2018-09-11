package ass2;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class System {

    private Map map;
    private player player;

    public System()
    {
        map = new Map(20,20);
        player = new player(map);

    }

    public  void key(KeyEvent keyEvent)
    {
        int key = keyEvent.getKeyCode();

        switch (key)
        {
            case KeyEvent.VK_UP:
                player.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                player.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRigt();
                break;
            default:
                break;
        }
    }


}
