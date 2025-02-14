package lab;

public interface Positioned {
    double getX();
    double getY();
    Rotation getRotation();
    Position getPosition();
    double distanceTo(Positioned other);
}
