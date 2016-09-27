package com.perpendicularwhales.warmup.components;

import com.artemis.Component;
import com.perpendicularwhales.warmup.hex.HexPosition;

public class HexPositionComponent extends Component {
    public HexPosition hexPosition;

    public HexPositionComponent(int x, int y) {
        hexPosition = new HexPosition(x, y);
    }

    public HexPositionComponent(HexPosition hexPosition) {
        this.hexPosition = hexPosition;
    }

    public HexPositionComponent() {
    }
}
