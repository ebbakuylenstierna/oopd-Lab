package lab.graphics;

import lab.car.TransportableCar;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/

public class CarWindow<T extends TransportableCar> extends JFrame implements CarView<T> {
    public static final int X = 800;
    public static final int Y = 800;

    // The controller member
    CarController carC;

    DrawPanel drawPanel = new DrawPanel(X, Y - 240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Turbo on");
    JButton turboOffButton = new JButton("Turbo off");
    JButton liftBedButton = new JButton("Lift bed");
    JButton lowerBedButton = new JButton("Lower bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JButton turnLeftButton = new JButton("Turn left");
    JButton turnRightButton = new JButton("Turn right");

    // Constructor
    public CarWindow(String framename, CarController cc) {
        this.carC = cc;
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);


        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner) e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        gasPanel.setPreferredSize(new Dimension(X / 6, 40));

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(startButton, 3);
        controlPanel.add(stopButton, 4);
        controlPanel.add(brakeButton, 5);
        controlPanel.add(turboOffButton, 6);
        controlPanel.add(lowerBedButton, 7);
        controlPanel.add(turnLeftButton, 8);
        controlPanel.add(turnRightButton, 9);
        controlPanel.setPreferredSize(new Dimension(5 * X / 6 - 13, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addModelObservers(CarModel<T> model) {
        gasButton.addActionListener(_ -> model.gas(gasAmount));
        brakeButton.addActionListener(_ -> model.brake(gasAmount));
        turboOnButton.addActionListener(_ -> model.turboOn());
        turboOffButton.addActionListener(_ -> model.turboOff());
        liftBedButton.addActionListener(_ -> model.liftBed());
        lowerBedButton.addActionListener(_ -> model.lowerBed());
        startButton.addActionListener(_ -> model.startAll());
        stopButton.addActionListener(_ -> model.stopAll());
        turnLeftButton.addActionListener(_ -> model.turnLeft());
        turnRightButton.addActionListener(_ -> model.turnRight());
    }

    @Override
    public void updateModel(CarModel<T> model) {
        drawPanel.updateModel(model);
    }
}