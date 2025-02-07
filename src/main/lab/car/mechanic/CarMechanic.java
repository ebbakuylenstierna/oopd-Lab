package lab.car.mechanic;

import lab.car.ICar;
import lab.car.holder.CarHolder;
import lab.car.holder.FifoHolder;

public class CarMechanic<T extends ICar> implements CarHolder<T> {
    private final CarHolder<T> holder;

    public CarMechanic(int size) {
        holder = new FifoHolder<>(size);
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
}
