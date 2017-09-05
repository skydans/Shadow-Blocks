package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite{

	//public Player(Sprite sprite){
	//	super(sprite);
	//}
	
	public Player(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
	}
	/*
	public boolean isPlayer(){
		return super.isPlayer() || true;
	}
	*/
	public void update(Input input,int delta){
		super.update(input,delta);
		if (input.isKeyPressed(Input.KEY_UP)) {
			setY(getY()-1);
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			setY(getY()+1);
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			setX(getX()-1);
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			setX(getX()+1);
		}
		
	}
}
