package project1;

import org.newdawn.slick.SlickException;

public class Player extends Sprite{

	//public Player(Sprite sprite){
	//	super(sprite);
	//}
	
	public Player(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
	}
	public boolean isPlayer(){
		return super.isPlayer() || true;
	}
}
