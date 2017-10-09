package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Mage extends Unit {
	public Mage(float x,float y) throws SlickException{
		super(x, y);
		setImageSrc("/assets/mage.png");
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
	public void update(Input input,int delta){
		if(World.playerMoved()){
			algorithmOne();
		}
		if(checkContactWithPlayer()){World.setWillRestart(true);}
	}
	@Override
	public boolean moveToDest(int dir){
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
		if(!World.isBlocked(getX()+deltaX,getY()+deltaY) && 
				!World.isBlockedByBlock(getX()+deltaX, getY()+deltaY) ){
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			return true;
		}else{
			return false;
		}
	}
	public void algorithmOne(){
		float distX,distY;
		float[] playerCoordinates=World.getSpriteCoordinates(Player.class);
		distX=playerCoordinates[0]-getX();
		distY=getY()-playerCoordinates[1];
		
		System.out.println("distX: "+distX);
		System.out.println("distY: "+distY);
		
		if(Math.abs(distX)>Math.abs(distY) &&
				moveToDest(distX<0 ? DIR_LEFT : DIR_RIGHT)){
		}else{
			moveToDest(distY<0?DIR_DOWN:DIR_UP);
		}
	}
	public boolean checkContactWithPlayer(){
		float[] playerCoordinates=World.getSpriteCoordinates(Player.class);
		if(playerCoordinates[0]==getX() && playerCoordinates[1]==getY()){
			return true;
		}
		return false;
	}
}
