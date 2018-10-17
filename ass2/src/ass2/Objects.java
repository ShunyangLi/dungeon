package ass2;

public class Objects {

    // this is all the static objects in the game
    public static final int road = 0;
    public static final int wall = 1;
    public static final int exit = 2;
    public static final int boulder = 3;
    public static final int floor_switch = 4;
    public static final int door = 5;
    public static final int key = 7;
    public static final int pit = 9;
    public static final int OpenDoor = 19;
//    public static final int fire = 20;

    // this is props player can pick up
    public static final int bomb = 8;    // 待定，bloom是否可以自带属
    public static final int sword = 10;
    public static final int arrow = 11;
    public static final int treasure = 6;
    public static final int invincibility = 12;
    public static final int hover = 13;

    // This is the players
    public static final int player = 14;
    public static final int hunter = 15;
    public static final int strategist = 16;
    public static final int hound = 17;
    public static final int coward = 18;

    public boolean isProps(int val) {

        if (val == Objects.arrow || val == Objects.bomb || val == Objects.key ||
            val == Objects.sword || val == Objects.treasure ||
            val == Objects.hover || val == Objects.invincibility) {
            return true;
        }
        return false;
    }

    public boolean isEnemy(int val) {

        if (val == Objects.hunter || val == Objects.strategist ||
            val == Objects.hound || val == Objects.coward) {
            return true;
        }
        return false;
    }

}
