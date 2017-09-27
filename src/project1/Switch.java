package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Switch extends Sprite{
	//private boolean isBlockOnSwitch;
	//private int doorIndex; //Is this okay for Java encapsulation?
	public Switch(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
		//isBlockOnSwitch=false;
		//doorIndex=World.getDoorIndex();
	}
	public void update(Input input, int delta){
		checkBlockOnSwitch();
	}
	public void checkBlockOnSwitch(){
		if(World.hasBlockAt(getX(), getY())){
			World.addToDelete(World.getDoorIndex());
		}else{
			World.addToRestore(World.getDoorIndex());
		}
	}
}
