package fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamestate.GameState;
import gamestate.Player;

public class SurfaceField extends Field
{
	public SurfaceField(Player[] players, int x, int y, int width, int height)
	{
		super(players, x, y, width, height);

	}
	
	@Override
	public void onStep(Player player)
	{
		super.onStep(player);

		System.out.println("You can perform dangerous scavenging(Q)");
	}
	
	@Override
	public void update()
	{
		if (GameState.playerID != GameState.playerTurn || GameState.players[GameState.playerID].rolledDice == false)
		{
			return;
		}
		if (Gdx.input.isKeyPressed(Keys.Q))
		{
			System.out.println("Dangerous Scavenge");
		}
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{

	}
}
