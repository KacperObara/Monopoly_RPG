package basic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** Beginning of everything, like main. render is called every frame, and I 
 * 	utilized this class to be sort of GameEngine in State design pattern */
public class MonopolyRPG extends ApplicationAdapter {

	private SpriteBatch batch;
	
	State activeState;
	State nextState;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		
		activeState = new MainMenuState();
		activeState.initialize();
		
		nextState = activeState.checkState();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (activeState.checkState() != nextState)
		{
			activeState = activeState.checkState();
			nextState = activeState.checkState();
			activeState.initialize();
		}
		
		activeState.update();
		
		batch.begin();
		
		activeState.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		activeState.cleanUp();
	}
	
	@Override
	public void resize(int newWidth, int newHeight)
	{
		activeState.onResize(newWidth, newHeight);
	}
}
