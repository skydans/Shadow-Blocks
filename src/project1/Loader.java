package project1;

import java.io.*;

//import java.util.*;

public class Loader {
	
	private static int width=0,height=0;
	
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile
	public static boolean isBlocked(float x, float y) {
		// Default to blocked
		return true;
	}
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 */
	public static Sprite[] loadSprites(String filename) {
		int count=-1;
		String text;
		try (BufferedReader br =
			new BufferedReader(new FileReader(filename))){
			while((text=br.readLine())!=null){
				count++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("DONE HERE!!!");
		try (BufferedReader br =
			new BufferedReader(new FileReader(filename))){
			int index=0;
			text=br.readLine();
			String[] input=text.split(",");
			width=Integer.parseInt(input[0]);
			height=Integer.parseInt(input[1]);
			
			Sprite[] sprite=new Sprite[count];
			
			while((text=br.readLine())!=null){
				input=text.split(",");
				switch(input[0]){
					case "wall":
						sprite[index]=new Wall("/assets/wall.png",Integer.parseInt(input[1]),Integer.parseInt(input[2]));
						break;
					case "stone":
						sprite[index]=new Stone("/assets/stone.png",Integer.parseInt(input[1]),Integer.parseInt(input[2]));
						break;
					case "floor":
						sprite[index]=new Floor("/assets/floor.png",Integer.parseInt(input[1]),Integer.parseInt(input[2]));
						break;
					case "target":
						sprite[index]=new Target("/assets/target.png",Integer.parseInt(input[1]),Integer.parseInt(input[2]));
						break;
					case "player":
						//spriteArrayList.add(new Sprite("/assets/player_left.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						//can't assign this to Player subclass, since the array is defined as Sprite?
						sprite[index]=new Player("/assets/player_left.png",Integer.parseInt(input[1]),Integer.parseInt(input[2]));
						break;
				}
				index++;
				
				
				/*
				 * need abstraction?
				 * how to display based on the tiles?
				 * throws SlickException to render?
				 * */
			}
			//System.out.println(Arrays.toString(sprite));
			return sprite;
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Done here");
		return null;
	}
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	
}
