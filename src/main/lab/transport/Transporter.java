package lab.transport;

import lab.Positioned;

public interface Transporter<T extends Transportable<?>> extends Positioned {
    void transport(T transportable);
    void updateTransportedPositions();
}
