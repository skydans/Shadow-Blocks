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
	/** Credit: Inspired by Eleanor's code */
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
		renderSprite();
	}
	
	public void renderSprite(){
		image.drawCentered((App.SCREEN_WIDTH-Loader.getWidth()*
				App.TILE_SIZE)/2+(x*App.TILE_SIZE+App.TILE_SIZE
				/2),(App.SCREEN_HEIGHT-Loader.getHeight()*App.TILE_SIZE)/2
				+(y*App.TILE_SIZE+App.TILE_SIZE/2));
	}
	
	public int compareTo(Sprite sprite){
		/*
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
		
		if(!this.getClass().equals(Explosion.class)&& !sprite.getClass().equals(Player.class)
				&& (sprite.getClass().equals(Explosion.class) || sprite.getClass().equals(Player.class))){
			return -1;
		}
		if(!this.getClass().equals(Explosion.class)&&
				(sprite.getClass().equals(Explosion.class))){
			return -1;
		}
		*/
		int leftDistance=0;
		if(this.getClass().equals(Floor.class)){
			leftDistance=0;
		}else if(this.getClass().equals(Wall.class)){
			leftDistance=1;
		}else if(this.getClass().equals(Target.class)){
			leftDistance=2;
		}else if(this.getClass().equals(Cracked.class)){
			leftDistance=3;
		}else if(this.getClass().equals(Switch.class)){
			leftDistance=4;
		}else if(this.getClass().equals(Ice.class)){
			leftDistance=5;
		}else if(this.getClass().equals(Stone.class)){
			leftDistance=6;
		}else if(this.getClass().equals(Tnt.class)){
			leftDistance=7;
		}else if(this.getClass().equals(Player.class)){
			leftDistance=8;
		}else if(this.getClass().equals(Explosion.class)){
			leftDistance=9;
		}
		int rightDistance=0;
		if(sprite.getClass().equals(Floor.class)){
			rightDistance=0;
		}else if(sprite.getClass().equals(Wall.class)){
			rightDistance=1;
		}else if(sprite.getClass().equals(Target.class)){
			rightDistance=2;
		}else if(sprite.getClass().equals(Cracked.class)){
			rightDistance=3;
		}else if(sprite.getClass().equals(Switch.class)){
			rightDistance=4;
		}else if(sprite.getClass().equals(Ice.class)){
			rightDistance=5;
		}else if(sprite.getClass().equals(Stone.class)){
			rightDistance=6;
		}else if(sprite.getClass().equals(Tnt.class)){
			rightDistance=7;
		}else if(sprite.getClass().equals(Player.class)){
			rightDistance=8;
		}else if(sprite.getClass().equals(Explosion.class)){
			rightDistance=9;
		}
		return leftDistance-rightDistance;
	}
}
