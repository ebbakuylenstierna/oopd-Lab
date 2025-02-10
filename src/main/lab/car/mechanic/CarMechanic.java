package lab.car.mechanic;

import lab.Positioned;
import lab.Rotation;
import lab.car.ICar;
import lab.car.holder.CarHolder;
import lab.car.holder.FifoHolder;

import java.util.List;

public class CarMechanic<T extends ICar> implements CarHolder<T>, Positioned {
    private final double x;
    private final double y;
    private final Rotation rotation;
    private final CarHolder<T> holder;

    public CarMechanic(double x, double y, Rotation rotation, int size) {
        holder = new FifoHolder<>(size);
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    @Override
    public void addCar(T car) {
        holder.addCar(car);
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
    public List<T> getCars() {
        return holder.getCars();
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }
}
