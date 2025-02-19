package lab.car.holder;

import lab.car.ICar;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public abstract class DequeHolder<T extends ICar> implements CarHolder<T> {
    protected final Deque<T> cars;
    private final int size;

    public DequeHolder(int size) {
        cars = new ArrayDeque<>();
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return cars.size() >= size;
    }

    @Override
    public boolean isEmpty() {
        return cars.isEmpty();
    }

    @Override
    public List<T> getCars() {
        return List.copyOf(cars);
    }

    @Override
    public boolean containsCar(T car) {
        return cars.contains(car);
    }
}
