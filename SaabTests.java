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
        assertEquals(Rotation.RIGHT, saab.rotation);
    }

    @Test
    public void turnsLeft() {
        saab.turnLeft();
        assertEquals(Rotation.LEFT, saab.rotation);
    }

    @Test
    public void turnsBackToStart() {
        saab.turnLeft();
        saab.turnLeft();
        saab.turnRight();
        saab.turnRight();
        assertEquals(Rotation.FORWARD, saab.rotation);
    }

    @Test
    public void incrementsSpeed() {
        saab.gas(1);
        assertTrue(saab.currentSpeed > 0, "Car has not sped up");
    }

    @Test
    public void decrementsSpeed() {
        saab.gas(1);
        double speed = saab.currentSpeed;
        saab.brake(1);
        assertTrue(saab.currentSpeed < speed, "Car has not slowed down");
    }

    @Test
    public void startsEngine() {
        saab.startEngine();
        assertTrue(saab.currentSpeed > 0, "Car has not started");
    }

    @Test
    public void stopsEngine() {
        saab.startEngine();
        saab.stopEngine();
        assertEquals(0, saab.currentSpeed);
    }

    @Test
    public void moveForward() {
        saab.gas(1);
        saab.move();
        assertTrue(saab.xPosition > 0, "Car has not moved forward");
    }


    @Test
    public void moveRight() {
        saab.gas(1);
        saab.turnRight();
        saab.move();
        assertTrue(saab.yPosition < 0, "Car has not moved right");
    }

    @Test
    public void moveLeft() {
        saab.gas(1);
        saab.turnLeft();
        saab.move();
        assertTrue(saab.yPosition > 0, "Car has not moved left");
    }

    @Test
    public void moveBack() {
        saab.gas(1);
        saab.turnRight();
        saab.turnRight();
        saab.move();
        assertTrue(saab.xPosition < 0, "Car has not moved back");
    }

    @Test
    public void changeColor() {
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    public void turboOnOff() {
        saab.setTurboOn();
        saab.setTurboOff();
        assertFalse(saab.turboOn);
    }

    @Test
    public void speedFasterWithTurbo() {
        saab.gas(1);
        double speedWithoutTurbo = saab.currentSpeed;
        saab.stopEngine();

        saab.setTurboOn();
        saab.gas(1);
        double speedWithTurbo = saab.currentSpeed;

        assertTrue(speedWithTurbo > speedWithoutTurbo, "Turbo is not faster than no turbo");
    }
}
