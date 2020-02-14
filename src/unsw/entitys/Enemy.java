package unsw.entitys;

import unsw.algorithm.Location;
import unsw.dungeon.Dungeon;
import java.util.List;

public class Enemy extends Entity {
    public Enemy(int x, int y) {
        super(x, y);
    }

    /**
     * @brief this function means the enemy is close to the player
     * and use the shortest way to find the player
     * maybe need have a check whether the player died
     */
    public void huntPlayer(List<Location> path) {
        if (path == null || path.size() < 1) return;
        // Collections.reverse(path);
        Location nextStep = path.get(0);

        x().set(nextStep.getX());
        y().set(nextStep.getY());
    }
    
    /**
     * @brief this function returns a new location away from the player: 
     * because enemy needs to run away from player when player is invincible
     * @param dungeon
     */
    public Location runaway(Dungeon dungeon) {
    	int finalx = getX();
    	int finaly = getY();
    	Player p = dungeon.getPlayer();
    	
    	if (p.getX() >= getX() && getX() > 0) {
    		finalx -= 1; 
    		//player right to enemy, enemy move left
    		if (dungeon.getEntity(new Location(finalx, finaly)) != null) finalx +=1;
    	}else if (p.getX() <= getX() && getX() < dungeon.getWidth()-1) {
    		finalx += 1; 
    		//player left to enemy, enemy move right
    		if (dungeon.getEntity(new Location(finalx, finaly)) != null) finalx -=1;
    	}
		if (p.getY() >= getY() && getY() > 0) {
			finaly -= 1; 
			//player above enemy, enemy move down
			if (dungeon.getEntity(new Location(finalx, finaly)) != null) finaly +=1;
		}else if (p.getY() <= getY() && getY() < dungeon.getHeight()-1) {
    		finaly += 1; 
    		//player below enemy, enemy move up
    		if (dungeon.getEntity(new Location(finalx, finaly)) != null) finaly -=1;
    	}
	
        return new Location(finalx, finaly);
    }

    public void update(Entity entity) {
        setChanged();
        notifyObservers(entity);
    }
}
