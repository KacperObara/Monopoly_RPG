package basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** States are basically separated loops, that can be jumped to with ChangeState
 * 	Note, that only one state can be loaded at a time and if done correctly, previous State
 * 	should be deleted from memory. */
public class State 
{
	State state;
	public void initialize() {}
	public void update() {}
	public void draw(SpriteBatch batch) {}
	
	public State checkState()
 	{
 		return state;
 	}
	public void changeState(State newState) 
	{
		state = newState;
	}
	public void cleanUp() {}
	
	public void onResize(int newWidth, int newHeight) {}
}
