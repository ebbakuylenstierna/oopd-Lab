package lab.car.mechanic;

import lab.Position;
import lab.Positioned;
import lab.Rotation;
import lab.car.TransportableCar;
import lab.car.holder.CarHolder;
import lab.car.holder.FifoHolder;
import lab.transport.Transporter;

import java.util.List;

public class CarMechanic<T extends TransportableCar> implements CarHolder<T>, Positioned, Transporter<T> {
    private final Position position;
    private final CarHolder<T> holder;

    public CarMechanic(Position position, int size) {
        holder = new FifoHolder<>(size);
        this.position = position.copy();
    }

    @Override
    public void addCar(T car) {
        if (distanceTo(car) < 5)
            throw new IllegalStateException("Car is too far away");

        car.startTransport(this);
        holder.addCar(car);
        updateTransportedPositions();
    }

    @Override
    public T removeCar() {
        return holder.removeCar();
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
    public boolean containsCar(T car) { return holder.containsCar(car); }

    @Override
    public List<T> getCars() {
        return holder.getCars();
    }

    @Override
    public double getX() {
        return position.getX();
    }

    @Override
    public double getY() {
        return position.getY();
    }

    @Override
    public Rotation getRotation() {
        return position.getRotation();
    }

    public Position getPosition() {
        return position.copy();
    }

    @Override
    public double distanceTo(Positioned other) {
        return position.distanceTo(other);
    }

    @Override
    public void transport(T transportable) {
        addCar(transportable);
    }

    @Override
    public void updateTransportedPositions() {
        for (T car : holder.getCars()) {
            car.moveToTransporter();
        }
    }
}
