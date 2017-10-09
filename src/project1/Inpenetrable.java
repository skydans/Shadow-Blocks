package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Inpenetrable extends Sprite {
	public Inpenetrable(float x,float y) throws SlickException{
		super(x,y);
	}
	public Inpenetrable(Inpenetrable inpenetrable) throws SlickException{
		super(inpenetrable);
	}
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
	}
}
