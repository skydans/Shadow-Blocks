package project1;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class handles interactions between sprites.
 * 
 * @author Daniel Gonawan
 *
 */
public class World {	
	private Sprite[] sprites;
	
	/**No direction*/
	public static final int DIR_NONE = 0;
	/**Left direction*/
	public static final int DIR_LEFT = 1;
	/**Right direction*/
	public static final int DIR_RIGHT = 2;
	/**Up direction*/
	public static final int DIR_UP = 3;
	/**Down direction*/
	public static final int DIR_DOWN = 4;
	
	private static boolean willRestart;//,undoPressed;
	private int level;
	
	private static int currentUpdateIndex;
	private static Sprite[] spritesCopy;
	private static List<Integer> toHide;
	private static List<Integer> toRestore;
	private static List<SpriteMove> movesHistory;
	public static final int WALL=1;
	public static final int STONE=2;
	/**Constructor of the World class. It sets the current level and loads 
	 * that level. It initializes the arrays for the sprites to be hidden, 
	 * the sprites to be restored, histories, copy of sprites. 
	 * 
	 * It also sets the willRestart variable to false.
	 * 
	 * @throws SlickException
	 */
	public World() throws SlickException {
		level=3;
		//loads the sprite when an instance of the world is created.
		loadLevel(level);
		toHide=new ArrayList<>();
		toRestore=new ArrayList<>();
		movesHistory=new ArrayList<>();
		willRestart=false;
		spritesCopy=Arrays.copyOf(sprites,sprites.length);
		recordMovesHistory();
	}
	/**This method is a getter which returns the index of the sprites array    
	 * that is being currently updated.
	 * 
	 * @return the index of the current sprites array that is being updated.
	 */
	public static int getCurrentUpdateIndex(){
		return currentUpdateIndex;
	}
	/**This method adds the index of the sprites array that are going to be 
	 * hidden.
	 * @param index index of the sprites array.
	 */
	public static void addToHide(int index){
		toHide.add(index);
	}
	/**This method returns a List of Integers which stores the indices of the 
	 * sprites in the sprites array that are going to be hidden.
	 * @return list of integers that stores the indices of the sprites in the 
	 * sprites array that are going to be hidden.
	 */
	public static List<Integer> getToHide(){
		List<Integer> toHideCopy=new ArrayList<>(toHide);
		return toHideCopy;
	}
	/**This method stores the positions of players and specific blocks and 
	 * uses their copy constructors to store information about the state 
	 * of those sprites in the movesHistory array.
	 * 
	 * @throws SlickException
	 */
	public static void recordMovesHistory() throws SlickException{
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			
			if(spritesCopy[j] instanceof Ice){ 
				Ice iceCopy=new Ice((Ice)spritesCopy[j]);
				movesHistory.add(new SpriteMove(
						((Player)getSprite("Player")).getMoves(),j,iceCopy));
			}else if(spritesCopy[j] instanceof Stone){
				Stone stoneCopy=new Stone((Stone)spritesCopy[j]);
				movesHistory.add(new SpriteMove(
						((Player)getSprite("Player")).getMoves(),j,stoneCopy));
			}else if(spritesCopy[j] instanceof Tnt){
				Tnt tntCopy=new Tnt((Tnt)spritesCopy[j]);
				movesHistory.add(new SpriteMove(
						((Player)getSprite("Player")).getMoves(),j,tntCopy));
			}else if(spritesCopy[j] instanceof Cracked){
				Cracked crackedCopy=new Cracked((Cracked)spritesCopy[j]);
				movesHistory.add(new SpriteMove(
						((Player)getSprite("Player")).getMoves(),j,
						crackedCopy));
			}else if(spritesCopy[j].getClass().equals(Player.class)){
				Player playerCopy=new Player((Player)spritesCopy[j]);
				movesHistory.add(new SpriteMove(
						((Player)getSprite("Player")).getMoves(),j,
						playerCopy));
			}
		}
	}
	/**This method executes the undo functionality of the game, which consists 
	 * of undoing the latest move done by the player and setting the positions 
	 * of the blocks into their previous latest positions.
	 * @throws SlickException
	 */
	public void undo() throws SlickException{
		/* immediately end the method if there are no moves to be undone. */
		if(((Player)getSprite("Player")).getMoves()==0){return;}
		/* Revert all the Sprite objects that are of the last index based on 
		 * the movesHistory array.
		 */
		for(int j=movesHistory.size()-1;0<=j;j--){
			if(movesHistory.get(j).getMoveIndex()==(
					((Player)getSprite("Player")).getMoves()-1)){
				if(movesHistory.get(j).getSprite().getClass().equals(Stone.class)){
					Stone stoneCopy=new Stone((Stone)movesHistory.get(j).getSprite());
					sprites[movesHistory.get(j).getSpriteIndex()]=stoneCopy;
				}else if(movesHistory.get(j).getSprite().getClass().equals(Ice.class)){
					Ice iceCopy=new Ice((Ice)movesHistory.get(j).getSprite());
					sprites[movesHistory.get(j).getSpriteIndex()]=iceCopy;
				}else if(movesHistory.get(j).getSprite().getClass().equals(Tnt.class)){
					Tnt tntCopy=new Tnt((Tnt)movesHistory.get(j).getSprite());
					sprites[movesHistory.get(j).getSpriteIndex()]=tntCopy;
				}else if(movesHistory.get(j).getSprite().getClass().equals(Cracked.class)){
					Cracked crackedCopy=new Cracked((Cracked)movesHistory.get(j).getSprite());
					sprites[movesHistory.get(j).getSpriteIndex()]=crackedCopy;
				}else if(movesHistory.get(j).getSprite().getClass().equals(Player.class)){
					
					Player playerCopy=new Player((Player)movesHistory.get(j).getSprite());
					
					sprites[movesHistory.get(j).getSpriteIndex()]=playerCopy;
					
					//System.out.println("Player undo");
					//System.out.println(sprites[movesHistory.get(j).getSpriteIndex()].getShow());
					
				}
			}
		}
		/* Remove all the SpriteMove objects that are of the last index.
		 */
		while(true){
			int tempIndex=movesHistory.size()-1;
			if(tempIndex<0){break;}
			if(movesHistory.get(tempIndex).getMoveIndex()==
					((Player)getSprite("Player")).getMoves()){
				movesHistory.remove(tempIndex);
			}else{
				break;
			}
		}
	}
	
	
	
	private void executeToHide(){
		for(int i=0;i<toHide.size();i++){
			sprites[toHide.get(i)].setShow(false);
		}
		//System.out.println("toHide.size: "+toHide.size());
		int size=toHide.size();
		for(int i=0;i<size;i++){
			toHide.remove(0);
		}
		//System.out.println("toHide.size after delete: "+toHide.size());
		
	}
	/**This method adds an index of the sprites array to the List that stores 
	 * the indices of the sprites that are going to be restored (or made 
	 * visible).
	 * @param index index of the Sprite in the sprites array.
	 */
	public static void addToRestore(int index){
		toRestore.add(index);
	}
	/* This method executes the operations needed for the sprites to be 
	 * restored.
	 */
	private void executeToRestore(){
		for(int i=0;i<toRestore.size();i++){
			sprites[toRestore.get(i)].setShow(true);
		}
		int size=toRestore.size();
		for(int i=0;i<size;i++){
			toRestore.remove(0);
		}
		
	}
	
	private void clearMovesHistory(){
		int size=movesHistory.size();
		for(int i=0;i<size-1;i++){
			movesHistory.remove(movesHistory.size()-1);
		}
	}
	/**This is a setter method which sets the state on whether the game should 
	 * restart or not at the next update method call.
	 * 
	 * @param newWillRestart boolean variable that shows whether to restart 
	 * or not.
	 */
	public static void setWillRestart(boolean newWillRestart){
		willRestart=newWillRestart;
	}
	
	private void restart() throws SlickException{
		loadLevel(level);
		clearMovesHistory();
	}
	/**This method increments the level variable and loads the level 
	 * corresponding to that variable that has been incremented. It also 
	 * resets the move history made by the blocks and player.
	 * 
	 * @throws SlickException
	 */
	public void levelUp() throws SlickException{
		level++;
		restart();
		int size=movesHistory.size();
		for(int j=0;j<size;j++){
			movesHistory.remove(0);
		}
		spritesCopy=Arrays.copyOf(sprites,sprites.length);
		recordMovesHistory();
	}
	/* This method loads the map of the given level.
	 */
	private void loadLevel(int level){
		switch(level){
		case 0:
			sprites=Loader.loadSprites("assets/levels/0.lvl");
			break;
		case 1:
			sprites=Loader.loadSprites("assets/levels/1.lvl");
			break;
		case 2:
			sprites=Loader.loadSprites("assets/levels/2.lvl");
			break;
		case 3:
			sprites=Loader.loadSprites("assets/levels/3.lvl");
			break;
		case 4:
			sprites=Loader.loadSprites("assets/levels/4.lvl");
			break;
		case 5:
			sprites=Loader.loadSprites("assets/levels/5.lvl");
			break;
		}
	}
	/* This method checks whether the level has been completed or not. It 
	 * checks whether all the targets are covered by blocks or not.
	 * It returns true if all targets are covered by blocks, false otherwise.
	 */
	private boolean levelCompletedCheck(){
		for (int j=sprites.length-1;0<=j;j--){
			//ignore things that are have been gone such as tnts that have
			//exploded.
			if(!sprites[j].getShow()){continue;}
			if (sprites[j].getClass().equals(Target.class)){
				Target target=(Target)sprites[j];	
				if(!target.getHasBlock()){
					return false;
				}
			}
		}
		return true;
	}
	/**This is a getter method that returns the coordinates of the player 
	 * sprite. Note that the coordinates of the player sprite is only 
	 * accessible in the sprites array that is initialised in the World class.
	 * @return a float array that has the x and y coordinates of the player.
	 */
	//public static <T extends Sprite> T getSprite(String tag) throws SlickException{
	public static Sprite getSprite(String tag) throws SlickException{
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			switch(tag){
			case "Player":
				if(spritesCopy[j].getClass().equals(Player.class)){
					Player spriteCopy=new Player((Player)spritesCopy[j]);
					return spriteCopy;
				}
				break;
			case "Tnt":
				if(spritesCopy[j].getClass().equals(Tnt.class)){ 
					Tnt spriteCopy=new Tnt((Tnt)spritesCopy[j]);
					return spriteCopy;
					
				}
				break;
			case "Rogue":
				if(spritesCopy[j].getClass().equals(Rogue.class)){ 
					Rogue spriteCopy=new Rogue((Rogue)spritesCopy[j]);
					return spriteCopy;
				}
				break;
			default:
				/* For error detection purposes */
				System.out.println("Spelling error");
				System.exit(0);
				break;
			}
		}
		
		return new Player(0,0);
	}
	/**This method checks whether a particular sprite type is in toHide or not.
	 * 
	 * @param className the class that wants to be checked.
	 * @return true if a sprite of that class exists in the toHide array, 
	 * false otherwise. 
	 */
	public static <T>boolean isSpriteInToHide(Class<T> className){
		for(int i=0;i<toHide.size();i++){
			if(spritesCopy[(int)toHide.get(i)].getClass().equals(className)){
				return true;
			}
		}
		return false;
	}
	
	/**This method returns the index of a particular sprite based on its 
	 * sprite type. This is done with the assumption that there is only one 
	 * door and one switch on each map.
	 * 
	 * @param className generic Class<T>
	 * @return the index in the sprites array
	 */
	public static <T> int getSpriteIndex(Class<T> className){
		for (int j=spritesCopy.length-1;0<=j;j--){
			//return the index regardless the state on whether the door is 
			//shown or not.
			if (spritesCopy[j].getClass().equals(className)){
				//tempIndex=j;
				return j;
			}
		}
		return -1;
	}
	/**This method checks whether there is a particular sprite type at a given 
	 * position in the map.
	 * @param tag name of the sprite type
	 * @param x x coordinate in the map
	 * @param y y coordinate in the map
	 * @return true if there exists, false otherwise.
	 */
	public static boolean hasSpriteTypeAt(String tag,float x,float y){
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			if(spritesCopy[j].getX()==x && spritesCopy[j].getY()==y){ 
				if (spritesCopy[j] instanceof Block){
					return true;
				}
			}
		}
		return false;
	}
	/**This method checks whether a sprite is blocked by another 
	 * particular sprite.
	 * @param x x coordinate in the map
	 * @param y y coordinate in the map
	 * @param className generic Class<T>
	 * @return true if there exists, false otherwise.
	 */
	public static <T> boolean isBlockedByParticularSprite(float x,float y,
			Class<T> className){
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			if(spritesCopy[j].getX()==x && spritesCopy[j].getY()==y){ 
				if(spritesCopy[j].getClass().equals(className)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**This method takes the x and y coordinates of a particular position 
	 * and returns whether the position is occupied by a sub-class of the 
	 * Inpenetrable class.
	 * @param x x coordinate in the map.
	 * @param y y coordinate in the map.
	 * @return true if the position is occupied by a sub-class of the 
	 * Inpenetrable class, false otherwise.
	 */
	public static boolean isBlocked(String tag,float x, float y) {
		//If wall is encountered
		/* No need bound checking because the wall is eventually will become
		 * the sign of the bounds?
		 */
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			if(spritesCopy[j].getX()==x && spritesCopy[j].getY()==y){ 
				switch(tag){
				case "Inpenetrable":
					if (spritesCopy[j] instanceof Inpenetrable){
						//tempIndex=j;
						return true;
					}
					break;
				case "Block":
					if(spritesCopy[j] instanceof Block){
						return true;
					}
					break;
				case "Unit":
					if(spritesCopy[j] instanceof Unit){
						return true;
					}
					break;
				default:
					/* For error detection purposes */
					System.out.println("Spelling error");
					System.exit(0);
					break;
				}
			}
		}
		
		return false;
	}
	
	
	/**This method returns true if the current coordinate is blocked by an 
	 * adjacent block.
	 * @param x x coordinate in the map.
	 * @param y y coordinate in the map.
	 * @param dir direction of the adjacent block.
	 * @return true if the current coordinate is blocked by an 
	 * adjacent block, false otherwise.
	 */
	public static boolean isBlockedByAdjacentBlock(float x,float y,int dir){
		
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			if(spritesCopy[j].getX()==x && spritesCopy[j].getY()==y){ 
				if(spritesCopy[j] instanceof Block){
					Block stone=(Block)spritesCopy[j];
					switch(dir){
					case DIR_UP:
						if(!stone.canMoveUp()){
							return true;
						}
						break;
					case DIR_DOWN:
						if(!stone.canMoveDown()){
							return true;
						}
						break;
					case DIR_RIGHT:
						if(!stone.canMoveRight()){
							return true;
						}
						break;
					case DIR_LEFT:
						if(!stone.canMoveLeft()){
							return true;
						}
						break;
					}
				}
			}
		}
		return false;
	}
	/**Sets up the values of the sprite(sprites) of concern before rendering 
	 * it. 
	 * 
	 * @param input
	 * @param delta
	 * @throws SlickException
	 */
	public void update(Input input, int delta) throws SlickException{
		for(int i=0;i<sprites.length;i++){
			spritesCopy=Arrays.copyOf(sprites,sprites.length);
			if(sprites[i]==null){continue;}
			currentUpdateIndex=i;
			sprites[i].update(input,delta);
		}
		if(toHide.size()>0){
			executeToHide();
			System.out.println("Execute toHide");
		}
		if(toRestore.size()>0){
			executeToRestore();
		}
		if(willRestart){
			willRestart=false;
			restart();
		}
		if(levelCompletedCheck()){levelUp();}
		if (input.isKeyPressed(Input.KEY_R)) {
			restart();
		}
		if (input.isKeyPressed(Input.KEY_Z)) {
			undo();
		}
	}
	/**
	 * This method renders the sprites onto the screen.
	 * @param g
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException{
		for(int i=0;i<sprites.length;i++){
			if(!sprites[i].getShow()){continue;}
    		sprites[i].render(g);
    	}
		g.drawString("Moves: "+((Player)getSprite("Player")).getMoves(), 11, 32);
	}
	
}
