package lab.car;

import lab.car.holder.CarHolder;
import lab.car.holder.FiloHolder;
import lab.ramp.BooleanRamp;
import lab.transport.Transporter;

import java.awt.*;
import java.util.List;

public class VolvoCarTransporter extends CarWithRamp<BooleanRamp> implements CarHolder<TransportableCar>, Transporter<TransportableCar> {

    private final CarHolder<TransportableCar> holder;

    public VolvoCarTransporter() {
        super(2, Color.WHITE, 250, "lab.car.VolvoCarTransporter", new BooleanRamp());
        holder = new FiloHolder<>(5);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void addCar(TransportableCar car) {
        if (!isRampLowered())
            throw new IllegalStateException("Cannot add car while ramp is raised");
        car.startTransport(this);
        holder.addCar(car);
    }

    @Override
    public TransportableCar removeCar() {
        if (!isRampLowered())
            throw new IllegalStateException("Cannot remove car while ramp is raised");
        TransportableCar car = holder.removeCar();
        car.endTransport();
        return car;
    }

    @Override
    public List<TransportableCar> getCars() {
        return holder.getCars();
    }

    @Override
    public int size() {
        return holder.size();
    }

    @Override
    public boolean isFull() {
        return holder.isFull();
    }

    @Override
    public boolean isEmpty() {
        return holder.isEmpty();
    }

    @Override
    public void move() {
        super.move();

        // Update held cars' positions
        updateTransportedPositions();
    }

    @Override
    public void transport(TransportableCar transportable) {
        addCar(transportable);
    }

    @Override
    public void updateTransportedPositions() {
        for (TransportableCar car : getCars()) {
            car.moveToTransporter();
        }
    }
}
