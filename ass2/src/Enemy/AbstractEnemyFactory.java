package Enemy;

import ass2.*;

public abstract class AbstractEnemyFactory {

    public abstract Enemy getEnemy(String className, Map map, Coordinate coordinate);
}
