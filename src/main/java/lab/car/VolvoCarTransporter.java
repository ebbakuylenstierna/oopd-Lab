package lab.car;

import lab.Position;
import lab.car.holder.CarHolder;
import lab.car.holder.FiloHolder;
import lab.ramp.BooleanRamp;
import lab.transport.Transporter;

import java.awt.*;
import java.util.List;

public class VolvoCarTransporter extends CarWithRamp<BooleanRamp> implements CarHolder<TransportableCar>, Transporter<TransportableCar> {

    private final CarHolder<TransportableCar> holder;

    public VolvoCarTransporter() {
        super(2, Color.WHITE, 250, "VolvoCarTransporter", new BooleanRamp());
        holder = new FiloHolder<>(5);
    }

    public VolvoCarTransporter(Position position){
        this();
        setPosition(position);
    }

    public VolvoCarTransporter(double x, double y){
        this();
        setX(x);
        setY(y);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void addCar(TransportableCar car) {
        if (!isRampLowered())
            throw new IllegalStateException("Cannot add car while ramp is raised");
        if (distanceTo(car) > 1)
            throw new IllegalStateException("Car is too far away");

        car.startTransport(this);
        holder.addCar(car);
        updateTransportedPositions();
    }

    @Override
    public TransportableCar removeCar() {
        if (!isRampLowered())
            throw new IllegalStateException("Cannot remove car while ramp is raised");

        TransportableCar car = holder.removeCar();
        car.setPosition(getPosition().offsetForward(-1));
        car.endTransport();
        return car;
    }

    @Override
    public List<TransportableCar> getCars() {
        return holder.getCars();
    }

    @Override
    public boolean containsCar(TransportableCar car) {
        return holder.containsCar(car);
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
        updateTransportedPositions();
    }

    @Override
    public void turnRight() {
        super.turnRight();
        updateTransportedPositions();
    }

    @Override
    public void turnLeft() {
        super.turnLeft();
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
