package net.msk;

public class NavData {
    private final NavDirection direction;
    private final int value;

    public NavData(final NavDirection direction, final int value) {
        this.direction = direction;
        this.value = value;
    }

    public NavDirection getDirection() {
        return direction;
    }

    public int getValue() {
        return value;
    }
}
