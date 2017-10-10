package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Rogue extends Unit{
	private int currentDirection;
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
	}
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
			World.setRogueLatestMove(getX()+deltaX,getY()+deltaY,dir);
			//float[] tempPlayerLatestMove=World.getPlayerLatestMove();
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			return true;
		}else{
			return false;
		}
	}
	public boolean checkContactWithPlayer() throws SlickException{
		if(World.getSprite("Player").getX()==getX() && World.getSprite("Player").getY()==getY()){
			return true;
		}
		return false;
		
	}
	@Override
	public void update(Input input,int delta) throws SlickException{
		if(World.playerMoved()){
			/* Check whether the rogue is in contact with player before the 
			 * rogue makes a move. 
			 */
			if(checkContactWithPlayer() && World.getPlayerLatestMove()[2]!=DIR_UP
					&& World.getPlayerLatestMove()[2]!=DIR_DOWN &&
					currentDirection!=World.getPlayerLatestMove()[2]){
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
				if(checkContactWithPlayer() && World.getPlayerLatestMove()[2]!=DIR_UP
						&& World.getPlayerLatestMove()[2]!=DIR_DOWN &&
						currentDirection!=World.getPlayerLatestMove()[2]){
					World.setWillRestart(true);}
				moveToDest(currentDirection);
			}
			/* Check whether the rogue is in contact with player after the 
			 * rogue makes a move. 
			 */
			if(checkContactWithPlayer()){World.setWillRestart(true);}
		}
		
	}
	
	
}
