package com.perpendicularwhales.warmup.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public class ColorComponent extends Component {
    public Color color;

    public ColorComponent() {
    }

    public ColorComponent(Color color) {
        this.color = color;
    }
}
