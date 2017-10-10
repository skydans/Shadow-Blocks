package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents the Skeleton sprite. It inherits form Unit class and 
 * contains methods that define the movement of Skeleton.
 * @author Daniel Gonawan
 *
 */
public class Skeleton extends Unit {
	private int timer,currentDirection;
	/**Constructor of Skeleton which initialises the image path of the 
	 * Skeleton. and makes the Skeleton visible. It also sets the initial 
	 * direction of the Skeleton to the up.
	 * @param x x coordinate of skeleton
	 * @param y y coordinate of skeleton
	 * @throws SlickException
	 */
	public Skeleton(float x,float y) throws SlickException{
		super(x,y);
		setImageSrc("/assets/skeleton.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		//Sets the Skeleton so that it becomes visible.
		setShow(true);
		//Sets the timer count to 0.
		timer=0;
		//Sets the initial direction upwards.
		currentDirection=DIR_UP;
	}
	/**This is the update method of Skeleton. It increments the timer so that 
	 * the timer is incremented every time the update method is called. It 
	 * also changes direction if the Skeleton tries to move into a tile that 
	 * is blocked.
	 */
	@Override
	public void update(Input input, int delta) throws SlickException{
		timer+=delta;
		if(timer>=1000){
			if(!moveToDest(currentDirection)){
				if(currentDirection==DIR_UP){
					currentDirection=DIR_DOWN;
				}else{
					currentDirection=DIR_UP;
				}
				moveToDest(currentDirection);
			}
			timer=0;
		}
		if(checkContactWithPlayer()){World.setWillRestart(true);}
	}
	/**This method executes the Skeleton so that it can move towards a 
	 * particular direction.
	 * 
	 * @param dir direction of movement.
	 */
	public boolean moveToDest(int dir){
		int deltaX=0,deltaY=0;
		switch(dir){
		case DIR_UP:
			deltaY-=1;
			break;
		case DIR_DOWN:
			deltaY+=1;
			break;
		}
		if(!World.isBlocked("Inpenetrable",getX()+deltaX,getY()+deltaY) && 
				!World.isBlocked("Block",getX()+deltaX, getY()+deltaY) ){
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			return true;
		}else{
			return false;
		}
	}
	/**This method checks whether the Skeleton touches the Player or not.
	 * 
	 * @return true if it touches the player, false otherwise.
	 * @throws SlickException
	 */
	public boolean checkContactWithPlayer() throws SlickException{
		if(World.getSprite("Player").getX()==getX() && World.getSprite("Player").getY()==getY()){
			return true;
		}
		return false;
	}
	
}
