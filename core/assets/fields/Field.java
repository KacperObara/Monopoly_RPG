package fields;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamestate.GameState;
import gamestate.Player;
import gamestate.SpriteManager;

public class Field {
	public static int IDCounter = 0;
	public int ID;
	public int x;
	public int y;
	public int width;
	public int height;
	
	Random random;
	
	Player[] players;
	//public List<Integer> playersID;
	
	public Field(Player[] players, int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		setScaledPosition();
		this.width = width;
		this.height = height;
		ID = IDCounter;
		++IDCounter;

		this.players = players;
		random = new Random();
		//playersID = new ArrayList<Integer>();
	}

	public void onLeave()
	{
		
	}
	
	public void onTurnStart()
	{
		
	}
	
	public void onStep(Player player)
	{
		ArrayList<Player> playersStanding = new ArrayList <Player>();
		for (int i = 0; i < players.length; ++i)
		{
			if (players[i].fieldId == ID)
			{
				playersStanding.add(players[i]);
			}
		}
		
		
		playersStanding.get(0).sprite.setPosition(x + (width* SpriteManager.scale.x/2) - ((playersStanding.get(0).sprite.getWidth() * SpriteManager.scale.x)/2), y + (height* SpriteManager.scale.y/2) - ((playersStanding.get(0).sprite.getHeight() * SpriteManager.scale.y)/2));
		if (playersStanding.size() >= 2)
		{
			playersStanding.get(1).sprite.setPosition(x + (width * SpriteManager.scale.x/2) - ((playersStanding.get(1).sprite.getWidth() * SpriteManager.scale.x)/2), y + (height * SpriteManager.scale.y/2));
		}
		if (playersStanding.size() >= 3)
		{
			playersStanding.get(2).sprite.setPosition(x + (width * SpriteManager.scale.x/2), y + (height * SpriteManager.scale.y/2) - ((playersStanding.get(2).sprite.getHeight() * SpriteManager.scale.y)/2));
		}
		if (playersStanding.size() == 4)
		{
			playersStanding.get(3).sprite.setPosition(x + (width * SpriteManager.scale.x/2), y + (height * SpriteManager.scale.y/2));
		}
		
		System.out.println("WTF");
	}
	
	public void update()
	{
		if (GameState.playerID != GameState.playerTurn || GameState.players[GameState.playerID].rolledDice == false)
		{
			return;
		}
	}
	
	public void draw(SpriteBatch batch)
	{
		
	}
	
	public void setScaledPosition()
	{
		this.x = (int) (x * SpriteManager.scale.x);
		this.y = (int) (y * SpriteManager.scale.y);
		this.width = (int) (width * SpriteManager.scale.x);
		this.height = (int) (height * SpriteManager.scale.y);
	}
	
	public void onClick()
	{
		
	}
}
