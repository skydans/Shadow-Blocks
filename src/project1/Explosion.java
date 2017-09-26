package project1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Explosion extends Sprite {
	private boolean show;
	private int timer;
	/** constructor of the Explosion sub-class. */
	public Explosion(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		/* exception for Explosion, the render method of Explosion is 
		 * overriden. */
		setShow(true);
		timer=0;
	}
	
	/*!!!! You have to recheck this function and make sure it returns a copy
	* and not a reference so that there will be no provacy leak.
	*/
	public void update(Input input, int delta) {
		
		showOnTntIfNeeded();
		if(show){timer+=delta;}
		if(timer>=300){
			show=false;
			/* reset the timer in case it needs to be used again */
			timer=0;
		}

	}
	
	@Override
	public void render(Graphics g) {
		if(show){
			renderSprite();
		}
	}
	
	public void showOnTntIfNeeded(){
		//ArrayList<Integer> toDeleteCopy=World.getToDelete();
		//Assuming no more than 1 TNT can explode at the same time
		if(World.isTntInToDelete()){
			System.out.println("toDelete not empty inside if statement");
			float[] coordinates=World.getLatestTntPosition();
			setX(coordinates[0]);
			setY(coordinates[1]);
			show=true;
		}
	}
}
