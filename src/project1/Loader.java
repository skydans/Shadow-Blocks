package project1;

import java.io.*;
/** reads the strings on the specified file and storing it to the Sprite 
 * object
 */
public class Loader {
	
	private static int width=0,height=0;
	
	
	/** Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a blocked tile
	 */
	public static boolean isBlocked(float x, float y) {
		
		//If wall is encountered
		for (int j=sprites.length-1;0<=j;j--){
			if(sprites[j].getX()==x && sprites[j].getY()==y){ 
				if(sprites[j].getClass().equals(Wall.class)){
					tempIndex=j;
					return WALL;
				}
			}
		}
		
		// Default to blocked
		return true;
	}
	
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 */
	public static Sprite[] loadSprites(String filename) {
		/* -1 as the starting index to offset the input of the dimension of 
		 * the tile.
		 */
		int count=-1;
		String text;
		/* Do the first read of the file to determine the size of the Sprite
		 * array.
		 */
		try (BufferedReader br =
			new BufferedReader(new FileReader(filename))){
			while((text=br.readLine())!=null){
				count++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		/* Do the second read of the file to start storing the values inside
		 * variables and an array.
		 */
		try (BufferedReader br =
			new BufferedReader(new FileReader(filename))){
			int index=0;
			text=br.readLine();
			/* split the string and remove the comma to extract information
			 * from the CSV file.
			 */
			String[] input=text.split(",");
			width=Integer.parseInt(input[0]);
			height=Integer.parseInt(input[1]);
			
			Sprite[] sprite=new Sprite[count];
			
			/* Begin reading the coordinates and assigning corresponding 
			 * paths to the array of Sprites.
			 */
			while((text=br.readLine())!=null){
				input=text.split(",");
				/* create subclasses based on the type of the sprite.
				 */
				switch(input[0]){
					case "wall":
						sprite[index]=new Wall("/assets/wall.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "stone":
						sprite[index]=new Stone("/assets/stone.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "floor":
						sprite[index]=new Floor("/assets/floor.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "target":
						sprite[index]=new Target("/assets/target.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "player":
						sprite[index]=new Player("/assets/player_left.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "mage":
						sprite[index]=new Player("/assets/mage.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "rogue":
						sprite[index]=new Player("/assets/rogue.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "skeleton":
						sprite[index]=new Player("/assets/skeleton.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "ice":
						sprite[index]=new Player("/assets/ice.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "tnt":
						sprite[index]=new Player("/assets/tnt.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "cracked":
						sprite[index]=new Player("/assets/cracked_wall.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "switch":
						sprite[index]=new Player("/assets/switch.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "door":
						sprite[index]=new Player("/assets/door.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
				}
				index++;
			}
			
			return sprite;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	// getter method for the width variable of the world tile dimension.
	public static int getWidth(){
		return width;
	}
	// getter method for the height variable of the world tile dimension. 
	public static int getHeight(){
		return height;
	}
}
