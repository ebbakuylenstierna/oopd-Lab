package lab.graphics;

import lab.Position;
import lab.Rotation;
import lab.car.ICar;
import lab.car.Saab95;
import lab.car.Scania;
import lab.car.Volvo240;
import lab.car.mechanic.CarMechanic;
import lab.car.HasTurbo;

import java.util.ArrayList;
import java.util.List;

import lab.car.mechanic.Volvo240Mechanic;
import lab.ramp.Ramp;

public class DemoModel implements CarModel {
    private final List<ICar> cars = new ArrayList<>();

    private final CarMechanic<Volvo240> workshop;

    double xMin = 0;
    double xMax;
    double yMin = 0;
    double yMax;

    int maxCars;

    public DemoModel(double width, double height, int maxCars) {
        workshop = new Volvo240Mechanic(new Position(300, 300, Rotation.SOUTH));

        cars.add(new Volvo240(0, 0));
        cars.add(new Saab95(0, 100));
        cars.add(new Scania(0, 200));

        xMax = width;
        yMax = height;

        this.maxCars = maxCars;
    }

    public DemoModel(double width, double height) {
        this(width, height, 10);
    }

    public void update() {
        for (int i = 0; i < cars.size(); i++) {
            ICar car = cars.get(i);
            car.move();
            workshop.updateTransportedPositions();
            flipIfNearWall(car);
            enterIfNearWorkshop(i);
        }
    }

    @Override
    public List<ICar> getCars() {
        return cars;
    }

    @Override
    public CarMechanic<Volvo240> getWorkshop() {
        return workshop;
    }


    private void flipIfNearWall(ICar car) {
        if ((car.getX() < xMin && car.getRotation() == Rotation.WEST) //
                || (car.getX() > xMax && car.getRotation() == Rotation.EAST) //
                || (car.getY() < yMin && car.getRotation() == Rotation.NORTH) //
                || (car.getY() > yMax && car.getRotation() == Rotation.SOUTH)) //
        {
            car.turnRight();
            car.turnRight();
        }
    }

    private void enterIfNearWorkshop(int index) {
        ICar car = cars.get(index);
        if (!(car instanceof Volvo240 volvo)) return;

        double distance = car.distanceTo(workshop);
        if (distance <= 50 && !workshop.containsCar(volvo)) {
            workshop.transport(volvo);
            System.out.println("Added Volvo to workshop");
        }
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (ICar car : cars)
            try {
                car.gas(gasAmount);
            } catch (IllegalStateException _) {
            }
    }

    public void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (ICar car : cars)
            try {
                car.brake(brakeAmount);
            } catch (IllegalStateException _) {
            }
    }

    public void turboOn() {
        for (ICar car : cars) {
            try {
                if (car instanceof HasTurbo carWithTurbo) {
                    carWithTurbo.setTurboOn();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    public void turboOff() {
        for (ICar car : cars) {
            try {

                if (car instanceof HasTurbo carWithTurbo) {
                    carWithTurbo.setTurboOff();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    public void liftBed() {
        for (ICar car : cars) {
            try {

                if (car instanceof Ramp carWithRamp) {
                    carWithRamp.raiseRamp();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    public void lowerBed() {
        for (ICar car : cars) {
            try {
                if (car instanceof Ramp carWithRamp) {
                    carWithRamp.lowerRamp();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    public void startAll() {
        for (ICar car : cars) {
            try {
                car.startEngine();

            } catch (IllegalStateException _) {
            }
        }
    }

    public void stopAll() {
        for (ICar car : cars) {
            try {
                car.stopEngine();
            } catch (IllegalStateException _) {
            }
        }
    }

    public void turnLeft() {
        for (ICar car : cars) {
            try {
                car.turnLeft();
            } catch (IllegalStateException _) {
            }
        }
    }

    public void turnRight() {
        for (ICar car : cars) {
            try {
                car.turnRight();
            } catch (IllegalStateException _) {
            }
        }
    }

    @Override
    public ICar addCar() {
        if (cars.size() >= maxCars) return null;

        double x = 0;
        double y = (100 * cars.size()) % (yMax - yMin) - yMin;

        int model = (int) (Math.random() * 3);
        ICar car = switch (model) {
            case 0 -> new Volvo240(x, y);
            case 1 -> new Saab95(x, y);
            case 2 -> new Scania(x, y);
            default -> throw new IllegalStateException("Unexpected value: " + model);
        };

        cars.add(car);
        return car;
    }

    @Override
    public int removeCar() {
        if (cars.isEmpty()) return -1;

        int index = (int) (Math.random() * cars.size());
        cars.remove(index);
        return index;
    }
}