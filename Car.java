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
    protected abstract void incrementSpeed(double amount);
    protected abstract void decrementSpeed(double amount);


    public void move() {
        switch (rotation) {
            case 0:
                xPosition += currentSpeed;
                break;
            case 1:
                yPosition += currentSpeed;
                break;
            case 2:
                xPosition -= currentSpeed;
                break;
            case 3:
                yPosition -= currentSpeed;
                break;
        }
    }

    public void turnLeft() {
        rotation --;
        rotation %= 4;
    }

    public void turnRight() {
        rotation++;
        rotation %= 4;
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }
    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }
}


