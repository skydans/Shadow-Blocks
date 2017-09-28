package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/** Target is a sub-class of Sprite. */
public class Target extends Sprite {
	private boolean hasBlock;
	/** constructor of the Target sub-class. */
	public Target(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
		hasBlock=false;
	}
	public void update(Input input,int delta){
		hasBlock=World.hasBlockAt(getX(),getY());
	}
	public boolean getHasBlock(){
		return hasBlock;
	}
	//no setters for hasBlock
}
