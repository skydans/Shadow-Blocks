package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents the cracked wall sprites. It inherits from  
 * Inpenetrable class and implements CanDisappear interface. 
 * @author Daniel Gonawan
 *
 */
public class Cracked extends Inpenetrable implements CanDisappear{
	public Cracked(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/cracked_wall.png");
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
	/**This is a copy constructor of the Cracked class.
	 * 
	 * @param cracked parameter of type Cracked.
	 * @throws SlickException
	 */
	public Cracked(Cracked cracked) throws SlickException {
		super(cracked);
		setShow(true);
	}
	/**This method calls the method that checks whether the cracked wall 
	 * should disappear or not.
	 */
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
		disappearIfNeeded();
	}
	/**This method checks whether the cracked wall should disappear or not.
	 */
	public void disappearIfNeeded(){
		float[] playerLatestMoveAttemptCopy=World.getPlayerLatestMoveAttempt();
		if(surroundingCheck() && ((playerLatestMoveAttemptCopy[0]==getX()+1 &&
				playerLatestMoveAttemptCopy[1]==getY())||
				(playerLatestMoveAttemptCopy[0]==getX()-1 &&
				playerLatestMoveAttemptCopy[1]==getY())||
				(playerLatestMoveAttemptCopy[0]==getX() &&
				playerLatestMoveAttemptCopy[1]==getY()+1)||
				(playerLatestMoveAttemptCopy[0]==getX() &&
				playerLatestMoveAttemptCopy[1]==getY()-1))){
			World.addToHide(World.getCurrentUpdateIndex());
			//System.out.println("Cracked Wall check");
		}
	}
	/**This method checks whether there is Tnt in the surroundings of the 
	 * cracked wall.
	 */
	public boolean surroundingCheck(){
		if(World.isBlockedByParticularSprite(getX(),getY()+1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),Tnt.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),Tnt.class)){
			return true;
		}
		return false;
	}
}
