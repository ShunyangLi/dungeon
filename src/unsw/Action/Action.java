package unsw.Action;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.algorithm.Location;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Objects;
import unsw.entitys.*;
import java.util.*;

public class Action extends Observable {

    /**
     * @brief it assigned to advanced model, because it include all the entities
     *        if will check if road, open door or props, it can pass
     *        otherwise not moveable
     * @param next next position of player move
     * @param next_next next of next
     * @param player the player
     * @return whether can move
     */
    public boolean isInvalidMove(Location next, Location next_next, Player player) {
        Dungeon dungeon = player.getDungeon();
        Entity nextEntity = dungeon.getEntity(next);
        Entity boulder = dungeon.getBoulder(next);
        
        if (!checkBoundary(next,dungeon)) return false;

        // if the player contain a key, then just go through it to open it
        if (nextEntity instanceof Door) {
            player.openDoor();
            return dungeon.getEntity(next) instanceof OpenDoor;
        }

        // if not closed door or wall, then check whether enemy or boulder
        if (!(nextEntity instanceof Wall)) {
            // if was enemy, then check whether invincible
            if (nextEntity instanceof Enemy) {
                // if not invincible, then notifity controller to stop the game
                // and make dialog
                if (!player.isInvincible()){
                    player.x().set(next.getX());
                    player.y().set(next.getY());
                    player.setAlive(false);
                    setChanged();
                    notifyObservers("Die");
                    return false;
                } else {
                    // else the enemy will be removed
                    dungeon.removeEntity(nextEntity);
                    setChanged();
                    notifyObservers(nextEntity);
                }
            } else if (boulder != null) {
                // check boulder
                Entity nextNext = dungeon.getBoulder(next_next);
                Entity entity1 = dungeon.getEntity(next_next);
                // if boulder next is null, that means was road, return true otherwise false
                if (nextNext != null) {
                    return false;
                } else if (entity1 instanceof Floorswitch || entity1 == null) {
                    // if boulder next is not a wall, so it can move
                    if (!checkBoundary(next_next, dungeon)) return false;
                    boulderMove(next_next,boulder);
                    return true;
                }

                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @brief pick up the current position props if has props
     *        after pick up, notify observer to update ui
     * @param curr the current position which need to pick up
     * @param player the player
     */
    public void pickUp(Location curr, Player player) {
        Dungeon dungeon = player.getDungeon();
        List<Entity> props = dungeon.getCurrentEntity(curr);
        if (props == null )return;
        if (props.size() == 0) return;
        // test whether get props in the position
        for (Entity entity:props) {
            if (player.pickProps(entity)) {
                dungeon.removeEntity(entity);
                setChanged();
                notifyObservers(entity);
            }
        }
    }

    /**
     * @brief lit bomb when player press k
     *        it will check whether has bomb
     *        if got bomb then use a timer to count the bomb time
     * @param curr
     * @param player
     */
    public void litBomb(Location curr, Player player) {
        Dungeon dungeon = player.getDungeon();
        // if the player want to lit bomb the space must be a road
        if (dungeon.getEntity(curr) != null) return;

        // check whether the player got bomb
        List<Entity> bomb = player.getEntityByKey(Objects.unlit_bomb);
        if (bomb == null || bomb.size() == 0) return;

        // remove the bomb from bag and update bag
        bomb.remove(0);
        if (bomb.size() == 0) bomb = null;
        player.setProps(Objects.unlit_bomb, bomb);

        LitBomb litBomb = new LitBomb(curr.getX(),curr.getY());
        dungeon.addEntity(litBomb);
        // we need to clear the location firstly
        // set a timer for bomb for 4 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int countTime = 0;
            @Override
            public void run() {

                // for time count
                if (countTime <= 3) {
                    countTime ++;
                    litBomb.setCountTime(countTime);
                } else {
                    countTime ++ ;
                    litBomb.setFinish(true);
                }

                // update the main thread ui
                Platform.runLater(() -> {
                    setChanged();
                    notifyObservers(litBomb);
                });

                if (countTime > 4) {
                    timer.cancel();
                    timer.purge();
                }

            }
        }, 0, 500);
    }

    /**
     * @brief the location is face direction, so it will check
     *        whether the sword is available, then check next is enemy
     * @param location the face location of player
     * @param player player
     */
    public void useSword(Location location, Player player) {
        // avoid null
        if (location == null || player == null) return;
        Dungeon dungeon = player.getDungeon();
        // get the sword form bag
        List<Entity> props = player.getEntityByKey(Objects.sword);
        // if no sword just return
        if (props == null || props.size() == 0) return;
        // get the sword
        Sword sword = (Sword) props.get(0);
        props.remove(0);
        // if the sword used time is > 0
        if (sword.getTimes() > 0) {

            // should add some gif for skills for player
            setChanged();
            notifyObservers(new ImageView(new Image("/sword_gif.gif")));

            // then check next entity
            Entity entity = dungeon.getEntity(location);
            // if next entity is enemy, then we need remove the enemy from dungeon
            if (entity instanceof Enemy) {
                setChanged();
                notifyObservers(entity);
                dungeon.removeEntity(entity);
            }

            // after use set the time minus 1
            sword.setTimes(sword.getTimes() - 1);
            props.add(sword);
            player.setProps(Objects.sword, props);

            return;
        }

        props = null;
        player.setProps(Objects.sword, props);
    }


    /**
     * @brief if the player got a key, and the key id is same with the door
     *        then the player can open the door, otherwise not open
     * @param location the next location which the player want to open
     * @param player
     */

    public void openDoor(Location location, Player player) {
        Dungeon dungeon = player.getDungeon();
        // avoid null
        if (location == null || dungeon == null) return;
        // get key
        List<Entity> key = player.getEntityByKey(Objects.key);
        if (key == null || key.size() == 0) return;
        Key k = (Key) key.get(0);

        // if next entity is not door, just return
        Entity entity = dungeon.getEntity(location);
        if (!(entity instanceof Door)) return;

        // if the key id is not same with door id, just return
        if (!k.getId().equals(((Door) entity).getId())) return;

        // otherwise set the door is open door
        setChanged();
        notifyObservers(entity);

        // remove the key from bag
        player.setProps(Objects.key, null);

        // change the entity into opened door
        dungeon.removeEntity(entity);
        OpenDoor openDoor = new OpenDoor(location.getX(), location.getY());
        dungeon.addEntity(openDoor);


        setChanged();
        notifyObservers(openDoor);

    }


    public void useFire(Player player) {
        Dungeon dungeon = player.getDungeon();
        if (dungeon == null) return;

        // check fires
        List<Entity> fires = player.getEntityByKey(String.valueOf(FireBall.class));
        if (fires == null || fires.size() == 0) return;
        player.setProps(String.valueOf(FireBall.class), null);
        FireBall fireBall = (FireBall) fires.get(0);

        // firstly we need get the direction of player's face
        int ox = 0;
        int oy = 0;

        ImageView imageView = fireBall.getNode();
        switch (player.getFace()) {
            case Objects.face_up:
                oy = -1;
                imageView.setRotate(-90);
                break;
            case Objects.face_down:
                oy = 1;
                imageView.setRotate(90);
                break;
            case Objects.face_left:
                ox = -1;
                imageView.setRotate(180);
                break;
            case Objects.face_right:
                ox = 1;
                imageView.setRotate(0);
                break;
            default:
                break;
        }

        List<Location> locations = new ArrayList<Location>();
        Location l = new Location(player.getX(), player.getY());

        for (int i = 0; i < 4; i ++) {
             Location newL = new Location(l.getX() + ox, l.getY() + oy);
             if (!checkBoundary(newL, dungeon)) break;
             locations.add(newL);
             l = newL;
        }

        if (locations.size() == 0) return;
        // reset the bag of fire

        fireBall.setFinish(false);
        setChanged();
        notifyObservers(fireBall);
        // then update ui

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> {

                    if (locations.size() <= 0) {
                        fireBall.setFinish(true);
                        setChanged();
                        notifyObservers(fireBall);
                        timer.cancel();
                        timer.purge();
                        return;
                    }

                    Location l = locations.get(0);
                    locations.remove(l);

                    Entity entity = dungeon.getEntity(l);
                    if (entity == null) {
                        // update ui
                        fireBall.x().set(l.getX());
                        fireBall.y().set(l.getY());
                    } else {
                        if (entity instanceof Enemy) {
                            //  remove the play
                            dungeon.removeEntity(entity);
                            setChanged();
                            notifyObservers(entity);
                        } else if (l.getX() == player.getX() && l.getY() == player.getY()) {
                            // remove thr fireball gonna stop
                            fireBall.setFinish(true);
                            setChanged();
                            notifyObservers(fireBall);
                            timer.cancel();
                            timer.purge();
                            // notify died
                            setChanged();
                            notifyObservers("You have been killed by fire !");
                        }
                        fireBall.setFinish(true);
                        setChanged();
                        notifyObservers(fireBall);
                        timer.cancel();
                        timer.purge();
                    }
                });

            }

        }, 0,300);

    }


    // move the boulder into the location
    public void boulderMove(Location location, Entity entity) {
        entity.x().set(location.getX());
        entity.y().set(location.getY());
    }


    /**
     * check the row and col value
     * @param next next position
     * @param dungeon dungeon
     * @return check whether is boundary
     */
    public boolean checkBoundary (Location next,  Dungeon dungeon ) {
        if (next.getX() >= dungeon.getWidth()
                || next.getY() >= dungeon.getHeight()
                || next.getX() < 0 || next.getY() < 0) return false;

        return true;
    }
}
