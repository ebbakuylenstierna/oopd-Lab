import lab.car.TransportableCar;
import lab.car.Volvo240;
import lab.car.VolvoCarTransporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TransportedTests {
    TransportableCar car;
    VolvoCarTransporter transporter;

    @BeforeEach
    public void setup() {
        car = new Volvo240();
        transporter = new VolvoCarTransporter();

        transporter.lowerRamp();
        transporter.transport(car);
    }

    @Test
    public void cannotGasWhileTransported() {
        assertThrowsExactly(IllegalStateException.class, () -> car.gas(0));
    }

    @Test
    public void cannotStartEngineWhileTransported() {
        assertThrowsExactly(IllegalStateException.class, () -> car.startEngine());
    }

    @Test
    public void cannotTurnLeftWhileTransported() {
        assertThrowsExactly(IllegalStateException.class, () -> car.turnLeft());
    }

    @Test
    public void cannotTurnRightWhileTransported() {
        assertThrowsExactly(IllegalStateException.class, () -> car.turnRight());
    }
}
