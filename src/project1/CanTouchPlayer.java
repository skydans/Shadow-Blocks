package project1;

import org.newdawn.slick.SlickException;
/**This interface is intended for the enemy units or the units other than the 
 * player unit.
 * @author Daniel Gonawan
 *
 */
public interface CanTouchPlayer {
	/**This method checks whether the Units that can cause the game to restart 
	 * touches the Player or not.
	 * 
	 * @return true if it touches the player, false otherwise.
	 * @throws SlickException
	 */
	public abstract boolean checkContactWithPlayer() throws SlickException;
}
