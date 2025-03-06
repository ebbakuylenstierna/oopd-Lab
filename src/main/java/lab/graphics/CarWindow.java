package lab.graphics;

import lab.car.ICar;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/

public class CarWindow extends JFrame implements CarFrame {
    public static final int X = 800;
    public static final int Y = 800;

    // The controller member
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

    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");

    // Constructor
    public CarWindow(String framename) {
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

        controlPanel.setLayout(new GridLayout(2, 6));

        controlPanel.add(gasButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(liftBedButton);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(addCarButton);
        controlPanel.add(brakeButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(lowerBedButton);
        controlPanel.add(turnLeftButton);
        controlPanel.add(turnRightButton);
        controlPanel.add(removeCarButton);
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

    @Override
    public void addModelObservers(CarModel model) {
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

        addCarButton.addActionListener(_ -> {
            ICar car = model.addCar();
            if (car == null) return;
            addCar(car);
        });
        removeCarButton.addActionListener(_ -> {
            int index = model.removeCar();
            if (index == -1) return;
            removeCar(index);
        });
    }

    @Override
    public void updateModel(CarModel model) {
        drawPanel.updateModel(model);
    }

    @Override
    public void addCar(ICar car) {
        drawPanel.addCar(car);
    }

    @Override
    public void removeCar(int index) {
        drawPanel.removeCar(index);
    }
}