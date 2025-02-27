package lab.graphics;

import lab.car.TransportableCar;

public interface CarView<T extends TransportableCar> {
    void updateModel(CarModel<T> model);
}
