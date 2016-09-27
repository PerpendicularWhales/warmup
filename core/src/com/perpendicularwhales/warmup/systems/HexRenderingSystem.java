package com.perpendicularwhales.warmup.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.perpendicularwhales.warmup.components.EmptyFieldComponent;
import com.perpendicularwhales.warmup.components.HexPositionComponent;
import com.perpendicularwhales.warmup.components.SpriteComponent;
import com.perpendicularwhales.warmup.hex.AxialHexMaps;
import com.perpendicularwhales.warmup.hex.HexConfiguration;


public class HexRenderingSystem extends IteratingSystem {
    @Wire private HexConfiguration hexConfiguration;
    @Wire private OrthographicCamera camera;
    @Wire private AxialHexMaps axialHexMaps;
    @Wire private SpriteBatch batch;

    private ComponentMapper<HexPositionComponent> hexPositionComponentMapper;
    private ComponentMapper<EmptyFieldComponent> emptyFieldComponentMapper;

    private ComponentMapper<SpriteComponent> spriteMapper;

    private final Vector2 onScreenPosition;
    private final Vector3 tmpVectorForProjection;

    public HexRenderingSystem() {
        super(Aspect.all(SpriteComponent.class, HexPositionComponent.class, EmptyFieldComponent.class));
        onScreenPosition = new Vector2();
        tmpVectorForProjection = new Vector3();
    }

    @Override
    protected void begin() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void process(int entityId) {
        SpriteComponent spriteComponent = spriteMapper.get(entityId);
        HexPositionComponent hexPositionComponent = hexPositionComponentMapper.get(entityId);

        axialHexMaps.hexPositionToPixelPosition(hexPositionComponent.hexPosition, onScreenPosition);

        //changes position to center of hex
        onScreenPosition.sub(hexConfiguration.getOriginX(), hexConfiguration.getOriginY());

        projectPositionToScreenPosition();

        //imo camera should scale it by itself but it doesn't work xD
        //should check working of camera and batch later on
        batch.draw(spriteComponent.texture,
                onScreenPosition.x, onScreenPosition.y,
                spriteComponent.texture.getWidth() / camera.zoom,
                spriteComponent.texture.getHeight() / camera.zoom);
    }

    private void projectPositionToScreenPosition() {
        //projecting takes only 3d vector...
        tmpVectorForProjection.set(onScreenPosition, 0);
        camera.project(tmpVectorForProjection);
        onScreenPosition.set(tmpVectorForProjection.x, tmpVectorForProjection.y);
    }
}
