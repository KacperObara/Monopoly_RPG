package gamestate;

import java.util.Random;

public class Dice
{
	Random random;
	
	public Dice()
	{
		random = new Random(System.currentTimeMillis() + GameState.playerID); // pass different seed for every player
	}
	
	public int rollDice()
	{
		return random.nextInt(5) + 1;
	}
}
