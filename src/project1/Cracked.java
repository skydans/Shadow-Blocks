package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Cracked extends Inpenetrable implements CanDisappear{
	public Cracked(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
	}
	public void update(Input input,int delta){
		super.update(input, delta);
		disappearIfNeeded();
	}
	public void disappearIfNeeded(){
		float[] playerLatestMoveAttemptCopy=World.getPlayerLatestMoveAttempt();
		if(tntCheck() && ((playerLatestMoveAttemptCopy[0]==getX()+1 &&
				playerLatestMoveAttemptCopy[1]==getY())||
				(playerLatestMoveAttemptCopy[0]==getX()-1 &&
				playerLatestMoveAttemptCopy[1]==getY())||
				(playerLatestMoveAttemptCopy[0]==getX() &&
				playerLatestMoveAttemptCopy[1]==getY()+1)||
				(playerLatestMoveAttemptCopy[0]==getX() &&
				playerLatestMoveAttemptCopy[1]==getY()-1))){
			World.addToDelete(World.getCurrentUpdateIndex());
			System.out.println("Cracked Wall check");
		}
	}
	
	public boolean tntCheck(){
		if(World.isBlockedByTntOrCracked(getX(),getY()+1) ||
				World.isBlockedByTntOrCracked(getX(),getY()-1) ||
				World.isBlockedByTntOrCracked(getX()+1,getY()) ||
				World.isBlockedByTntOrCracked(getX()-1,getY())){
			return true;
		}
		return false;
	}
}
