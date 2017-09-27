package project1;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/** World class handles interactions between sprites */
public class World {	
	/** array of Sprite objects */
	private Sprite[] sprites;
	
	//private Image draw;
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	private static boolean willRestart;
	private int level;
	private static int moves;
	private static float[] rogueLatestMove;
	private static float[] playerLatestMove;
	private static float[] playerLatestMoveAttempt;
	private static float[] latestTntPosition;
	private static int currentUpdateIndex;
	//this is used to store temporary index produced by isBlockedByBlock method
	//private int tempIndex;
	private static Sprite[] tempSprites;
	private static List<Integer> toDelete;
	private static List<Integer> toRestore;
	//private static int level;
	public static final int WALL=1;
	public static final int STONE=2;
	/** constructor of the World class. */
	public World() {
		//loads the sprite when an instance of the world is created.
		sprites=Loader.loadSprites("assets/levels/3.lvl");
		level=3;
		rogueLatestMove=new float[3];
		playerLatestMove=new float[3];
		playerLatestMoveAttempt=new float[2];
		latestTntPosition=new float[2];
		moves=0;
		toDelete=new ArrayList<>();
		toRestore=new ArrayList<>();
		willRestart=false;
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
	public static int getCurrentUpdateIndex(){
		return currentUpdateIndex;
	}
	
	public static void setMoves(int newMoves){
		moves=newMoves; //this does not seem to apply here
	}
	public static int getMoves(){
		return moves;
	}
	
	public static float[] getPlayerLatestMoveAttempt(){
		float[] playerLatestMoveAttemptCopy=
				Arrays.copyOf(playerLatestMoveAttempt, 
						playerLatestMoveAttempt.length);
		return playerLatestMoveAttemptCopy;
	}
	
	public static float[] getPlayerLatestMove(){
		float[] playerLatestMoveCopy=Arrays.copyOf(playerLatestMove, 
				playerLatestMove.length);
		return playerLatestMoveCopy;
	}
	
	public static float[] getRogueLatestMove(){
		float[] rogueLatestMoveCopy=Arrays.copyOf(rogueLatestMove, 
				rogueLatestMove.length);
		return rogueLatestMoveCopy;
	}
	
	public static void addToDelete(int index){
		toDelete.add(index);
	}
	
	public static List<Integer> getToDelete(){
		List<Integer> toDeleteCopy=new ArrayList<>(toDelete);
		return toDeleteCopy;
	}
	
	public void executeToDelete(){
		for(int i=0;i<toDelete.size();i++){
			//if(sprites[toDelete.get(i)].equals(Tnt.class)){
			sprites[toDelete.get(i)].setShow(false);;
			/*	
			}else{
				sprites[toDelete.get(i)]=null;
			}
			*/
			/*
			int waitCounter=0;
			while(waitCounter<=10){
				waitCounter+=delta;
			}
			*/
			
		}
		/*List<Sprite> tempToDelete = new ArrayList<Sprite>(Arrays.asList(sprites));
		for(int i=0;i<toDelete.size();i++){
			for (Iterator<Sprite> it = tempToDelete.iterator(); it.hasNext();){
				Sprite sprite=it.next();
				if(sprite.equals(sprites[toDelete.get(i)])){
					it.remove();
				}
			}
		}
		System.out.println("To delete index:"+toDelete.get(0));
		for(int i=0;i<tempToDelete.size();i++){
			System.out.println(tempToDelete.get(i).getImageSrc()+
					tempToDelete.get(i).getX()+
					tempToDelete.get(i).getY());
		}
		
		sprites = tempToDelete.toArray(sprites);
		*/
		System.out.println("toDelete.size: "+toDelete.size());
		int size=toDelete.size();
		for(int i=0;i<size;i++){
			toDelete.remove(0);
		}
		
		/*
		for (Iterator<Integer> it = toDelete.iterator(); it.hasNext();){
			it.remove();
		}
		*/
		System.out.println("toDelete.size after delete: "+toDelete.size());
		
		//
		/*
		for(int i=0;i<sprites.length;i++){
			System.out.println(sprites[i].getImageSrc()+sprites[i].getX()+
					sprites[i].getY());
		}
		*/
		
	}
	
	public static void addToRestore(int index){
		toRestore.add(index);
	}
	
	public static List<Integer> getToRestore(){
		List<Integer> toRestoreCopy=new ArrayList<>(toRestore);
		return toRestoreCopy;
	}
	
	public void executeToRestore(){
		for(int i=0;i<toRestore.size();i++){
			sprites[toRestore.get(i)].setShow(true);
		}
		int size=toRestore.size();
		for(int i=0;i<size;i++){
			toRestore.remove(0);
		}
		
	}
	
	public static void setRogueLatestMove(float x,float y, int dir){
		rogueLatestMove[0]=x;
		rogueLatestMove[1]=y;
		rogueLatestMove[2]=dir;
	}
	
	public static void setPlayerLatestMove(float x,float y, int dir){
		playerLatestMove[0]=x;
		playerLatestMove[1]=y;
		playerLatestMove[2]=dir;
	}
	
	public static void setPlayerLatestMoveAttempt(float x,float y){
		playerLatestMoveAttempt[0]=x;
		playerLatestMoveAttempt[1]=y;
	}
	
	public static void setWillRestart(boolean newWillRestart){
		willRestart=newWillRestart;
	}
	
	public void restart(){
		loadLevel(level);
	}
	
	public void levelUp(){
		level++;
		loadLevel(level);
	}
	
	public void loadLevel(int level){
		switch(level){
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
			if (sprites[j] instanceof Block){
				Block block=(Block)sprites[j];	
				if(!block.getOnTarget()){
					return false;
				}
			}
		}
		return true;
	}
	
	public static float[] getPlayerCoordinates(){
		float[] coordinates=new float[2];
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getClass().equals(Player.class)){ 
				coordinates[0]=tempSprites[j].getX();
				coordinates[1]=tempSprites[j].getY();
				break;
			}
		}
		return coordinates;
	}
	
	/*
	public static float[] getSpriteCoordinatesByIndex(int index){
		float[] coordinates=new float[2];
		coordinates[0]=tempSprites[index].getX();
		coordinates[1]=tempSprites[index].getY();
		return coordinates;
	}
	*/
	public static float[] getLatestTntPosition(){
		/*Using copy of array to prevent privacy leak*/
		float[] latestTntPositionCopy=Arrays.copyOf(latestTntPosition,
				latestTntPosition.length);
		return latestTntPositionCopy;
	}
	
	public static void setLatestTntPosition(float x,float y){
		latestTntPosition[0]=x;
		latestTntPosition[1]=y;
	}
	
	public static boolean isTntInToDelete(){
		for(int i=0;i<toDelete.size();i++){
			if(tempSprites[(int)toDelete.get(i)].getClass().equals(Tnt.class)){
				return true;
			}
		}
		return false;
	}
	
	/* Assuming that there is only one door and one switch on each map. */
	public static int getDoorIndex(){
		for (int j=tempSprites.length-1;0<=j;j--){
			//return the index regardless the state on whether the door is 
			//shown or not.
			if (tempSprites[j].getClass().equals(Door.class)){
				//tempIndex=j;
				return j;
			}
		}
		return -1;
	}
	
	public static boolean isOnTarget(float x,float y){
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getX()==x && tempSprites[j].getY()==y){ 
				if (tempSprites[j].getClass().equals(Target.class)){
					//tempIndex=j;
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean hasBlockAt(float x,float y){
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getX()==x && tempSprites[j].getY()==y){ 
				if (tempSprites[j] instanceof Block){
					//tempIndex=j;
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public static boolean isBlockedByTntOrCracked(float x,float y){
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getX()==x && tempSprites[j].getY()==y){ 
				if(tempSprites[j].getClass().equals(Tnt.class) ||
						tempSprites[j].getClass().equals(Cracked.class)){
					return true;
				}
			}
		}
		return false;
	}
	
	/** Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a blocked tile
	 */
	public static boolean isBlocked(float x, float y) {
		
		//If wall is encountered
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getX()==x && tempSprites[j].getY()==y){ 
				if (tempSprites[j] instanceof Inpenetrable){
					//tempIndex=j;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isBlockedByBlock(float x,float y){
		/* No need bound checking because the wall is eventually will become
		 * the sign of the bounds?
		 */
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getX()==x && tempSprites[j].getY()==y){ 
				if(tempSprites[j] instanceof Block){
					//tempIndex=j;
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public static boolean isBlockedByAdjacentBlock(float x,float y,int dir){
		
		for (int j=tempSprites.length-1;0<=j;j--){
			if(!tempSprites[j].getShow()){continue;}
			if(tempSprites[j].getX()==x && tempSprites[j].getY()==y){ 
				if(tempSprites[j] instanceof Block){
					//tempIndex=j;
					Block stone=(Block)tempSprites[j];
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
	/** Sets up the values of the sprite(sprites) of concern before rendering it. 
	 */
	public void update(Input input, int delta) {
		for(int i=0;i<sprites.length;i++){
			tempSprites=Arrays.copyOf(sprites,sprites.length);
			if(sprites[i]==null){continue;}
			currentUpdateIndex=i;
			sprites[i].update(input,delta);
		}
		if(toDelete.size()>0){
			executeToDelete();
			System.out.println("Execute toDelete");
		}
		if(toRestore.size()>0){
			executeToRestore();
		}
		if(willRestart){
			willRestart=false;
			restart();
		}
		if(levelCompletedCheck()){levelUp();}
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
