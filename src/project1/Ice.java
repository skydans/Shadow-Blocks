package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class contains methods that define the behavior of ice once it is 
 * moved by the player or rogue.
 * 
 * @author Daniel Gonawan
 *
 */
public class Ice extends Block {
	private int timer,tempDirection=DIR_NONE;
	/**Constructor of Ice class. It inherits the code from the Block class and 
	 * sets the timer (for the time interval of the movement of the ice) to 
	 * zero.
	 * 
	 * @param image_src
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Ice(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/ice.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		timer=0;
	}
	
	/**Copy constructor of Ice class. It inherits the code from the Block class 
	 * and sets the timer (for the time interval of the movement of the ice) 
	 * to zero.
	 * 
	 * @param ice
	 * @throws SlickException
	 */
	public Ice(Ice ice) throws SlickException {
		super(ice);
		timer=0;
	}
	/**This method checks whether a rogue or player has occupied the ice block 
	 * and moves the ice towards the same direction in whcih the player or 
	 * rogue pushed the ice from.
	 * 
	 * It also uses a timer variable to set a time interval for the ice to 
	 * keep moving accordingly.
	 * 
	 */
	@Override
	public void update(Input input,int delta) throws SlickException{
		/* Check the available directions the block can move for the current
		 * position. 
		 */
		blockCheck();
		/* Retrieve the current coordinates and directions of the player and
		 * rogue latest moves. It also handles the problem if player or rogue 
		 * is not loaded on the sprite array. 
		 */
		float[] playerLatestMoveCopy=World.getSprite("Player").getX()!=-1?
				((Player)World.getSprite("Player")).getLatestMove():
					new float[]{-1,-1};
		float[] rogueLatestMoveCopy=
				World.getSprite("Rogue").getX()!=-1?
						((Rogue)World.getSprite("Rogue")).getLatestMove():
							new float[]{-1,-1};
		if(playerLatestMoveCopy[0]==getX() && playerLatestMoveCopy[1]==getY()){
			tempDirection=(int)playerLatestMoveCopy[2];
			moveToDest(tempDirection);
		}else if(rogueLatestMoveCopy[0]==getX() && 
				rogueLatestMoveCopy[1]==getY()){
			tempDirection=(int)rogueLatestMoveCopy[2];
			moveToDest(tempDirection);
		}
		// Add up the timer value based on delta.
		timer+=delta;
		if(timer>=100){
			int deltaX=0,deltaY=0;
			switch(tempDirection){
			case DIR_UP:
				deltaY-=1;
				break;
			case DIR_DOWN:
				deltaY+=1;
				break;
			case DIR_LEFT:
				deltaX-=1;
				break;
			case DIR_RIGHT:
				deltaX+=1;
				break;
			}
			//Move the ice block if its surroundings are not blocked.
			if(!World.isBlocked("Inpenetrable",getX()+deltaX,getY()+deltaY)
					&& !World.isBlocked("Block",getX()+deltaX, 
							getY()+deltaY) && !World.isBlocked("Unit",getX()+deltaX, 
									getY()+deltaY)){
				moveToDest(tempDirection);
			}else{
				tempDirection=DIR_NONE;
			}
			//Reset the timer
			timer=0;
		}
		
		blockCheck();
	}
}
