package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tnt extends Block implements CanDisappear{
	public Tnt(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
	}
	public void update(Input input,int delta){
		super.update(input, delta);
		
		disappearIfNeeded();
		World.setLatestTntPosition(getX(), getY());
	}
	
	public void disappearIfNeeded(){
		float[] playerLatestMoveAttemptCopy=World.getPlayerLatestMoveAttempt();
		if(crackedCheck() && playerLatestMoveAttemptCopy[0]==getX() && playerLatestMoveAttemptCopy[1]==getY()){
			World.addToDelete(World.getCurrentUpdateIndex());
			System.out.println("Tnt check");
		}
	}
	
	public boolean crackedCheck(){
		if(World.isBlockedByTntOrCracked(getX(),getY()+1) ||
				World.isBlockedByTntOrCracked(getX(),getY()-1) ||
				World.isBlockedByTntOrCracked(getX()+1,getY()) ||
				World.isBlockedByTntOrCracked(getX()-1,getY())){
			return true;
		}
		return false;
	}
	
}
