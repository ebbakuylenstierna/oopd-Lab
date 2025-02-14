import lab.ramp.AngledRamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AngledRampTests {
    AngledRamp ramp;

    @BeforeEach
    public void setup() {
        ramp= new AngledRamp(0,70);
    }

    @Test
    public void startsRaised() {
        assertFalse(ramp.isRampLowered());
    }

    @Test
    public void lowerRamp()   {
        ramp.lowerRamp();
        assertTrue(ramp.isRampLowered(), "Ramp should be lowered.");
    }

    @Test
    public void raiseRamp() {
        ramp.lowerRamp();
        ramp.raiseRamp();
        assertFalse(ramp.isRampLowered(), "Ramp should be raised back up.");
    }

    @Test
    public void raiseToRampLimit() {
        ramp.raiseRamp();
        assertEquals(0, ramp.getRampAngle(), "Ramp should not be able to be raised below minAngle 0.");
    }

    @Test
    public void lowerToRampLimit() {
        for (int i = 0; i < 360; i++) {
            ramp.lowerRamp();
        }
        assertEquals(70, ramp.getRampAngle(), "Ramp should not be able to be lowered to more than maxAngle 70");
    }
}


