package gamestate;

public class Enemy
{
	public int ID = 0;
	public int strength = 0;
	public String name = "";
	
	public int food = 0;
	public int min_MGR = 0;
	public int max_MGR = 0;
	
	public Enemy(int ID)
	{
		this.ID = ID;
		loadEnemy();
	}
	
	public void loadEnemy()
	{
		switch (ID)
		{
			case 0:
			{
				name = "";
				strength = 0;
				food = 0;
				min_MGR = 0;
				max_MGR = 0;
				break;
			}
			case 1:
			{
				name = "Lurker";
				strength = 1;
				food = 2;
				min_MGR = 0;
				max_MGR = 0;
				break;
			}
			case 2:
			{
				name = "Nosalis";
				strength = 2;
				food = 3;
				min_MGR = 0;
				max_MGR = 2;
				break;
			}
			case 3:
			{
				name = "SlimeSlug";
				strength = 3;
				food = 3;
				min_MGR = 1;
				max_MGR = 2;
				break;
			}
			case 4:
			{
				name = "SpiderBug";
				strength = 4;
				food = 2;
				min_MGR = 2;
				max_MGR = 3;
				break;
			}
			case 5:
			{
				name = "Watcher";
				strength = 5;
				food = 4;
				min_MGR = 2;
				max_MGR = 4;
				break;
			}
			case 6:
			{
				name = "Habitant"; // humans
				strength = 5;
				food = 0;
				min_MGR = 6;
				max_MGR = 8;
				break;
			}
			case 7:
			{
				name = "Poorly equiped guard";
				strength = 8;
				food = 0;
				min_MGR = 10;
				max_MGR = 15;
				break;
			}
		}
	}
}
