package lab.car;

import lab.Position;

import java.awt.*;

public class Volvo240 extends TransportableCar {

    protected double trimFactor = 1.25;
    
    public Volvo240(){
        super(4, Color.black, 100, "Volvo240");
    }

    public Volvo240(Position position){
        this();
        setPosition(position);
    }

    public Volvo240(double x, double y){
        this();
        setX(x);
        setY(y);
    }
    
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
