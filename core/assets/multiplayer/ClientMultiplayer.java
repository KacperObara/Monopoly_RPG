package multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMultiplayer
{
	public String ip = "localhost";
	public int port = 22222;
	
	public Socket socket;
	
	/** send and receive Packet class objects through these */
	public ObjectOutputStream outputStream; 
	public ObjectInputStream inputStream;
	
	public OutputStream connectedOutputStream; 
	public InputStream connectedInputStream;
	
	public boolean connect()
	{
		try {
			socket = new Socket(ip, port);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			connectedOutputStream = new DataOutputStream(socket.getOutputStream());
			connectedInputStream = new DataInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println("Unable to connect to the adress");
			return false;
		}
		System.out.println("Successfully connected to the server");
		return true;
	}
}
