package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tnt extends Block implements CanDisappear{
	public Tnt(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/tnt.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public Tnt(Tnt tnt) throws SlickException {
		super(tnt);
	}
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
		
		disappearIfNeeded();
		//World.setLatestTntPosition(getX(), getY());
	}
	
	public void disappearIfNeeded(){
		float[] playerLatestMoveAttemptCopy=World.getPlayerLatestMoveAttempt();
		if(surroundingCheck() && playerLatestMoveAttemptCopy[0]==getX() && playerLatestMoveAttemptCopy[1]==getY()){
			World.addToHide(World.getCurrentUpdateIndex());
			System.out.println("Tnt check");
		}
	}
	
	public boolean surroundingCheck(){
		if(World.isBlockedByParticularSprite(getX(),getY()+1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),
						Cracked.class)){
			/*if(World.isBlockedByParticularSprite(getX(),getY()+1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX(),getY()+1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,Tnt.class) ||
				World.isBlockedByParticularSprite(getX(),getY()-1,
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),Tnt.class) ||
				World.isBlockedByParticularSprite(getX()+1,getY(),
						Cracked.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),Tnt.class) ||
				World.isBlockedByParticularSprite(getX()-1,getY(),
						Cracked.class)){
			 */
			return true;
		}
		return false;
	}
	
}
