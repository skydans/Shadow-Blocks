package project1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/** World class handles interactions between sprites */
public class World {	
	/** array of Sprite objects */
	private Sprite[] sprites;
	
	//private Image draw;
	
	//this is used to store temporary index produced by getType method
	private int tempIndex;
	//private static int level;
	public static final int WALL=1;
	public static final int STONE=2;
	/** constructor of the World class. */
	public World() {
		//loads the sprite when an instance of the world is created.
		sprites=Loader.loadSprites("assets/levels/0.lvl");
		stoneCheck();
	}
	public void stoneCheck(){
		for(int i=0;i<sprites.length;i++){
			if(sprites[i].getClass().equals(Stone.class)){
				Stone stone=(Stone)sprites[i];
				if(getType(sprites[i].getX(),sprites[i].getY()+1)==WALL ||
						getType(sprites[i].getX(),sprites[i].getY()+1)==STONE){
					stone.setCanMoveDown(false);
				}else{
					stone.setCanMoveDown(true);
				}
				if(getType(sprites[i].getX(),sprites[i].getY()-1)==WALL ||
						getType(sprites[i].getX(),sprites[i].getY()-1)==STONE){
					stone.setCanMoveUp(false);
				}else{
					stone.setCanMoveDown(true);
				}
				if(getType(sprites[i].getX()+1,sprites[i].getY())==WALL ||
						getType(sprites[i].getX()+1,sprites[i].getY())==STONE){
					stone.setCanMoveRight(false);
				}else{
					stone.setCanMoveDown(true);
				}
				if(getType(sprites[i].getX()-1,sprites[i].getY())==WALL ||
						getType(sprites[i].getX()-1,sprites[i].getY())==STONE){
					stone.setCanMoveLeft(false);
				}else{
					stone.setCanMoveDown(true);
				}
			}
		}
	}
	
	
	
	public int getType(float x,float y){
		/* No need bound checking because the wall is eventually will become
		 * the sign of the bounds?
		 */
		for (int j=sprites.length-1;0<=j;j--){
			if(sprites[j].getX()==x && sprites[j].getY()==y){ 
				if(sprites[j].getClass().equals(Wall.class)){
					tempIndex=j;
					return WALL;
				}else if(sprites[j].getClass().equals(Stone.class)){
					tempIndex=j;
					return STONE;
				}
			}
		}
		return 0;
	}
	/** Sets up the values of the sprite(sprites) of concern before rendering it. 
	 */
	public void update(Input input, int delta) {
		for(int i=0;i<sprites.length;i++){
			float prevX,prevY;
			/* stores the x and y coordinates before changing it, in case
			 * reverting back the position is needed.
			 */
			prevX=sprites[i].getX();
			prevY=sprites[i].getY();
			sprites[i].update(input,delta);
			if(sprites[i].getClass().equals(Player.class)){
				if (getType(sprites[i].getX(),sprites[i].getY())==WALL){
					/* revert back the position of the player if it 
					 * hits an inpenetrable sprite such as wall, door, etc.
					 */
					sprites[i].setX(prevX);
					sprites[i].setY(prevY);
				}
				if (getType(sprites[i].getX(),sprites[i].getY())==STONE){
					Stone stone=(Stone)sprites[tempIndex];
					if(sprites[i].getX()-prevX==-1 && sprites[i].getY()-prevY==0 && 
							stone.canMoveLeft()){
						sprites[tempIndex].setX(sprites[tempIndex].getX()-1);
						stoneCheck();
					}else if(sprites[i].getX()-prevX==1 && sprites[i].getY()-prevY==0 && 
							stone.canMoveRight()){
						sprites[tempIndex].setX(sprites[tempIndex].getX()+1);
						stoneCheck();
					}else if(sprites[i].getX()-prevX==0 && sprites[i].getY()-prevY==1 && 
							stone.canMoveDown()){
						sprites[tempIndex].setY(sprites[tempIndex].getY()+1);
						stoneCheck();
					}else if(sprites[i].getX()-prevX==0 && sprites[i].getY()-prevY==-1 && 
							stone.canMoveUp()){
						sprites[tempIndex].setY(sprites[tempIndex].getY()-1);
						stoneCheck();
					}else{
						sprites[i].setX(prevX);
						sprites[i].setY(prevY);
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
		for(int i=0;i<sprites.length;i++){
    		sprites[i].render(g);
    	}
	}
	
}
