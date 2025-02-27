package lab.graphics;

import lab.car.ICar;
import lab.car.TransportableCar;
import lab.car.mechanic.CarMechanic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This panel represents the animated part of the view with the car images.
public class DrawPanel<T extends TransportableCar> extends JPanel implements CarView<T> {

    List<BufferedImage> carImages = new ArrayList<>();
    List<Point> carPoints = new ArrayList<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    void moveit(int index, int x, int y){
        carPoints.get(index).x = x;
        carPoints.get(index).y = y;
    }

    void moveWorkshop(int x, int y) {
        volvoWorkshopPoint.x = x;
        volvoWorkshopPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            String PICS_PATH = "src/main/resources/pics";
            String[] fileNames = {
                "Volvo240.jpg",
                "Saab95.jpg",
                "Scania.jpg",
            };
            for (String fileName : fileNames) {
                File imageFile = new File(String.format("%s/%s", PICS_PATH, fileName));
                carImages.add(ImageIO.read(imageFile));

                carPoints.add(new Point());
            }
            volvoWorkshopImage = ImageIO.read(new FileInputStream(String.format("%s/VolvoBrand.jpg", PICS_PATH)));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImages.size(); i++) {
            BufferedImage image = carImages.get(i);
            Point point = carPoints.get(i);
            g.drawImage(image, point.x, point.y, null); // see javadoc for more info on the parameters
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }

    @Override
    public void updateModel(CarModel<T> model) {
        CarMechanic<?> workshop = model.getWorkshop();
        volvoWorkshopPoint = new Point((int)Math.round(workshop.getX()), (int)Math.round(workshop.getY()));
        List<ICar> cars = model.getCars();
        for (int i = 0; i < carImages.size(); i++) {
            ICar car = cars.get(i);
            carPoints.get(i).setLocation((int)Math.round(car.getX()), (int)Math.round(car.getY()));
        }
    }
}
