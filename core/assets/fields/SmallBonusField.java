package fields;

import gamestate.Player;

public class SmallBonusField extends Field
{

	public SmallBonusField(Player[] players, int x, int y, int width, int height)
	{
		super(players, x, y, width, height);

	}
	
	@Override
	public void onStep(Player player)
	{
		super.onStep(player);
		
		player.setMGR(player.getMGR() + 2);// += 2;
	}

}
