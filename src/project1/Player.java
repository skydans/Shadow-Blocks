package project1;

import java.util.Arrays;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents the Player sprite and it is a sub-class of Sprite. It 
 * contains methods to execute player movements and record the history of 
 * player movements.
 *  
 * @author Daniel Gonawan
 *
 */
public class Player extends Unit{
	private int moves;
	private boolean playerMoved;
	private float[] latestMove,latestMoveAttempt;
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
		moves=0;
		playerMoved=false;
		latestMove=new float[3];
		latestMoveAttempt=new float[2];
	}
	/**This is a copy constructor of Player class. It initialises the instance 
	 * variables of the Rogue class based on the object that it is going to 
	 * copy from.
	 * 
	 * @param player parameter of type Player.
	 * @throws SlickException
	 */
	public Player(Player player) throws SlickException{
		super(player);
		moves=player.moves;
		playerMoved=player.playerMoved;
		latestMove=Arrays.copyOf(player.latestMove, player.latestMove.length);
		latestMoveAttempt=Arrays.copyOf(player.latestMoveAttempt, 
				player.latestMoveAttempt.length);
	}
	
	
	/**This method executes the movement of Player towards a particular 
	 * direction. It overrides the method that is in the superclass of Player 
	 * class.
	 * It also checks whether the movement is possible. It executes the 
	 * movement and returns true if it the movement is possible and returns 
	 * false otherwise.
	 */
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
				!World.isBlockedByAdjacentBlock(getX()+deltaX, getY()+deltaY, 
						dir) ){
			latestMove[0]=getX()+deltaX;
			latestMove[1]=getY()+deltaY;
			latestMove[2]=dir;
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			
		}else{
			latestMoveAttempt[0]=getX()+deltaX;
			latestMoveAttempt[1]=getY()+deltaY;
		}
		/* Increment the number of moves made by the player if the player 
		 * attempted to make a move (regardless the success of the move).
		 */
		if(dir!=DIR_NONE){
			moves+=1;
			//For debugging purposes
			//System.out.println("moves: "+moves);
			World.recordMovesHistory();
			playerMoved=true;
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
		playerMoved=false;
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
	/**This is a getter method that returns the number of moves.
	 *  
	 * @return the number of moves
	 */
	public int getMoves(){
		return moves;
	}
	
	/**This method returns a copy of an array which stores the data of the 
	 * latest move done by the player.
	 * 
	 * @return a copy of an array of 3 elements which are the 
	 * x and y coordinates the player attempted to move into, and
	 * the direction of that attempt.
	 */
	public float[] getLatestMove(){
		float[] latestMoveCopy=Arrays.copyOf(latestMove, 
				latestMove.length);
		return latestMoveCopy;
	}
	/**This method returns true if the player has moved.
	 * @return true if the player has moved, false otherwise.
	 */
	public boolean getPlayerMoved(){
		if(playerMoved){
			return true;
		}
		return false;
	}
	/**This method returns a copy of an array of 2 elements which are the 
	 * x coordinate and the y coordinate the player attempted to move into.
	 *  
	 * @return a copy of an array of 2 elements which are the 
	 * x coordinate and the y coordinate the player attempted to move into.
	 */
	
	public float[] getLatestMoveAttempt(){
		float[] playerLatestMoveAttemptCopy=
				Arrays.copyOf(latestMoveAttempt, 
						latestMoveAttempt.length);
		return playerLatestMoveAttemptCopy;
	}
	
}
