package lab.car;

import lab.Rotation;

import java.awt.*;

public abstract class Car implements ICar {

    private double xPosition = 0;
    private double yPosition = 0;
    private Rotation rotation = Rotation.FORWARD;

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

    protected void setX(double x) {
        this.xPosition = x;
    }

    @Override
    public double getX() {return xPosition;}


    protected void setY(double y) {
        this.yPosition = y;
    }

    @Override
    public double getY() {return yPosition;}

    protected void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public Rotation getRotation() {return rotation;}

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
        switch (rotation) {
            case Rotation.FORWARD:
                xPosition += currentSpeed;
                break;
            case Rotation.RIGHT:
                yPosition -= currentSpeed;
                break;
            case Rotation.BACK:
                xPosition -= currentSpeed;
                break;
            case Rotation.LEFT:
                yPosition += currentSpeed;
                break;
        }
    }

    public void turnLeft() {
        rotation = rotation.turnLeft();
    }

    public void turnRight() {
        rotation = rotation.turnRight();
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


