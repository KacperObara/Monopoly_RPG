package basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gamestate.GameState;
import multiplayer.ClientWaitingState;
import multiplayer.ServerWaitingState;

public class MainMenuState extends State
{
	public static Skin skin;
	public static Stage stage;
	
	TextButton hostGameButton;
	TextButton joinGameButton;
	TextButton exitButton;
	
	@Override
	public void initialize()
	{
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume events

        int buttonOffset = 20;
        
        createBasicSkin();
        
        hostGameButton = new TextButton("Host Game", skin);
        hostGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        hostGameButton.addListener(new ClickListener());
        stage.addActor(hostGameButton);
        
        joinGameButton = new TextButton("Join Game", skin);
        joinGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2 + (hostGameButton.getHeight() + buttonOffset));
        joinGameButton.addListener(new ClickListener());
        stage.addActor(joinGameButton);
        
        exitButton = new TextButton("Exit", skin);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2  - (hostGameButton.getHeight() + buttonOffset));
        exitButton.addListener(new ClickListener());
        stage.addActor(exitButton);
	}

	@Override
	public void update()
	{
		if (hostGameButton.getClickListener().isPressed())
		{
			System.out.println("Host");
			changeState(new ServerWaitingState());
			cleanUp();
		}
		if (joinGameButton.getClickListener().isPressed())
		{
			System.out.println("Join");
			changeState(new ClientWaitingState());
			cleanUp();
		}
		if (exitButton.getClickListener().isPressed())
		{
			System.out.println("Exit (for now quick GameState)");
			changeState(new GameState(0));
			cleanUp();
		}
	}

	@Override
	public void draw(SpriteBatch batch)
	{
		stage.act();
        stage.draw();
	}

	@Override
	public void cleanUp()
	{
		System.out.println("Cleaning MainMenuState");
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
