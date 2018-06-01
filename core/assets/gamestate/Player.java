package gamestate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fields.StationField;

public class Player{
	
	public Sprite sprite;
	
	/**	keeps check, on which field is the player
	 *  this is important, because field sets player position
	 *  with OnStep method */
	public int fieldId = 0;
	
	private int MGR = 20; // money/Military Grade Round
	public int scrap = 0;
	public int food = 0;
	
	public int strength = 0; // better in fights
	public int charisma = 0; // more people in metro stations
	public int scavenging = 0; // more scavenging, then better chance of avoiding fight
	
	public Item[] items; // 4 slots for items
	
	public boolean rolledDice = false;
	
	public List <StationField> ownedStations;
	
	Random random;
	
	public Player(int ID)
	{
		random = new Random(System.currentTimeMillis() + GameState.playerID);
		//this.sprite = sprite;
		this.sprite = SpriteManager.getSprite("Piece" + ID);
		
		items = new Item[4];
		for (int i = 0; i < 4; ++i)
		{
			items[i] = new Item(0);
		}
		
		ownedStations = new ArrayList<StationField>();
	}
	
	public static void reloadSkills(Player player)
	{
		player.strength = 0;
		player.charisma = 0;
		player.scavenging = 0;
		for (int i = 0; i < player.items.length; ++i)
		{
			player.strength += player.items[i].strength;
			player.charisma += player.items[i].charisma;
			player.scavenging += player.items[i].scavenging;
		}
	}
	
	public void onDeath()
	{
		fieldId = 0;
		GameState.fields[0].onStep(this);
		Packet packet = new Packet();
		packet.loadPacket();
		Packet.sendPacket(packet);

		//GameState.getInstance().fields[0].OnStep(this);
		System.out.println("You Died!");
	}
	
	public void countOwnedStations()
	{
		ownedStations.clear();
		for (int i = 0; i < GameState.fields.length; ++i)
		{
			if (GameState.fields[i] instanceof StationField)
			{
				StationField field = (StationField) GameState.fields[i];
				if (field.ownerID == GameState.playerID)
				{
					ownedStations.add(field);
				}
			}
		}
	}
	
	public int getFood()
	{
		return food;
	}
	public void setFood(int food)
	{
		if (food < 0)
		{
			System.out.println("Nie masz jedzenia!");
			food = 0;
			
			if (ownedStations.isEmpty() == false) // revolt at random station
			{
				int stationNumber = random.nextInt(ownedStations.size());
				System.out.println("Station " + stationNumber + " has revolted!");
				ownedStations.remove(stationNumber);
			}
		}
	}
	
	public int getMGR()
	{
		return MGR;
	}
	public void setMGR(int MGR)
	{
		if (MGR < 0)
		{
			System.out.println("Blad, MGR < 0!");
			MGR = 0;
		}
	}
}
