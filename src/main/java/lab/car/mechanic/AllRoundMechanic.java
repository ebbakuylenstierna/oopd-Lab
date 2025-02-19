package lab.car.mechanic;

import lab.Position;
import lab.car.TransportableCar;

public class AllRoundMechanic extends CarMechanic<TransportableCar> {
    public AllRoundMechanic(Position position) {
        super(position, 2);
    }
}
