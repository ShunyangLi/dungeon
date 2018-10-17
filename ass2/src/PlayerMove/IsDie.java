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

    public IsDie (Player player) {
        this.player = player;
        this.map = this.player.getMap();
        this.bag = this.player.getBag();
    }

    @Override
    public boolean state(int x, int y) {
        int objects = this.map.getValue(x,y);
        int[] priority = {Objects.invincibility,Objects.arrow,Objects.sword};

        if (new Objects().isEnemy(objects)) {
            for (int i = 0; i < 3; i ++) {
                if (this.bag.get(priority[i]).use()) {
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
