package ass2;


import javafx.scene.layout.CornerRadii;

public class Player {

    private Coordinate position;
    private boolean alive;
    private boolean hover;
    private int invincibility;
    private Map map;
    private Bag bag;
    private int preValue = -1;
    private int flag = -1;

    public Player(Map map, Bag bag)
    {
        this.position = new Coordinate(0,0,Objects.player);
        this.map = map;
        this.map.setupMap(this.position);
        this.alive = true;
        this.bag = bag;
        this.hover = false;
        this.invincibility = 0;
    }

    // 如果放下炸弹的话，等三秒以后把周围四个格子改成fire， if fire then die
    // 少一个检测炸弹的范围，如果这个人在炸弹的范围内，直接gg
    // isDie 和 isMoveable 只替换当前的格子里面的值，把当前的值替换成road
    public boolean isDie(int x, int y)
    {
        int value = this.map.getValue(x,y);
        // 如果是pit的话直接死亡, setAlibe = false
        if (value == Objects.pit && ! this.hover)
        {
            System.out.println("Sorry you fall into pit, you die!!");
            this.setAlive(false);
            return true;
        }
        // 检测，如果碰到敌人
        if (value == Objects.hunter || value == Objects.strategist
                || value == Objects.hound || value == Objects.coward)
        {
            // 如果没有武器也没有无敌药水的话直接死亡
            if (this.bag.getSword().getNum() == 0 && this.bag.getArrow().getNum() == 0 && this.getInvincibility() == 0)
            {
                System.out.println("You have been killed!!");
                this.setAlive(false);
                return true;
            }

            // 如果有无敌药水的话， 敌人直接被杀死，下一个位置被改成road
            if (this.getInvincibility() != 0)
            {
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                return false;

            } else if (this.bag.getSword().getNum() != 0) {
                // 如果没有无敌药水，检测是不是有sword，如果有sword，直接杀死敌人，把当前的位置设置成road
                this.bag.getSword().use();
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                return false;
            } else if (this.bag.getArrow().getNum() != 0) {
                // 如果没有sword，检测arrow，如果有arrow，直接杀死敌人，然后设置当前位置road
                this.bag.getArrow().use();
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                return false;

            } else {

                // 如果什么都没有，直接gg
                System.out.println("You have been killed!!");
                return true;
            }
        }
        return false;
    }


    // 检测能不能移动，以及在移动中捡的东西
    // 写出来一个pre用来储存open door或者pit， 如果pre == 这两个值，然后把当前的值替换为pre
    // TODO 没有检查嗑药的情况
    public boolean isMoveable(int x, int y)
    {
//        boolean flag = false;
        int value = this.map.getValue(x,y);
        // 如果是墙的话不能移动
        if (value == Objects.wall) {
            return false;
        } else if (value == Objects.sword) {
            // 如果是sword的话，先检测是不是已经有了，如果咩有，直接捡起来，否则捡不起来，也过不去
            if (! this.bag.getSword().pickUp())
            {
                System.out.println("You already get one sword");
                return false;
            } else {
                this.position.setValue(Objects.road);
                this.map.setupMap(this.position);
                return true;
            }
        } else if (value == Objects.arrow) {
            // 如果为arrow的话直接捡起来
            this.bag.getArrow().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.treasure) {
            this.bag.getTreasure().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.bomb) {
            this.bag.getBomb().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.key) {
            this.bag.getKey().pickUp();
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.pit && this.hover) {
            this.preValue = Objects.pit;
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.OpenDoor) {
            // 如果是open door需要fix
            this.preValue = Objects.OpenDoor;
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.door && this.bag.getKey().getNum() != 0) {
            this.bag.getKey().use();
            this.position.setValue(Objects.OpenDoor);
            this.map.setupMap(this.position);
            return true;
        } else if (value == Objects.road) {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        }
        return false;
    }

    public boolean isExit(int x, int y)
    {
        int value = this.map.getValue(x, y);
        if (value == Objects.exit)
        {
            System.out.println("SUCCESS!!");
            return true;
        }
        return false;
    }


