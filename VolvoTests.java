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
        assertEquals(1, volvo.rotation);
    }

    @Test
    public void turnsLeft() {
        volvo.turnLeft();
        assertEquals(3, volvo.rotation);
    }

    @Test
    public void turnsBackToStart() {
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnRight();
        volvo.turnRight();
        assertEquals(0, volvo.rotation);
    }

    @Test
    public void incrementsSpeed() {
        volvo.gas(1);
        assertTrue(volvo.currentSpeed > 0, "Car has not sped up");
    }

    @Test
    public void decrementsSpeed() {
        volvo.gas(1);
        double speed = volvo.currentSpeed;
        volvo.brake(1);
        assertTrue(volvo.currentSpeed < speed, "Car has not slowed down");
    }

    @Test
    public void startsEngine() {
        volvo.startEngine();
        assertTrue(volvo.currentSpeed > 0, "Car has not started");
    }

    @Test
    public void stopsEngine() {
        volvo.startEngine();
        volvo.stopEngine();
        assertEquals(0, volvo.currentSpeed);
    }

    @Test
    public void moveForward() {
        volvo.gas(1);
        volvo.move();
        assertTrue(volvo.xPosition > 0, "Car has not moved forward");
    }


    @Test
    public void moveRight() {
        volvo.gas(1);
        volvo.turnRight();
        volvo.move();
        assertTrue(volvo.yPosition < 0, "Car has not moved right");
    }

    @Test
    public void moveLeft() {
        volvo.gas(1);
        volvo.turnLeft();
        volvo.move();
        assertTrue(volvo.yPosition > 0, "Car has not moved left");
    }

    @Test
    public void moveBack() {
        volvo.gas(1);
        volvo.turnRight();
        volvo.turnRight();
        volvo.move();
        assertTrue(volvo.xPosition < 0, "Car has not moved back");
    }

    @Test
    public void changeColor() {
        volvo.setColor(Color.RED);
        assertEquals(Color.RED, volvo.getColor());
    }

    @Test
    public void correctEnginePower() {
        assertEquals(100, volvo.getEnginePower());
    }

    @Test
    public void correctNrOfDoors() {
        assertEquals(4, volvo.getNrDoors());
    }
}
