package lab.graphics;

import lab.Position;
import lab.Rotation;
import lab.car.*;
import lab.car.mechanic.CarMechanic;
import lab.car.mechanic.Volvo240Mechanic;
import lab.ramp.Ramp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarWindow frame;
    // A list of cars, modify if needed
    private final List<ICar> cars = new ArrayList<>();

    private final CarMechanic<? super Volvo240> workshop;

    public CarController(CarMechanic<? super Volvo240> workshop) {
        this.workshop = workshop;
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        Volvo240Mechanic workshop = new Volvo240Mechanic(new Position(300, 300, Rotation.SOUTH));
        CarController cc = new CarController(workshop);

        cc.cars.add(new Volvo240(0, 0));
        cc.cars.add(new Saab95(0, 100));
        cc.cars.add(new Scania(0, 200));


        // Start a new view and send a reference of self
        cc.frame = new CarWindow("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < cars.size(); i++) {
                ICar car = cars.get(i);
                car.move();
                workshop.updateTransportedPositions();
                flipIfNearWall(i);
                enterIfNearWorkshop(i);
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(i, x, y);
            }
            int x = (int) Math.round(workshop.getX());
            int y = (int) Math.round(workshop.getY());
            frame.drawPanel.moveWorkshop(x, y);
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    private void flipIfNearWall(int index) {
        ICar car = cars.get(index);

        double xMin = 0;
        BufferedImage image = frame.drawPanel.carImages.get(index);
        double xMax = frame.drawPanel.getWidth() - image.getWidth();
        double yMin = 0;
        double yMax = frame.drawPanel.getHeight() - image.getHeight();

        if (car.getX() < xMin
                || car.getX() > xMax
                || car.getY() < yMin
                || car.getY() > yMax) {
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
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (ICar car : cars)
            try {
                car.gas(gasAmount);
            } catch (IllegalStateException _) {
            }
    }

    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (ICar car : cars)
            try {
                car.brake(brakeAmount);
            } catch (IllegalStateException _) {
            }
    }

    void turboOn() {
        for (ICar car : cars) {
            try {
                if (car instanceof HasTurbo carWithTurbo) {
                    carWithTurbo.setTurboOn();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    void turboOff() {
        for (ICar car : cars) {
            try {

                if (car instanceof HasTurbo carWithTurbo) {
                    carWithTurbo.setTurboOff();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    void liftBed() {
        for (ICar car : cars) {
            try {

                if (car instanceof Ramp carWithRamp) {
                    carWithRamp.raiseRamp();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    void lowerBed() {
        for (ICar car : cars) {
            try {
                if (car instanceof Ramp carWithRamp) {
                    carWithRamp.lowerRamp();
                }
            } catch (IllegalStateException _) {
            }
        }
    }

    void startAll() {
        for (ICar car : cars) {
            try {
                car.startEngine();

            } catch (IllegalStateException _) {
            }
        }
    }

    void stopAll() {
        for (ICar car : cars) {
            try {
                car.stopEngine();
            } catch (IllegalStateException _) {
            }
        }
    }

    void turnLeft() {
        for (ICar car : cars) {
            try {


                car.turnLeft();
            } catch (IllegalStateException _) {
            }
        }
    }

    void turnRight() {
        for (ICar car : cars) {
            try {
                car.turnRight();
            } catch (IllegalStateException _) {
            }
        }
    }
}
