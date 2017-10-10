package project1;
/**This class stores the sprite, its move index and sprite index in the 
 * sprites array in the World class. This class is the main type to store the 
 * move history and to allow undo functionality.
 * 
 * @author Daniel Gonawan
 *
 */
public class SpriteMove {
	private int moveIndex,spriteIndex;
	private Sprite sprite;
	/**Constructor of SpriteMove class. It initialises the move index, sprite 
	 * index and sprite.
	 * 
	 * @param moveIndex
	 * @param spriteIndex
	 * @param sprite
	 */
	public SpriteMove(int moveIndex,int spriteIndex,Sprite sprite){
		this.moveIndex=moveIndex;
		this.spriteIndex=spriteIndex;
		this.sprite=sprite;
	}
	/**This is a getter method which returns the move index of SpriteMove 
	 * object.
	 * 
	 * @return
	 */
	public int getMoveIndex(){
		return moveIndex;
	}
	/**This is a getter method which returns the sprite index of SpriteMove 
	 * object.
	 * 
	 * @return
	 */
	public int getSpriteIndex(){
		return spriteIndex;
	}
	/**This is a getter method which returns the sprite of SpriteMove object.
	 * 
	 * @return
	 */
	public Sprite getSprite(){
		return sprite;
	}
	/**This is a setter method which sets the move index of SpriteMove object.
	 * 
	 * @param moveIndex
	 */
	public void setMoveIndex(int moveIndex){
		this.moveIndex=moveIndex;
	}
	/**This is the setter method which sets the sprite index of SpriteMove 
	 * object.
	 * @param spriteIndex
	 */
	public void setSpriteIndex(int spriteIndex){
		this.spriteIndex=spriteIndex;
	}
	/**This is the setter method which sets the sprite of SpriteMove 
	 * object.
	 * @param sprite
	 */
	public void setSprite(Sprite sprite){
		this.sprite=sprite;
	}
}
