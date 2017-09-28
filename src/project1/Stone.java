package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/** Stone is a sub-class of Sprite. */
public class Stone extends Block{
	/** constructor of the Stone sub-class. */
	public Stone(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
	}
	
	public void update(Input input,int delta)throws SlickException{
		super.update(input, delta);
		
	}
	

	

}
