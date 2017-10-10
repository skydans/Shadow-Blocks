package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class is an abstract class that inherits from Sprite class. It is 
 * intended for sprites that are not not penetrable by units and blocks, such 
 * as wall and cracked wall.
 * @author Daniel Gonawan
 *
 */
public abstract class Inpenetrable extends Sprite {
	/**Constructor of the Inpenetrable class
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Inpenetrable(float x,float y) throws SlickException{
		super(x,y);
	}
	/**Copy constructor of the Inpenetrable abstract class.
	 * 
	 * @param inpenetrable parameter of type Inpenetrable.
	 * @throws SlickException
	 */
	public Inpenetrable(Inpenetrable inpenetrable) throws SlickException{
		super(inpenetrable);
	}
	/**Update method for the Inpenetrable class.
	 * 
	 */
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
	}
}
