package multiplayer;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import basic.State;

public class ServerWaitingState extends State implements Runnable
{
	public static ServerMultiplayer multiplayer;
	boolean waiting = false;
	int playerID = 0;
	public static int connectedPlayers = 0;

	@Override
	public void initialize()
	{
		//Scanner scanner = new Scanner(System.in);

		multiplayer = new ServerMultiplayer();
			
		System.out.println("Please input server port:");
		//multiplayer.port = scanner.nextInt();
		//multiplayer.port = 1;
		
		//multiplayer.port = 1; // do debugowania, usun to i odkomentuj wyzej
		multiplayer.initializeServer();
			
		Thread t = new Thread (this, "Thread1");
		t.start();
			
		waiting = true;
	}
	
	@Override
	public void update()
	{
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && connectedPlayers >= 2 || connectedPlayers == 4)
		{
			waiting = false;
			for (int i = 0; i < connectedPlayers; ++i) // sending -1 means client should stop waiting
			{
				try
				{
					multiplayer.connectedOutputStream[i].write(connectedPlayers + 10);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (waiting == false)
		{
			changeState(new ServerGameState());
		}
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{

	}
	
	public void run()
	{
		multiplayer.listenForServerRequest(connectedPlayers);
		try
		{
			multiplayer.connectedOutputStream[connectedPlayers].write(connectedPlayers);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		++connectedPlayers;
		run();
	}

}
