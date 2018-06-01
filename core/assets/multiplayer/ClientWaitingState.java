package multiplayer;

import java.io.IOException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import basic.State;
import gamestate.GameState;

public class ClientWaitingState extends State
{
	public static ClientMultiplayer multiplayer;
	/** If waiting = false, then host is finished and can start the game */
	boolean waiting = false;
	/** Host will give this player ID */
	int playerID = 0;
	public static int connectedPlayers = 0;
	
	/** Received when Host finishes waiting. Message contains number of connected players.
	 *  Because it uses the same inputStream as message containing playerID, in order to distinguish messages,
	 *  serverMessage is always > 10 */
	int serverMessage = 0;
	
	@Override
	public void initialize()
	{
		multiplayer = new ClientMultiplayer();
		
		System.out.println("Please input server IP:");
		// multiplayer.ip = scanner.nextLine();
		//multiplayer.ip = "localhost"; // do debugowania, usun to i odkomentuj wyzej
		System.out.println("Please input server port:");
		// multiplayer.port = scanner.nextInt();
		//multiplayer.port = 1; // do debugowania, usun to i odkomentuj wyzej
		System.out.println("Connecting...");
		
		if (multiplayer.connect() == true)
		{
			try
			{
				playerID = multiplayer.connectedInputStream.read();
				if (playerID > 10)
				{
					System.out.println("Error in initialize() in clientWaitingState");
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update()
	{
		try
		{
			serverMessage = multiplayer.connectedInputStream.read();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if (serverMessage > 10)
		{
			connectedPlayers = serverMessage - 10;
			waiting = false;
		}
		
		if (waiting == false)
		{
			changeState(new GameState(playerID));
		}
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{

	}
}
