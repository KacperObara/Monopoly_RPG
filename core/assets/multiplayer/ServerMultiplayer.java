package multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiplayer
{
	
	public String ip = "localhost";
	public int port = 22222;
	
	public Socket[] socket;
	
	/** send and receive Packet class objects through these */
	public ObjectOutputStream[] outputStream; 
	public ObjectInputStream[] inputStream;
	
	public OutputStream[] connectedOutputStream; 
	public InputStream[] connectedInputStream;
	
	public ServerSocket serverSocket;
	
	public ServerMultiplayer()
	{
		socket = new Socket[3];
		outputStream = new ObjectOutputStream[4];
		inputStream = new ObjectInputStream[4];
		
		connectedInputStream = new DataInputStream[4];
		connectedOutputStream = new DataOutputStream[4];
	}
	
	//public Scanner scanner;
	
	public void initialize()
	{
		// not used for now
	}

	
	/** Host uses this, to check, if client executed Connect method 
	 * 	This must be done with a thread, because serverSocket.accept() stops the program
	 * 	until it finds client */
	public void listenForServerRequest(int i)
	{
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			outputStream[i] = new ObjectOutputStream(socket.getOutputStream());
			inputStream[i] = new ObjectInputStream(socket.getInputStream());
			
			connectedOutputStream[i] = new DataOutputStream(socket.getOutputStream());
			connectedInputStream[i] = new DataInputStream(socket.getInputStream());
			
			System.out.println("Client found, joining");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**	One time method executed by server */
	public void initializeServer()
	{
		try {
			System.out.println(port + " " + ip);
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
