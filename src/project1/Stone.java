package project1;

import org.newdawn.slick.SlickException;
/** Stone is a sub-class of Sprite. */
public class Stone extends Sprite implements Movable{
	private boolean isInTarget;
	private boolean canBeMovedUp,canBeMovedDown,canBeMovedRight,canBeMovedLeft;
	/** constructor of the Stone sub-class. */
	public Stone(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		canBeMovedUp=canBeMovedDown=canBeMovedRight=canBeMovedLeft=true;
	}
	public boolean canMoveUp(){
		return canBeMovedUp;
	}
	public boolean canMoveDown(){
		return canBeMovedDown;
	}
	public boolean canMoveRight(){
		return canBeMovedRight;
	}
	public boolean canMoveLeft(){
		return canBeMovedLeft;
	}
	public void setCanMoveDown(boolean canBeMovedDown){
		this.canBeMovedDown=canBeMovedDown;
	}
	public void setCanMoveUp(boolean canBeMovedUp){
		this.canBeMovedUp=canBeMovedUp;
	}
	public void setCanMoveRight(boolean canBeMovedRight){
		this.canBeMovedRight=canBeMovedRight;
	}
	public void setCanMoveLeft(boolean canBeMovedLeft){
		this.canBeMovedLeft=canBeMovedLeft;
	}
}
