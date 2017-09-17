package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/** super-class for sprites which handle instance variables that commonly 
 * exist in sprites. 
 */
public class Sprite {	
	/** primitive variables for image source and position */
	private String image_src;
	private float x,y;
	/** variable to store Image object */
	private Image image;
	
	/** Constructor of Sprite */
	public Sprite(String image_src, float x, float y) throws SlickException{
		this.image_src=image_src;
		this.x=x;
		this.y=y;
		//this.image=new Image(image_src);
		/* Try to create an image object using the image source path and 
		 * catch the error if this is unsuccessful.
		 */
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	/** getter method for the x coordinate (in tiles). */
	public float getX(){
		return x;
	}
	/** getter method for the y coordinate (in tiles). */
	public float getY(){
		return y;
	}
	/** setter method for the x coordinate (in tiles). */
	public void setX(float x){
		this.x=x;
	}
	/** getter method for the y coordinate (in tiles). */
	public void setY(float y){
		this.y=y;
	}
	/** getter method for the image file path. */
	public String getImageSrc(){
		return image_src;
	}
	/** getter method for the image object. */
	public Image getImage(){
		return image;
	}
	/** update method for the Sprite class. */
	public void update(Input input, int delta) {
		
	}
	/**  render method for the Sprite class. */	
	public void render(Graphics g) {
		
	}
	
	
}
