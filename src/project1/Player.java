package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/** Player is a sub-class of Sprite. */
public class Player extends Unit{
	
	/** constructor of the Player sub-class. */
	public Player(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/player_left.png");
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
	
	public Player(Player player) throws SlickException{
		super(player);
		setShow(true);
	}
	
	//this moveToDest is different from the one in Stone class.
	public boolean moveToDest(int dir) throws SlickException{
		int deltaX=0,deltaY=0;
		switch(dir){
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
		if(!World.isBlocked("Inpenetrable",getX()+deltaX,getY()+deltaY) && 
				!World.isBlockedByAdjacentBlock(getX()+deltaX, getY()+deltaY, dir) ){
			World.setPlayerLatestMove(getX()+deltaX,getY()+deltaY,dir);
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			
		}else{
			World.setPlayerLatestMoveAttempt(getX()+deltaX, getY()+deltaY);
		}
		if(dir!=DIR_NONE){
			World.setMoves(World.getMoves()+1);
			World.recordMovesHistory();
		}
		/* to follow the abstract method in Unit */
		return true;
	}
	
	/** Handles the update method for the player sub-class.
	 *  It detects key presses and change the position of the player using
	 *  setters and getters.
	 */
	public void update(Input input,int delta) throws SlickException{
		//inherit the code from the super-class.
		super.update(input,delta);
		int dir=DIR_NONE;
		if (input.isKeyPressed(Input.KEY_UP)) {
			dir=DIR_UP;
			
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir=DIR_DOWN;
			
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir=DIR_LEFT;
			
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir=DIR_RIGHT;
			
		}
		moveToDest(dir);
	}
	
	
	
}
