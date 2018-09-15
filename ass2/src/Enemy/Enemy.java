package Enemy;

import ass2.*;

public abstract class Enemy {

    public boolean vildateMove(Enemy enemy, int x, int y)
    {
        if (enemy.getMap(enemy).getValue(x, y) == Objects.road)
        {
            return true;
        }

        return false;
    }

    public void setEnemyX(Enemy enemy, int x)
    {
        enemy.getPosition(enemy).setValue(Objects.road);
        enemy.getMap(enemy).setupMap(enemy.getPosition(enemy));
        enemy.getPosition(enemy).setX(x);
        enemy.getPosition(enemy).setValue(enemy.getVal(enemy));
        enemy.getMap(enemy).setupMap(enemy.getPosition(enemy));
    }

    public void setEnemyY(Enemy enemy, int y)
    {
        enemy.getPosition(enemy).setValue(Objects.road);
        enemy.getMap(enemy).setupMap(enemy.getPosition(enemy));
        enemy.getPosition(enemy).setY(y);
        enemy.getPosition(enemy).setValue(enemy.getVal(enemy));
        enemy.getMap(enemy).setupMap(enemy.getPosition(enemy));
    }

    public boolean setDie(Enemy enemy)
    {
        enemy.getPosition(enemy).setValue(Objects.road);
        enemy.getMap(enemy).setupMap(enemy.getPosition(enemy));
        return true;
    }


    public void moveUp(Enemy enemy)
    {
        int x = enemy.getPosition(enemy).getX() - 1;
        int y = enemy.getPosition(enemy).getY();

        if (vildateMove(enemy, x, y))
        {
            setEnemyX(enemy, x);
        } else if (enemy.getMap(enemy).getValue(x, y) == Objects.pit) {
            setDie(enemy);
        }
    }

    public void moveDown(Enemy enemy)
    {
        int x = enemy.getPosition(enemy).getX() + 1;
        int y = enemy.getPosition(enemy).getY();

        if (vildateMove(enemy, x, y))
        {
            setEnemyX(enemy, x);
        } else if (enemy.getMap(enemy).getValue(x, y) == Objects.pit) {
            setDie(enemy);
        }
    }

    public void moveLeft(Enemy enemy)
    {
        int x = enemy.getPosition(enemy).getX();
        int y = enemy.getPosition(enemy).getY() - 1;

        if (vildateMove(enemy, x, y))
        {
            setEnemyY(enemy, y);
        } else if (enemy.getMap(enemy).getValue(x, y) == Objects.pit) {
            setDie(enemy);
        }
    }

    public void moveRight(Enemy enemy)
    {
        int x = enemy.getPosition(enemy).getX();
        int y = enemy.getPosition(enemy).getY() + 1;

        if (vildateMove(enemy, x, y))
        {
            setEnemyY(enemy, y);
        } else if (enemy.getMap(enemy).getValue(x, y) == Objects.pit) {
            setDie(enemy);
        }
    }


    public Coordinate getPosition(Enemy enemy)
    {
        return enemy.getPosition(enemy);
    }

    public Map getMap(Enemy enemy)
    {
        return enemy.getMap(enemy);
    }

    public int getVal(Enemy enemy)
    {
        return enemy.getVal(enemy);
    }

}