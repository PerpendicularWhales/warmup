package com.perpendicularwhales.warmup.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perpendicularwhales.warmup.components.SpriteComponent;

@Wire
public class RenderingSystem extends EntityProcessingSystem {

    protected ComponentMapper<SpriteComponent> spriteMapper;

    private SpriteBatch batch;
    private AssetManager assetManager;

    public RenderingSystem() {
        super(Aspect.all(SpriteComponent.class));

        batch = new SpriteBatch();
    }

    @Override
    protected void begin() {
        // TODO: gl buffer clear logic?
        batch.begin();
    }

    @Override
    protected void process(Entity e) {
        // TODO: somehow draw entity
        SpriteComponent spriteComponent = spriteMapper.get(e);

        batch.draw(spriteComponent.texture, 100, 100);
    }

    @Override
    protected void end() {
        batch.end();
    }

}
