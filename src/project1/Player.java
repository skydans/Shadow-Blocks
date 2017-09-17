package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/** Player is a sub-class of Sprite. */
public class Player extends Sprite{
	
	/** constructor of the Player sub-class. */
	public Player(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
	}
	
	/** Handles the update method for the player sub-class.
	 *  It detects key presses and change the position of the player using
	 *  setters and getters.
	 */
	public void update(Input input,int delta){
		//inherit the code from the super-class.
		super.update(input,delta);
		if (input.isKeyPressed(Input.KEY_UP)) {
			setY(getY()-1);
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			setY(getY()+1);
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			setX(getX()-1);
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			setX(getX()+1);
		}
		
	}
}
