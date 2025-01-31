import java.awt.*;

public abstract class Car implements Movable {

    protected double xPosition = 0;
    protected double yPosition = 0;
    protected byte rotation = 0;

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
            case 0:
                xPosition += currentSpeed;
                break;
            case 1:
                yPosition -= currentSpeed;
                break;
            case 2:
                xPosition -= currentSpeed;
                break;
            case 3:
                yPosition += currentSpeed;
                break;
        }
    }

    public void turnLeft() {
        rotation--;
        rotation = (byte)Math.floorMod(rotation, 4);
    }

    public void turnRight() {
        rotation++;
        rotation = (byte)Math.floorMod(rotation, 4);
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


