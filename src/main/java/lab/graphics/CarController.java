package lab.graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final CarFrame frame;

    // Represents Model of the MVC pattern
    private final CarModel model;

    //methods:
    public CarController(CarFrame frame, CarModel model) {
        this.frame = frame;
        this.model = model;

        // Connect model to action buttons
        frame.addModelObservers(model);

        // Start the timer
        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.update();
            frame.updateModel(model);
        }
    }
}
