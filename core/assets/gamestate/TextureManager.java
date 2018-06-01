package gamestate;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public final class TextureManager
{
	private static HashMap<String, Texture> textures;
	
	public static void initialize()
	{
		textures = new HashMap<String, Texture>();
	}
	
	public static Texture getTexture(String name)
	{
		return textures.get(name);
	}
	
	public static void addTexture(String path, String name)
	{
		Texture texture = new Texture(path);
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // makes texture clear when resized, but Im resizing sprite, will it do anything?
		textures.put(name, texture);
	}
}
