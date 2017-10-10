package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class represents the mage
 * 
 * @author Daniel Gonawan
 *
 */
public class Mage extends Unit {
	public Mage(float x,float y) throws SlickException{
		super(x, y);
		setImageSrc("/assets/mage.png");
		/* Try to create an image object using the image source path and 
		* catch the error if this is unsuccessful.
		*/
		try {
			setImage(new Image(getImageSrc()));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setShow(true);
		
	}
	/**Update method of the Mage class. It executes algorithm 1 when the 
	 * player moves and uses a setter method in the World class to restart if 
	 * the mage touches the player.
	 */
	public void update(Input input,int delta) throws SlickException{
		if(((Player)World.getSprite("Player")).playerMoved()){
			algorithmOne();
		}
		if(checkContactWithPlayer()){World.setWillRestart(true);}
	}
	/**This method overrides the method from the superclass of mage class. It 
	 * checks whether the mage can move to a particular direction without 
	 * being blocked and does it and returns true if the move is successful. 
	 * It returns false otherwise.
	 */
	@Override
	public boolean moveToDest(int dir){
		int deltaX=0,deltaY=0;
		switch(dir){
		case DIR_UP:
			deltaY-=1;
			break;
		case DIR_DOWN:
			deltaY+=1;
			break;
		case DIR_LEFT:
			deltaX-=1;
			break;
		case DIR_RIGHT:
			deltaX+=1;
			break;
		}
		if(!World.isBlocked("Inpenetrable",getX()+deltaX,getY()+deltaY) && 
				!World.isBlocked("Block",getX()+deltaX, getY()+deltaY) ){
			setX(getX()+deltaX);
			setY(getY()+deltaY);
			return true;
		}else{
			return false;
		}
	}
	/**This is the method that runs algorithm one that is mentioned on the 
	 * specification.
	 * @throws SlickException
	 */
	public void algorithmOne() throws SlickException{
		float distX,distY;
		
		distX=World.getSprite("Player").getX()-getX();
		distY=getY()-World.getSprite("Player").getY();
		
		System.out.println("distX: "+distX);
		System.out.println("distY: "+distY);
		
		if(Math.abs(distX)>Math.abs(distY) &&
				moveToDest(distX<0 ? DIR_LEFT : DIR_RIGHT)){
		}else{
			moveToDest(distY<0?DIR_DOWN:DIR_UP);
		}
	}
	/**This method checks whether the mage touches the player or not.
	 * 
	 * @return true if the mage touches the player, false otherwise.
	 * @throws SlickException
	 */
	public boolean checkContactWithPlayer() throws SlickException{
		if(World.getSprite("Player").getX()==getX() && World.getSprite("Player").getY()==getY()){
			return true;
		}
		return false;
	}
}
