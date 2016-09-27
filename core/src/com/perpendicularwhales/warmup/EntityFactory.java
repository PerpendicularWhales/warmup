package com.perpendicularwhales.warmup;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.perpendicularwhales.warmup.components.EmptyFieldComponent;
import com.perpendicularwhales.warmup.components.HexPositionComponent;
import com.perpendicularwhales.warmup.components.SpriteComponent;

public class EntityFactory {
    private final World world;

    //TODO: Create asset manager, certainly not a place for keeping textures.
    private final Texture fieldTexture = new Texture(Gdx.files.internal("hex.png"));

    public EntityFactory(World world) {
        this.world = world;
    }

    int createEmptyField(int x, int y) {
        EntityEdit edit = world.createEntity().edit()
                .add(new EmptyFieldComponent())
                .add(new HexPositionComponent(x, y))
                .add(new SpriteComponent(fieldTexture));
        return edit.getEntityId();
    }



}
