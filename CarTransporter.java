public class CarTransporter extends CarWithRamp {
    private boolean isRampUp = true;

    @Override
    public void raiseRamp() {
        if (isStandingStill())
            isRampUp = true;
    }

    @Override
    public void lowerRamp() {
        if (isStandingStill())
            isRampUp = false;
    }

    @Override
    public boolean isRampLowered() {
        return isRampUp;
    }
}
