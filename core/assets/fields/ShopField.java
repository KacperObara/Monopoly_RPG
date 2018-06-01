package fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gamestate.GameState;
import gamestate.Item;
import gamestate.Player;

public class ShopField extends Field
{
	public ShopField(Player[] players, int x, int y, int width, int height)
	{
		super(players, x, y, width, height);
		items = new Item[4];
	}
	
	
	Item[] items;
	
	@Override
	public void onStep(Player player)
	{
		super.onStep(player);

		items[0] = new Item(0);
		items[1] = new Item(1);
		items[2] = new Item(2);
		items[3] = new Item(3);
		
		System.out.println("You can view stock with(Q)");
	}
	
	
	boolean tmp = false;
	boolean tmp2 = false;
	boolean tmp3 = false;
	int shopItemID;
	@Override
	public void update()
	{
		if (GameState.playerID != GameState.playerTurn)
		{
			return;
		}
		if (Gdx.input.isKeyPressed(Keys.Q))
		{
			tmp = true;
			System.out.println("(1)" + items[0].name + " " + items[0].description);
			System.out.println("(2)" + items[1].name + " " + items[1].description);
			System.out.println("(3)" + items[2].name + " " + items[2].description);
			System.out.println("(4)" + items[3].name + " " + items[3].description);
		}
		
		if (tmp2 == false && tmp == true)
		{
			if (Gdx.input.isKeyPressed(Keys.NUM_1))
			{
				if (players[GameState.playerID].getMGR() >= items[0].cost)
				{
					tmp2 = true;
					shopItemID = 0;
					players[GameState.playerID].setMGR(players[GameState.playerID].getMGR() - items[0].cost);// -= items[0].cost;
				}
			}
			else if (Gdx.input.isKeyPressed(Keys.NUM_2))
			{
				if (players[GameState.playerID].getMGR() >= items[1].cost)
				{
					tmp2 = true;
					shopItemID = 1;
					players[GameState.playerID].setMGR(players[GameState.playerID].getMGR() - items[1].cost);// -= items[1].cost;
				}
			}
			else if (Gdx.input.isKeyPressed(Keys.NUM_3))
			{
				if (players[GameState.playerID].getMGR() >= items[2].cost)
				{
					tmp2 = true;
					shopItemID = 2;
					players[GameState.playerID].setMGR(players[GameState.playerID].getMGR() - items[2].cost);// -= items[2].cost;
				}
			}
			else if (Gdx.input.isKeyPressed(Keys.NUM_4))
			{
				if (players[GameState.playerID].getMGR() >= items[3].cost)
				{
					tmp2 = true;
					shopItemID = 3;
					players[GameState.playerID].setMGR(players[GameState.playerID].getMGR() - items[3].cost);// -= items[3].cost;
				}
			}
		}
		else if (tmp2 == true)
		{
			if (tmp3 == false)
			{
				System.out.println("Write a number of which item is to be replaced");
				tmp3 = true;
			}
			if (Gdx.input.isKeyJustPressed(Keys.NUM_1))
			{
				Item.addItem(players[GameState.playerID], items[shopItemID], 0);
			}
			else if (Gdx.input.isKeyJustPressed(Keys.NUM_2))
			{
				Item.addItem(players[GameState.playerID], items[shopItemID], 1);
			}
			else if (Gdx.input.isKeyJustPressed(Keys.NUM_3))
			{
				Item.addItem(players[GameState.playerID], items[shopItemID], 2);
			}
			else if (Gdx.input.isKeyJustPressed(Keys.NUM_4))
			{
				Item.addItem(players[GameState.playerID], items[shopItemID], 3);
			}
		}
	}
	
	@Override
	public void onTurnStart()
	{
		tmp = false;
		tmp2 = false;
		tmp3 = false;
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{

	}
}
