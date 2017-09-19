package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/** super-class for sprites which handle instance variables that commonly 
 * exist in sprites. 
 */
public class Sprite implements Comparable<Sprite>{	
	/** primitive variables for image source and position */
	private String image_src;
	private float x,y;
	/** variable to store Image object */
	private Image image;
	
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
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
		//draw=new Image(s[i].getImageSrc());
		//output the image of the sprite onto the screen
		image.drawCentered((App.SCREEN_WIDTH-Loader.getWidth()*
				App.TILE_SIZE)/2+(x*App.TILE_SIZE+App.TILE_SIZE
				/2),(App.SCREEN_HEIGHT-Loader.getHeight()*App.TILE_SIZE)/2
				+(y*App.TILE_SIZE+App.TILE_SIZE/2));
	}
	
	public int compareTo(Sprite sprite){
		if((this.getClass().equals(Floor.class) || 
				this.getClass().equals(Wall.class) ||
				this.getClass().equals(Target.class) ||
				this.getClass().equals(Floor.class)) &&
				!(sprite.getClass().equals(Floor.class) || 
				sprite.getClass().equals(Wall.class) ||
				sprite.getClass().equals(Target.class) ||
				sprite.getClass().equals(Floor.class))){
			return -1;
		}
		/*
		if(!this.getClass().equals(Player.class) && 
				sprite.getClass().equals(Player.class)){
			return -1;
		}
		*/
		return 1;
	}
}
