package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Inpenetrable extends Sprite {
	public Inpenetrable(String img_src,float x,float y) throws SlickException{
		super(img_src,x,y);
	}
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
	}
}
