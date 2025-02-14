package lab;

public enum Rotation {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

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

    public Rotation flip() {
        return Rotation.values()[Math.floorMod(value + 2, 4)];
    }
}
