//package ass2;
//import Enemy.*;
//import ShortestPath.Location;
//import props.*;
//import java.util.Scanner;
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//public class GameSystem {
//    static Timer timer = new Timer();
//    static int seconds = 0;
////
////    public static void main(String[] args) {
////        Map map = new Map(16,18);
////        // map.setWall();
////        Sword sword = new Sword(map);
////        Arrow arrow = new Arrow(map);
////        Bomb bomb = new Bomb(map);
////        Key key = new Key(map);
////        Treasure treasure = new Treasure(map);
////
////        Bag bag = new Bag(sword,arrow,bomb,treasure,key);
////
////        Player player = new Player(map,map.getPosition(Objects.player));
////
////
////        Enemy hunter = new Hunter(map.getPosition(Objects.hunter),map,true);
////        hunter.setMove(new TrackPlayer(hunter));
////
////        Enemy hound = new Hound(map.getPosition(Objects.hound),map,true);
////        hound.setMove(new TrackPlayer(hound));
////
////        Enemy strategist = new Strategist(map.getPosition(Objects.hound),map,true);
////        strategist.setMove(new MoveAround());
////
////        Enemy coward = new Coward(map.getPosition(Objects.hound),map,true);
////        coward.setMove(new TrackPlayer(coward));
////
////
////        map.showMap(player.getPosition());
////        // player.showProps();
////        // System.out.println("This is how to control the game!!");
////        Scanner scanner = new Scanner(System.in);
////
////        // 还要判断是不是赢了
////        while (player.getAlive() && ! player.isSuccess())
////        {
////            if (PlayerDie(player,hunter))
////            {
////                System.out.println("Die!!");
////                break;
////            }else if (PlayerDie(player, hound)) {
////                System.out.println("Die!!");
////                break;
////            } else if (PlayerDie(player, coward)) {
////                System.out.println("Die!!");
////            }
////
////            if (hound.isAlive())
////            {
////                hound.autoMove();
////            }
////            if (hunter.isAlive())
////            {
////                // System.out.println(hunter.alive());
////                hunter.autoMove();
////            }
////            if (coward.isAlive())
////            {
////                if (hide(player, coward))
////                {
////                    System.out.println("Hiding !!");
////                    // TODO coward can move, but still need to hide when the close to the player
////                    // maybe try to put it into coward
////                    // coward.hide();
////                    coward.autoMove();
////                } else {
////                    coward.autoMove();
////                }
////            }
////
////            if (strategist.isAlive())
////            {
////                // do something
////            }
////
////            System.out.println("w: go up, s: go down, a: go left, d: go right, l: light bomb. Enter your action: ");
////            String action = scanner.nextLine();
////            if (action.compareTo("w") == 0)
////            {
////                player.moveUp();
////            } else if (action.compareTo("s") == 0) {
////                player.moveDown();
////            } else if (action.compareTo("a") == 0) {
////                player.moveLeft();
////            } else if (action.compareTo("d") == 0) {
////                player.moveRight();
////            } else if (action.compareTo("l") == 0) {
////                player.lightBomb();
////            }
////
////            if (player.getBag().getBomb().isLight())
////            {
////                // 这个地方就是延迟三秒，然后检查爆炸范围的人物，方圆十里不见人烟
////                System.out.println("Bomb is lighting " + player.getPosition().getX() + " " + player.getPosition().getY());
////                Coordinate b = map.getPosition(Objects.bomb);
////
////                if (b != null)
////                {
////                    MyTimer(b,map, player, hunter, coward, hound, strategist);
////                    player.getBag().getBomb().setLight(false);
////                }
////            }
////
////            // System.out.println();
////            player.showProps();
////            map.showMap(player.getPosition());
////        }
////
////        System.out.println("game over!!");
////        timer.cancel();
////
////    }
////
//   public static void MyTimer(Coordinate position, Map map, Player player, Enemy hunter, Enemy coward, Enemy hound, Enemy strategist) {
//
//       TimerTask task;
//
//       task = new TimerTask() {
//           @Override
//           public void run() {
//               // Bomb bomb = player.getBag().getBomb();
//               Coordinate b =  position;
//
//               System.out.println(position.getX() + " " + position.getY());
//
//               int left_x = b.getX();
//               int left_y = b.getY() - 1;
//
//               int right_x = b.getX();
//               int right_y = b.getY() + 1;
//
//               int up_x = b.getX() - 1;
//               int up_y = b.getY();
//
//               int down_x = b.getX() + 1;
//               int down_y = b.getY();
//
//               int Left = map.getValue(left_x, left_y);
//               int right = map.getValue(right_x, right_y);
//               int up = map.getValue(up_x, up_y);
//               int down = map.getValue(down_x, down_y);
//
//               removeBlock(map, Left, new Location(left_x, left_y), player, hunter, coward, hound, strategist);
//               removeBlock(map, right, new Location(right_x, right_y), player, hunter, coward, hound, strategist);
//               removeBlock(map, up, new Location(up_x, up_y), player, hunter, coward, hound, strategist);
//               removeBlock(map, down, new Location(down_x, down_y), player, hunter, coward, hound, strategist);
//
//               map.setupMap(new Coordinate(b.getX(), b.getY(), Objects.road));
//           }
//       };
//       timer.schedule(task, 3000);
//   }
////
////
//   public static void removeBlock(Map map, int val, Location location, Player player, Enemy hunter, Enemy coward, Enemy hound, Enemy strategist)
//   {
//       if (val == Objects.player)
//       {
//           player.setAlive(false);
//       } else if (val == Objects.hunter) {
//           hunter.setAlive(false);
//           map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
//       } else if (val == Objects.hound) {
//           hound.setAlive(false);
//           map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
//       } else if (val == Objects.coward) {
//           coward.setAlive(false);
//           map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
//       } else if (val == Objects.strategist) {
//           strategist.setAlive(false);
//           map.setupMap(new Coordinate(location.getX(), location.getY(), Objects.road));
//       }
//
//       return;
//   }
////
////    /**
////     * @param player
////     *
////     */
////    public static boolean hide(Player player, Enemy crowd)
////    {
////        int p_x = player.getPosition().getX();
////        int p_y = player.getPosition().getY();
////
////        int h_x = crowd.getPosition().getX();
////        int h_y = crowd.getPosition().getY();
////
////        int val = Math.abs(p_x - h_x) + Math.abs(p_y - h_y);
////        if (val < 5)
////        {
////            return true;
////        }
////        return false;
////    }
////
////    public static boolean PlayerDie(Player player, Enemy hunter)
////    {
////        int p_x = player.getPosition().getX();
////        int p_y = player.getPosition().getY();
////
////        int h_x = hunter.getPosition().getX();
////        int h_y = hunter.getPosition().getY();
////
////        if (p_x - h_x == 0 && p_y - h_y == 0)
////        {
////            if (player.getBag().getSword().getNum() == 0 && player.getInvincibility() == 0 && player.getBag().getArrow().getNum() == 0)
////            {
////                player.setAlive(false);
////                return true;
////            } else {
////                hunter.setAlive(false);
////            }
////
////        }
////        return false;
////    }
//
//
//}
