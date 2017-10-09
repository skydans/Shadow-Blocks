package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents Target sprites and it is a sub-class of Sprite class.
 * 
 * @author Daniel Gonawan
 *
 */
public class Target extends Sprite {
	private boolean hasBlock;
	/**Constructor of the Target sub-class. It sets the image path for the 
	 * sub-class and makes the sub-class visible when loaded. It is also 
	 * initialised at a state in which there is no block on it.
	 * @param x x coordinate
	 * @param y y coordinate
	 * @throws SlickException
	 */
	public Target(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/target.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		//make the sprite visible when first loaded.
		setShow(true);
		//no block at the beginning.
		hasBlock=false;
	}
	public void update(Input input,int delta){
		hasBlock=World.hasBlockAt(getX(),getY());
	}
	/**This is a getter method that returns true if there is a block on the 
	 * target sprite, and false otherwise.
	 * @return true if there is a block on the target sprite, and  
	 * false otherwise.
	 */
	public boolean getHasBlock(){
		return hasBlock;
	}
	//no setters for hasBlock
}
