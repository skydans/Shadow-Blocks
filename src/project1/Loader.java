package project1;

import java.io.*;

import java.util.*;

public class Loader {
	
	public static int width=0,height=0;
	
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
		try (BufferedReader br =
			new BufferedReader(new FileReader("assets/levels/0.lvl"))){
			String text;
			text=br.readLine();
			String[] input=text.split(",");
			width=Integer.parseInt(input[0]);
			height=Integer.parseInt(input[1]);
			
			List<Sprite> spriteArrayList=new ArrayList<Sprite>();
			
			while((text=br.readLine())!=null){
				input=text.split(",");
				switch(input[0]){
					case "wall":
						spriteArrayList.add(new Sprite("/assets/wall.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						break;
					case "stone":
						spriteArrayList.add(new Sprite("/assets/stone.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						break;
					case "floor":
						spriteArrayList.add(new Sprite("/assets/floor.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						break;
					case "target":
						spriteArrayList.add(new Sprite("/assets/target.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						break;
					case "player":
						//spriteArrayList.add(new Sprite("/assets/player_left.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						//can't assign this to Player subclass, since the array is defined as Sprite?
						spriteArrayList.add(new Player("/assets/player_left.png",Integer.parseInt(input[1]),Integer.parseInt(input[2])));
						break;
				}
				
				
				/*
				 * need abstraction?
				 * how to display based on the tiles?
				 * throws SlickException to render?
				 * */
			}
			Sprite[] sprite=new Sprite[spriteArrayList.size()];
			for(int i=0;i<spriteArrayList.size();i++){
				sprite[i]=new Sprite(spriteArrayList.get(i));
				System.out.println(sprite[i].getX());
			}
			System.out.println(Arrays.toString(sprite));
			return sprite;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
