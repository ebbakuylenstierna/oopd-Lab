package lab.graphics;

import lab.car.ICar;
import lab.car.mechanic.CarMechanic;

import java.util.List;

public interface CarModel {
    void update();
    List<ICar> getCars();
    CarMechanic<?> getWorkshop();
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

    ICar addCar();
    int removeCar();
}
