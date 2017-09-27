package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Rogue extends Unit{
	private int timer,currentDirection;
	public Rogue(String img_src,float x,float y) throws SlickException{
		super(img_src,x,y);
		setShow(true);
		timer=0;
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
		if(!World.isBlocked(getX()+deltaX,getY()+deltaY) && 
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
	public boolean checkContactWithPlayer(){
		float[] playerCoordinates=World.getPlayerCoordinates();
		if(playerCoordinates[0]==getX() && playerCoordinates[1]==getY()){
			return true;
		}
		return false;
	}
	@Override
	public void update(Input input,int delta){
		timer+=delta;
		if(timer>=1000){
			if(!moveToDest(currentDirection)){
				if(currentDirection==DIR_LEFT){
					currentDirection=DIR_RIGHT;
				}else{
					currentDirection=DIR_LEFT;
				}
				moveToDest(currentDirection);
			}
			timer=0;
		}
		if(checkContactWithPlayer()){World.setWillRestart(true);}
	}
	
	
}
