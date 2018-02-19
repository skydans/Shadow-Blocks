package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**This class contains the constructor for the Door class.
 * 
 * @author Daniel Gonawan
 *
 */
public class Door extends Inpenetrable {
	/**This constructor makes the door visible when the door is loaded.
	 * 
	 * @param image_src
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Door(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/door.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		//Make the door visible
		setShow(true);
	}
	
}
