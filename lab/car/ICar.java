package lab.car;

import lab.Movable;

import java.awt.*;

public interface ICar extends Movable {
    int getNrDoors();
    double getEnginePower();
    String getModelName();

    Color getColor();
    void setColor(Color color);

    void startEngine();
    void stopEngine();

    double getCurrentSpeed();
    boolean isStandingStill();

    void gas(double amount);
    void brake(double amount);
}
