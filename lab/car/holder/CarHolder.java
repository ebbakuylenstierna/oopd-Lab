package lab.car.holder;

import lab.car.ICar;

public interface CarHolder<T extends ICar> {
    void addCar(T car);
    T removeCar();
    int size();
}
