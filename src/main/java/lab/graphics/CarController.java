package lab.graphics;

import lab.car.HasTurbo;
import lab.car.ICar;
import lab.car.Volvo240;
import lab.ramp.Ramp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
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

        cc.cars.add(new Volvo240());

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
            for (ICar car : cars) {
                car.move();
                flipIfNearWall(car);
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    private void flipIfNearWall(ICar car) {
        if (car.getX() < 0 || car.getX() > frame.drawPanel.getWidth() - frame.drawPanel.volvoImage.getWidth()) {
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
