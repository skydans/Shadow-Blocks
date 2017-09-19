package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Block extends Sprite {
	private boolean canBeMovedUp,canBeMovedDown,canBeMovedRight,canBeMovedLeft;
	public Block(String image_src, float x, float y) throws SlickException {
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
	
	public void update(Input input,int delta){
		super.update(input, delta);
		blockCheck();
		float[] tempPlayerLatestMove=World.getPlayerLatestMove();
		if(tempPlayerLatestMove[0]==getX() && tempPlayerLatestMove[1]==getY()){
			moveToDest((int)tempPlayerLatestMove[2]);
			System.out.println("Block moveToDest");
		}
		blockCheck();
	}
	
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
	public void blockCheck(){
		if(World.isBlocked(getX(),getY()+1) ||
				World.isBlockedByBlock(getX(),getY()+1)){
			setCanMoveDown(false);
		}else{
			setCanMoveDown(true);
		}
		if(World.isBlocked(getX(),getY()-1) ||
				World.isBlockedByBlock(getX(),getY()-1)){
			setCanMoveUp(false);
		}else{
			setCanMoveUp(true);
		}
		if(World.isBlocked(getX()+1,getY()) ||
				World.isBlockedByBlock(getX()+1,getY())){
			setCanMoveRight(false);
		}else{
			setCanMoveRight(true);
		}
		if(World.isBlocked(getX()-1,getY()) ||
				World.isBlockedByBlock(getX()-1,getY())){
			setCanMoveLeft(false);
		}else{
			setCanMoveLeft(true);
		}
	}
}
