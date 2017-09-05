package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite {	
	private String image_src; //primitive
	private float x,y; //primitive
	private Image image;
	//private int spriteType;
	
	/*
	//Copy constructor
	public Sprite(Sprite sprite){
		this.image_src=sprite.image_src;
		this.x=sprite.x;
		this.y=sprite.y;
		this.image=sprite.image;
	}
	*/
	
	//Constructor
	public Sprite(String image_src, float x, float y) throws SlickException{
		this.image_src=image_src;
		this.x=x;
		this.y=y;
		this.image=new Image(image_src);
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public String getImageSrc(){
		return image_src;
	}
	
	public Image getImage(){
		return image;
	}
	
	public boolean isPlayer(){
		return false;
	}
	
	public void update(Input input, int delta) {
		//System.out.println(image_src);
		//System.out.println(x);
		//System.out.println(y);
	}
	
	public void render(Graphics g) {
		
	}
	
	
}
