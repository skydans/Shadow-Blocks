package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ice extends Block {
	private int timer,tempDirection=DIR_NONE;
	public Ice(String image_src, float x, float y) throws SlickException {
		super(image_src,x,y);
		setShow(true);
		timer=0;
	}
	@Override
	public void update(Input input,int delta){
		blockCheck();
		float[] tempPlayerLatestMove=World.getPlayerLatestMove();
		if(tempPlayerLatestMove[0]==getX() && tempPlayerLatestMove[1]==getY()){
			tempDirection=(int)tempPlayerLatestMove[2];
			moveToDest(tempDirection);
			System.out.println("Ice moveToDest triggered by Player");
		}
		float[] tempRogueLatestMove=World.getRogueLatestMove();
		if(tempRogueLatestMove[0]==getX() && tempRogueLatestMove[1]==getY()){
			tempDirection=(int)tempRogueLatestMove[2];
			moveToDest(tempDirection);
			System.out.println("Ice moveToDest triggered by Rogue");
		}
		
		timer+=delta;
		if(timer>=100){//if(keepMoving && timer>=5){
			int deltaX=0,deltaY=0;
			switch(tempDirection){
			case DIR_UP:
				deltaY-=1;
				break;
			case DIR_DOWN:
				deltaY+=1;
				break;
			case DIR_LEFT:
				deltaX-=1;
				break;
			case DIR_RIGHT:
				deltaX+=1;
				break;
			}
			
			if(!World.isBlocked(getX()+deltaX,getY()+deltaY)
					&& !World.isBlockedByBlock(getX()+deltaX, 
							getY()+deltaY) && !World.isBlockedByUnit(getX()+deltaX, 
									getY()+deltaY)){
				moveToDest(tempDirection);
			}else{
				tempDirection=DIR_NONE;
			}
			timer=0;
		}
		
		//if(keepMoving){timer+=delta;System.out.println("timer: "+timer+"\n");}
		//if(timer>=200){timer=0;}
		blockCheck();
	}
}
