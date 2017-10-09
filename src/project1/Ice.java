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
	public void update(Input input,int delta){
		/* Check the available directions the block can move for the current
		 * position. 
		 */
		blockCheck();
		/* Retrieve the current coordinates and directions of the player and
		 * rogue latest moves. 
		 */
		float[] playerLatestMoveCopy=World.getPlayerLatestMove();
		float[] rogueLatestMoveCopy=World.getRogueLatestMove();
		if(playerLatestMoveCopy[0]==getX() && playerLatestMoveCopy[1]==getY()){
			tempDirection=(int)playerLatestMoveCopy[2];
			moveToDest(tempDirection);
			//System.out.println(playerLatestMoveCopy[0]);
			//System.out.println(playerLatestMoveCopy[1]);
			//System.out.println("Ice moveToDest triggered by Player");
		}else if(rogueLatestMoveCopy[0]==getX() && 
				rogueLatestMoveCopy[1]==getY()){
			tempDirection=(int)rogueLatestMoveCopy[2];
			moveToDest(tempDirection);
			//System.out.println("Ice moveToDest triggered by Rogue");
		}
		// Add up the timer value based on delta.
		timer+=delta;
		if(timer>=100){//if(keepMoving && timer>=5){
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
			if(!World.isBlocked(getX()+deltaX,getY()+deltaY)
					&& !World.isBlockedByBlock(getX()+deltaX, 
							getY()+deltaY) && !World.isBlockedByUnit(getX()+deltaX, 
									getY()+deltaY)){
				moveToDest(tempDirection);
			}else{
				tempDirection=DIR_NONE;
			}
			//Reset the timer
			timer=0;
		}
		
		//if(keepMoving){timer+=delta;System.out.println("timer: "+timer+"\n");}
		//if(timer>=200){timer=0;}
		/* Check the available directions the block can move for the current
		 * position. 
		 */
		blockCheck();
	}
}