    // 把人物覆盖的object显示出来
    public void setPre()
    {
        if (this.preValue != -1 && this.flag != -1)
        {
            this.position.setValue(this.preValue);
            this.map.setupMap(this.position);
            this.preValue = -1;
            this.flag = -1;
        } else {
            this.flag ++;
        }
    }

    // 判断是不是road 或者 pit
    // 如果可以移动的话把当前的位置替换为road
    public boolean isBoulderMove(int x, int y)
    {
        if (this.map.getValue(x,y) == Objects.road)
        {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        } else if (this.map.getValue(x,y) == Objects.pit) {
            this.position.setValue(Objects.road);
            this.map.setupMap(this.position);
            return true;
        }

        return false;
    }


    // 暂定在move里面设置下一个坐标的value
    // moveable 只设置物品的坐标
    // 需要设置face的朝向， maybe implement later
    public void moveUp()
    {
        Coordinate coordinate = new Coordinate(this.position.getX() - 1 ,this.position.getY(), Objects.player);

        // TODO
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position.setX(this.position.getX() - 1);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);
    }


    public void moveDown()
    {
        // Coordinate coordinate = new Coordinate(this.position.getX() + 1, this.position.getY(), Objects.player);
        int x = this.position.getX() + 1;
        int y = this.position.getY();
        // TODO 试着去写
        if (! isExit(x,y))
        {
            if (! isDie(x,y))
            {
                // 如果可以移动的话直接移动
                if (isMoveable(x,y))
                {
                    setPre();
                    this.position.setX(x);
                    this.position.setValue(Objects.player);
                    this.map.setupMap((this.position));

                } else if (this.map.getValue(x,y) == Objects.boulder) {
                    // 如果不可以移动的话，判断是不是boulder，如果是就推箱子
                    // 利用boulderMove来判断是不是能推箱子
                    System.out.println("here!!");
                    if(isBoulderMove(x + 1, y))
                    {
                        if (this.map.getValue(x + 1, y) == Objects.pit)
                        {
                            Coordinate coordinate = new Coordinate(x + 1, y, Objects.road);
                            this.map.setupMap(coordinate);
                            this.position.setX(x);
                            this.position.setValue(Objects.player);
                            this.map.setupMap(this.position);
                        } else if (this.map.getValue(x + 1, y) == Objects.road){
                            Coordinate coordinate = new Coordinate(x + 1, y, Objects.boulder);
                            this.map.setupMap(coordinate);
                            this.position.setX(x);
                            this.position.setValue(Objects.player);
                            this.map.setupMap(this.position);
                        }
                    }

                }
            }
        }
    }

    public void moveLeft()
    {
        Coordinate coordinate = new Coordinate(this.position.getX(), this.position.getY() - 1,Objects.player);

        // TODO
        this.position.setValue(Objects.road);
        this.map.setupMap(this.position);
        this.position.setY(this.position.getY() - 1);
        this.position.setValue(Objects.player);
        this.map.setupMap(this.position);

    }

    public void moveRight()
    {

        int x = this.position.getX();
        int y = this.position.getY() + 1;
        // TODO 试着去写
        if (! isExit(x,y))
        {
            if (! isDie(x,y))
            {
                if (isMoveable(x,y))
                {
                    setPre();
                    this.position.setY(this.position.getY() + 1);
                    this.position.setValue(Objects.player);
                    this.map.setupMap((this.position));
                }
            }
        }

//        Coordinate coordinate = new Coordinate(this.position.getX(),this.position.getY() + 1,this.position.getValue());
//
//        // TODO
//        this.position.setValue(Objects.road);
//        this.map.setupMap(this.position);
//        this.position.setY(this.position.getY() + 1);
//        this.position.setValue(Objects.player);
//        this.map.setupMap(this.position);

    }

    public Coordinate getPosition() {
        return this.position;
    }

    public Bag getBag() {
        return this.bag;
    }

    public int getInvincibility() {
        return this.invincibility;
    }

    public boolean getHover()
    {
        return this.hover;
    }

    public void setHover(boolean status)
    {
        this.hover = status;
    }

    public void setInvincibility(int invincibility) {
        this.invincibility = invincibility;
    }

    public void setAlive(boolean status)
    {
        this.alive = status;
    }

    public boolean getAlive()
    {
        return this.alive;
    }

}
