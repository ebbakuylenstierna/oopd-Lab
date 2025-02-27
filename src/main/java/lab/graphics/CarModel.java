package lab.graphics;

import lab.car.ICar;
import lab.car.TransportableCar;
import lab.car.mechanic.CarMechanic;

import java.util.List;

public interface CarModel<T extends TransportableCar> {
    void update();
    List<ICar> getCars();
    CarMechanic<T> getWorkshop();
    void gas(int amount);
    void brake(int amount);
    void turboOn();
    void turboOff();
    void liftBed();
    void lowerBed();
    void startAll();
    void stopAll();
    void turnLeft();
    void turnRight();
}
