package com.monopolyrpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;

import basic.MonopolyRPG;
import gamestate.SpriteManager;
import gamestate.TextureManager;

public class DesktopLauncher {
	public static float originalWidth = 1285;
	public static float originalHeight = 910;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "MonopolyRPG";
		config.width = (int) originalWidth;
		config.height = (int) originalHeight;

		TextureManager.initialize();
		SpriteManager.initialize();
		SpriteManager.setInitialScale(new Vector2(originalWidth, originalHeight));
		
		new LwjglApplication(new MonopolyRPG(), config);
	}
}
