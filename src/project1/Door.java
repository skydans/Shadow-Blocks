package project1;

import org.newdawn.slick.SlickException;

public class Door extends Inpenetrable {
	public Door(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
	}
	
}
