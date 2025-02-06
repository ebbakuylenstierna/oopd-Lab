import java.awt.*;

public abstract class CarWithRamp extends Car implements Ramp {

    public CarWithRamp(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    @Override
    public void gas(double amount) {
        if (!isRampLowered()) {
            super.gas(amount);
        }
    }
}
