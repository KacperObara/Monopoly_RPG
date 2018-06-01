package gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.monopolyrpg.desktop.DesktopLauncher;

import basic.State;
import fields.Field;
import fields.FreeDiceRollField;
import fields.ShopField;
import fields.SmallBonusField;
import fields.StationField;
import fields.SurfaceField;
import multiplayer.ClientMultiplayer;
import multiplayer.ClientWaitingState;

public class GameState extends State implements Runnable
{
	public static ClientMultiplayer multiplayer;
	
	public GUI gui;
	
	//Sprite boardSprite;
	public static Player players[];
	public static Field fields[];
	
	int windowWidth;
	int windowHeight;
	
	Dice dice;
	
	public static int connectedPlayers = 0;
	
	/** Player with playerID == playerTurn is active */
	public static int playerTurn = 0;
	public static int playerID = 0;
	
	public static void nextTurn() 
	{
		++playerTurn;
		if (playerTurn > connectedPlayers - 1)
		{
			playerTurn = 0;
		}
		
		if (playerTurn == playerID)
		{
			onPlayerTurnStart();
		}
	}
	
	public static void onPlayerTurnStart() // when this player is active
	{
		players[playerID].rolledDice = false;
		for (int i = 0; i < fields.length; ++i)
		{
			fields[i].onTurnStart();
		}
		
		
		int consumedFood = 0;
		for (int i = 0; i < players[playerID].ownedStations.size(); ++i)
		{
			consumedFood += players[playerID].ownedStations.get(i).defenseLevel;
		}
		players[playerID].setFood(players[playerID].getFood() - consumedFood);
		
		int gainedMGR = 0;
		for (int i = 0; i < players[playerID].ownedStations.size(); ++i)
		{
			gainedMGR += players[playerID].ownedStations.get(i).defenseLevel;
		}
		players[playerID].setMGR(players[playerID].getMGR() + gainedMGR);
		
	}

	public GameState(int ID)
	{
		playerID = ID;
		connectedPlayers = ClientWaitingState.connectedPlayers;
		dice = new Dice();
		
		System.out.println(connectedPlayers);
	}
	
	@Override
	public void initialize() {
		windowWidth = (int) com.monopolyrpg.desktop.DesktopLauncher.originalWidth;
		windowHeight = (int) com.monopolyrpg.desktop.DesktopLauncher.originalHeight;
		
		TextureManager.addTexture("GameBoard.png", "BoardTexture");
		SpriteManager.addSprite("BoardTexture", "BoardSprite");
		
		TextureManager.addTexture("GUI_MESSAGES.png", "GUI");
		SpriteManager.addSprite("GUI", "GUISprite");
		SpriteManager.setPosition(SpriteManager.getSprite("GUISprite"), 910, 0);
		
		multiplayer = ClientWaitingState.multiplayer;
		
		TextureManager.addTexture("Pieces.png", "Pieces");
		SpriteManager.addSprite("Pieces", "Piece0", 0, 0, 20, 20);
		SpriteManager.addSprite("Pieces", "Piece1", 20, 0, 20, 20);
		SpriteManager.addSprite("Pieces", "Piece2", 0, 20, 20, 20);
		SpriteManager.addSprite("Pieces", "Piece3", 20, 20, 20, 20);
		
		players = new Player[connectedPlayers];
		
		players[0] = new Player(0);
		players[1] = new Player(1);
		if (connectedPlayers >= 3) 
		{
			players[2] = new Player(2);
		}
		if (connectedPlayers == 4) 
		{
			players[3] = new Player(3);
		}
		
		//initializing fields
		fields = new Field[40];

		fields[0] = new ShopField(players, 770, 770, 140, 140); // beginning big field
		fields[1] = new StationField(players, 770, 700, 140, 70);
		fields[2] = new FreeDiceRollField(players, 770, 630, 140, 70);
		fields[3] = new StationField(players, 770, 560, 140, 70);
		fields[4] = new SmallBonusField(players, 770, 490, 140, 70);
		fields[5] = new SurfaceField(players, 770, 420, 140, 70);
		fields[6] = new StationField(players, 770, 350, 140, 70);
		fields[7] = new Field(players, 770, 280, 140, 70);
		fields[8] = new StationField(players, 770, 210, 140, 70);
		fields[9] = new StationField(players, 770, 140, 140, 70);
		fields[10] = new Field(players, 770, 0, 140, 140); // big field
		
		fields[11] = new StationField(players, 700, 0, 70, 140);
		fields[12] = new Field(players, 630, 0, 70, 140);
		fields[13] = new StationField(players, 560, 0, 70, 140);
		fields[14] = new StationField(players, 490, 0, 70, 140);
		fields[15] = new SurfaceField(players, 420, 0, 70, 140);
		fields[16] = new StationField(players, 350, 0, 70, 140);
		fields[17] = new Field(players, 280, 0, 70, 140);
		fields[18] = new StationField(players, 210, 0, 70, 140);
		fields[19] = new StationField(players, 140, 0, 70, 140);
		fields[20] = new ShopField(players, 0, 0, 140, 140);  // big field
		
		fields[21] = new StationField(players, 0, 140, 140, 70);
		fields[22] = new Field(players, 0, 210, 140, 70);
		fields[23] = new StationField(players, 0, 280, 140, 70);
		fields[24] = new StationField(players, 0, 350, 140, 70);
		fields[25] = new SurfaceField(players, 0, 420, 140, 70);
		fields[26] = new StationField(players, 0, 490, 140, 70);
		fields[27] = new StationField(players, 0, 560, 140, 70);
		fields[28] = new Field(players, 0, 630, 140, 70);
		fields[29] = new StationField(players, 0, 700, 140, 70);
		fields[30] = new Field(players, 0, 770, 140, 140); // big field
		
		fields[31] = new StationField(players, 140, 770, 70, 140);
		fields[32] = new StationField(players, 210, 770, 70, 140);
		fields[33] = new Field(players, 280, 770, 70, 140);
		fields[34] = new StationField(players, 350, 770, 70, 140);
		fields[35] = new SurfaceField(players, 420, 770, 70, 140);
		fields[36] = new Field(players, 490, 770, 70, 140);
		fields[37] = new StationField(players, 560, 770, 70, 140);
		fields[38] = new Field(players, 630, 770, 70, 140);
		fields[39] = new StationField(players, 700, 770, 70, 140);
		
		for (int i = 0; i < connectedPlayers; ++i)
		{
			fields[players[i].fieldId].onStep(players[i]);
		}
		
		gui = new GUI();
		
		Thread t = new Thread (this, "Thread2");
		t.start();
	}

