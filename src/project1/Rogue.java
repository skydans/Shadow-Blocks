package project1;

import java.util.Arrays;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents the Rogue sprite. It inherits from Unit class and 
 * it contains methods that define how the rogue moves (e.g. it moves only 
 * in left or right directions).
 * @author Daniel Gonawan
 *
 */
public class Rogue extends Unit implements CanTouchPlayer{
	private int currentDirection;
	private float[] latestMove;
	//private float[] latestMove;
	/**This is the constructor of the Rogue class. It initialises the path 
	 * of the rogue image file.
	 * 
	 * @param x x coordinate of the rogue sprite.
	 * @param y y coordinate of the rogue sprite.
	 * @throws SlickException
	 */
	public Rogue(float x,float y) throws SlickException{
		super(x,y);
		setImageSrc("/assets/rogue.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setShow(true);
		currentDirection=DIR_LEFT;
		latestMove=new float[3];
	}
	/**This is a copy constructor of Rogue class. It initialises the instance 
	 * variables of the Rogue class based on the object that it is going to 
	 * copy from.
	 * 
	 * @param player parameter of type Rogue.
	 * @throws SlickException
	 */
	public Rogue(Rogue rogue) throws SlickException{
		super(rogue);
		//setShow(rogue.getShow());
		latestMove=Arrays.copyOf(rogue.latestMove, rogue.latestMove.length);
		currentDirection=rogue.currentDirection;
		
	}
	/**This method defines how the Rogue moves. It only allows the rogue to 
	 * move either to the left or to the right. It also checks whether the 
	 * move will be possible and changes direction if the move is impossible.
	 */
	public boolean moveToDest(int dir){
		int deltaX=0,deltaY=0;
		switch(dir){
		case DIR_LEFT:
			deltaX-=1;
			break;
		case DIR_RIGHT:
			deltaX+=1;
			break;
		}
		if(!World.isBlocked("Inpenetrable",getX()+deltaX,getY()+deltaY) && 
				!World.isBlockedByAdjacentBlock(getX()+deltaX, getY()+deltaY,
						dir)){
			latestMove[0]=getX()+deltaX;
			latestMove[1]=getY()+deltaY;
			latestMove[2]=dir;
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			return true;
		}else{
			return false;
		}
	}
	/**This method checks whether the rogue touches the player or not.
	 * 
	 * @return true if the rogue touches the player, false otherwise.
	 * @throws SlickException
	 */
	public boolean checkContactWithPlayer() throws SlickException{
		if(World.getSprite("Player").getX()==getX() && 
				World.getSprite("Player").getY()==getY()){
			return true;
		}
		return false;
		
	}
	/**This method checks whether the player has made a move and makes the 
	 * rogue to move if the player moves.
	 * It also calls functions that check whether the rogue touches the player 
	 * or not.
	 */
	@Override
	public void update(Input input,int delta) throws SlickException{
		
		if(((Player)World.getSprite("Player")).getPlayerMoved()){
			/* Check whether the rogue is in contact with player before the 
			 * rogue makes a move. 
			 */
			if(checkContactWithPlayer() && 
					((Player)World.getSprite("Player"))
					.getLatestMove()[2]!=DIR_UP
					&& ((Player)World.getSprite("Player"))
					.getLatestMove()[2]!=DIR_DOWN &&
					currentDirection!=((Player)World.getSprite("Player"))
					.getLatestMove()[2]){
				World.setWillRestart(true);}
			if(!moveToDest(currentDirection)){
				if(currentDirection==DIR_LEFT){
					currentDirection=DIR_RIGHT;
				}else{
					currentDirection=DIR_LEFT;
				}
				/*If the moveToDest fails, then the direction changes. An 
				 * additional checking is required to know whether the rogue 
				 * is in contact with the player or not.
				 * This is to handle the case where the player moves in the 
				 * opposite direction into a rogue that changes direction 
				 * next to a wall.
				 */
				if(checkContactWithPlayer() && 
						((Player)World.getSprite("Player"))
						.getLatestMove()[2]!=DIR_UP
						&& ((Player)World.getSprite("Player"))
						.getLatestMove()[2]!=DIR_DOWN &&
						currentDirection!=((Player)World.getSprite("Player"))
						.getLatestMove()[2]){
					World.setWillRestart(true);}
				moveToDest(currentDirection);
			}
			/* Check whether the rogue is in contact with player after the 
			 * rogue makes a move. 
			 */
			if(checkContactWithPlayer()){World.setWillRestart(true);}
		}
		
	}
	/**This method returns a copy of an array which stores the data of the 
	 * latest move done by the rogue.
	 * 
	 * @return a copy of an array of 3 elements which are the 
	 * x and y coordinates the rogue attempted to move into, and
	 * the direction of that attempt.
	 */	
	public float[] getLatestMove(){
		float[] rogueLatestMoveCopy=Arrays.copyOf(latestMove, 
				latestMove.length);
		return rogueLatestMoveCopy;
	}
	
	
}
