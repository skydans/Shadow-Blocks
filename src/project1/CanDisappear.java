package project1;

import org.newdawn.slick.SlickException;

public interface CanDisappear{
	public abstract void disappearIfNeeded() throws SlickException;
	public abstract boolean surroundingCheck();
}
