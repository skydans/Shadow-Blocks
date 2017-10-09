package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Cracked extends Inpenetrable implements CanDisappear{
	public Cracked(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/cracked_wall.png");
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
	public Cracked(Cracked cracked) throws SlickException {
		super(cracked);
		setShow(true);
	}
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
		disappearIfNeeded();
	}
	public void disappearIfNeeded(){
		float[] playerLatestMoveAttemptCopy=World.getPlayerLatestMoveAttempt();
		if(surroundingCheck() && ((playerLatestMoveAttemptCopy[0]==getX()+1 &&
				playerLatestMoveAttemptCopy[1]==getY())||
				(playerLatestMoveAttemptCopy[0]==getX()-1 &&
				playerLatestMoveAttemptCopy[1]==getY())||
				(playerLatestMoveAttemptCopy[0]==getX() &&
				playerLatestMoveAttemptCopy[1]==getY()+1)||
				(playerLatestMoveAttemptCopy[0]==getX() &&
				playerLatestMoveAttemptCopy[1]==getY()-1))){
			World.addToHide(World.getCurrentUpdateIndex());
			//System.out.println("Cracked Wall check");
		}
	}
	
	public boolean surroundingCheck(){
		if(World.isBlockedByParticularSprite(getX(),getY()+1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),Tnt.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),Tnt.class)){
			return true;
		}
		return false;
	}
}
