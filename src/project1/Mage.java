package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Mage extends Unit {
	public Mage(String img_src,float x,float y) throws SlickException{
		super(img_src, x, y);
	}
	public void update(Input input,int delta){
		
	}
	@Override
	public boolean moveToDest(int dir) {
		// TODO Auto-generated method stub
		return true;
	}
}
