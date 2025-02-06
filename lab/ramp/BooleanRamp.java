package lab.ramp;

public class BooleanRamp implements Ramp {
    private boolean isRampUp = true;

    @Override
    public void raiseRamp() {
        isRampUp = true;
    }

    @Override
    public void lowerRamp() {
        isRampUp = false;
    }

    @Override
    public boolean isRampLowered() {
        return isRampUp;
    }
}
