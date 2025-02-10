package lab;

public class Position implements Positioned {
    public static Position ZERO = new Position(0, 0, Rotation.FORWARD);

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
            case Rotation.FORWARD -> offsetX(offset);
            case Rotation.RIGHT -> offsetY(-offset);
            case Rotation.BACK -> offsetX(-offset);
            case Rotation.LEFT -> offsetY(offset);
        };
    }

    public Position offsetRight(double offset) {
        return switch (rotation) {
            case Rotation.FORWARD -> offsetY(-offset);
            case Rotation.RIGHT -> offsetX(-offset);
            case Rotation.BACK -> offsetY(offset);
            case Rotation.LEFT -> offsetX(offset);
        };
    }

    public Position turnedLeft() {
        return new Position(x, y, rotation.turnLeft());
    }

    public Position turnedRight() {
        return new Position(x, y, rotation.turnRight());
    }
}
