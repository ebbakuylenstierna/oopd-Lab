package lab.transport;

import lab.Positioned;

import java.util.Optional;

public interface Transportable<T extends Transportable<?>> extends Positioned {
    /**
     * @throws IllegalStateException when already being transported
     */
    void startTransport(Transporter<T> transporter);
    /**
     * @throws IllegalStateException when not being transported
     */
    void endTransport();
    Optional<Transporter<T>> getTransporter();
    boolean isBeingTransported();

    /**
     * Move this transportable to the transporter that is carrying it.
     * @throws IllegalStateException when not being transported
     */
    void moveToTransporter();
}
