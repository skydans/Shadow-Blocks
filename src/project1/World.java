package project1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {	
	
	private Sprite[] s;
	
	private Image draw;
	
	public World() {
		s=Loader.loadSprites("assets/levels/0.lvl");
	}
	
	public void update(Input input, int delta) {
		for(int i=0;i<s.length;i++){
			s[i].update(input,delta);
		}
	}
	
	public void render(Graphics g) throws SlickException{
		for(int i=0;i<s.length;i++){
    		draw=new Image(s[i].getImageSrc());
			draw.drawCentered((App.SCREEN_WIDTH-Loader.getWidth()*App.TILE_SIZE)/2+(s[i].getX()*App.TILE_SIZE+App.TILE_SIZE/2),(App.SCREEN_HEIGHT-Loader.getHeight()*App.TILE_SIZE)/2+(s[i].getY()*App.TILE_SIZE+App.TILE_SIZE/2));
			//System.out.println(s[i].getClass());
			if(s[i].isPlayer()){
				//System.out.println("Player found");
			}
    	}
		
	}
	/*
	public void setPlayer(Sprite player){
		this.player=player;
	}
	public Sprite getPlayer(){
		return player;
	}
	*/
	
}
