package project1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {	
	
	private Sprite[] s;
	
	Image draw;
	int playerIndex;
	
	public World() {
		s=Loader.loadSprites("/assets/levels/0.lvl");
	}
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g) throws SlickException{
		//App.
		//.draw(App.SCREEN_WIDTH/2,App.SCREEN_HEIGHT/2);
		//test.draw(App.SCREEN_WIDTH,App.SCREEN_HEIGHT);
		for(int i=0;i<s.length;i++){
    		draw=new Image(s[i].getImageSrc());
			draw.drawCentered((App.SCREEN_WIDTH-Loader.width*App.TILE_SIZE)/2+(s[i].getX()*App.TILE_SIZE+App.TILE_SIZE/2),(App.SCREEN_HEIGHT-Loader.height*App.TILE_SIZE)/2+(s[i].getY()*App.TILE_SIZE+App.TILE_SIZE/2));
			//if(s[i].isPlayer()){
			//	playerIndex=i;
			//	System.out.println("Player found");
			//}
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
