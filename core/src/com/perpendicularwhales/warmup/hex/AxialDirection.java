package com.perpendicularwhales.warmup.hex;

public interface AxialDirection {
    HexPosition getRelativeDirection();
    int getArrayIndex();
}
