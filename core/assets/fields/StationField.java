package fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gamestate.Enemy;
import gamestate.Fight;
import gamestate.GameState;
import gamestate.Packet;
import gamestate.Player;

public class StationField extends Field
{
	public int ownerID = -1;
	public int defenseLevel = 0;
	//public boolean upgraded = false;
	public boolean usedAction = false;
	
	public StationField(Player[] players, int x, int y, int width, int height)
	{
		super(players, x, y, width, height);
	}
	
	@Override
	public void onStep(Player player)
	{
		super.onStep(player);

		System.out.print("You can Scavenge(Q) ");
		if (GameState.playerID != ownerID)
		{
			System.out.println("or Take Over(W)");
		}
		else
		{
			System.out.println("or Upgrade Station(W)");
		}
	}
	
	@Override
	public void update()
	{
		if (GameState.playerID != GameState.playerTurn || GameState.players[GameState.playerID].rolledDice == false)
		{
			return;
		}

		if (usedAction == false)
		{
			if (Gdx.input.isKeyJustPressed(Keys.Q))
			{
				if (ownerID != -1)
				{
					this.onScavenge(players[GameState.playerID], 0);
				}
				else
				{
					int chanceToFight = 50;
					this.onScavenge(players[GameState.playerID], chanceToFight);
				}
				usedAction = true;
			} 
			else if (Gdx.input.isKeyJustPressed(Keys.W))
			{
				if (GameState.playerID != ownerID)
				{
					System.out.println("Taking Over");
					this.onFight(players[GameState.playerID]);
					
					
					if (players[GameState.playerID].fieldId != 0) // if 0 then died
					{
						ownerID = GameState.playerID;
						
						Packet packet = new Packet();
						packet.loadPacket();
						Packet.sendPacket(packet);
					}
					usedAction = true;
				} 
				else if (usedAction == false)
				{
					System.out.println("Upgrading");
					++defenseLevel;
					usedAction = true;
				}
			}
		}
	}
	
	@Override
	public void onTurnStart()
	{
		usedAction = false;
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{

	}

	@Override
	public void onClick()
	{
		System.out.println("Field ID: " + ID + " OwnerID: " + ownerID + " Defense level: " + defenseLevel );
	}
	
	public void onScavenge(Player player, int chanceToFight)
	{
		System.out.println("Scavenging");
		if (chanceToFight > random.nextInt(100)+1)
		{
			onFight(player);
		}
		if (players[GameState.playerID].fieldId != 0) // haven't died
		{
			if (ID < 10)
			{
				int luck = (player.scavenging * 2); 
				
				player.scrap += random.nextInt(5)+1 * luck + random.nextInt(7) - 3;
				player.setFood(player.getFood() + random.nextInt(3)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(3)+1 * luck + random.nextInt(7) - 3;
				player.setMGR(player.getMGR() + random.nextInt(2)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(2)+1 * luck + random.nextInt(7) - 3;
			}
			else if (ID < 20)
			{
				int luck = (player.scavenging * 2); 
				
				player.scrap += random.nextInt(8)+1 * luck + random.nextInt(7) - 3;
				player.setFood(player.getFood() + random.nextInt(7)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(7)+1 * luck + random.nextInt(7) - 3;
				player.setMGR(player.getMGR() + random.nextInt(7)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(7)+1 * luck + random.nextInt(7) - 3;
			}
			else if (ID < 30)
			{
				int luck = (player.scavenging * 3); 
				
				player.scrap += random.nextInt(8)+1 * luck + random.nextInt(7) - 3;
				player.setFood(player.getFood() + random.nextInt(7)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(7)+1 * luck + random.nextInt(7) - 3;
				player.setMGR(player.getMGR() + random.nextInt(7)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(7)+1 * luck + random.nextInt(7) - 3;
			}
			else if (ID < 40)
			{
				int luck = (player.scavenging * 3); 
				
				player.scrap += random.nextInt(16)+1 * luck + random.nextInt(7) - 3;
				player.setFood(player.getFood() + random.nextInt(15)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(15)+1 * luck + random.nextInt(7) - 3;
				player.setMGR(player.getMGR() + random.nextInt(15)+1 * luck + random.nextInt(7) - 3);// += random.nextInt(15)+1 * luck + random.nextInt(7) - 3;
			}
		}
	}
	
	public void onFight(Player player)
	{
		Enemy enemy;
		if (ownerID != -1) // station is occupied by other player
		{
			if (defenseLevel == 0)
			{
				enemy = new Enemy(6);
				Fight.performFight(player, enemy);
			}
			else if (defenseLevel == 1)
			{
				enemy = new Enemy(7);
				Fight.performFight(player, enemy);
			}
		}
		else	// station is occuped by monsters
		{
			if (ID < 10)
			{
				enemy = new Enemy(random.nextInt(2)+1);
				Fight.performFight(player, enemy);
			}
			else if (ID < 20)
			{
				enemy = new Enemy(random.nextInt(3)+1);
				Fight.performFight(player, enemy);
			}
			else if (ID < 30)
			{
				enemy = new Enemy(random.nextInt(4)+1);
				Fight.performFight(player, enemy);
			}
			else if (ID < 40)
			{
				enemy = new Enemy(random.nextInt(5)+1);
				Fight.performFight(player, enemy);
			}
		}
	}
}

