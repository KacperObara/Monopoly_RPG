package multiplayer;

import java.io.IOException;
import basic.State;
import gamestate.Packet;

public class ServerGameState extends State
{
	Thread[] threads;
	ServerMultiplayer multiplayer;
	int connectedPlayers = 0;
	
	Packet[] packets;
	public boolean[] receivedPacket;
	
	@Override
	public void initialize()
	{
		connectedPlayers = ServerWaitingState.connectedPlayers;
		multiplayer = ServerWaitingState.multiplayer;
		
		receivedPacket = new boolean[4];
		packets = new Packet[4];
		
		threads = new Thread[connectedPlayers];
		for (int i = 0; i < connectedPlayers; ++i)
		{
			startReceivingData(i);
		}
	}
	
	@Override
	public void update()
	{
		for (int i = 0; i < receivedPacket.length; ++i)
		{
			if (receivedPacket[i] == true)
			{
				sendDataToAll(packets[i]);
				receivedPacket[i] = false;
			}
		}
	}

	public void sendDataToAll(Packet packet)
	{
		for (int i = 0; i < connectedPlayers; ++i)
		{
			if (packet.playerID != i)
			{
				try
				{
					multiplayer.outputStream[i].writeObject(packet);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void startReceivingData(int ID)
	{
		 class OneShotTask implements Runnable {
		     int ID;
		     OneShotTask(int ID) { this.ID = ID; }
		     public void run()
		     {
		    	try
				{
					packets[ID] = (Packet)multiplayer.inputStream[ID].readObject();
					receivedPacket[ID] = true;
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		    	
		    	run();
		     }
		 }
		 Thread t = new Thread(new OneShotTask(ID));
		 t.start();
	}
}
