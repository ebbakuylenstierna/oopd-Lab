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
        if (!isBeingTransported())
            throw new IllegalStateException("Car is not being transported");

        setPosition(transporter.getPosition());
    }

    @Override
    public void gas(double amount) {
        if (isBeingTransported())
            throw new IllegalStateException("Cannot gas while being transported");

        super.gas(amount);
    }

    @Override
    public void startEngine() {
        if (isBeingTransported())
            throw new IllegalStateException("Cannot start engine while being transported");

        super.startEngine();
    }

    @Override
    public void turnLeft() {
        if (isBeingTransported())
            throw new IllegalStateException("Cannot turn while being transported");

        super.turnLeft();
    }

    @Override
    public void turnRight() {
        if (isBeingTransported())
            throw new IllegalStateException("Cannot turn while being transported");
        super.turnRight();
    }
}
