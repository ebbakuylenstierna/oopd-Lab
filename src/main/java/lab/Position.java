package lab;

import java.util.Objects;

public class Position implements Positioned {
    private double x;
    private double y;
    private Rotation rotation;

    public Position(double x, double y, Rotation rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Position(Position position) {
        this(position.getX(), position.getY(), position.getRotation());
    }

    public Position() {
        this(0, 0, Rotation.EAST);
    }

    public static Position copyOf(Position position) {
        return new Position(position);
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public double distanceTo(Positioned other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Position copy() {
        return Position.copyOf(this);
    }

    public void update(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.rotation = position.getRotation();
    }

    public Position offsetX(double offset) {
        return new Position(x + offset, y, rotation);
    }

    public Position offsetY(double offset) {
        return new Position(x, y + offset, rotation);
    }

    public Position offsetForward(double offset) {
        return switch (rotation) {
            case Rotation.EAST -> offsetX(offset);
            case Rotation.SOUTH -> offsetY(offset);
            case Rotation.WEST -> offsetX(-offset);
            case Rotation.NORTH -> offsetY(-offset);
        };
    }

    public Position offsetRight(double offset) {
        return switch (rotation) {
            case Rotation.EAST -> offsetY(-offset);
            case Rotation.SOUTH -> offsetX(-offset);
            case Rotation.WEST -> offsetY(offset);
            case Rotation.NORTH -> offsetX(offset);
        };
    }

    public Position turnedLeft() {
        return new Position(x, y, rotation.turnLeft());
    }

    public Position turnedRight() {
        return new Position(x, y, rotation.turnRight());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position position)) return false;
        return Double.compare(x, position.x) == 0 && Double.compare(y, position.y) == 0 && rotation == position.rotation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, rotation);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", rotation=" + rotation +
                '}';
    }
}
