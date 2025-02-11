package lab.car;

import java.awt.*;

public class Saab95 extends TransportableCar {

    private boolean turboOn;

    public Saab95(){
        super(2, Color.red, 125, "lab.car.Saab95");
	    turboOn = false;
    }


    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }
    public boolean isTurboOn() {return turboOn;}
    
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
