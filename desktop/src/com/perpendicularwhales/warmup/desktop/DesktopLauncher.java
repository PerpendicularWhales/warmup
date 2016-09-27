package com.perpendicularwhales.warmup.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.perpendicularwhales.warmup.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) (1920 / 1.5);
        config.height = (int) (1080 / 1.5);
        config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
