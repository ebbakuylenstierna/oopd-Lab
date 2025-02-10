package lab.car;

import lab.Position;
import lab.Rotation;

import java.awt.*;

public abstract class Car implements ICar {

    private final Position position = Position.ZERO;

    private final int nrDoors; // Number of doors on the lab.car

    private final double enginePower; // Engine power of the lab.car

    private double currentSpeed; // The current speed of the lab.car

    private Color color; // Color of the lab.car

    private final String modelName; // The lab.car model name

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;

        stopEngine();
    }

    @Override
    public double getX() {
        return position.getX();
    }

    protected void setX(double x) {
        position.setX(x);
    }

    @Override
    public double getY() {return position.getY();}

    protected void setY(double y) {
        position.setY(y);
    }

    @Override
    public Rotation getRotation() {return position.getRotation();}

    protected void setRotation(Rotation rotation) {
        position.setRotation(rotation);
    }

    public Position getPosition() {
        return position.copy();
    }

    protected void setPosition(Position position) {
        position.update(position);
    }

    @Override
    public int getNrDoors() {return nrDoors;}

    @Override
    public double getEnginePower() {return enginePower;}

    @Override
    public double getCurrentSpeed() {return currentSpeed;}

    @Override
    public String getModelName() {return modelName;}

    @Override
    public Color getColor() {return color;}

    @Override
    public void setColor(Color color) {
        this.color = color;}

    @Override
    public void startEngine() {currentSpeed = 0.1;}

    @Override
    public void stopEngine() {currentSpeed = 0;}

    @Override
    public boolean isStandingStill() {return currentSpeed == 0;}

    protected abstract double speedFactor();

    /**
     * Increases current speed by an amount. Speed must never be greater than enginePower.
     */
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }
    /**
     * Decreases current speed by an amount. Speed must never be less than zero.
     */
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);
    }


    public void move() {
        position.update(position.offsetForward(currentSpeed));
    }

    public void turnLeft() {
        position.update(position.turnedLeft());
    }

    public void turnRight() {
        position.update(position.turnedRight());
    }

    @Override
    public void gas(double amount){
        if (amount > 1 || amount < 0) {
            throw new IllegalArgumentException("amount must be in the range 0-1");
        }
        incrementSpeed(amount);
    }

    @Override
    public void brake(double amount){
        if (amount > 1 || amount < 0) {
            throw new IllegalArgumentException("amount must be in the range 0-1");
        }
        decrementSpeed(amount);
    }
}


