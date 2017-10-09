package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/** Floor is a sub-class of Sprite. */
public class Floor extends Sprite{
	/** constructor of the Floor sub-class. */
	public Floor(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/floor.png");
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
