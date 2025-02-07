package lab.car;

import lab.ramp.AngledRamp;
import lab.ramp.IAngledRamp;

import java.awt.*;

public class ScaniaTruck extends CarWithRamp<IAngledRamp> implements IAngledRamp {
    public ScaniaTruck() {
        super(2, Color.BLUE, 200, "Scania", new AngledRamp(0, 70));
    }

    @Override
    public int getRampAngle() {
        return ramp.getRampAngle();
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
