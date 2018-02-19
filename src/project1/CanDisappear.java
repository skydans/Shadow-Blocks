package project1;

import org.newdawn.slick.SlickException;
/**This interface is intended for classes that can disappear such as Tnt and 
 * cracked wall.
 * @author Daniel Gonawan
 *
 */
public interface CanDisappear{
	/**This abstract method is the prototype method for the method that makes 
	 * the object disappear.
	 * @throws SlickException
	 */
	public abstract void disappearIfNeeded() throws SlickException;
	/**This method is some kind of polling which regularly checks for a 
	 * callback.
	 * @return
	 */
	public abstract boolean surroundingCheck();
}
