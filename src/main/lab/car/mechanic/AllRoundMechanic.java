package lab.car.mechanic;

import lab.Position;
import lab.car.ICar;

public class AllRoundMechanic extends CarMechanic<ICar> {
    public AllRoundMechanic(Position position) {
        super(position, 2);
    }
}
