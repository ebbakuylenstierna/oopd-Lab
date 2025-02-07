package lab.car;

import lab.car.holder.CarHolder;
import lab.car.holder.FiloHolder;
import lab.ramp.BooleanRamp;

import java.awt.*;

public class VolvoCarTransporter extends CarWithRamp<BooleanRamp> implements CarHolder<SmallCar> {

    private final CarHolder<SmallCar> holder;

    public VolvoCarTransporter() {
        super(2, Color.WHITE, 250, "lab.car.VolvoCarTransporter", new BooleanRamp());
        holder = new FiloHolder<>(5);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void addCar(SmallCar car) {
        if (!isRampLowered())
            throw new IllegalStateException("Cannot add car while ramp is raised");
        holder.addCar(car);
    }

    @Override
    public SmallCar removeCar() {
        if (!isRampLowered())
            throw new IllegalStateException("Cannot remove car while ramp is raised");
        return holder.removeCar();
    }

    @Override
    public int size() {
        return holder.size();
    }

    @Override
    public void move() {
        super.move();

        // Update held cars' positions
        // TODO
    }
}
