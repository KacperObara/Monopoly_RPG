package gamestate;

import java.util.Random;

public final class Fight
{
	static Random random;
	
	private Fight() {}
	
	public static void performFight(Player player, Enemy enemy)
	{
		System.out.println("Fighting with: " + enemy.name);
		random = new Random();
		
		int luck = random.nextInt(7) - 3; // random value between -3 to 3
		System.out.println("Strength comparison: " + (player.strength + luck) + " " + enemy.strength);
		if (player.strength + luck >= enemy.strength) // player wins some food and possibly MGR
		{
			if (enemy.max_MGR > 0)
			{
				player.setMGR(player.getMGR() + random.nextInt(enemy.max_MGR) + enemy.min_MGR);//+= random.nextInt(enemy.max_MGR) + enemy.min_MGR;
			}
			player.setFood(player.getFood() + enemy.food);// += enemy.food;
			
			System.out.println("You Won with " + enemy.name + "!");
		}
		else
		{
			player.onDeath();
		}
	}
}
