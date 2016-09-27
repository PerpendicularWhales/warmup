package com.perpendicularwhales.warmup.hex;

public class HexConfiguration {
    private final HexOrientation hexOrientation;
    private final int sizeInPixels;

    public HexConfiguration(HexOrientation hexOrientation, int sizeInPixels) {
        this.hexOrientation = hexOrientation;
        this.sizeInPixels = sizeInPixels;
    }

    public HexOrientation getHexOrientation() {
        return hexOrientation;
    }

    public int getSizeInPixels() {
        return sizeInPixels;
    }

    public float getOriginX() {
        switch (hexOrientation) {
            case FLAT_TOPPED:
                return getSizeInPixels();
            case POINTY_TOPPED:
                return (float) Math.sqrt(3) * getSizeInPixels() / 2f;
        }
        throw new RuntimeException("Unsupported Hex Orientation");
    }

    public float getOriginY() {
        switch (hexOrientation) {
            case FLAT_TOPPED:
                return (float) Math.sqrt(3) * getSizeInPixels() / 2f;
            case POINTY_TOPPED:
                return getSizeInPixels();
        }
        throw new RuntimeException("Unsupported Hex Orientation");
    }
}
