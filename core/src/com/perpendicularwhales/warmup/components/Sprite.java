package com.perpendicularwhales.warmup.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

public class Sprite extends Component {

    private Texture texture;

    public Sprite() {}

    public void init(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

}
