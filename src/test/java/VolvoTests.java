import lab.Rotation;
import lab.car.Volvo240;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class VolvoTests {
    Volvo240 volvo;

    @BeforeEach
    public void setup() {
        volvo = new Volvo240();
    }

    @Test
    public void turnsRight() {
        volvo.turnRight();
        Assertions.assertEquals(Rotation.SOUTH, volvo.getRotation());
    }

    @Test
    public void turnsLeft() {
        volvo.turnLeft();
        Assertions.assertEquals(Rotation.NORTH, volvo.getRotation());
    }

    @Test
    public void turnsBackToStart() {
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnRight();
        volvo.turnRight();
        Assertions.assertEquals(Rotation.EAST, volvo.getRotation());
    }

    @Test
    public void incrementsSpeed() {
        volvo.gas(1);
        assertTrue(volvo.getCurrentSpeed() > 0, "lab.car.Car has not sped up");
    }

    @Test
    public void decrementsSpeed() {
        volvo.gas(1);
        double speed = volvo.getCurrentSpeed();
        volvo.brake(1);
        assertTrue(volvo.getCurrentSpeed() < speed, "lab.car.Car has not slowed down");
    }

    @Test
    public void startsEngine() {
        volvo.startEngine();
        assertTrue(volvo.getCurrentSpeed() > 0, "lab.car.Car has not started");
    }

    @Test
    public void stopsEngine() {
        volvo.startEngine();
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    public void moveForward() {
        volvo.gas(1);
        volvo.move();
        assertTrue(volvo.getX() > 0, "lab.car.Car has not moved forward");
    }


    @Test
    public void moveRight() {
        volvo.gas(1);
        volvo.turnRight();
        volvo.move();
        assertTrue(volvo.getY() > 0, "lab.car.Car has not moved right");
    }

    @Test
    public void moveLeft() {
        volvo.gas(1);
        volvo.turnLeft();
        volvo.move();
        assertTrue(volvo.getY() < 0, "lab.car.Car has not moved left");
    }

    @Test
    public void moveBack() {
        volvo.gas(1);
        volvo.turnRight();
        volvo.turnRight();
        volvo.move();
        assertTrue(volvo.getX() < 0, "lab.car.Car has not moved back");
    }


    @Test
    public void changeColor() {
        volvo.setColor(Color.RED);
        assertEquals(Color.RED, volvo.getColor());
    }

    @Test
    public void correctModelName() {
        assertEquals("Volvo240", volvo.getModelName());
    }

    @Test
    public void correctEnginePower() {
        assertEquals(100, volvo.getEnginePower());
    }

    @Test
    public void correctNrOfDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void gasOnlyAcceptsAmountInInterval() {
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(2));
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(-1));
    }

    @Test
    public void brakeOnlyAcceptsAmountInInterval() {
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(2));
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(-1));
    }

    @Test
    public void tryDecrementSpeedOutsideInterval() {
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    public void tryIncrementSpeedOutsideInterval() {
        for (int i = 0; i < 100; i++) {
            volvo.gas(1);
        }
        assertFalse(volvo.getCurrentSpeed() > volvo.getEnginePower());
    }
}
