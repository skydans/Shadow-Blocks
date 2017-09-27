package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Unit extends Sprite {
	public Unit(String img_src,float x,float y) throws SlickException{
		super(img_src,x,y);
	}
	public abstract void update(Input input,int delta);
	public abstract boolean moveToDest(int dir);
}
