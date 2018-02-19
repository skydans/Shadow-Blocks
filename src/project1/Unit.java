package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This abstract class is intended for sprites that are classified as units.
 * 
 * @author Daniel Gonawan
 *
 */
public abstract class Unit extends Sprite {
	/**Constructor of Unit class.
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Unit(float x,float y) throws SlickException{
		super(x,y);
	}
	/**Copy constructor of Unit class.
	 * 
	 * @param unit
	 * @throws SlickException
	 */
	public Unit(Unit unit) 
			throws SlickException{
		super(unit);
	}
	/**Update method of Unit class. This method is then inherited to the 
	 * sub-classes of Unit class.
	 */
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
