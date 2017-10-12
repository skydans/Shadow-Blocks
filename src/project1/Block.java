package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**This class is for the sprites that are considered a block. It contains 
 * attributes and methods which determine in which directions the block can 
 * be moved.
 * @author Daniel Gonawan
 *
 */
public abstract class Block extends Sprite{
	private boolean canBeMovedUp,canBeMovedDown,canBeMovedRight,canBeMovedLeft;
	/**The constructor of the Block class. It also sets that the block 
	 * can be moved up, down, left and right.
	 * 
	 * @param image_src
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @throws SlickException
	 */
	public Block(float x, float y) throws SlickException {
		super(x,y);
		setShow(true);
		canBeMovedUp=canBeMovedDown=canBeMovedRight=canBeMovedLeft=true;
	}
	/**The copy constructor of the Block class.
	 * 
	 * @param block
	 * @throws SlickException
	 */
	public Block(Block block) throws SlickException{
		super(block);
	}
	/**This method returns whether the block can move up or not.
	 * 
	 * @return
	 */
	public boolean canMoveUp(){
		return canBeMovedUp;
	}
	/**This method returns whether the block can move down or not.
	 * 
	 * @return
	 */
	public boolean canMoveDown(){
		return canBeMovedDown;
	}
	/**This method returns whether the block can move right or not.
	 * 
	 * @return
	 */
	public boolean canMoveRight(){
		return canBeMovedRight;
	}
	/**This method returns whether the block can move left or not.
	 * 
	 * @return
	 */
	public boolean canMoveLeft(){
		return canBeMovedLeft;
	}
	/**This method sets whether the Block can move down or not.
	 * 
	 * @param canBeMovedDown
	 */
	public void setCanMoveDown(boolean canBeMovedDown){
		this.canBeMovedDown=canBeMovedDown;
	}
	/**This method sets whether the Block can move up or not.
	 * 
	 * @param canBeMovedUp
	 */
	public void setCanMoveUp(boolean canBeMovedUp){
		this.canBeMovedUp=canBeMovedUp;
	}
	/**This method sets whether the Block can move to the right or not.
	 * 
	 * @param canBeMovedRight
	 */
	public void setCanMoveRight(boolean canBeMovedRight){
		this.canBeMovedRight=canBeMovedRight;
	}
	/**This method sets whether the Block can move to the left or not.
	 * 
	 * @param canBeMovedLeft
	 */
	public void setCanMoveLeft(boolean canBeMovedLeft){
		this.canBeMovedLeft=canBeMovedLeft;
	}
	/**This method checks the side surroundings of the blocks to see whether 
	 * the block can be moved towards its surroundings.
	 * In addition it moves depending on either the player's latest move 
	 * first or then depending on the rogue's latest move.
	 */
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
		//Check in which directions the block can be moved.
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
		/* Move the block if the player or rogue is at the current position 
		 * of the block. 
		 */
		if(playerLatestMoveCopy[0]==getX() && playerLatestMoveCopy[1]==getY()){
			moveToDest((int)playerLatestMoveCopy[2]);
			//For debugging purposes
			//System.out.println("Block moveToDest triggered by Player");
		}else if(rogueLatestMoveCopy[0]==getX() && rogueLatestMoveCopy[1]==
				getY()){
			moveToDest((int)rogueLatestMoveCopy[2]);
			//For debugging purposes
			//System.out.println("Block moveToDest triggered by Rogue");
		}
		blockCheck();
	}
	
	/**This method moves the player to its destination. 
	 * Credit: Inspired by Eleanor's code
	 * 
	 * @param dir the move direction.
	 */
	public void moveToDest(int dir){
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
		setX(getX()+deltaX);
		setY(getY()+deltaY);
	}
	/**This method checks the surroundings of the block and the possible 
	 * directions the block can move.
	 */
	public void blockCheck(){
		/* Set the instance variable to false if the block is blocked by 
		 * an instance of Inpenetrable class or by a block, set to true 
		 * otherwise.
		 */
		if(World.isBlocked("Inpenetrable",getX(),getY()+1) ||
				World.isBlocked("Block",getX(),getY()+1)){
			setCanMoveDown(false);
		}else{
			setCanMoveDown(true);
		}
		if(World.isBlocked("Inpenetrable",getX(),getY()-1) ||
				World.isBlocked("Block",getX(),getY()-1)){
			setCanMoveUp(false);
		}else{
			setCanMoveUp(true);
		}
		if(World.isBlocked("Inpenetrable",getX()+1,getY()) ||
				World.isBlocked("Block",getX()+1,getY())){
			setCanMoveRight(false);
		}else{
			setCanMoveRight(true);
		}
		if(World.isBlocked("Inpenetrable",getX()-1,getY()) ||
				World.isBlocked("Block",getX()-1,getY())){
			setCanMoveLeft(false);
		}else{
			setCanMoveLeft(true);
		}
	}
}
