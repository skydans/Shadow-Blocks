package project1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {	
	
	private Sprite[] s;
	
	private Image draw;
	
	//constructor of the World class.
	public World() {
		//loads the sprite when an instance of the world is created.
		s=Loader.loadSprites("assets/levels/0.lvl");
	}
	
	/* Sets up the values of the sprite(s) of concern before rendering it. 
	 */
	public void update(Input input, int delta) {
		for(int i=0;i<s.length;i++){
			float prevX,prevY;
			/* stores the x and y coordinates before changing it, in case
			 * reverting back the position is needed.
			 */
			prevX=s[i].getX();
			prevY=s[i].getY();
			s[i].update(input,delta);
			if(s[i].getClass().equals(Player.class)){
				for (int j=s.length-1;0<=j;j--){
					if(s[j].getX()==s[i].getX() && s[j].getY()==s[i].getY() 
							&& s[j].getClass().equals(Wall.class)){
						/* revert back the position of the player if it 
						 * hits a wall.
						 */
						s[i].setX(prevX);
						s[i].setY(prevY);
					}
				}
			}
		}
	}
	/**
	 * This method renders the sprites onto the screen.
	 * @param g
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException{
		for(int i=0;i<s.length;i++){
    		draw=new Image(s[i].getImageSrc());
    		//output the image of the sprite onto the screen
			draw.drawCentered((App.SCREEN_WIDTH-Loader.getWidth()*
					App.TILE_SIZE)/2+(s[i].getX()*App.TILE_SIZE+App.TILE_SIZE
					/2),(App.SCREEN_HEIGHT-Loader.getHeight()*App.TILE_SIZE)/2
					+(s[i].getY()*App.TILE_SIZE+App.TILE_SIZE/2));
    	}
		
	}
	
}
