package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Skeleton extends Unit {
	private int timer,currentDirection;
	public Skeleton(String img_src,float x,float y) throws SlickException{
		super(img_src,x,y);
		setShow(true);
		timer=0;
		currentDirection=DIR_UP;
	}
	@Override
	public void update(Input input, int delta){
		timer+=delta;
		if(timer>=1000){
			if(!moveToDest(currentDirection)){
				if(currentDirection==DIR_UP){
					currentDirection=DIR_DOWN;
				}else{
					currentDirection=DIR_UP;
				}
				moveToDest(currentDirection);
			}
			timer=0;
		}
		if(checkContactWithPlayer()){World.setWillRestart(true);}
	}
	public boolean moveToDest(int dir){
		int deltaX=0,deltaY=0;
		switch(dir){
		case DIR_UP:
			deltaY-=1;
			break;
		case DIR_DOWN:
			deltaY+=1;
			break;
		}
		if(!World.isBlocked(getX()+deltaX,getY()+deltaY) && 
				!World.isBlockedByBlock(getX()+deltaX, getY()+deltaY) ){
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
	
}
