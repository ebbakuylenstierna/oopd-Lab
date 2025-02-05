import java.awt.*;

public class Volvo240 extends Car{

    protected double trimFactor = 1.25;

    
    public Volvo240(){
        super(4, Color.black, 100, "Volvo240");
    }
    
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    protected void incrementSpeed(double amount){
	    currentSpeed = Math.min(
                getCurrentSpeed() + speedFactor() * amount,
                getEnginePower()
        );
    }

    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
}
