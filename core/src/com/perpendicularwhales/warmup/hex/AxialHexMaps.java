package com.perpendicularwhales.warmup.hex;

import com.badlogic.gdx.math.Vector2;

public class AxialHexMaps {
    private HexConfiguration hexConfiguration;

    public AxialHexMaps(HexConfiguration hexConfiguration) {
        this.hexConfiguration = hexConfiguration;
    }

    public void hexPositionToPixelPosition(HexPosition hexPosition, Vector2 outputPixelPosition) {
        switch (hexConfiguration.getHexOrientation()) {
            case FLAT_TOPPED:
                hexPositionToPPFlatTop(hexPosition, outputPixelPosition);
                return;
            case POINTY_TOPPED:
                hexPositionToPPPointyTop(hexPosition, outputPixelPosition);
                return;
        }
        throw new RuntimeException("Unsupported Hex Orientation");
    }

    public void pixelPositionToHexPosition(Vector2 pixelPosition, HexPosition outputHexPosition) {
        switch (hexConfiguration.getHexOrientation()) {
            case FLAT_TOPPED:
                pixelPositionToHPFlatTop(pixelPosition, outputHexPosition);
                return;
            case POINTY_TOPPED:
                pixelPositionToHPPointyTop(pixelPosition, outputHexPosition);
                return;
        }
        throw new RuntimeException("Unsupported Hex Orientation");
    }

    private void pixelPositionToHPFlatTop(Vector2 pixelPosition, HexPosition outputHexPosition) {
        outputHexPosition.x = (int) (pixelPosition.x * Math.sqrt(3) / 3 - pixelPosition.y / 3) / hexConfiguration.getSizeInPixels();
        outputHexPosition.y = (int) (pixelPosition.y * 2 / 3f / hexConfiguration.getSizeInPixels());
    }

    private void pixelPositionToHPPointyTop(Vector2 pixelPosition, HexPosition outputHexPosition) {
        outputHexPosition.x = (int) (pixelPosition.x * 2 / 3f / hexConfiguration.getSizeInPixels());
        outputHexPosition.y = (int) (-pixelPosition.x / 3 +  Math.sqrt(3) / 3 * pixelPosition.y) / hexConfiguration.getSizeInPixels();
    }

    private void hexPositionToPPFlatTop(HexPosition hexPosition, Vector2 outputPixelPosition) {
        outputPixelPosition.x = hexConfiguration.getSizeInPixels() * 3 / 2f * hexPosition.x;
        outputPixelPosition.y = hexConfiguration.getSizeInPixels() * (float) Math.sqrt(3) * (hexPosition.y + hexPosition.x / 2f);
    }
    private void hexPositionToPPPointyTop(HexPosition hexPosition, Vector2 outputPixelPosition) {
        outputPixelPosition.x = hexConfiguration.getSizeInPixels() * (float) Math.sqrt(3) * (hexPosition.x + hexPosition.y / 2f);
        outputPixelPosition.y = hexConfiguration.getSizeInPixels() * 3 / 2f * hexPosition.y;
    }
}
