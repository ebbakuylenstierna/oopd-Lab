public enum Rotation {
    FORWARD(0),
    RIGHT(1),
    BACK(2),
    LEFT(3);

    public final int value;
    Rotation(int value) {
        this.value = value;
    }

    public Rotation turnRight() {
        return Rotation.values()[Math.floorMod(value + 1, 4)];
    }

    public Rotation turnLeft() {
        return Rotation.values()[Math.floorMod(value - 1, 4)];
    }
}
