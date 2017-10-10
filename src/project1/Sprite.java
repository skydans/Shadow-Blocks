package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**Super-class for sprites which handle instance variables that commonly 
 * exist in sprites.
 * 
 * @author Daniel Gonawan
 *
 */
public abstract class Sprite implements Comparable<Sprite>{	
	/** primitive variables for image source and position */
	private String image_src;
	private float x,y;
	/** variable to store Image object */
	private Image image;
	/** variable to indicate whether it is to be renedered or not */
	private boolean show;
	/** Credit: Inspired by Eleanor's code */
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	/**Copy constructor of Sprite. It initialises the image source file path, 
	 * x and y coordinates.
	 * 
	 * @param sprite
	 * @throws SlickException
	 */
	public Sprite(Sprite sprite) 
			throws SlickException{
		this.image_src=sprite.image_src;
		this.x=sprite.x;
		this.y=sprite.y;
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	/** Constructor of Sprite
	 * 
	 * @param x x coordinate of sprite.
	 * @param y y coordinate of sprite.
	 * @throws SlickException
	 */
	public Sprite(float x, float y) 
			throws SlickException{
		this.x=x;
		this.y=y;
	}
	/** getter method for the x coordinate (in tiles). */
	public float getX(){
		return x;
	}
	/** getter method for the y coordinate (in tiles). */
	public float getY(){
		return y;
	}
	/** getter method for the y coordinate (in tiles). */
	public boolean getShow(){
		return show;
	}
	/** setter method for the x coordinate (in tiles). */
	public void setX(float x){
		this.x=x;
	}
	/** getter method for the y coordinate (in tiles). */
	public void setY(float y){
		this.y=y;
	}
	/** getter method for the y coordinate (in tiles). */
	public void setShow(boolean show){
		this.show=show;
	}
	/** setter method for the image file path. */
	public void setImageSrc(String image_src){
		this.image_src=image_src;
	}
	/** getter method for the image file path. */
	public String getImageSrc(){
		return image_src;
	}
	/** getter method for the image object. */
	public Image getImage(){
		return image;
	}
	/** setter method for the image object. */
	public void setImage(Image image){
		this.image=image;
	}
	/** update method for the Sprite class. */
	public void update(Input input, int delta) throws SlickException{
		
	}
	/**  render method for the Sprite class. */	
	public void render(Graphics g) {
		renderSprite();
	}
	/**This method renders the sprite onto the screen.
	 * 
	 */
	public void renderSprite(){
		image.drawCentered((App.SCREEN_WIDTH-Loader.getWidth()*
				App.TILE_SIZE)/2+(x*App.TILE_SIZE+App.TILE_SIZE
				/2),(App.SCREEN_HEIGHT-Loader.getHeight()*App.TILE_SIZE)/2
				+(y*App.TILE_SIZE+App.TILE_SIZE/2));
	}
	/**It defines the order of how the sprite is going to be sorted.
	 * 
	 */
	public int compareTo(Sprite sprite){
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
		}else if(this.getClass().equals(Rogue.class)){
			leftDistance=8;
		}else if(this.getClass().equals(Skeleton.class)){
			leftDistance=9;
		}else if(this.getClass().equals(Mage.class)){
			leftDistance=10;
		}else if(this.getClass().equals(Player.class)){
			leftDistance=11;
		}else if(this.getClass().equals(Explosion.class)){
			leftDistance=12;
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
		}else if(sprite.getClass().equals(Rogue.class)){
			rightDistance=8;
		}else if(sprite.getClass().equals(Skeleton.class)){
			rightDistance=9;
		}else if(sprite.getClass().equals(Mage.class)){
			rightDistance=10;
		}else if(sprite.getClass().equals(Player.class)){
			rightDistance=11;
		}else if(sprite.getClass().equals(Explosion.class)){
			rightDistance=12;
		}
		return leftDistance-rightDistance;
	}
}
