package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Skeleton extends Unit {
	private int timer,currentDirection;
	public Skeleton(float x,float y) throws SlickException{
		super(x,y);
		setImageSrc("/assets/skeleton.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setShow(true);
		timer=0;
		currentDirection=DIR_UP;
	}
	@Override
	public void update(Input input, int delta) throws SlickException{
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
		if(!World.isBlocked("Inpenetrable",getX()+deltaX,getY()+deltaY) && 
				!World.isBlocked("Block",getX()+deltaX, getY()+deltaY) ){
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
	
}
