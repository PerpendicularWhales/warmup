package com.perpendicularwhales.warmup.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

public class SpriteComponent extends Component {
    public Texture texture;

    public SpriteComponent() {
    }

    public SpriteComponent(Texture texture) {
        this.texture = texture;
    }
}
