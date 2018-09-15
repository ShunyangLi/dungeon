package Enemy;

import ass2.*;

public class EnemyFactory extends AbstractEnemyFactory {

    @Override
    public Enemy getEnemy(String className, Map map, Coordinate coordinate) {

        if (className.compareTo("Enemy.Hunter") == 0)
        {
            return new Hunter(map, coordinate);
        } else if (className.compareTo("Enemy.Hound") == 0) {
            return new Hound(map, coordinate);
        } else if (className.compareTo("Enemy.Coward") == 0) {
            return new Coward(map, coordinate);
        } else if (className.compareTo("Enemy.Strategist") == 0) {
            return new Strategist(map, coordinate);
        }
        return null;
    }
}
