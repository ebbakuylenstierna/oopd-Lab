package lab.car;

import lab.Position;

import java.awt.*;

public class Saab95 extends TransportableCar implements HasTurbo {

    private boolean turboOn;

    public Saab95(){
        super(2, Color.red, 125, "Saab95");
	    turboOn = false;
    }

    public Saab95(Position position){
        this();
        setPosition(position);
    }

    public Saab95(double x, double y){
        this();
        setX(x);
        setY(y);
    }

    @Override
    public void setTurboOn(){
	    turboOn = true;
    }
    @Override
    public void setTurboOff(){
	    turboOn = false;
    }
    @Override
    public boolean isTurboOn() {return turboOn;}
    
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
