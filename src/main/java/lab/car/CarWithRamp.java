package lab.car;

import lab.ramp.Ramp;

import java.awt.*;

public abstract class CarWithRamp<T extends Ramp> extends Car implements Ramp {
    protected final T ramp;

    public CarWithRamp(int nrDoors, Color color, double enginePower, String modelName, T ramp) {
        super(nrDoors, color, enginePower, modelName);
        this.ramp = ramp;
    }

    @Override
    public void gas(double amount) {
        if (isRampLowered())
            throw new IllegalStateException("Cannot drive while ramp is lowered");
        super.gas(amount);
    }

    @Override
    public void startEngine() {
        if (isRampLowered())
            throw new IllegalStateException("Cannot drive while ramp is lowered");
        super.startEngine();
    }

    @Override
    public void raiseRamp() {
        if (!isStandingStill())
            throw new IllegalStateException("Cannot raise ramp while moving");
        ramp.raiseRamp();
    }

    @Override
    public void lowerRamp() {
        if (!isStandingStill())
            throw new IllegalStateException("Cannot lower ramp while moving");
        ramp.lowerRamp();
    }

    @Override
    public boolean isRampLowered() {
        return ramp.isRampLowered();
    }
}
