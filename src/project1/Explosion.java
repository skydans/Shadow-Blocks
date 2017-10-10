package project1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**This class contains methods to show where the explosion is.
 * 
 * @author Daniel Gonawan
 *
 */
public class Explosion extends Sprite {
	private boolean show;
	private int timer;
	/**Constructor of the Explosion sub-class.
	 * 
	 * @param image_src
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Explosion(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/explosion.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		/* exception for Explosion, the render method of Explosion is 
		 * overriden. */
		setShow(true);
		timer=0;
	}
	
	/**This method updates the timer that determines how long the explosion 
	 * will be shown.
	 */
	public void update(Input input, int delta) throws SlickException{
		
		showOnTntIfNeeded();
		/*Add the timer when the explosion is being shown.*/
		if(show){timer+=delta;}
		if(timer>=300){
			show=false;
			/* reset the timer in case it needs to be used again */
			timer=0;
		}

	}
	/**This method renders the explosion sprite when it is needed to be shown.
	 * 
	 */
	@Override
	public void render(Graphics g) {
		if(show){
			renderSprite();
		}
	}
	/**This method calls another function to retrieve the latest Tnt position 
	 * and set the coordinate on that TNT position.
	 */
	public void showOnTntIfNeeded() throws SlickException{
		/* Checks whether the Tnt is going to disappear, if yes, show the 
		 * explosion. This is with the assumption no more than 1 TNT can 
		 * explode at the same time.
		 */
		if(World.isSpriteInToHide(Tnt.class)){
			System.out.println("toDelete not empty inside if statement");
			setX(World.getSprite("Tnt").getX());
			setY(World.getSprite("Tnt").getY());
			show=true;
		}
	}
}
