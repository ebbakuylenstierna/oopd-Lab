package lab.graphics;

public class CarProgram {
    public static void main(String[] args) {
        // Start a new view and send a reference of self
        CarWindow frame = new CarWindow("CarSim 1.0");
        // Start model
        CarModel model = new DemoModel(700, 500);

        CarController controller = new CarController(frame, model);
    }
}
