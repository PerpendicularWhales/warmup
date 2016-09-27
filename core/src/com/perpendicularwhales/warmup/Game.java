package com.perpendicularwhales.warmup;

import com.artemis.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.perpendicularwhales.warmup.systems.HexRenderingSystem;
import com.perpendicularwhales.warmup.hex.AxialHexMaps;
import com.perpendicularwhales.warmup.hex.HexConfiguration;
import com.perpendicularwhales.warmup.hex.HexOrientation;

public class Game extends ApplicationAdapter {

	private World world;
	
	@Override
	public void create () {
		WorldConfiguration configuration = new WorldConfigurationBuilder()
				.build();

		OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        HexConfiguration hexConfiguration = new HexConfiguration(HexOrientation.POINTY_TOPPED, (int)(40f / Math.sqrt(3)));

        configuration.register(camera);
        configuration.register(hexConfiguration);
        //Wires only work for systems, managers so normal objects cannot be injected
        configuration.register(new AxialHexMaps(hexConfiguration));
        configuration.setSystem(HexRenderingSystem.class);

		world = new World(configuration);

        EntityFactory entityFactory = new EntityFactory(world);
        for (int i = 3; i < 13; i++) {
            for (int j = 3; j < 13; j++) {
                entityFactory.createEmptyField(i, j);
            }
        }
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
