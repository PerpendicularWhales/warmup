package com.perpendicularwhales.warmup.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent extends Component {
    public TextureRegion texture;

    public SpriteComponent() {
    }

    public SpriteComponent(TextureRegion texture) {
        this.texture = texture;
    }
}
