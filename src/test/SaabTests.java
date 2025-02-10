import lab.Rotation;
import lab.car.Saab95;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SaabTests {
    Saab95 saab;

    @BeforeEach
    public void setup() {
        saab = new Saab95();
    }

    @Test
    public void turnsRight() {
        saab.turnRight();
        Assertions.assertEquals(Rotation.RIGHT, saab.getRotation());
    }

    @Test
    public void turnsLeft() {
        saab.turnLeft();
        Assertions.assertEquals(Rotation.LEFT, saab.getRotation());
    }

    @Test
    public void turnsBackToStart() {
        saab.turnLeft();
        saab.turnLeft();
        saab.turnRight();
        saab.turnRight();
        Assertions.assertEquals(Rotation.FORWARD, saab.getRotation());
    }

    @Test
    public void incrementsSpeed() {
        saab.gas(1);
        assertTrue(saab.getCurrentSpeed() > 0, "lab.car.Car has not sped up");
    }

    @Test
    public void decrementsSpeed() {
        saab.gas(1);
        double speed = saab.getCurrentSpeed();
        saab.brake(1);
        assertTrue(saab.getCurrentSpeed() < speed, "lab.car.Car has not slowed down");
    }

    @Test
    public void startsEngine() {
        saab.startEngine();
        assertTrue(saab.getCurrentSpeed() > 0, "lab.car.Car has not started");
    }

    @Test
    public void stopsEngine() {
        saab.startEngine();
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    public void moveForward() {
        saab.gas(1);
        saab.move();
        assertTrue(saab.getX() > 0, "lab.car.Car has not moved forward");
    }


    @Test
    public void moveRight() {
        saab.gas(1);
        saab.turnRight();
        saab.move();
        assertTrue(saab.getY() < 0, "lab.car.Car has not moved right");
    }

    @Test
    public void moveLeft() {
        saab.gas(1);
        saab.turnLeft();
        saab.move();
        assertTrue(saab.getY() > 0, "lab.car.Car has not moved left");
    }

    @Test
    public void moveBack() {
        saab.gas(1);
        saab.turnRight();
        saab.turnRight();
        saab.move();
        assertTrue(saab.getX() < 0, "lab.car.Car has not moved back");
    }

    @Test
    public void changeColor() {
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    public void correctModelName() {
        assertEquals("lab.car.Saab95", saab.getModelName());
    }

    @Test
    public void turboOnOff() {
        saab.setTurboOn();
        saab.setTurboOff();
        assertFalse(saab.isTurboOn());
    }

    @Test
    public void speedFasterWithTurbo() {
        saab.gas(1);
        double speedWithoutTurbo = saab.getCurrentSpeed();
        saab.stopEngine();

        saab.setTurboOn();
        saab.gas(1);
        double speedWithTurbo = saab.getCurrentSpeed();

        assertTrue(speedWithTurbo > speedWithoutTurbo, "Turbo is not faster than no turbo");
    }
}
