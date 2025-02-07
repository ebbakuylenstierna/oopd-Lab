package lab.ramp;

public class AngledRamp implements IAngledRamp {
    private int rampAngle;
    private final int minAngle;
    private final int maxAngle;

    public AngledRamp(int minAngle, int maxAngle) {
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        rampAngle = minAngle;
    }

    @Override
    public void raiseRamp() {
        if (rampAngle > minAngle)
            rampAngle--;
    }

    @Override
    public void lowerRamp() {
        if (rampAngle < maxAngle)
            rampAngle++;
    }

    @Override
    public boolean isRampLowered() {
        return rampAngle != minAngle;
    }

    @Override
    public int getRampAngle() {
        return rampAngle;
    }
}
