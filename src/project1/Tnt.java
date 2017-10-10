package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents the Tnt sprite. It contains methods that will make 
 * the Tnt disappear when it hits the cracked wall.
 * 
 * @author Daniel Gonawan
 *
 */
public class Tnt extends Block implements CanDisappear{
	/**Constructor of Tnt class.
	 * It initialises the image path of Tnt.
	 * @param x x coordinate of Tnt sprite.
	 * @param y y coordinate of TNt.
	 * @throws SlickException
	 */
	public Tnt(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/tnt.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	/**Copy constructor of Tnt class.
	 * 
	 * @param tnt
	 * @throws SlickException
	 */
	public Tnt(Tnt tnt) throws SlickException {
		super(tnt);
	}
	/**This is the update method of the Tnt class. It calls the function that 
	 * will make the Tnt disappear if it is being pushed towards a cracked 
	 * wall.
	 */
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
		
		disappearIfNeeded();
		//World.setLatestTntPosition(getX(), getY());
	}
	/**This method will make the Tnt disappear after the explosion and when it 
	 * collides into a cracked wall.
	 */
	public void disappearIfNeeded(){
		float[] playerLatestMoveAttemptCopy=World.getPlayerLatestMoveAttempt();
		if(surroundingCheck() && playerLatestMoveAttemptCopy[0]==getX() && playerLatestMoveAttemptCopy[1]==getY()){
			World.addToHide(World.getCurrentUpdateIndex());
			System.out.println("Tnt check");
		}
	}
	/**This method checks whether there is a cracked wall in the surrounding 
	 * of the Tnt.
	 */
	public boolean surroundingCheck(){
		if(World.isBlockedByParticularSprite(getX(),getY()+1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),
						Cracked.class)){
			/*if(World.isBlockedByParticularSprite(getX(),getY()+1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX(),getY()+1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),Tnt.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),Tnt.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),
						Cracked.class)){
			 */
			return true;
		}
		return false;
	}
	
}
