package gamestate;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import fields.StationField;

public class Packet implements Serializable{

	private static final long serialVersionUID = 1L;

	public int playerID;
	public int fieldId;
	public boolean endTurn = false;

	public int MGR;
	public int strength;
	public int charisma;
	public int scavenging;
	
	public int scrap;
	public int food;
	
	//public ArrayList<Integer> ownedStationsID;
	public int cardID = -1; // >0 if picked up a card
	public int upgradedStationID = -1; // >0 if upgraded a station. For now once a turn
	
	public HashMap<Integer, Integer> ownedStations;
	
	/** Applies taking Over, move, gain/lose resources/skills packets*/
	public void loadPacket()
	{
		ownedStations = new HashMap<Integer, Integer>();
		for (int i = 0; i < GameState.fields.length; ++i)
		{
			if (GameState.fields[i] instanceof StationField)
			{
				StationField field = (StationField) GameState.fields[i];
				ownedStations.put(field.ID, field.ownerID);
			}
		}
//		this.ownedStationsID = new ArrayList<Integer>();
//		for (int i = 0; i < GameState.players[playerID].ownedStations.size(); ++i)
//		{
//			this.ownedStationsID.add(GameState.players[playerID].ownedStations.get(i).ID);
//		}
		
		this.playerID = GameState.playerID;
		this.fieldId = GameState.players[playerID].fieldId;
		
		this.MGR = GameState.players[playerID].getMGR();
		this.strength = GameState.players[playerID].strength;
		this.charisma = GameState.players[playerID].charisma;
		this.scavenging = GameState.players[playerID].scavenging;
		this.scrap = GameState.players[playerID].scrap;
		this.food = GameState.players[playerID].getFood();
	}
	
	public void loadEndingTurnPacket()
	{
		loadPacket();
		this.endTurn = true;
	}
	
	public void loadUpgradingStationPacket(int stationID)
	{
		this.upgradedStationID = stationID;
	}
	
	public void loadDrawingCardPacket(int cardID)
	{
		this.cardID = cardID;
	}
	
	public void unPackPacket()
	{
		GameState.players[playerID].fieldId = this.fieldId;
		
		GameState.players[playerID].setMGR(this.MGR);// = this.MGR;
		GameState.players[playerID].strength = this.strength;
		GameState.players[playerID].charisma = this.charisma;
		GameState.players[playerID].scavenging = this.scavenging;
		GameState.players[playerID].scrap = this.scrap;
		GameState.players[playerID].setFood(this.food);// = this.food;
		
//		for (int i = 0; i < 4; ++i)
//		{
//			GameState.players[playerID].ownedStations.clear();
//		}
		for (int i = 0; i < GameState.fields.length; ++i)
		{
			if (this.ownedStations.containsKey(i))
			{
				
				StationField field = (StationField) GameState.fields[i];
				field.ownerID = this.ownedStations.get(i);
			}
		}
		
		for (int i = 0; i < GameState.players.length; ++i)
		{
			GameState.players[i].countOwnedStations();
		}
		/*
		GameState.players[playerID].ownedStations.clear();
		for (int i = 0; i < this.ownedStationsID.size(); ++i)	// Updating which player has which field (could be done better...)
		{
			if (GameState.players[0].ownedStations.contains(GameState.fields[ownedStationsID.get(i)]))
			{
				GameState.players[0].ownedStations.remove(GameState.fields[ownedStationsID.get(i)]);
			}
			if (GameState.players[1].ownedStations.contains(GameState.fields[ownedStationsID.get(i)]))
			{
				GameState.players[1].ownedStations.remove(GameState.fields[ownedStationsID.get(i)]);
			}
			if (GameState.connectedPlayers >= 3)
			{
				if (GameState.players[2].ownedStations.contains(GameState.fields[ownedStationsID.get(i)]))
				{
					GameState.players[2].ownedStations.remove(GameState.fields[ownedStationsID.get(i)]);
				}
			}
			if (GameState.connectedPlayers == 4)
			{
				if (GameState.players[3].ownedStations.contains(GameState.fields[ownedStationsID.get(i)]))
				{
					GameState.players[3].ownedStations.remove(GameState.fields[ownedStationsID.get(i)]);
				}
			}
			
			GameState.players[playerID].ownedStations.add(GameState.fields[ownedStationsID.get(i)]);
		}*/
	
		// here cardID
		// here upgradedStationID
	}
	
	public static void sendPacket(Packet packet)
	{
		try {
			GameState.multiplayer.outputStream.writeObject(packet);
			GameState.multiplayer.outputStream.flush();
			System.out.println("DATA WAS SENT");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void receivePacket()
	{
		Packet packet = new Packet();
		try {
			packet = (Packet) GameState.multiplayer.inputStream.readObject();
			packet.unPackPacket();
			GameState.fields[GameState.players[packet.playerID].fieldId].onStep(GameState.players[packet.playerID]);
			if (packet.endTurn == true)
			{
				GameState.nextTurn();
			}

			System.out.println("DATA RECEIVED");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
    		e.printStackTrace();
		}
	}
}
