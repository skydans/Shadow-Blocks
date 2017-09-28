package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Unit extends Sprite {
	public Unit(String img_src,float x,float y) throws SlickException{
		super(img_src,x,y);
	}
	public Unit(Unit unit) 
			throws SlickException{
		super(unit);
	}
	public void update(Input input,int delta) throws SlickException{
		super.update(input, delta);
	};
	public abstract boolean moveToDest(int dir) throws SlickException;
}
