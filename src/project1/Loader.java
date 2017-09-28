package project1;

import java.io.*;
import java.util.Arrays;
/** reads the strings on the specified file and storing it to the Sprite 
 * object
 */
public class Loader {
	
	private static int width=0,height=0;
	
	
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 */
	public static Sprite[] loadSprites(String filename) {
		/* -1 as the starting index to offset the input of the dimension of 
		 * the tile.
		 * But then +1 to offset the input of the explosion sprite.
		 */
		int count=0;
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
						sprite[index]=new Mage("/assets/mage.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "rogue":
						sprite[index]=new Rogue("/assets/rogue.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "skeleton":
						sprite[index]=new Skeleton("/assets/skeleton.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "ice":
						sprite[index]=new Ice("/assets/ice.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "tnt":
						sprite[index]=new Tnt("/assets/tnt.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "cracked":
						sprite[index]=new Cracked("/assets/cracked_wall.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "switch":
						sprite[index]=new Switch("/assets/switch.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
					case "door":
						sprite[index]=new Door("/assets/door.png",
								Integer.parseInt(input[1]),
								Integer.parseInt(input[2]));
						break;
				}
				index++;
			}
			sprite[index]=new Explosion("/assets/explosion.png",0,0);
			Arrays.sort(sprite);
			for(int i=0;i<sprite.length;i++){
				System.out.println(sprite[i].getImageSrc()+sprite[i].getX()+
						sprite[i].getY());
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
