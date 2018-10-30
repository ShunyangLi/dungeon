package PlayerMove;

import ass2.Coordinate;
import ass2.Map;
import ass2.Objects;
import ass2.Player;
import props.Props;

import java.util.HashMap;

public class MoveAble implements MoveState {

    private Player player;
    private Map map;
    private HashMap<Integer, Props> bag;

    /**
     * @param player which is Class player, because we need to detect whether the player can move around in the position
     */
    public MoveAble (Player player) {
        this.player = player;
        this.map = this.player.getMap();
        this.bag = this.player.getBag();
    }

    // this state means, whether the objects is throughable
    @Override
    public boolean state(int x, int y) {
        int objects = this.map.getValue(x,y);
        // boolean state = true;
        if (objects == Objects.exit) {
            this.player.setSuccess(true);
            return false;
        }else if (objects == Objects.wall || objects == Objects.boulder) {
            return false;
        } else if (new Objects().isProps(objects)) {
            if (this.bag.get(objects).pickUp()) {
                this.player.setPlayer(new Coordinate(x,y,Objects.road));
            } else {
                return false;
            }
        } else if (objects == Objects.door
                && this.bag.get(Objects.key).getNum() > 0) {
            this.bag.get(Objects.key).use();
            this.player.setPreValue(Objects.OpenDoor);
            this.player.setPlayer(new Coordinate(x,y,Objects.road));
            return true;
        } else if (objects == Objects.door
                && this.bag.get(Objects.key).getNum() <= 0 ){
            return false;
        } else if (objects == Objects.OpenDoor) {
            this.player.setPreValue(Objects.OpenDoor);
        }

        this.player.setPlayer(new Coordinate(x,y,Objects.road));
        return true;
    }
}
