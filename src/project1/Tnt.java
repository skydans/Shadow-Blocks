package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tnt extends Block{
	public Tnt(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
	}
	public void update(Input input,int delta){
		super.update(input, delta);
	}
	
}
