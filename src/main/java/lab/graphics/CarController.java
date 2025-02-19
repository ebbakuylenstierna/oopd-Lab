package lab.graphics;

import lab.car.*;
import lab.ramp.Ramp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private final List<ICar> cars = new ArrayList<>();

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    // ArrayList<ACar> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(0, 0));
        cc.cars.add(new Saab95(0, 100));
        cc.cars.add(new Scania(0, 200));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

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
                flipIfNearWall(i);
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(i, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    private void flipIfNearWall(int index) {
        ICar car = cars.get(index);
        double xMin = 0;
        double xMax = frame.drawPanel.getWidth() - frame.drawPanel.carImages.get(index).getWidth();
        double yMin = 0;
        double yMax = frame.drawPanel.getHeight() - frame.drawPanel.carImages.get(index).getHeight();

        if (car.getX() < xMin
                || car.getX() > xMax
                || car.getY() < yMin
                || car.getY() > yMax) {
            car.turnRight();
            car.turnRight();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (ICar car : cars)
            car.gas(gasAmount);
    }

    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (ICar car : cars)
            car.brake(brakeAmount);
    }

    void turboOn() {
        for (ICar car : cars) {
            if (car instanceof HasTurbo carWithTurbo) {
                carWithTurbo.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (ICar car : cars) {
            if (car instanceof HasTurbo carWithTurbo) {
                carWithTurbo.setTurboOff();
            }
        }
    }

    void liftBed() {
        for (ICar car : cars) {
            if (car instanceof Ramp carWithRamp) {
                carWithRamp.raiseRamp();
            }
        }
    }

    void lowerBed() {
        for (ICar car : cars) {
            if (car instanceof Ramp carWithRamp) {
                carWithRamp.lowerRamp();
            }
        }
    }

    void startAll() {
        for (ICar car : cars) {
            car.startEngine();
        }
    }

    void stopAll() {
        for (ICar car : cars) {
            car.stopEngine();
        }
    }
}
