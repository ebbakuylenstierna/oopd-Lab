package lab.car;

import java.awt.*;

public class Volvo240 extends Car implements SmallCar {

    protected double trimFactor = 1.25;
    
    public Volvo240(){
        super(4, Color.black, 100, "lab.car.Volvo240");
    }
    
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