	@Override
	public void update() {
		if (playerTurn == playerID)
		{
			if (Gdx.input.isKeyJustPressed(Keys.SPACE) && players[playerID].rolledDice == false)
			{
				players[playerID].rolledDice = true;
				fields[players[playerID].fieldId].onLeave();
					
				players[playerID].fieldId += dice.rollDice();
				
				while (players[playerID].fieldId > 39)
				{
					players[playerID].fieldId -= 40;
				}
				
				fields[players[playerID].fieldId].onStep(players[playerID]);
				Packet packet = new Packet();
				packet.loadPacket();
				Packet.sendPacket(packet);
			}
			if (Gdx.input.isKeyJustPressed(Keys.Z) && players[playerID].rolledDice == true)
			{
				nextTurn();
				Packet packet = new Packet();
				packet.loadEndingTurnPacket();
				Packet.sendPacket(packet);
			}
		}

		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
		{
			checkMouseClickPosition();
		}
		
		fields[players[playerID].fieldId].update();
		gui.update();
		
		debug();
	}

	public void checkMouseClickPosition()
	{
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		int x = (mouseX * 1285) / windowWidth;
		int y = 910 - (mouseY * 910 / windowHeight); // note, that libgdx and mouse uses different coordinates and aren't adjusted automatically
		
		for (int i = 0; i < fields.length; ++i)
		{
			if (x >= fields[i].x && x <= fields[i].x + fields[i].width && y >= fields[i].y && y <= fields[i].y + fields[i].height)
			{
				fields[i].onClick();
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		SpriteManager.getSprite("BoardSprite").draw(batch);
		SpriteManager.getSprite("GUISprite").draw(batch);
		
		players[0].sprite.draw(batch);
		
		batch.draw(players[0].sprite, players[0].sprite.getX(), players[0].sprite.getY());
		batch.draw(players[1].sprite, players[1].sprite.getX(), players[1].sprite.getY());
		if (connectedPlayers >= 3)
		{
			batch.draw(players[2].sprite, players[2].sprite.getX(), players[2].sprite.getY());
		}
		if (connectedPlayers == 4)
		{
			batch.draw(players[3].sprite, players[3].sprite.getX(), players[3].sprite.getY());
		}
		
		fields[players[playerID].fieldId].draw(batch);
		gui.draw(batch);
	}

	@Override
	public void cleanUp() {
		
	}
	
	/** this method is called every time, window is resized */
	@Override
	public void onResize(int newWidth, int newHeight) 
	{
		System.out.println("Window was resized");
		windowHeight = newHeight;
		windowWidth = newWidth;
	}
	
	/**	class have interface Runnable and this method run, because
	 *	Thread requires both, and run is the only method used by thread */
	public void run()
	{
		Packet.receivePacket();
		run();
	}
	
	
	public void debug()
	{
		if (Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT))
		{
			System.out.println("MGR: " + players[playerID].getMGR() + " Strength: " + players[playerID].strength + 
							  " Charisma: " + players[playerID].charisma + " Scavenging: " + players[playerID].scavenging +
							  " Scrap " + players[playerID].scrap + " Food " + players[playerID].food);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT))
		{
			System.out.println("Owned Stations:");
			for (int i = 0; i < GameState.fields.length; ++i)
			{
				if (GameState.fields[i] instanceof StationField)
				{
					StationField field = (StationField) GameState.fields[i];
					if (field.ownerID == playerID)
					{
						System.out.print(field.ID + " ");
					}
				}
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.TAB))
		{
			System.out.println("Your ID: " + playerID + " and turn: " + playerTurn);
		}
		if (Gdx.input.isKeyJustPressed(Keys.A))
		{
			System.out.println("Your items: " + players[playerID].items[0].name + " " + players[playerID].items[1].name + " " + players[playerID].items[2].name + " " + players[playerID].items[3].name);
		}
		
		for (int i = 0; i < players.length; ++i)
		{
			if (playerTurn == playerID && i != playerID && players[i].fieldId == players[playerID].fieldId)
			{
				//System.out.println("Attack another player? not implemented");
			}
		}
	}
}
