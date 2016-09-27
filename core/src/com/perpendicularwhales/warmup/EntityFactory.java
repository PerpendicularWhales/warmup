package com.perpendicularwhales.warmup;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.perpendicularwhales.warmup.components.*;
import com.perpendicularwhales.warmup.hex.HexConfiguration;
import com.perpendicularwhales.warmup.hex.HexPosition;

public class EntityFactory {
    private final World world;
    private final HexConfiguration hexConfiguration;

    //TODO: Create asset manager, certainly not a place for keeping textures.
    private final TextureRegion fieldTexture = new TextureRegion(new Texture(Gdx.files.internal("hex.png")));
    private final TextureRegion circleTexture = new TextureRegion(new Texture(Gdx.files.internal("circle.png")));
    private final Texture linesTexture = new Texture(Gdx.files.internal("lines.png"));
    private final TextureRegion[] lineTextureRegion = generateLineTextureRegions(linesTexture);

    private TextureRegion[] generateLineTextureRegions(Texture linesTexture) {
        TextureRegion[] result = new TextureRegion[6];
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 2; j++) {
                result[2 * j + i] = new TextureRegion(
                        linesTexture,
                        i * linesTexture.getWidth() / 2,
                        j * linesTexture.getHeight() / 3, linesTexture.getWidth() / 2, linesTexture.getHeight() / 3);

            }
        }
        return result;
    }

    public EntityFactory(World world, HexConfiguration hexConfiguration) {
        this.world = world;
        this.hexConfiguration = hexConfiguration;
    }

    int createEmptyField(int x, int y) {
        EntityEdit edit = world.createEntity().edit()
                .add(new EmptyFieldComponent())
                .add(new HexPositionComponent(x, y))
                .add(new SpriteComponent(fieldTexture));
        return edit.getEntityId();
    }

    int createColoredCircle(int x, int y, Color color) {
        EntityEdit edit = world.createEntity().edit()
                .add(new ColorComponent(color))
                .add(new HexPositionComponent(x, y))
                .add(new SpriteComponent(circleTexture));
        return edit.getEntityId();
    }

    int createHalfLine(int x, int y, Color color, int relativeFromX, int relativeFromY) {
        return createHalfLine(new HexPosition(x, y), color, new HexPosition(relativeFromX, relativeFromY));
    }

    int createHalfLine(HexPosition position, Color color, HexPosition relativeFrom) {
        EntityEdit edit = world.createEntity().edit()
                .add(new ColorComponent(color))
                .add(new HexPositionComponent(position))
                .add(new SpriteComponent(lineTextureRegion[
                        hexConfiguration.
                                getIntDirectionFromRelativeAxialDirection(
                                        relativeFrom.x, relativeFrom.y)]));
        return edit.getEntityId();
    }

}
