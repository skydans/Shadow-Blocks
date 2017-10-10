package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class contains the attributes to check whether a block is on the 
 * switch or not.
 * 
 * @author Daniel Gonawan
 *
 */
public class Switch extends Sprite{
	//private boolean isBlockOnSwitch;
	//private int doorIndex; //Is this okay for Java encapsulation? NO.
	/**Constructor of the Switch class. It inherits the constructor from the 
	 * Sprite class and in addition, it sets the switch to be visible when 
	 * loaded.
	 * 
	 * @param image_src
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Switch(float x, float y) throws SlickException {
		super(x,y);
		setImageSrc("/assets/switch.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		//show the Switch sprite.
		setShow(true);
		//isBlockOnSwitch=false;
		//doorIndex=World.getDoorIndex();
	}
	/**This method calls the checkBlockOnSwitch method.
	 * 
	 */
	public void update(Input input, int delta){
		checkBlockOnSwitch();
	}
	/**This method checks whether there is a block on the switch. If it does 
	 * it will issue a command to make the door disappear.
	 * 
	 */
	public void checkBlockOnSwitch(){
		if(World.hasSpriteTypeAt("Block",getX(), getY())){
			World.addToHide(World.getSpriteIndex(Door.class));
		}else{
			World.addToRestore(World.getSpriteIndex(Door.class));
		}
	}
}
