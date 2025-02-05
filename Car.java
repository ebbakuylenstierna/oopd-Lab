import java.awt.*;

public abstract class Car implements Movable {

    protected double xPosition = 0;
    protected double yPosition = 0;
    protected Rotation rotation = Rotation.FORWARD;

    protected int nrDoors; // Number of doors on the car

    protected double enginePower; // Engine power of the car

    protected double currentSpeed; // The current speed of the car

    protected Color color; // Color of the car

    protected String modelName; // The car model name


    public int getNrDoors() {return nrDoors;}

    public double getEnginePower() {return enginePower;}

    public double getCurrentSpeed() {return currentSpeed;}

    public Color getColor() {return color;}

    public void setColor(Color clr) {color = clr;}

    public void startEngine() {currentSpeed = 0.1;}

    public void stopEngine() {currentSpeed = 0;}

    protected abstract double speedFactor();

    /**
     * Increases current speed by an amount. Speed must never be greater than enginePower.
     */
    protected abstract void incrementSpeed(double amount);
    /**
     * Decreases current speed by an amount. Speed must never be less than zero.
     */
    protected abstract void decrementSpeed(double amount);


    public void move() {
        switch (rotation) {
            case FORWARD:
                xPosition += currentSpeed;
                break;
            case RIGHT:
                yPosition -= currentSpeed;
                break;
            case BACK:
                xPosition -= currentSpeed;
                break;
            case LEFT:
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

    public void gas(double amount){
        if (amount > 1 || amount < 0) {
            throw new IllegalArgumentException("amount must be in the range 0-1");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount > 1 || amount < 0) {
            throw new IllegalArgumentException("amount must be in the range 0-1");
        }
        decrementSpeed(amount);
    }
}


