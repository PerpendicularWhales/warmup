package com.perpendicularwhales.warmup;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

	private World world;
	
	@Override
	public void create () {
		WorldConfiguration configuration = new WorldConfigurationBuilder()
				.build();

		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		configuration.register(camera);

		world = new World(configuration);
	}

	@Override
	public void render () {
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
	}
	
	@Override
	public void dispose () {
	}
}
