package project1;

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
		//prevMoves=0;
		//resetPrevMoves();
		playerMoved=false;
	}
	/**This is a copy constructor of Player class.
	 * 
	 * @param player parameter of type Player.
	 * @throws SlickException
	 */
	public Player(Player player) throws SlickException{
		super(player);
		setShow(player.getShow());
		moves=player.moves;
		playerMoved=player.playerMoved;
		//prevMoves=player.prevMoves;
	}
	
	
	//this moveToDest is different from the one in Stone class.
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
				!World.isBlockedByAdjacentBlock(getX()+deltaX, getY()+deltaY, dir) ){
			World.setPlayerLatestMove(getX()+deltaX,getY()+deltaY,dir);
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			
		}else{
			World.setPlayerLatestMoveAttempt(getX()+deltaX, getY()+deltaY);
		}
		/* Increment the number of moves made by the player if the player 
		 * attempted to make a move (regardless the success of the move).
		 */
		if(dir!=DIR_NONE){
			moves+=1;
			System.out.println("moves: "+moves);
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
		/*
		if(World.getUndoPressed()){
			//moves-=1;
		}
		*/
	}
	
	/**This is a setter method that sets the number of moves recorded.
	 * 
	 * @param newMoves number of moves made by the player
	 */
	/*
	public void setMoves(int newMoves){
		moves=newMoves; //this does not seem to apply here
	}
	*/
	/**This is a getter method that returns the number of moves.
	 *  
	 * @return the number of moves
	 */
	
	public int getMoves(){
		return moves;
	}
	
	/*
	public void resetPrevMoves(){
		prevMoves=moves;
	}
	*/
	/**This method returns true if the player has moved.
	 * @return true if the player has moved, false otherwise.
	 */
	public boolean getPlayerMoved(){
		//System.out.println("moves: "+moves);
		//System.out.println("prevMoves: "+prevMoves);
		//System.out.println("playerMoved: "+playerMoved);
		if(playerMoved){
			return true;
		}
		return false;
	}
	//Old version
	/*
	public boolean playerMoved(){
		System.out.println("moves: "+moves);
		System.out.println("prevMoves: "+prevMoves);
		if(moves>prevMoves){
			prevMoves=moves;
			return true;
		}
		return false;
	}
	*/
	
}
