package com.perpendicularwhales.warmup.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.perpendicularwhales.warmup.components.ColorComponent;
import com.perpendicularwhales.warmup.components.HexPositionComponent;
import com.perpendicularwhales.warmup.components.SpriteComponent;
import com.perpendicularwhales.warmup.hex.AxialHexMaps;
import com.perpendicularwhales.warmup.hex.HexConfiguration;

public class ColoredFieldRenderingSystem extends IteratingSystem{
    @Wire private HexConfiguration hexConfiguration;
    @Wire private OrthographicCamera camera;
    @Wire private AxialHexMaps axialHexMaps;
    @Wire private SpriteBatch batch;
    @Wire(name = "board translation") private Vector2 boardTranslation;

    private final Vector2 positionVector;
    private final Vector3 tmpVectorForProjection;

    private ComponentMapper<ColorComponent> colorComponentMapper;
    private ComponentMapper<HexPositionComponent> hexPositionComponentMapper;
    private ComponentMapper<SpriteComponent> spriteComponentMapper;

    public ColoredFieldRenderingSystem() {
        super(Aspect.all(ColorComponent.class, HexPositionComponent.class, SpriteComponent.class));
        positionVector = new Vector2();
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
        batch.setColor(Color.WHITE);
    }


    @Override
    protected void process(int entityId) {
        SpriteComponent spriteComponent = spriteComponentMapper.get(entityId);
        ColorComponent colorComponent = colorComponentMapper.get(entityId);
        HexPositionComponent hexPositionComponent = hexPositionComponentMapper.get(entityId);

        axialHexMaps.hexPositionToPixelPosition(hexPositionComponent.hexPosition, positionVector);

        positionVector.sub(spriteComponent.texture.getRegionWidth() / 2, spriteComponent.texture.getRegionHeight() / 2);

        tmpVectorForProjection.set(positionVector, 0);
        camera.project(tmpVectorForProjection);
        positionVector.set(tmpVectorForProjection.x, tmpVectorForProjection.y);

        positionVector.add(boardTranslation);

        batch.setColor(colorComponent.color);

        batch.draw(spriteComponent.texture,
                positionVector.x, positionVector.y,
                spriteComponent.texture.getRegionWidth() / camera.zoom,
                spriteComponent.texture.getRegionHeight() / camera.zoom);
    }
}
