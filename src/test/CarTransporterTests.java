import lab.car.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTransporterTests {
    VolvoCarTransporter transporter;

    @BeforeEach
    public void setup() {
        transporter = new VolvoCarTransporter();
    }

    @Test
    public void cannotLowerWhileDriving() {
        transporter.gas(1);
        assertThrowsExactly(IllegalStateException.class, () -> transporter.lowerRamp());
    }

    @Test
    public void canLowerWhileStandingStill() {
        assertDoesNotThrow(() -> transporter.lowerRamp());
    }

    @Test
    public void startsEmpty() {
        assertTrue(transporter.isEmpty());
    }

    @Test
    public void cannotAddCarsWhileRampRaised() {
        assertFalse(transporter.isRampLowered());
        TransportableCar car = new Volvo240();
        assertThrowsExactly(IllegalStateException.class, () -> transporter.addCar(car));
    }

    @Test
    public void cannotAddCarsWhileFarAway() {
        transporter.lowerRamp();
        assertTrue(transporter.isRampLowered());

        TransportableCar car = new Volvo240();
        // Move car far away
        car.gas(1);
        for (int i = 0; i < 100; i++) {
            car.move();
        }

        assertThrows(IllegalStateException.class, () -> transporter.addCar(car));
    }

    @Test
    public void addsCars() {
        transporter.lowerRamp();
        TransportableCar car = new Volvo240();
        transporter.addCar(car);

        assertTrue(transporter.getCars().contains(car));
        assertTrue(car.isBeingTransported());
        assertTrue(car.getTransporter().isPresent());
        assertEquals(transporter, car.getTransporter().orElseThrow());
    }

    @Test
    public void cannotAddCarsWhenFull() {
        transporter.lowerRamp();
        for (int i = 0; i < transporter.size(); i++) {
            TransportableCar car = new Volvo240();
            transporter.addCar(car);
        }
        TransportableCar car = new Volvo240();
        assertThrows(IllegalStateException.class, () -> transporter.addCar(car));
    }

    @Test
    public void cannotRemoveCarsWhileRampRaised() {
        transporter.lowerRamp();
        TransportableCar car = new Volvo240();
        transporter.addCar(car);

        transporter.raiseRamp();
        assertThrowsExactly(IllegalStateException.class, () -> transporter.removeCar());
    }

    @Test
    public void removesLastCar() {
        transporter.lowerRamp();
        TransportableCar car1 = new Volvo240();
        TransportableCar car2 = new Saab95();
        transporter.addCar(car1);
        transporter.addCar(car2);

        TransportableCar carOut = transporter.removeCar();
        assertEquals(car2, carOut);

        assertFalse(transporter.getCars().contains(car2));
        assertTrue(transporter.getCars().contains(car1));
    }

    @Test
    public void removedCarIsNearTransporter() {
        transporter.lowerRamp();
        TransportableCar car = new Volvo240();
        transporter.addCar(car);
        Car carOut = transporter.removeCar();
        assertTrue(carOut.distanceTo(transporter) <= 5);
    }

    @Test
    public void removedCarIsNotOnTransporter() {
        transporter.lowerRamp();
        TransportableCar car = new Volvo240();
        transporter.addCar(car);
        transporter.removeCar();
        assertNotEquals(transporter.getPosition(), car.getPosition());
    }

    @Test
    public void cannotTransportTransporter() {
        transporter.lowerRamp();
        Car otherTransporter = new VolvoCarTransporter();
        assertThrows(ClassCastException.class, () -> transporter.addCar((TransportableCar) otherTransporter));
    }

    @Test
    public void transportedPositionsAreEqual() {
        TransportableCar car = new Volvo240();

        // Move transporter slightly
        transporter.gas(0.1);
        transporter.move();
        transporter.stopEngine();
        assertNotEquals(car.getPosition(), transporter.getPosition());

        // Add car
        transporter.lowerRamp();
        transporter.addCar(car);
        transporter.raiseRamp();
        assertEquals(transporter.getPosition(), car.getPosition());

        transporter.gas(1);
        transporter.move();
        assertEquals(transporter.getPosition(), car.getPosition());

        transporter.turnRight();
        assertEquals(transporter.getPosition(), car.getPosition());
    }
}
