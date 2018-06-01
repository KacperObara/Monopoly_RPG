package fields;

import gamestate.Player;

public class FreeDiceRollField extends Field
{

	public FreeDiceRollField(Player[] players, int x, int y, int width, int height)
	{
		super(players, x, y, width, height);
	}

	@Override
	public void onStep(Player player)
	{
		super.onStep(player);
		
		player.rolledDice = false;
	}
}
