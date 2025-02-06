package lab.car.holder;

import lab.car.ICar;

public class FiloHolder<T extends ICar> extends DequeHolder<T> {
    public FiloHolder(int size) {
        super(size);
    }

    @Override
    public void addCar(T car) {
        if (cars.size() >= size()) {
            throw new RuntimeException("Car holder is full");
        }
        cars.addLast(car);
    }

    @Override
    public T removeCar() {
        return cars.removeLast();
    }
}
