package project1;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**This class handles interactions between sprites.
 * 
 * @author Daniel Gonawan
 *
 */
public class World {	
	private Sprite[] sprites;
	
	//private Image draw;
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
	
	private static boolean willRestart;
	private int level;
	private static int moves,prevMoves;
	private static float[] rogueLatestMove;
	private static float[] playerLatestMove;
	private static float[] playerLatestMoveAttempt;
	//private static float[] latestTntPosition;
	private static int currentUpdateIndex;
	//this is used to store temporary index produced by isBlockedByBlock method
	//private int tempIndex;
	private static Sprite[] spritesCopy;
	private static List<Integer> toHide;
	private static List<Integer> toRestore;
	private static List<SpriteMove> movesHistory;
	private static List<float[]> playerLatestMoveHistory;
	private static List<float[]> playerLatestMoveAttemptHistory;
	//private static int level;
	public static final int WALL=1;
	public static final int STONE=2;
	/**Constructor of the World class. It sets the current level and loads 
	 * that level. It initializes the arrays for the latest move of rogue, 
	 * the latest move of player, the latest move attempt made by player, 
	 * the latest TNT position, the sprites to be hidden, the sprites to be 
	 * restored, histories, copy of sprites. 
	 * 
	 * It also sets the willRestart variable to false.
	 * 
	 * In addition, it records the current state of the player, so that the 
	 * undo functionality will apply until the initial state of the player.
	 * 
	 * @throws SlickException
	 */
	public World() throws SlickException {
		//loads the sprite when an instance of the world is created.
		level=3;
		loadLevel(level);
		rogueLatestMove=new float[3];
		playerLatestMove=new float[3];
		playerLatestMoveAttempt=new float[2];
		//latestTntPosition=new float[2];
		moves=0;
		resetPrevMoves();
		toHide=new ArrayList<>();
		toRestore=new ArrayList<>();
		movesHistory=new ArrayList<>();
		playerLatestMoveHistory=new ArrayList<>();
		playerLatestMoveAttemptHistory=new ArrayList<>();
		willRestart=false;
		spritesCopy=Arrays.copyOf(sprites,sprites.length);
		recordMovesHistory();
	}
	/*
	public void stoneCheck(){
		for(int i=0;i<sprites.length;i++){
			if(sprites[i].getClass().equals(Stone.class)){
				Stone stone=(Stone)sprites[i];
				
			}
		}
	}
	*/
	/**This method is a getter which returns the index of the sprites array    
	 * that is being currently updated.
	 * 
	 * @return the index of the current sprites array that is being updated.
	 */
	public static int getCurrentUpdateIndex(){
		return currentUpdateIndex;
	}
	/**This is a setter method that sets the number of moves recorded.
	 * 
	 * @param newMoves number of moves made by the player
	 */
	public static void setMoves(int newMoves){
		moves=newMoves; //this does not seem to apply here
	}
	/**This is a getter method that returns the number of moves.
	 *  
	 * @return the number of moves
	 */
	public static int getMoves(){
		return moves;
	}
	/**This method returns a copy of an array of 2 elements which are the 
	 * x coordinate and the y coordinate the player attempted to move into.
	 *  
	 * @return a copy of an array of 2 elements which are the 
	 * x coordinate and the y coordinate the player attempted to move into.
	 */
	public static float[] getPlayerLatestMoveAttempt(){
		float[] playerLatestMoveAttemptCopy=
				Arrays.copyOf(playerLatestMoveAttempt, 
						playerLatestMoveAttempt.length);
		return playerLatestMoveAttemptCopy;
	}
	/**This method returns a copy of an array which stores the data of the 
	 * latest move done by the player.
	 * 
	 * @return a copy of an array of 3 elements which are the 
	 * x and y coordinates the player attempted to move into, and
	 * the direction of that attempt.
	 */
	public static float[] getPlayerLatestMove(){
		float[] playerLatestMoveCopy=Arrays.copyOf(playerLatestMove, 
				playerLatestMove.length);
		return playerLatestMoveCopy;
	}
	/**This method returns a copy of an array which stores the data of the 
	 * latest move done by the rogue.
	 * 
	 * @return a copy of an array of 3 elements which are the 
	 * x and y coordinates the rogue attempted to move into, and
	 * the direction of that attempt.
	 */
	public static float[] getRogueLatestMove(){
		float[] rogueLatestMoveCopy=Arrays.copyOf(rogueLatestMove, 
				rogueLatestMove.length);
		return rogueLatestMoveCopy;
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
		playerLatestMoveHistory.add(getPlayerLatestMove());
		playerLatestMoveAttemptHistory.add(getPlayerLatestMoveAttempt());
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			
			if(spritesCopy[j] instanceof Ice){ 
				Ice iceCopy=new Ice((Ice)spritesCopy[j]);
				movesHistory.add(new SpriteMove(moves,j,iceCopy));
			}else if(spritesCopy[j] instanceof Stone){
				Stone stoneCopy=new Stone((Stone)spritesCopy[j]);
				movesHistory.add(new SpriteMove(moves,j,stoneCopy));
			}else if(spritesCopy[j] instanceof Tnt){
				Tnt tntCopy=new Tnt((Tnt)spritesCopy[j]);
				movesHistory.add(new SpriteMove(moves,j,tntCopy));
			}else if(spritesCopy[j] instanceof Cracked){
				Cracked crackedCopy=new Cracked((Cracked)spritesCopy[j]);
				movesHistory.add(new SpriteMove(moves,j,crackedCopy));
			}else if(spritesCopy[j].getClass().equals(Player.class)){
				Player playerCopy=new Player((Player)spritesCopy[j]);
				movesHistory.add(new SpriteMove(moves,j,playerCopy));
				//System.out.println("spritesCopy X: "+spritesCopy[j].getX());
				//System.out.println("spritesCopy Y: "+spritesCopy[j].getY());
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
		if(moves==0){return;}
		/* reverts the playerLatestMoveHistory array (which stores the latest 
		 * move of the player.
		 */
		for(int j=playerLatestMoveHistory.size()-1;0<=j;j--){
			if(j==(moves-1)){
				setPlayerLatestMove(playerLatestMoveHistory.get(j)[0],
						playerLatestMoveHistory.get(j)[1],
						(int)playerLatestMoveHistory.get(j)[2]);
			}
		}
		/* reverts the playerLatestMoveAttemptHistory array (which stores the 
		 * latest move attempt of the player.
		 */
		for(int j=playerLatestMoveAttemptHistory.size()-1;0<=j;j--){
			if(j==(moves-1)){
				setPlayerLatestMoveAttempt(playerLatestMoveAttemptHistory.get(j)[0],
						playerLatestMoveAttemptHistory.get(j)[1]);
			}
		}
		
		/* Revert all the Sprite objects that are of the last index based on 
		 * the movesHistory array.
		 */
		for(int j=movesHistory.size()-1;0<=j;j--){
			/*
			System.out.println("moveHistoryList");
			System.out.println("moveIndex: "+movesHistory.get(j).getMoveIndex());
			System.out.println("movesHistory X: "+movesHistory.get(j).getSprite().getX());
			System.out.println("movesHistory Y: "+movesHistory.get(j).getSprite().getY());
			*/
			if(movesHistory.get(j).getMoveIndex()==(moves-1)){
				//System.out.println("moveIndex: "+movesHistory.get(j).getMoveIndex());
				//System.out.println("movesHistory X: "+movesHistory.get(j).getSprite().getX());
				//System.out.println("movesHistory Y: "+movesHistory.get(j).getSprite().getY());
				//sprites[movesHistory.get(j).getSpriteIndex()]=
				//		movesHistory.get(j).getSprite();
				
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
					//sprites[movesHistory.get(j).getSpriteIndex()].setX(3);
					//sprites[movesHistory.get(j).getSpriteIndex()].setY(5);
					//sprites[movesHistory.get(j).getSpriteIndex()].setShow(true);
					
					Player playerCopy=new Player((Player)movesHistory.get(j).getSprite());
					
					sprites[movesHistory.get(j).getSpriteIndex()]=playerCopy;
					/*
					System.out.println("Player undo");
					System.out.println(sprites[movesHistory.get(j).getSpriteIndex()].getShow());
					*/
				}
			}
		}
		/* Remove all the SpriteMove objects that are of the last index.
		 */
		while(true){
			int tempIndex=movesHistory.size()-1;
			if(tempIndex<0){break;}
			if(movesHistory.get(tempIndex).getMoveIndex()==moves){
				movesHistory.remove(tempIndex);
			}else{
				break;
			}
		}
		/* Remove the playerLatestMove array that is of the latest index.
		 */
		int tempIndex=playerLatestMoveHistory.size()-1;
		if(tempIndex>=0){
			playerLatestMoveHistory.remove(tempIndex);
		}
		/* Remove the playerLatestMoveAttempt array that is of the latest 
		 * index.
		 */
		tempIndex=playerLatestMoveAttemptHistory.size()-1;
		if(tempIndex>=0){
			playerLatestMoveAttemptHistory.remove(tempIndex);
		}
		//decrement the number of moves.
		moves--;
		/* reset the prevMoves variable so that it can be usable again to check
		 * whether a move has been made. 
		 */
		resetPrevMoves();
		/*
		for(int j=movesHistory.size()-1;0<=j;j--){
			System.out.println("moveHistoryList after undo");
			System.out.println("moveIndex: "+movesHistory.get(j).getMoveIndex());
			System.out.println("movesHistory X: "+movesHistory.get(j).getSprite().getX());
			System.out.println("movesHistory Y: "+movesHistory.get(j).getSprite().getY());
		}
		*/
	}
	
