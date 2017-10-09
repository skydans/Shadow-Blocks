package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class is a sub-class of Sprite. The methods in this class is 
 * inherited from the Sprite class.
 * 
 * @author Daniel Gonawan
 *
 */
public class Stone extends Block{
	/**Constructor of the Stone sub-class.  
	 * 
	 * @param image_src
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Stone(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/stone.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	/**Copy constructor of Stone sub-class.
	 * 
	 * @param stone
	 * @throws SlickException
	 */
	public Stone(Stone stone) throws SlickException {
		super(stone);
	}
	/**This method is the update method for the Stone sub-class.
	 * 
	 */
	public void update(Input input,int delta)throws SlickException{
		super.update(input, delta);
		
	}
	

	

}
