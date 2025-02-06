import java.awt.*;

public class ScaniaTruck extends CarWithRamp {
    private int rampAngle = 0;

    public ScaniaTruck() {
        super(2, Color.BLUE, enginePower, "Scania");
    }

    @Override
    protected double speedFactor() {
        return 0;
    }

    @Override
    protected void incrementSpeed(double amount) {

    }

    @Override
    protected void decrementSpeed(double amount) {

    }

    @Override
    public void raiseRamp() {
        if (rampAngle > 0 && isStandingStill())
            rampAngle--;
    }

    @Override
    public void lowerRamp() {
        if (rampAngle < 70 && isStandingStill())
            rampAngle++;
    }

    @Override
    public boolean isRampLowered() {
        return rampAngle != 0;
    }
}