	public void resetPrevMoves(){
		prevMoves=moves;
	}
	
	public void executeToHide(){
		for(int i=0;i<toHide.size();i++){
			//if(sprites[toHide.get(i)].equals(Tnt.class)){
			sprites[toHide.get(i)].setShow(false);;
			/*	
			}else{
				sprites[toHide.get(i)]=null;
			}
			*/
			/*
			int waitCounter=0;
			while(waitCounter<=10){
				waitCounter+=delta;
			}
			*/
			
		}
		/*List<Sprite> tempToHide = new ArrayList<Sprite>(Arrays.asList(sprites));
		for(int i=0;i<toHide.size();i++){
			for (Iterator<Sprite> it = tempToHide.iterator(); it.hasNext();){
				Sprite sprite=it.next();
				if(sprite.equals(sprites[toHide.get(i)])){
					it.remove();
				}
			}
		}
		System.out.println("To delete index:"+toHide.get(0));
		for(int i=0;i<tempToHide.size();i++){
			System.out.println(tempToHide.get(i).getImageSrc()+
					tempToHide.get(i).getX()+
					tempToHide.get(i).getY());
		}
		
		sprites = tempToHide.toArray(sprites);
		*/
		System.out.println("toHide.size: "+toHide.size());
		int size=toHide.size();
		for(int i=0;i<size;i++){
			toHide.remove(0);
		}
		
		/*
		for (Iterator<Integer> it = toHide.iterator(); it.hasNext();){
			it.remove();
		}
		*/
		System.out.println("toHide.size after delete: "+toHide.size());
		
		//
		/*
		for(int i=0;i<sprites.length;i++){
			System.out.println(sprites[i].getImageSrc()+sprites[i].getX()+
					sprites[i].getY());
		}
		*/
		
	}
	/**This method adds an index of the sprites array to the List that stores 
	 * the indices of the sprites that are going to be restored (or made 
	 * visible).
	 * @param index index of the Sprite in the sprites array.
	 */
	public static void addToRestore(int index){
		toRestore.add(index);
	}
	/**This is a getter method that returns a copy of a List of indices of 
	 * Sprites that are going to be restored.
	 * @return a copy of a List of indices of Sprites that are going to be 
	 * restored.
	 */
	public static List<Integer> getToRestore(){
		List<Integer> toRestoreCopy=new ArrayList<>(toRestore);
		return toRestoreCopy;
	}
	/**This method executes the operations needed for the sprites to be 
	 * restored.
	 */
	public void executeToRestore(){
		for(int i=0;i<toRestore.size();i++){
			sprites[toRestore.get(i)].setShow(true);
		}
		int size=toRestore.size();
		for(int i=0;i<size;i++){
			toRestore.remove(0);
		}
		
	}
	/**This is a setter method that sets the x and y coordinates as a result 
	 * of the latest move made by the rogue. It also sets the direction of the 
	 * latest move.
	 * @param x x coordinate as a result of the latest move made by the rogue.
	 * @param y y coordinate as a result of the latest move made by the rogue.
	 * @param dir direction of the latest move.
	 */
	public static void setRogueLatestMove(float x,float y, int dir){
		rogueLatestMove[0]=x;
		rogueLatestMove[1]=y;
		rogueLatestMove[2]=dir;
	}
	/**This is a setter method that sets the x and y coordinates as a result 
	 * of the latest move vmade by the player. It also sets the direction of the 
	 * latest move.
	 * @param x x coordinate as a result of the latest move made by the player.
	 * @param y y coordinate as a result of the latest move made by the player.
	 * @param dir direction of the latest move.
	 */
	public static void setPlayerLatestMove(float x,float y, int dir){
		playerLatestMove[0]=x;
		playerLatestMove[1]=y;
		playerLatestMove[2]=dir;
	}
	/**This is a setter method that sets the x and y coordinates of the 
	 * player's latest move attempt. A move attempt is an unsuccessful move 
	 * made by the player, such as when the player tries to walk into a wall. 
	 * @param x x coordinate as a result of the latest move attempt made by 
	 * the player.
	 * @param y x coordinate as a result of the latest move attempt made by 
	 * the player.
	 */
	public static void setPlayerLatestMoveAttempt(float x,float y){
		playerLatestMoveAttempt[0]=x;
		playerLatestMoveAttempt[1]=y;
	}
	
