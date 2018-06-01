package gamestate;

//import com.mygdx.monopolyrpg.desktop.DesktopLauncher;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public final class SpriteManager
{
	private static HashMap<String, Sprite> sprites;
	public static Vector2 defaultSize;
	public static Vector2 scale;
	
	public static void initialize()
	{
		sprites = new HashMap<String, Sprite>();
		defaultSize = new Vector2(1285, 910);
	}
	
	public static Sprite getSprite(String name)
	{
		return sprites.get(name);
	}
	
	public static void setInitialScale(Vector2 size)
	{
		scale = new Vector2(size.x/defaultSize.x, size.y/defaultSize.y);
	}
	
	public static void addSprite(String textureName, String spriteName)
	{
		Sprite sprite = new Sprite(TextureManager.getTexture(textureName));
		sprite.setOrigin(0, 0);
		sprite.setScale(scale.x, scale.y);
		sprites.put(spriteName, sprite);
	}
	
	public static void addSprite(String textureName, String spriteName, int x, int y, int width, int height)
	{
		Sprite sprite = new Sprite(TextureManager.getTexture(textureName), x, y, width, height);
		sprite.setOrigin(0, 0);
		sprite.setScale(scale.x, scale.y);
		sprites.put(spriteName, sprite);
	}
	
	public static void setPosition(Sprite sprite, float x, float y)
	{
		sprite.setPosition(x * scale.x, y * scale.y);
	}
}
