package gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import basic.MainMenuState;
import multiplayer.ServerWaitingState;

public class GUI
{
	public static Skin skin;
	public static Stage stage;
	
	TextButton endTurn;
	
	Sprite avatarSprites[];
	//static Player players[];
	public GUI()
	{
		/*
		players = new Player[GameState.players.length];
		for (int i = 0; i < GameState.players.length; ++i)
		{
			players[i] = GameState.players[i];
		}
		*/
		
		TextureManager.addTexture("Avatar0.png", "Avatar0Texture"); // wrzucic potem do petli
		SpriteManager.addSprite("Avatar0Texture", "Avatar0");
		
		TextureManager.addTexture("Avatar1.png", "Avatar1Texture");
		SpriteManager.addSprite("Avatar1Texture", "Avatar1");
		
		TextureManager.addTexture("Avatar2.png", "Avatar2Texture");
		SpriteManager.addSprite("Avatar2Texture", "Avatar2");
		
		TextureManager.addTexture("Avatar3.png", "Avatar3Texture");
		SpriteManager.addSprite("Avatar3Texture", "Avatar3");
		
		TextureManager.addTexture("Avatar3.png", "Avatar4Texture");
		SpriteManager.addSprite("Avatar4Texture", "Avatar4");
		
		avatarSprites = new Sprite[GameState.connectedPlayers + 1]; 
		for (int i = 0; i < avatarSprites.length; ++i)
		{
			avatarSprites[i] = SpriteManager.getSprite("Avatar" + i);
		}
		
		avatarSprites[0].setPosition(930, 800);
		avatarSprites[1].setPosition(930, 695);
		if (avatarSprites.length >= 3)
		{
			avatarSprites[2].setPosition(930, 590);
		}
		if (avatarSprites.length >= 4)
		{
			avatarSprites[3].setPosition(930, 485);
		}
		if (avatarSprites.length == 5)
		{
			avatarSprites[4].setPosition(930, 485);
		}
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		createBasicSkin();
        
		endTurn = new TextButton("End Turn", skin);
		endTurn.setPosition(933 , 15);
		endTurn.addListener(new ClickListener());
        stage.addActor(endTurn);
	}
	
	
	public void update()
	{
		if (endTurn.getClickListener().isPressed())
		{
			System.out.println("HEHEHE");
		}
	}
	
	public void draw(SpriteBatch batch)
	{
		for (int i = 0; i < avatarSprites.length; ++i)
		{
			avatarSprites[i].draw(batch);
		}
		
		stage.act();
        stage.draw();
	}
	
	
	
	
	private void createBasicSkin()
	{
	  //Create a font
	  BitmapFont font = new BitmapFont();
	  skin = new Skin();
	  skin.add("default", font);

	  //Create a texture
	  Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
	  pixmap.setColor(Color.WHITE);
	  pixmap.fill();
	  skin.add("background",new Texture(pixmap));

	  
	 // TextureAtlas textureAtlas = new TextureAtlas();
	  //textureAtlas.addRegion(name, texture, x, y, width, height);
	  
	  //Create a button style
	  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
	  textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
	  textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
	  textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
	  textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
	  textButtonStyle.font = skin.getFont("default");
	  skin.add("default", textButtonStyle);
	}
}
