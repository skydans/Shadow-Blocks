package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Cracked extends Sprite{
	private boolean once;
	public Cracked(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		once=true;
	}
	public void update(Input input,int delta){
		super.update(input, delta);
		if(tntCheck() && once){
			World.addToDelete(World.getCurrentUpdateIndex());
			once=false;
			System.out.println("Tnt check");
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
