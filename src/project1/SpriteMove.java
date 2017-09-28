package project1;

public class SpriteMove {
	private int moveIndex,spriteIndex;
	private Sprite sprite;
	/*
	//Copy constructor
	public SpriteMove(SpriteMove spriteMove){
		this.moveIndex=spriteMove.moveIndex;
		this.spriteIndex=spriteMove.spriteIndex;
		this.sprite=spriteMove.sprite;
	}
	*/
	public SpriteMove(int moveIndex,int spriteIndex,Sprite sprite){
		this.moveIndex=moveIndex;
		this.spriteIndex=spriteIndex;
		this.sprite=sprite;
	}
	public int getMoveIndex(){
		return moveIndex;
	}
	public int getSpriteIndex(){
		return spriteIndex;
	}
	public Sprite getSprite(){
		return sprite;
	}
	public void setMoveIndex(int moveIndex){
		this.moveIndex=moveIndex;
	}
	public void setSpriteIndex(int spriteIndex){
		this.spriteIndex=spriteIndex;
	}
	public void setSprite(Sprite sprite){
		this.sprite=sprite;
	}
}
