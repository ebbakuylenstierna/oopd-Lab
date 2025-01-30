import java.awt.*;

public class Volvo240 extends Car{

    protected double trimFactor = 1.25;

    
    public Volvo240(){
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        stopEngine();
    }
    
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    protected void incrementSpeed(double amount){
	    currentSpeed = Math.min(
                getCurrentSpeed() + speedFactor() * amount,
                enginePower
        );
    }

    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
}
