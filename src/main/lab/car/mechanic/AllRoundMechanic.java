package lab.car.mechanic;

import lab.Rotation;
import lab.car.ICar;

public class AllRoundMechanic extends CarMechanic<ICar> {
    public AllRoundMechanic(double x, double y, Rotation rotation) {
        super(x, y, rotation, 2);
    }
}
