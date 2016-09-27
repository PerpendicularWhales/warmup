package com.perpendicularwhales.warmup.hex;

public enum PointyTopAxialDirection implements AxialDirection {
    LEFT(new HexPosition(-1, 0), 0),
    RIGHT(new HexPosition(1, 0), 1),
    TOP_RIGHT(new HexPosition(1, -1), 5),
    BOTTOM_LEFT(new HexPosition(-1, 1), 4),
    TOP_LEFT(new HexPosition(0, -1), 3),
    BOTTOM_RIGHT(new HexPosition(0, 1), 2);

    private final HexPosition relativeDirection;
    private final int arrayIndex;

    PointyTopAxialDirection(HexPosition relativeDirection, int arrayIndex) {
        this.relativeDirection = relativeDirection;
        this.arrayIndex = arrayIndex;
    }

    @Override
    public HexPosition getRelativeDirection() {
        return relativeDirection;
    }

    @Override
    public int getArrayIndex() {
        return arrayIndex;
    }

    public static int arrayIndexFromDirection(HexPosition relativeDirection) {
        for (PointyTopAxialDirection direction : PointyTopAxialDirection.values()) {
            if (direction.relativeDirection.equals(relativeDirection)) {
                return direction.arrayIndex;
            }
        }
        throw new RuntimeException("Wrong direction");
    }

    public static int arrayIndexFromDirection(int x, int y) {
        for (PointyTopAxialDirection direction : PointyTopAxialDirection.values()) {
            if (direction.relativeDirection.x == x && direction.relativeDirection.y == y) {
                return direction.arrayIndex;
            }
        }
        throw new RuntimeException("Wrong direction");
    }
}
