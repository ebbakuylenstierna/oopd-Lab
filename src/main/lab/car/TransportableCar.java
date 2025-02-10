package lab.car;

import lab.transport.Transportable;
import lab.transport.Transporter;

import java.awt.*;
import java.util.Optional;

public abstract class TransportableCar extends Car implements Transportable<TransportableCar> {
    private Transporter<TransportableCar> transporter;

    public TransportableCar(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        this.transporter = null;
    }

    @Override
    public void startTransport(Transporter<TransportableCar> transporter) {
        if (isBeingTransported())
            throw new IllegalStateException("Car is already being transported");

        this.transporter = transporter;
    }

    @Override
    public void endTransport() {
        if (!isBeingTransported())
            throw new IllegalStateException("Car is not being transported");

        this.transporter = null;
    }

    @Override
    public Optional<Transporter<TransportableCar>> getTransporter() {
        return Optional.ofNullable(this.transporter);
    }

    @Override
    public boolean isBeingTransported() {
        return getTransporter().isPresent();
    }

    @Override
    public void moveToTransporter() {
        setX(transporter.getX());
        setY(transporter.getY());
        setRotation(transporter.getRotation());
    }
}