	public void clearMovesHistory(){
		int size=movesHistory.size();
		for(int i=0;i<size-1;i++){
			movesHistory.remove(movesHistory.size()-1);
		}
	}
	
	public void clearPlayerLatestMoveAttemptHistory(){
		int size=playerLatestMoveAttemptHistory.size();
		for(int i=0;i<size-1;i++){
			playerLatestMoveAttemptHistory.remove(
					playerLatestMoveAttemptHistory.size()-1);
		}
	}
	public void clearPlayerLatestMoveHistory(){
		int size=playerLatestMoveHistory.size();
		for(int i=0;i<size-1;i++){
			playerLatestMoveHistory.remove(playerLatestMoveHistory.size()-1);
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
	
	public void restart() throws SlickException{
		loadLevel(level);
		moves=0;
		resetPrevMoves();
		clearMovesHistory();
		clearPlayerLatestMoveHistory();
		clearPlayerLatestMoveAttemptHistory();
	}
	public void levelUp() throws SlickException{
		level++;
		restart();
		int size=movesHistory.size();
		for(int j=0;j<size;j++){
			movesHistory.remove(0);
		}
		size=playerLatestMoveAttemptHistory.size();
		for(int j=0;j<size;j++){
			playerLatestMoveAttemptHistory.remove(0);
		}
		size=playerLatestMoveHistory.size();
		for(int j=0;j<size;j++){
			playerLatestMoveHistory.remove(0);
		}
		spritesCopy=Arrays.copyOf(sprites,sprites.length);
		recordMovesHistory();
	}
	
	public void loadLevel(int level){
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
	
	public boolean levelCompletedCheck(){
		for (int j=sprites.length-1;0<=j;j--){
			//ignore things that are have been gone such as tnt that has
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
	public static Sprite getSprite(String tag) throws SlickException{
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			switch(tag){
			case "Player":
				if(spritesCopy[j].getClass().equals(Player.class)){ 
					Sprite spriteCopy=new Player((Player)spritesCopy[j]);
					return spriteCopy;
				}
				break;
			case "Tnt":
				if(spritesCopy[j].getClass().equals(Tnt.class)){ 
					Sprite spriteCopy=new Tnt((Tnt)spritesCopy[j]);
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
	
	/*
	public static float[] getSpriteCoordinatesByIndex(int index){
		float[] coordinates=new float[2];
		coordinates[0]=spritesCopy[index].getX();
		coordinates[1]=spritesCopy[index].getY();
		return coordinates;
	}
	*/
	/**This is a getter method that returns the coordinates of the Tnt 
	 * sprite. Note that the coordinates of the Tnt sprite is only 
	 * accessible in the sprites array that is initialised in the World class.
	 * 
	 * @return
	 */
	/*
	private static float[] getLatestTntPosition(){
		//Using copy of array to prevent privacy leak
		float[] latestTntPositionCopy=Arrays.copyOf(latestTntPosition,
				latestTntPosition.length);
		return latestTntPositionCopy;
	}
	 */
	
	/*
	public static void setLatestTntPosition(float x,float y){
		latestTntPosition[0]=x;
		latestTntPosition[1]=y;
	}
	*/
	
	public static boolean isTntInToHide(){
		for(int i=0;i<toHide.size();i++){
			if(spritesCopy[(int)toHide.get(i)].getClass().equals(Tnt.class)){
				return true;
			}
		}
		return false;
	}
	
	/* Assuming that there is only one door and one switch on each map. */
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
	
	public static boolean hasBlockAt(float x,float y){
		for (int j=spritesCopy.length-1;0<=j;j--){
			if(!spritesCopy[j].getShow()){continue;}
			if(spritesCopy[j].getX()==x && spritesCopy[j].getY()==y){ 
				if (spritesCopy[j] instanceof Block){
					//tempIndex=j;
					return true;
				}
			}
		}
		return false;
	}
	
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
						//tempIndex=j;
						return true;
					}
					break;
				case "Unit":
					if(spritesCopy[j] instanceof Unit){
						//tempIndex=j;
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
	
	/**This method returns true if the player has moved.
	 * @return true if the player has moved, false otherwise.
	 */
	public static boolean playerMoved(){
		if(moves>prevMoves){
			prevMoves=moves;
			return true;
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
					//tempIndex=j;
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
		g.drawString("Moves: "+moves, 11, 32);
	}
	
}
