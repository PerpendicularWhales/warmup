package com.perpendicularwhales.warmup.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

public class Sprite extends Component {
    public Texture texture;

    public Sprite() {
    }

    public Sprite(Texture texture) {
        this.texture = texture;
    }
}
