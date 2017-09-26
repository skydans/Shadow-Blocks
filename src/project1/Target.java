package project1;

import org.newdawn.slick.SlickException;
/** Target is a sub-class of Sprite. */
public class Target extends Sprite {
	/** constructor of the Target sub-class. */
	public Target(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
	}
}
