package lab.graphics;

import lab.car.ICar;

public interface CarView {
    void updateModel(CarModel model);
    void addCar(ICar car);
    void removeCar(int index);
}
