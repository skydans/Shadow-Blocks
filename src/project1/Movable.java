package project1;

public interface Movable {
	public abstract boolean canMoveUp();
	public abstract boolean canMoveDown();
	public abstract boolean canMoveRight();
	public abstract boolean canMoveLeft();
	public abstract void setCanMoveDown(boolean canBeMovedDown);
	public abstract void setCanMoveUp(boolean canBeMovedUp);
	public abstract void setCanMoveRight(boolean canBeMovedRight);
	public abstract void setCanMoveLeft(boolean canBeMovedLeft);
}
