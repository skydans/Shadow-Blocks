package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**This class represents the Wall sprite and it is a sub-class of Inpenetrable 
 * class.
 * @author Daniel Gonawan
 *
 */
public class Wall extends Inpenetrable {
	/**constructor of the Wall sub-class.
	 * 
	 * @param x x coordinate of sprite.
	 * @param y y coordinate of sprite.
	 * @throws SlickException
	 */
	public Wall(float x, float y) throws SlickException {
		super(x,y);
		//set the image source of the wall sub-class
		setImageSrc("/assets/wall.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setShow(true);
	}
}
