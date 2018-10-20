package PlayerMove;

import ass2.Coordinate;
import ass2.Map;
import ass2.Objects;
import ass2.Player;
import props.Props;

import java.util.HashMap;

public class IsDie implements MoveState {

    private Player player;
    private Map map;
    private HashMap<Integer, Props> bag;

    /**
     *
     * @param player the player in the map
     * @brief just push the player into the class
     */
    public IsDie (Player player) {
        this.player = player;
        this.map = this.player.getMap();
        this.bag = this.player.getBag();
    }

    /**
     *
     * @param x the next x of the player move
     * @param y the next y of the player move
     * @return return the sate of moveable
     * @brief if the next grid is enemy, then kill them, and clear the road
     *          else if the player do not have weapon or invi, then set the player is die
     */
    @Override
    public boolean state(int x, int y) {
        int objects = this.map.getValue(x,y);
        int[] priority = {Objects.invincibility,Objects.arrow,Objects.sword};

        // to check whether is a enemy
        if (new Objects().isEnemy(objects)) {
            for (int i = 0; i < 3; i ++) {
                if (this.bag.get(priority[i]).use()) {
                    System.out.println(i + " "+ this.bag.get(priority[i]));
                    this.player.setPlayer(new Coordinate(x,y,Objects.road));
                    return false;
                }
            }
            this.player.setAlive(false);
            return true;
        } else if (objects == Objects.pit && this.bag.get(Objects.hover).isBuff()) {
            this.player.setPlayer(new Coordinate(x,y,Objects.road));
            this.player.setPreValue(Objects.pit);
            return false;
        } else if (objects == Objects.pit && ! this.bag.get(Objects.hover).isBuff()) {
            this.player.setAlive(false);
            return true;
        }
        return false;
    }
}
