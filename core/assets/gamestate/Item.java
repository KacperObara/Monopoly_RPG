package gamestate;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Item
{
	public int ID = -1;
	public int cost = 0;
	public int strength = 0;
	public int charisma = 0;
	public int scavenging = 0;
	public String name = "";
	public String description = "";
	
	public Sprite sprite;
	
	public Item(int ID)
	{
		this.ID = ID;
		loadItem();
	}
	
	public static void addItem(Player player, Item item, int replaceID)
	{
		player.items[replaceID] = item;
		Packet packet = new Packet();
		packet.loadPacket();
		Packet.sendPacket(packet);
		Player.reloadSkills(player);
//		for (int i = 0; i < player.items.length; ++i)
//		{
//			if(player.items[i] == null)
//			{
//				player.items[i] = item;
//			}
//		}
	}
	
	public void loadItem()
	{
		switch(ID)
		{
			case 0:
			{
				name = "Nothing";
				strength = 0;
				charisma = 0;
				scavenging = 0;
				cost = 0;
				break;
			}
			case 1:
			{
				name = "Universal Charger";
				description = "(+1 Scavenging)(-2 MGR)";
				strength = 0;
				charisma = 0;
				scavenging = 1;
				cost = 2;
				break;
			}
			case 2:
			{
				name = "Fragmentation Grenade";
				description = "(+1 Strength)(-3 MGR)";
				strength = 1;
				charisma = 0;
				scavenging = 0;
				cost = 3;
				break;
			}
			case 3:
			{
				name = "AK-74M Kalash";
				description = "(+2 Strength)(-5 MGR)";
				strength = 2;
				charisma = 0;
				scavenging = 0;
				cost = 5;
				break;
			}
			case 4:
			{
				name = "Small Medkit";
				description = "(+1 Charisma)(-2 MGR)";
				strength = 0;
				charisma = 1;
				scavenging = 0;
				cost = 2;
				break;
			}
		}
	}
}
