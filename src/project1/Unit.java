package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This abstract class is intended for sprites that are classified as units.
 * 
 * @author Daniel Gonawan
 *
 */
public abstract class Unit extends Sprite {
	public Unit(float x,float y) throws SlickException{
		super(x,y);
	}
	public Unit(Unit unit) 
			throws SlickException{
		super(unit);
	}
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
	};
	/**This abstract method is used to signify that the unit sprites are able 
	 * to move from one position to another in the map.
	 * @param dir direction of movement.
	 * @return boolean value
	 * @throws SlickException
	 */
	public abstract boolean moveToDest(int dir) throws SlickException;
}
