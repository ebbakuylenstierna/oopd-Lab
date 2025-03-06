package lab.graphics;

import lab.car.ICar;
import lab.car.Saab95;
import lab.car.Scania;
import lab.car.Volvo240;
import lab.car.mechanic.CarMechanic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This panel represents the animated part of the view with the car images.
public class DrawPanel extends JPanel implements CarView {

    List<Class<? extends ICar>> carTypes = new ArrayList<>();
    List<Point> carPoints = new ArrayList<>();
    Map<Class<? extends ICar>, BufferedImage> carImages = new HashMap<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        // Get images
        try {
            carImages.put(Scania.class, getImage("Scania.jpg"));
            carImages.put(Saab95.class, getImage("Saab95.jpg"));
            carImages.put(Volvo240.class, getImage("Volvo240.jpg"));

            volvoWorkshopImage = getImage("VolvoBrand.jpg");
        } catch (IOException e) {
            // Print an error message in case file is not found with a try/catch block
            System.out.println("Failed to get images");
            e.printStackTrace();
        }
    }

    private BufferedImage getImage(String fileName) throws IOException {
        String PICS_PATH = "src/main/resources/pics";
        File imageFile = new File(String.format("%s/%s", PICS_PATH, fileName));
        return ImageIO.read(imageFile);
    }

    @Override
    public void addCar(ICar car) {
        carTypes.add(car.getClass());
        carPoints.add(new Point());
    }

    @Override
    public void removeCar(int index) {
        carTypes.remove(index);
        carPoints.remove(index);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw cars
        for (int i = 0; i < carTypes.size(); i++) {
            Class<? extends ICar> type = carTypes.get(i);
            BufferedImage image = carImages.get(type);
            Point point = carPoints.get(i);
            g.drawImage(image, point.x, point.y, null); // see javadoc for more info on the parameters
        }

        // Draw workshop
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }

    @Override
    public void updateModel(CarModel model) {
        // Move workshop
        CarMechanic<?> workshop = model.getWorkshop();
        int workshopX = (int) Math.round(workshop.getX());
        int workshopY = (int) Math.round(workshop.getY());
        volvoWorkshopPoint = new Point(workshopX, workshopY);

        // Move cars
        List<ICar> cars = model.getCars();
        for (int i = 0; i < cars.size(); i++) {
            ICar car = cars.get(i);
            int carX = (int) Math.round(car.getX());
            int carY = (int) Math.round(car.getY());

            if (carPoints.size() <= i)
                addCar(car);
            carPoints.get(i).setLocation(carX, carY);
        }

        // Repaint
        repaint();
    }
}
