package lab.car.holder;

import lab.car.ICar;

import java.util.List;

public interface CarHolder<T extends ICar> {
    /**
     * @throws IllegalStateException when the holder is full
     */
    void addCar(T car);
    T removeCar();
    int size();
    boolean isFull();
    boolean isEmpty();
    List<T> getCars();
}
