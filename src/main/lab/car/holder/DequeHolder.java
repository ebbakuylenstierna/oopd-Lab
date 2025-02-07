package lab.car.holder;

import lab.car.ICar;

import java.util.ArrayDeque;
import java.util.Deque;

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
}
